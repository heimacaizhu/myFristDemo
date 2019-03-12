<%@page import="com.clothes.pojo.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script type="text/javascript" src="js/jquery.min-1.11.3.js"></script>
    <style type="text/css">
        .top-right .layui-nav .layui-nav-item a:hover,.top-right .layui-nav .layui-this a {
            color: #FDEA04;
            background-color: #444E50;
        }

        .top-right .layui-nav {
            background-color: #444E50;
        }

        .top-right .layui-nav .layui-nav-item .layui-this a {
            color: #000;
        }

    </style>
</head>
<body>
	<%@ include file="message.jsp"%>
	<div class="top" style="width: 100%; height: 70px;background-color: #444E50">
        <div style="float: left;height: 70px;width: 445px" class="top-left" >
            <img src="img/logo.jpg" style="height: 70px;width: 100%">
        </div>

        <div class="top-right" style="line-height: 30px;float: right;margin-right: 30px;margin-top: 10px">
            <ul class="layui-nav">
                <li class="layui-nav-item layui-this">
                    <a href="javascript:;">工号:33520</a>
                </li>
                <li class="layui-nav-item"><a href="javascript:;">仓库管理员</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">退出</a>
                </li>
            </ul>
        </div>


	</div>
	<div style="width: 100%;height: 4px;background-color: #009688;"></div>
	
	<div class="center">
	<%
	       HttpSession se = request.getSession(false);
	       if(se==null){
	    	   response.sendRedirect("login.html");
	       }
	       Employee em = (Employee)se.getAttribute("employee");
	       if(em==null||em.getEmAcount()==null||em.getEmAcount().equals("")){
	    	   response.sendRedirect("login.html");
	       }
	       int emtype = em.getEmType();
	%>
		<!--右侧菜单-->
		<div class="left-navigation" style="width: 200px; float: left;">
            <ul class="layui-nav layui-nav-tree" lay-filter="left-navigation">
            <%
                 //办公室人员
                 if(emtype == 3){
            %>
                <li class="layui-nav-item">
                    <!--a标签-->
                    <a href="javascript:;">物品管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">添加物品</a><span style="display: none">goods</span></dd>
                        <dd><a href="javascript:;">添加物品图片</a><span style="display: none">goodsimg</span></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <!--a标签-->
                    <a href="javascript:;">业务安排</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">采购计划</a><span style="display: none">PurchaseRequisition</span></dd>
                        <dd><a href="javascript:;">退货请求</a><span style="display: none">ReturnRequeisiton</span></dd>
                        <dd><a href="javascript:;">生产计划</a><span style="display: none">GenerateRequisition</span></dd>
                        <dd><a href="javascript:;">客户报单</a><span style="display: none">dealapply</span></dd>
                        <dd><a href="javascript:;">客户报单记录</a><span style="display: none">historyapply</span></dd>
                        
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <!--a标签-->
                    <a href="javascript:;">需求统计</a>
                    <dl class="layui-nav-child">
                         <dd><a href="javascript:;">需求统计</a><span style="display: none">DataAnalysis</span></dd>
                    </dl>
                </li>
                <%} %>
                <%
                //管理员
                if(emtype == 1){
                %>
                <li class="layui-nav-item">
                    <!--a标签-->
                    <a href="javascript:;">员工管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">查看员工</a><span style="display: none">employee</span></dd>
                    </dl>
                </li>
                <%} %>
                <%
                     //客服人员
                     if(emtype ==2){
                %>
                 <li class="layui-nav-item">
                    <!--a标签-->
                    <a href="javascript:;">客户相关</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">客户区域</a><span style="display: none">region</span></dd>
                        <dd><a href="javascript:;">添加客户</a><span style="display: none">kh-add</span></dd>
                        <dd><a href="javascript:;">我的客户</a><span style="display: none">kh-show</span></dd>
                        <dd><a href="javascript:;">客户统计</a><span style="display: none">kh-count</span></dd>
                        <dd><a href="javascript:;">客户订单</a><span style="display: none">order-kf</span></dd>
                        <dd><a href="javascript:;">客户报单</a><span style="display: none">kh-bd</span></dd>
                    </dl>
                </li>
                <%} %>
                <%
                   if(emtype ==5){
                %>
                <li class="layui-nav-item">
                    <!--a标签-->
                    <a href="javascript:;">出入库</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">物品出入库</a><span style="display: none">GoodR</span></dd>
                        <dd><a href="javascript:;">库存统计</a><span style="display: none">GoodCount</span></dd>
                    </dl>
                </li>
                <%} %>
            </ul>
		</div> 
		<!--
	    	中间内容部分
	    -->
		<div class="center-right" style=" min-height: 610px; float: left;">
			
			 <!--选项卡-->
			<div class="layui-tab layui-tab-brief" lay-filter="demo" lay-allowClose="true">
			  <ul class="layui-tab-title">
                  <li class="layui-this indexContent" lay-allowClose="false">网站首页</li>
			  </ul>
			  <div class="layui-tab-content">
                  <div class="layui-tab-item layui-show">首页内容</div>
			  </div>
			</div>  
			
		</div>
		
	</div>

<script src="layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
//center-right的宽度
var width = document.body.scrollWidth - 200;
var height = document.body.scrollHeight;
$(".center-right").css("width",width);
$(".left-navigation").css({"min-height":height,"background-color":"#393D49"});

layui.use('element', function(){
	var element = layui.element();
    iframeStyle();

	//	监听右侧菜单  
	element.on('nav(left-navigation)', function(elem){
        //elem是DOM对象
        var a = $(elem).find("span").text();
        var title = $(elem).find("a").text();
        //菜单点击
        leftMenu(a,title,element);
        leftMenu(a,title,element);
        //检测tab
  		checkTab();
        iframeStyle();
	});
});

//检测是否已经有tab
function checkTab(){

}
//右侧菜单操作
function leftMenu(a,title,element) {
    var index = tabIndexByName(title);
    var dizhi = "<iframe src='"+a+".jsp'></iframe>";
    if(index != 0){
        element.tabChange('demo', index);
    }else {
        //新增一个Tab项
        element.tabAdd('demo', {
            title: title
            ,content: dizhi
        });
    }
}
//根据名字确定tab下标  有返回true
function tabIndexByName(name){
    var index = 0;
    if ($(".layui-tab-title li") == undefined){
        return index;
    }
    $(".layui-tab-title li").each(function (i,val) {
        if (name+"ဆ" == $(val).text()){
            index = i;
        }
    });
    return index;
}

//设置iframe的样式
function  iframeStyle() {
    $("iframe").css({"border":"0px","width":"100%","min-height":"620px"});
    $(".indexContent i").empty();
}    
</script>

</body>
</html>