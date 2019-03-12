<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8">
    <title>库存统计</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script type="text/javascript" src="js/jquery.min-1.11.3.js"></script>
    <script type="text/javascript" src="layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript">
        layui.use('form', function(){
            var form = layui.form(); //只有执行了这一步，部分表单元素才会修饰成功
        });

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
</head>
<body>
    <!--主框架-->
    <div style="padding: 10px">
        <div class="layui-form-item" >
                <label class="layui-form-label">请选择物品类型:</label>
                <div class="layui-input-block">
                    <select name="goods" lay-filter="aihao">
                        <option value="0" selected="">商品</option>
                        <option value="1">原材料</option>
                    </select>
                </div>
                    <input type="text" placeholder="请物品名称或物品编号..." class="layui-input in_search_emp" style="width:300px;display: inline">
                    <button class="layui-btn layui-btn-radius btn_search_emp" style="display: inline" >搜索</button>
                    <button class="layui-btn layui-btn-radius btn_add_emp" style="display: inline" onclick="ruku()">入库</button>

        </div>
        <!--显示表格-->
        <table class="layui-table" lay-skin="line">
            <thead>
            <tr>
                <th>物品库存编号</th>
                <th>物品名称</th>
                <th>物品品牌</th>
                <th>物品颜色</th>
                <th>物品尺寸</th>
                <th>仓库号</th>
                <th>库存数量</th>
                <th>最低库存量</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>10001</td>
                <td>毛衣</td>
                <td>大头儿子小头爸爸</td>
                <td>红色</td>
                <td>18</td>
                <td>1</td>
                <td>900</td>
                <td>100</td>
                <td>新品</td>
                <td><a href="#">查看详情</a></td>
            </tr>
            <tr>
                <td>10002</td>
                <td>卫衣</td>
                <td>大头儿子小头爸爸</td>
                <td>红色</td>
                <td>89</td>
                <td>1</td>
                <td>1000</td>
                <td>100</td>
                <td>新品</td>
                <td><a href="#">查看详情</a></td>
            </tr>
            <tr>
                <td>10003</td>
                <td>羽绒服</td>
                <td>大头儿子小头爸爸</td>
                <td>黄色</td>
                <td>107</td>
                <td>1</td>
                <td>980</td>
                <td>100</td>
                <td>新品</td>
                <td><a href="#">查看详情</a></td>
            </tr>
            <tr>
                <td>10004</td>
                <td>鹅绒羽绒服</td>
                <td>大头儿子小头爸爸</td>
                <td>蓝色</td>
                <td>188</td>
                <td>1</td>
                <td>690</td>
                <td>100</td>
                <td>新品</td>
                <td><a href="#">查看详情</a></td>
            </tr>
            <tr>
                <td>10005</td>
                <td>棉衣</td>
                <td>大头儿子小头爸爸</td>
                <td>蓝色</td>
                <td>256</td>
                <td>1</td>
                <td>800</td>
                <td>100</td>
                <td>新品</td>
                <td><a href="#">查看详情</a></td>
            </tr>
            </tbody>
        </table>

        <!-- 分页 -->
        <div id="demo7" align="center" style="position: absolute;bottom:80px;left: 15%;width:800px"></div>
    </div>
</body>
</html>