<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>处理完的报单</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <link rel="stylesheet" href="css/historyapply.css" media="all">
    <style type="text/css">

    </style>
</head>
<body>
<!--工具栏开始-->
<div>
    <fieldset class="layui-elem-field layui-field-title">
        <legend>操作栏</legend>
    </fieldset>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="text" placeholder="请输入申请人或处理人的名字..." class="layui-input in_search_apply">
            <button class="layui-btn layui-btn-radius btn_search_apply">搜索</button>
        </div>
    </div>
</div>
<!--工具栏开始-->
<!--中间表格开始-->
<div class="apply">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>报单信息</legend>
    </fieldset>
    <table class="layui-table" lay-skin="line">
        <colgroup>
            <col width="30">
            <col width="80">
        </colgroup>
        <thead>
        <tr>
            <th></th>
            <th>编号</th>
            <th>客户</th>
            <th>申请人</th>
            <th>处理人</th>
            <th>申请类别</th>
            <th>申请时间</th>
            <th>处理时间</th>
            <th>处理级别</th>
            <th>状态</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        
        </tbody>
    </table>
</div>
<!--中间表格结束-->
<!-- 分页开始-->
<div class="apply_page">
    <div id="apply_page"></div>
</div>
<!-- 分页结束-->



<script src="layui/layui.js" charset="utf-8"></script>
<script src="js/jquery.min-1.11.3.js" charset="utf-8"></script>
<script src="js/historyapply.js" charset="utf-8"></script>
<script type="text/javascript">


</script>
</body>
</html>