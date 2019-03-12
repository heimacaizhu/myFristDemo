<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>商品入库管记录</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script type="text/javascript" src="js/jquery.min-1.11.3.js"></script>
    <script type="text/javascript" src="layui/layui.js"></script>

    <script type="text/javascript">
    $(function(){
    	showgoods();
    })
    
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

    <!--入库弹框-->
    function ruku(){
        //页面层
        layer.open({
            type:2,
            title: ['入库', 'font-size:18px;'],
            skin: 'layui-layer-rim', //加上边框
            area: ['1000px', '500px'], //宽高
            content: ['./Ruku.html','no']
        });
    }
    <!--出库弹框-->
    function ruku(){
        //页面层
        layer.open({
            type:2,
            title: ['出库', 'font-size:18px;'],
            skin: 'layui-layer-rim', //加上边框
            area: ['1000px', '500px'], //宽高
            content: ['./ChuKu.html','no']
        });
    }
    <!--  显示所有物品数据 -->
    function showgoods(){
    	$.post("getAllKctj.do",null,function(data){
    		//alert(JSON.stringify(data));
    		var str = "";
     		$.each(data,function(index,item){
	    		
	    		str +="<tr>";
	    		str +="<td>"+item.re_id+"</td>";
	    		str +="<td>"+item.s_id+"</td>";
	    		str +="<td>"+item.re_date+"</td>";
	    		str +="<td>"+item.g_name+"</td>";
	    		str +="<td>"+item.brand_name+"</td>";
	    		str +="<td>"+item.size+"</td>";
	    		str +="<td>"+item.color+"</td>";
	    		str +="<td>"+item.num+"</td>";
	    		str +="<td>"+item.em_name+"</td>";
	    		str +="<td>"+item.remark+"</td>";
	    		str +="<td><a href=#>删除</a></td>";
	    		str +="</tr>";
     		});  
     		$("#bbb").append(str);
    	});
    }

    <!-- 搜索 -->
    function query(){
    	$.post("getAllKctj.do",null,function(data){
    	var re_id = $('#input').val();
    	
    	alert(data);
     	for(var i=0;i<data.length;i++){
    		if(re_id == data[i].re_id){
    			var d = data[i];
    			var str = "";

    	    		str +="<tr>";
    	    		str +="<td>"+d.re_id+"</td>";
    	    		str +="<td>"+d.s_id+"</td>";
    	    		str +="<td>"+d.re_date+"</td>";
    	    		str +="<td>"+d.g_name+"</td>";
    	    		str +="<td>"+d.brand_name+"</td>";
    	    		str +="<td>"+d.size+"</td>";
    	    		str +="<td>"+d.color+"</td>";
    	    		str +="<td>"+d.num+"</td>";
    	    		str +="<td>"+d.em_name+"</td>";
    	    		str +="<td>"+d.remark+"</td>";
    	    		str +="<td><a href=#>删除</a></td>";
    	    		str +="</tr>";
         		$("#bbb").children().remove();
         		$("#bbb").append(str);
    		}
    	}
     	
    });
    	
    }
	</script>
<body>
<!-- 总框架 -->
    <!--标题-->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>物品出入库管理</legend>
    </fieldset>
    <!-- 右边框架 -->
    <div style="display: inline-block; width: 100%; height: 550px; padding: 10px; border: 1px solid #ddd; overflow: auto; position: relative">
        <!--搜索框-->
        <div class="layui-form-item" style="margin-left:300px;" >
            <div>
                <input id="input" type="text" placeholder="请商品名称或商品编号..." class="layui-input in_search_emp" style="width:300px;display: inline">
                <button class="layui-btn layui-btn-radius btn_search_emp" style="display: inline" onclick="query()">搜索</button>
                <button class="layui-btn layui-btn-radius btn_add_emp" style="display: inline" onclick="ruku()">入库</button>
                                <button class="layui-btn layui-btn-radius btn_add_emp" style="display: inline" onclick="ChuKu()">出库</button>
            </div>
        </div>
	<!--物品信息展示选项卡开始-->
  <div class="layui-tab-content" style="height: 100%;">
    <div class="layui-tab-item layui-show">
      <!-- 右边表格 -->
        <table class="layui-table" lay-skin="line">
            <thead>
            <tr>
                <th>商品出入库编号</th>
                <th>仓库编号</th>
                <th>出库时间</th>
                <th>商品名称</th>
                <th>品牌名称</th>
                <th>商品型号</th>
                <th>商品颜色</th>
                <th>出库数量</th>
                <th>经办人</th>
                <th>出库备注</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="bbb">
            <!-- 原材料信息 -->
            </tbody>
        </table>

        <!-- 分页 -->
        <div id="demo7" align="center" style="position: absolute;bottom:0;left: 15%;width:800px"></div>
    </div>

	</div>
	</div>
</body>
</html>