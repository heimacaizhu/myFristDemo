package com.clothes.controller.tq;

import com.clothes.dao.tq.messageTMapper;
import com.clothes.pojo.EmployeeT;
import com.clothes.pojo.MessageT;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)
public class WebSocket {
    private ServletContext servletContext = null ;

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，
    // 可以使用Map来存放，其中Key可以为用户标识
    private static Map<String, WebSocket> webSocketMap = new HashMap<String, WebSocket>();
    //存储用户数据
    private static ArrayList<EmployeeT> employeeList = new ArrayList<EmployeeT>();
    //httpsession
    private HttpSession httpSession = null;

    //发给谁
    String toEm ="";
    //谁发的
    String fromEm = "";

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        //HttpSession
        httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        servletContext = httpSession.getServletContext();
        //将员工存到list中
        EmployeeT employee = (EmployeeT) httpSession.getAttribute("employeeT");
        employeeList.add(employee);
        //名字
        String name = employee.getEmName();
        //所属部门
        String type = employee.getEmType();
        System.out.println(name + "==部门是:" + type + "===成功连接");

        webSocketMap.put(name + "&" + type, this);     //加入map中
        //谁发的
        fromEm = name + "&" + type ;
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        //将在线员工数据发到前台   返回json数据 [{"name":"","type":""},{"":""}]
        sendJson();
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //删除websocket的存储
        Set<String> set = webSocketMap.keySet();
        for (String key : set) {
            if (webSocketMap.get(key) == this) {
                webSocketMap.remove(key);
                //删除list中的用户数据
                if (employeeList == null) break;
                String[] split = key.split("&");
                if (split.length >= 2) {
                    String name = split[0];
                    for (EmployeeT employee : employeeList) {
                        if (name.equals(employee.getEmName())) {
                            //移除元素
                            employeeList.remove(employee);
                            System.out.println("用户数据删除");
                            break;
                        }
                    }
                }
                System.out.println("删除数据");
                break;
            }
        }
        //更新列表
        sendJson();
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("来自客户端的消息:" + message);
        //处理信息发送给指定的人
        String[] strings = message.split("-");
        if (strings.length == 2) {
            message = strings[0];
            String toUser = strings[1];
            //发消息
            WebSocket web = (WebSocket) webSocketMap.get(toUser);
            //自己的信息
            EmployeeT employee = (EmployeeT) httpSession.getAttribute("employeeT");
            toEm = toUser;
            if (web != null) web.sendMessage(fromEm+"&"+message);
            //保存到数据库中
            String msgId =UUID.randomUUID().toString().replaceAll("-","");
            String content = message;
            String msgDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            String msgState = "1" ;
            MessageT message1 = new MessageT(msgId,fromEm,toUser,content,msgDate,msgState);
            ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
            messageTMapper mapper = (messageTMapper) ctx.getBean("messageTMapper");
            mapper.addMessage(message1);

        } else {
            System.out.println("消息格式有错");
        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
    //发送给所有人
    public void sendMessageAll(String message) throws IOException {
        Set<String> set = webSocketMap.keySet();
        for(String s : set){
            webSocketMap.get(s).getSession().getBasicRemote().sendText(message);
        }
    }


    public static synchronized int getOnlineCount() {
        return webSocketMap.keySet().size();
    }
    //发送json数据
    public void sendJson() {
        StringBuilder message = new StringBuilder("[");
        if(employeeList.size() == 0 ) return;
        for (int i = 0; i < employeeList.size(); i++) {
            if (i == 0) {
                message.append("{\"name\"");
                message.append(":\"");
                message.append(employeeList.get(i).getEmName());
                message.append("\",");

                message.append("\"type\":\"");
                message.append(employeeList.get(i).getEmType());
                message.append("\"}");
            } else {
                message.append(",{\"name\"");
                message.append(":\"");
                message.append(employeeList.get(i).getEmName());
                message.append("\",");

                message.append("\"type\":\"");
                message.append(employeeList.get(i).getEmType());
                message.append("\"}");
            }
        }
        message.append("]");
        //发送给所有人
        try {
            sendMessageAll(message.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}