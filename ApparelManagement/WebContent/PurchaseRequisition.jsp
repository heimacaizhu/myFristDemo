<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>采购申请</title>
<link rel="stylesheet" href="layui/css/layui.css">
<link rel="stylesheet" href="css/ysh/aCSS.css">
<style type="text/css">
</style>
</head>
<body>
	<fieldset class="layui-elem-field layui-field-title site-title">
		<legend>操作</legend>
	</fieldset>
	<from class="layui-form" action="apply/showAllApply.do">
	<div class="layui-form-item">
		<lable class="layui-form-label">申请名称:</lable>
		<div class="layui-input-block">
			<input type="text" class="layui-form-label" placeholder="请输入搜索内容"
				style="width: 300px"> &nbsp;&nbsp;&nbsp;&nbsp; <input
				type="submit" class="layui-btn layui-btn-radius"
				style="width: 100px">
		</div>
	</div>
	</from>
	<fieldset class="layui-elem-field layui-field-title site-title">
		<legend>采购申请</legend>
	</fieldset>
	<div class="layui-tab layui-tab-card">
		<ul class="layui-tab-title">
            <li class="layui-this">未处理申请</li>
            <li onclick="toYesApply()">已通过申请</li>
			<li onclick="toNoApply()">未通过申请</li>
        </ul>
		<div class="layui-tab-content" style="height: 300px;">
			<div class="layui-tab-item layui-show">
				<table id="ysh-tab-showAllWaitApply" class="layui-table"
					lay-skin="line">
					<!-- <colgroup>
                        <col width="50">
                        <col width="100">
                        <col width="550">
                        <col width="100">
                        <col>
                    </colgroup> -->
					<thead>
						<tr>
							<th>id</th>
							<th>申请人</th>
							<th>备注</th>
							<th>申请时间</th>
							<th>级别</th>
							<th>文档操作</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
				<div id="pageWAIT"></div>
			</div>
			<!--已通过申请-->
			<div class="layui-tab-item">
				<table id="ysh-tab-showAllYesApply" class="layui-table" lay-skin="line">
					
					<thead>
						<tr>
							<th>id</th>
							<th>申请人</th>
							<th>处理人</th>
							<th>备注</th>
							<th>处理结果</th>
							<th>申请时间</th>
							<th>处理时间</th>
							<th>级别</th>
							<th>文档连接</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>张三</td>
							<td>管理员1</td>
							<td>我们需要</td>
							<td>同意，</td>
							<td><a href="#">在线预览</a> | <a href="#">下载</a></td>
							<td><a href="javascript:applyShow()">查看详细</a></td>
						</tr>
					</tbody>
				</table>
				<div id="pageNO"></div>
			</div>
			<!-- 未通过申请 -->
			<div class="layui-tab-item">
				<table id="ysh-tab-showAllNoApply" class="layui-table" lay-skin="line">
					<thead>
						<tr>
							<th>id</th>
							<th>申请人</th>
							<th>处理人</th>
							<th>备注</th>
							<th>处理结果</th>
							<th>申请时间</th>
							<th>处理时间</th>
							<th>级别</th>
							<th>文档连接</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>张三</td>
							<td>管理员1</td>
							<td>需要</td>
							<td>否定，已审核完毕</td>
							<td><a href="#">在线预览</a> | <a href="#">下载</a></td>
							<td><a href="#">查看详细</a></td>
						</tr>
					</tbody>
				</table>
				<div id="pageOK"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/jquery.min-1.11.3.js"></script>
<script src="layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function() {
		toStart();
	})
	
	function toStart() {
		$.ajax({
			type : "post",
			url : "apply/showAllApply.do?ap_state=1&ap_type=2",
			dataType : "json",
			success : function(data) {
				var str = "";
				$(data.rows).each(
					function(index, content) {
						str += "<tr>";
						str += "<td>" + content.ap_id+ "</td>";
						str += "<td>" + content.applicant+ "</td>";
						str += "<td>" + content.remark+ "</td>";
						str += "<td>" + content.ap_date+ "</td>";
						if (content.ap_lever == 1) {
							str += '<td><lable style="color:red;">加急</lable></td>';
						} else {
							str += "<td>普通</td>";
						}
						
						str += '<td><a href="openword.do?file='+content.accessory+'">在线预览</a>';
						/* str += '<td><a href="javascript:showWord(\''+content.accessory+'\')">在线预览</a>'; */
						str += "<span style='display:none'>"+content.accessory+"</span>";
						str += "|<a href='source/"+content.accessory+"'>下载</a></td>";
						str += '<td><a href="javascript:applyShow(\''+content.ap_id+'\',\''+content.applicant+'\',\''+content.deal_person+'\',\''+content.remark+'\',\''+content.result+'\',\''+content.ap_date+'\',\''+content.ap_dealdate+'\',\''+content.ap_lever+'\')" class="ysh">查看详细</a>';
						str += '&nbsp;|&nbsp;<a href="javascript:nagativeApply(\''+content.ap_id+'\',\''+content.applicant+'\')">否定申请</a>&nbsp;|&nbsp;<a href="javascript:agreeApply(\''+content.ap_id+'\',\''+content.applicant+'\')">同意申请</a></td>';
						str += "</tr>";
					})
				$("#ysh-tab-showAllWaitApply tbody").append(str);
			}
		})
	}
	layui.use('element', function(){
        var element = layui.element();

        //一些事件监听
        element.on('tab(demo)', function(data){
            console.log(data);
        });
    });
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage
                ,layer = layui.layer;

        laypage({
            cont: 'pageWAIT'
            ,pages: 100
            ,skip: true
        });
        var nums = 5; //每页出现的数据量

        //模拟渲染
        var render = function(curr){
            //此处只是演示，实际场景通常是返回已经当前页已经分组好的数据
            var str = '', last = curr*nums - 1;
            last = last >= data.length ? (data.length-1) : last;
            for(var i = (curr*nums - nums); i <= last; i++){
                str += '<li>'+ data[i] +'</li>';
            }
            return str;
        };
    });
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage
                ,layer = layui.layer;

        laypage({
            cont: 'pageNO'
            ,pages: 100
            ,skip: true
        });
        var nums = 5; //每页出现的数据量

        //模拟渲染
        var render = function(curr){
            //此处只是演示，实际场景通常是返回已经当前页已经分组好的数据
            var str = '', last = curr*nums - 1;
            last = last >= data.length ? (data.length-1) : last;
            for(var i = (curr*nums - nums); i <= last; i++){
                str += '<li>'+ data[i] +'</li>';
            }
            return str;
        };
    });
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage
                ,layer = layui.layer;

        laypage({
            cont: 'pageOK'
            ,pages: 100
            ,skip: true
        });
        var nums = 5; //每页出现的数据量

        //模拟渲染
        var render = function(curr){
            //此处只是演示，实际场景通常是返回已经当前页已经分组好的数据
            var str = '', last = curr*nums - 1;
            last = last >= data.length ? (data.length-1) : last;
            for(var i = (curr*nums - nums); i <= last; i++){
                str += '<li>'+ data[i] +'</li>';
            }
            return str;
        }
    });
	//否定计划
	function nagativeApply(ap_id,applicant) {
		layer.prompt(function(val, index) {
			$.ajax({
				type : "post",
				url : "apply/updateOneApply.do?ap_id="+ap_id+"&result="+val+"&ap_state=2",
				dataType : "json",
				success : function(data) {
					if(data == 1){
						layer.msg('处理成功！', {icon: 1});
					}else{
						layer.msg('处理失败！', {icon: 5});
					}
				}
			})
			layer.close(index);
		});
	}
	//同意计划
	function agreeApply(ap_id,applicant) {
		layer.prompt(function(val, index) {
			$.ajax({
				type : "post",
				url : "apply/updateOneApply.do?ap_id="+ap_id+"&result="+val+"&ap_state=3",
				dataType : "json",
				success : function(data) {
					if(data == 1){
						layer.msg('处理成功！', {icon: 1});
					}else{
						layer.msg('处理失败！', {icon: 5});
					}
				}
			})
			layer.close(index);
		});
	}
	
	//申请内容展示
	function applyShow(ap_id,applicant,deal_person,remark,result,ap_date,ap_dealdate,ap_lever) {
		//页面层
		var lever = "";
		if (ap_lever == 1) {
			lever += "<td>加急</td>";
		} else {
			lever += "<td>普通</td>";
		}
		$.ajax({
			type : "post",
			url : "apply/showOneApply.do?ap_id="+ap_id+"",
			dataType : "json",
			success : function(data) {
				var str = "";
				str += "<table class='layui-table'>";
				str += "<tr><th>物品图片</th><th>物品名称</th><th>物品尺寸</th><th>物品颜色</th><th>物品数量</th><th>出厂单价</th></tr>";
				$(data.rows).each(
					function(index, content) {
						str += "<tr><td><img src='source/upload/"+content.img_url+"' style='height:50px;width:50px;'></td>";
						str += "<td>"+content.g_name+"</td>";
						str += "<td>"+content.size+"</td>";
						str += "<td>"+content.color+"</td>";
						str += "<td>"+content.num+"</td>";
						str += "<td>"+content.g_price+"</td></tr>";
				})
				str += "</table>";
				layer.open({
				    type: 1,
				    skin: 'layui-layer-rim', //加上边框
				    area: ['800px', '400px'], //宽高
				    content: '<div><label>ID:</label><label>'+ap_id+'</label>' +
				    '<br><lable>申  请  人: </lable><label>'+applicant+'</label>' +
				    '<br><lable>处  理  人: </lable><label>'+deal_person+'</label>' +
				    '<br><label>备       注: </label><label>'+remark+'</label>' +
				    '<br><lable>处理结果: </lable><label>'+result+'</label>' +
				    '<br><label>申请时间: </label><label>'+ap_date+'</label>' +
				    '<br><lable>处理时间: </lable><label>'+ap_dealdate+'</label>' +
				    '<br><label>申请级别: </label><label>'+lever+'</label>' +
				    '<br><lable>详情列表: </lable><br>'+str+'</div>'
				});
			}
		})
		
	  	var str2 = "<table class='layui-table'>" +
			"<tr><td>物品名称</td><td>物品数量</td></tr>" +
			"<tr><td>003</td><td>004</td></tr>" +
			"<tr><td>003</td><td>004</td></tr>" +
			"</table>";
	}
	//展示已通过采购申请
	function toYesApply() {
		$.ajax({
			type : "post",
			url : "apply/showAllApply.do?ap_state=3&ap_type=2",
			dataType : "json",
			success : function(data) {
				var str = "";
				$(data.rows).each(
					function(index, content) {
						str += "<tr>";
						str += "<td>" + content.ap_id+ "</td>";
						str += "<td>" + content.applicant+ "</td>";
						str += "<td>" + content.deal_person+ "</td>";
						str += "<td>" + content.remark+ "</td>";
						str += "<td>" + content.result+ "</td>";
						str += "<td>" + content.ap_date+ "</td>";
						str += "<td>" + content.ap_dealdate+ "</td>";
						if (content.ap_lever == 1) {
							str += '<td><lable style="color:red;">加急</lable></td>';
						} else {
							str += "<td>普通</td>";
						}
						str += '<td><a href="openword.do?file='+content.accessory+'">在线预览</a>';
						str += "<span style='display:none'>"+content.accessory+"</span>";
						str += "|<a href='"+content.accessory+"'>下载</a></td>";
						str += '<td><a href="javascript:applyShow(\''+content.ap_id+'\',\''+content.applicant+'\',\''+content.deal_person+'\',\''+content.remark+'\',\''+content.result+'\',\''+content.ap_date+'\',\''+content.ap_dealdate+'\',\''+content.ap_lever+'\')" class="ysh">查看详细</a></td>';
						str += "</tr>";
					})
				$("#ysh-tab-showAllYesApply tbody").empty();
				$("#ysh-tab-showAllYesApply tbody").append(str);
			}
		})
	}
	
	//展示已否定采购申请
	function toNoApply() {
		$.ajax({
			type : "post",
			url : "apply/showAllApply.do?ap_state=2&ap_type=2",
			dataType : "json",
			success : function(data) {
				var str = "";
				$(data.rows).each(
					function(index, content) {
						str += "<tr>";
						str += "<td>" + content.ap_id+ "</td>";
						str += "<td>" + content.applicant+ "</td>";
						str += "<td>" + content.deal_person+ "</td>";
						str += "<td>" + content.remark+ "</td>";
						str += "<td>" + content.result+ "</td>";
						str += "<td>" + content.ap_date+ "</td>";
						str += "<td>" + content.ap_dealdate+ "</td>";
						if (content.ap_lever == 1) {
							str += '<td><lable style="color:red;">加急</lable></td>';
						} else {
							str += "<td>普通</td>";
						}
						str += '<td><a href="openword.do?file='+content.accessory+'">在线预览</a>';
						str += "<span style='display:none'>"+content.accessory+"</span>";
						str += "|<a href='"+content.accessory+"'>下载</a></td>";
						str += '<td><a href="javascript:applyShow(\''+content.ap_id+'\',\''+content.applicant+'\',\''+content.deal_person+'\',\''+content.remark+'\',\''+content.result+'\',\''+content.ap_date+'\',\''+content.ap_dealdate+'\',\''+content.ap_lever+'\')" class="ysh">查看详细</a></td>';
						str += "</tr>";
					})
				$("#ysh-tab-showAllNoApply tbody").empty();
				$("#ysh-tab-showAllNoApply tbody").append(str);
				
			}
		})
	}
	
	
	//在线浏览word
	function showWord(accessory) {
		var index = layer.open({
			type : 2,
			content : 'editword.jsp',
			area : [ '800px', '500px' ],
			maxmin : true
		});
	}
</script>
</html>