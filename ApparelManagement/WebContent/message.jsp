<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script type="text/javascript" src="js/jquery.min-1.11.3.js"></script>
    <script src="layui/layui.js" charset="utf-8"></script>
    <script src="js/message.js"></script>
</head>
<body>
<div class="messqge" style="cursor: pointer;width: 155px;height: 52px;background-color: rgba(160,11,152,0.02);position: fixed;border-radius: 3px" ;>
    <img src="img/commiteLogo.jpg" style="width: 40px;height: 40px;border-radius: 20px;margin-left: 20px">
    <span style="line-height: 52px">我的消息</span>
    <div class="redpoint" style="display: none;float: right;margin-top: 20px;margin-right: 20px;width: 10px;height: 10px; border-radius: 5px; background-color: red "></div>
</div>

<div class="content" style="width: 200px; height: 400px;position: fixed;background-color: #393D49;display: none;">
    <div class="x" style="float: right;color: white;font-size: 20px;margin-right: 10px;margin-top: 5px;cursor: pointer;">X</div>
    <h1 style="color: white ; font-size: 20px">用户名:<span style="font-size: 8px">${sessionScope.employeeT.emType}_${sessionScope.employeeT.emName}</span></h1>
    <ul class="layui-nav layui-nav-tree" lay-filter="demo" style="margin-top: 10px">
        <li class="layui-nav-item">
            <a href="javascript:;">客服人员</a>
            <input class="department" type="hidden" value="客服人员">
            <dl class="layui-nav-child  kf">
                <%--<dd><a href="javascript:;" class="site-demo-layer" data-type="setTop">a</a></dd>--%>
            </dl>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:;">办公室人员</a>
            <input class="department" type="hidden" value="办公室人员">
            <dl class="layui-nav-child bgs">
            </dl>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:;">管理员</a>
            <input class="department" type="hidden" value="管理员">
            <dl class="layui-nav-child bgs">
            </dl>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:;">车间主任</a>
            <input class="department" type="hidden" value="车间主任">
            <dl class="layui-nav-child  cj">
            </dl>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:;">仓库管理员</a>
            <input class="department" type="hidden" value="仓库管理员">
            <dl class="layui-nav-child  ck">
            </dl>
        </li>
    </ul>
    <button class="layui-btn site-demo-layer" data-type="notice">我的消息</button>
    <button class="layui-btn site-demo-layer" data-type="notice">历史消息</button>
</div>
</body>
</html>
