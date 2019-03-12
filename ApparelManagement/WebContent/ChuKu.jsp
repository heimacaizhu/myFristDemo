<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <link rel="stylesheet" href="css/yyq_css.css">
    <script type="text/javascript" src="js/jquery.min-1.11.3.js"></script>
    <script type="text/javascript" src="layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript">
        <!-- 分页 -->
        layui.use(['laypage', 'layer'], function() {
            var laypage = layui.laypage
                    , layer = layui.layer;
            laypage({
                cont: 'demo7'
                ,pages: 60
                ,skip: true
            });
        });
    </script>
    <style type="text/css">
    </style>
</head>
<body>
<div style="padding: 10px">
    <table class="layui-table" lay-skin="line">
        <!--            <colgroup>
                        <col width="100">
                        <col width="140">
                        <col width="90">
                    </colgroup>-->
        <thead>
        <tr>
            <th><input type="checkbox">全选</th>
            <th>仓库编号</th>
            <th>商品名称</th>
            <th>品牌名称</th>
            <th>商品型号</th>
            <th>商品颜色</th>
            <th>经办人</th>
            <th>出库数量</th>
            <th>库存数量</th>
            <th>出库备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="checkbox"></td>
            <td>1</td>
            <td>羽绒服</td>
            <td>大头儿子小头爸爸</td>
            <td>M</td>
            <td>黑色</td>
            <td>张三</td>
            <td>1</td>
            <td>1</td>
            <td>
                <select name="quiz1">
                    <option value="正常入库" selected="">正常出库</option>
                    <option value="需注意">需注意</option>
                </select>
            </td>
            <td><a href="#">出库</a></td>
        </tr>
        <tr>
            <td><input type="checkbox"></td>
            <td>1</td>
            <td>羽绒服</td>
            <td>大头儿子小头爸爸</td>
            <td>M</td>
            <td>黑色</td>
            <td>张三</td>
            <td>1</td>
            <td>1</td>
            <td>
                <select name="quiz1">
                    <option value="正常入库" selected="">正常出库</option>
                    <option value="需注意">需注意</option>
                </select>
            </td>
            <td><a href="#">出库</a></td>
        </tr>
        <tr>
            <td><input type="checkbox"></td>
            <td>1</td>
            <td>羽绒服</td>
            <td>大头儿子小头爸爸</td>
            <td>M</td>
            <td>黑色</td>
            <td>张三</td>
            <td>1</td>
            <td>1</td>
            <td>
                <select name="quiz1">
                    <option value="正常入库" selected="">正常出库</option>
                    <option value="需注意">需注意</option>
                </select>
            </td>
            <td><a href="#">出库</a></td>
        </tr>
        <tr>
            <td><input type="checkbox"></td>
            <td>1</td>
            <td>羽绒服</td>
            <td>大头儿子小头爸爸</td>
            <td>M</td>
            <td>黑色</td>
            <td>张三</td>
            <td>1</td>
            <td>1</td>
            <td>
                <select name="quiz1">
                    <option value="正常入库" selected="">正常出库</option>
                    <option value="需注意">需注意</option>
                </select>
            </td>
            <td><a href="#">出库</a></td>
        </tr>
        <tr>
            <td><input type="checkbox"></td>
            <td>1</td>
            <td>羽绒服</td>
            <td>大头儿子小头爸爸</td>
            <td>M</td>
            <td>黑色</td>
            <td>张三</td>
            <td>1</td>
            <td>1</td>
            <td>
                <select name="quiz1">
                    <option value="正常出库" selected="">正常出库</option>
                    <option value="需注意">需注意</option>
                </select>
            </td>
            <td><a href="#">出库</a></td>
        </tr>
        </tbody>
    </table>

    <!-- 分页 -->
    <div id="demo7" align="center" style="position: absolute;bottom:80px;left: 15%;width:800px"></div>

    <!--按钮-->
    <div><button class="layui-btn layui-btn-radius btn_search_emp" style="display: inline;position: absolute;bottom:30px;left: 83%; " >一键出库</button></div>
</div>
</body>
</html>
    