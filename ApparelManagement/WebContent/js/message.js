var message = "";
var toUser = "";
var department="";

//消息显示
var m_name = "";
var m_dp = "";
var c_begin = "<div style='padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;'>";
// {/*"+m_name+"_"+m_dp+":"+message+"<hr>*/}
var c_content = "";
var c_end = "</div>";
$(function () {
    var content = "<textarea style='width:375px ;height: 110px' id='value'>"
        +"</textarea>";
    //messqge宽高
    var width_m = document.body.scrollWidth - 180;
    var height_m = document.body.scrollHeight - 200;
    //content宽高
    var width = document.body.scrollWidth - 200;
    var height = document.body.scrollHeight - 400;
    $(".messqge").css({"left":width_m,"top":height_m});
    $(".content").css({"left":width,"top":height});
    //
    layui.use('element', function() {
        var element = layui.element();
    })
    //点击图标弹出列表
    $(".messqge").click(function () {
        $(this).css({"display":"none"});
        $(".content").css({"display":"block"});
    });
    $(".x").click(function () {
        $(".content").css({"display":"none"});
        $(".messqge").css({"display":"block"});
    });
    //使用弹出窗
    var layerWidth = 500;
    var layerHeight = 200;
    layui.use('layer', function(){ //独立版的layer无需执行这一句
        layer = layui.layer; //独立版的layer无需执行这一句

        //绑定弹出消息框时间
        $(".content").delegate(".site-demo-layer","click",function () {
            var type = $(this).data('type');
            var text = $(this).text();
            active[type] ? active[type].call(this) : '';
        })
        //触发事件
        var active = {
            setTop: function(){
                var that = this;
                //多窗口模式，层叠置顶
                layer.open({
                    type: 1 //可以传普通的html
                    ,title: '消息窗口'
                    ,area: ['390px', '260px']
                    ,shade: 0
                    ,maxmin: true
                    ,offset: [ //为了演示，随机坐标
                        layerHeight
                        ,layerWidth
                    ]
                    ,content: content
                    ,btn: ["发送"] //只是为了演示
                    //如果只有一个按钮就是这么函数
                    ,yes: function(){
                        //进行异步传输
                        send();
                    }
                    ,btn2: function(){
                        layer.closeAll();
                    }

                    ,zIndex: layer.zIndex //重点1
                    ,success: function(layero){
                        layer.setTop(layero); //重点2
                    }
                });
            }
            ,notice: function() {
                //示范一个公告层
                layer.open({
                    type: 1
                    ,
                    title: false //不显示标题栏
                    ,
                    closeBtn: false
                    ,
                    area: '600px;'
                    ,
                    shade: 0.8
                    ,
                    id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,
                    btn: ['知道了!']
                    // btn: ['知道了!', '残忍拒绝']
                    ,
                    moveType: 1 //拖拽模式，0或者1
                    ,
                    content: c_begin+c_content+c_end
                    ,
                    success: function (layero) {
                        var btn = layero.find('.layui-layer-btn');
                        btn.css('text-align', 'center');
                        //隐藏小红点
                        hiddenRedPoint();
                    }
                });
            }

        };
    });
    // 加入触发事件
    $("dl").delegate("dd","click",function () {
        //发送给谁
        toUser = $(this).children("a").text();
        //所属部门
        department = $(this).parents("li").find(".department").val();
    })
});


/*加入websocket*/
var websocket = null;
//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://localhost:8080/ApparelManagement/websocket");
}
else {
    alert('当前浏览器 Not support websocket')
}

//连接发生错误的回调方法
websocket.onerror = function () {
    // setMessageInnerHTML("WebSocket连接发生错误");
};

//连接成功建立的回调方法
websocket.onopen = function () {
    // setMessageInnerHTML("WebSocket连接成功");
}

//接收到消息的回调方法
websocket.onmessage = function (event) {
    if (event.data.charAt(0) == "["){
        //生成在线列表
        onlineEmployee(event.data);
    }else {
        message += event.data;
        var mesArray = message.split("&");
        //名字
        m_name = mesArray[0];
        //部门
        m_dp = mesArray[1];
        //消息
        message = mesArray[2];
        //拼接消息
        c_content +="<span style='color: #118000'>"+m_name+"_"+m_dp+":&nbsp;&nbsp;&nbsp;"+getTime()+"</span><br>"+message+"<hr>"
        showRedPoint();
    }
}

//连接关闭的回调方法
websocket.onclose = function () {
//    setMessageInnerHTML("WebSocket连接关闭");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    closeWebSocket();
}

//关闭WebSocket连接
function closeWebSocket() {
    websocket.close();
}

//发送消息
function send() {
    var message = $("#value").val() + "-" +toUser+"&"+department;
    websocket.send(message);
}
// 显示小红点
function showRedPoint() {
    $(".redpoint").css({"display":"block"});
}
//隐藏小红点
function hiddenRedPoint() {
    $(".redpoint").css({"display":"none"});
}

/*[{"name"":"zansan","type":"办公室"}]*/
//生成在线列表
function onlineEmployee(message) {
    //先清空列表
    $(".kf,.bgs,.cj,.ck").empty();

    var json = JSON.parse(message);
    var head = "<dd><a href='javascript:;' class='site-demo-layer' data-type='setTop'>"
    var end = "</a></dd>";
    $(json).each(function (i , val) {
        var type = val.type;
        if(type == "管理员"){
            $(".kf").append(head+val.name+end);
            $(".kf").append("<input class='department' type='hidden' value='"+type+"'>");
        }
        if(type == "客服人员"){
            $(".kf").append(head+val.name+end);
            $(".kf").append("<input class='department' type='hidden' value='"+type+"'>");
        }
        if(type == "办公室人员"){
            $(".bgs").append(head+val.name+end);
            $(".bgs").append("<input class='department' type='hidden' value='"+type+"'>");
        }
        if(type == "车间主任"){
            $(".cj").append(head+val.name+end);
            $(".cj").append("<input class='department' type='hidden' value='"+type+"'>");
        }
        if(type == "仓库管理员"){
            $(".ck").append(head+val.name+end);
            $(".ck").append("<input class='department' type='hidden' value='"+type+"'>");
        }
    });
}
//获取时间
function getTime() {
    var time = new Date();
    var hours = time.getHours();
    var min = time.getMinutes();
    return hours+"."+min
}