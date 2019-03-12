<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>员工管理</title>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <link rel="stylesheet" href="css/employee.css" media="all">
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
            <input type="text" placeholder="请输入员工姓名或工号..." class="layui-input in_search_emp">
            <button class="layui-btn layui-btn-radius btn_search_emp">搜索</button>
            <button class="layui-btn layui-btn-radius btn_add_emp">增加</button>
        </div>
    </div>
</div>
<!--工具栏开始-->
<!--中间表格开始-->
<div class="emp">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>员工信息</legend>
    </fieldset>
    <table class="layui-table" lay-skin="line">
        <thead>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>联系方式</th>
            <th>员工工号</th>
            <th>员工类型</th>
            <th>员工状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <!-- 员工信息显示 -->
        </tbody>
    </table>
</div>
<!--中间表格结束-->
<!-- 分页开始-->
<div class="emp_page">
    <div id="emp_page"></div>
</div>
<!-- 分页结束-->
<script src="js/jquery.min-1.11.3.js" charset="utf-8"></script>
<script src="layui/layui.js" charset="utf-8"></script>
<script src="js/employee.js" charset="utf-8"></script>
<script type="text/javascript">


</script>

</body>
</html>