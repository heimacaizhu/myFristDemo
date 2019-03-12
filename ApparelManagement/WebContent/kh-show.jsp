<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>我的客户</title>
		<style type="text/css">
			tr th:nth-of-type(2),tr td:nth-of-type(2){
				display: none;
			}
			td,th{
				text-align: center;
			}
		</style>
		<link rel="stylesheet" href="layui/css/layui.css" media="all">
		<link rel="stylesheet" href="dome/zeroModal/zeroModal.css" />
	</head>
	<body>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>我的客户</legend>
		</fieldset>
		<div>
			<form class="layui-form">
				<div class="layui-form-item">
				    <div class="layui-input-inline">
				      <input name="cu_name" autocomplete="off" placeholder="请输入客户名称" class="layui-input" type="text">
				    </div>
				    <div class="layui-input-inline">
				    	<select name="cu_type">
					        <option value="0">不限</option>
					        <option value="1">配套</option>
					        <option value="2">最终</option>
					        <option value="3">销售</option>
					    </select>
				    </div>
				    <div class="layui-input-inline">
				    	<button class="layui-btn layui-btn-normal layui-btn-normal khshow-search-btn">搜索</button>
				    </div>
				</div>
			</form>
		</div>
		<table class="layui-table">
			<caption style="text-align: left;margin-bottom: 10px;">
				<button class="layui-btn layui-btn-normal layui-btn-small kh-del">删除</button>
				<button class="layui-btn layui-btn-normal layui-btn-small kh-edit site-demo-layer" data-type="notice">编辑</button>
				<button class="layui-btn layui-btn-normal layui-btn-small kh-msg">发送消息</button>
			</caption>
			<thead>
				<tr>
					<th style="text-align: center;">
						<input type="checkbox" class="cheack cheack-all" />
					</th>
					<th>编号</th>
					<th style="text-align: center;">客户名称</th>
					<th style="text-align: center;">联系人</th>
					<th style="text-align: center;">联系电话</th>
					<th style="text-align: center;">所在区域</th>
					<th style="text-align: center;">通讯地址</th>
					<th style="text-align: center;">邮箱</th>
					<th style="text-align: center;">邮编</th>
					<th style="text-align: center;">创建时间</th>
					<th style="text-align: center;">客户类型</th>
				</tr>
			</thead>
			<tbody>
				<!-- <tr>
					<td><input type="checkbox" class="cheack cheack-one" /></td>
					<td>1</td>
					<td>六一童装</td>
					<td>蓝山</td>
					<td>18909006054</td>
					<td>高新区</td>
					<td>四川成都高新区XXX</td>
					<td>7878596814@163.com</td>
					<td>643001</td>
					<td>2016-12-05</td>
					<td>销售</td>
				</tr>
				<tr>
					<td><input type="checkbox" class="cheack cheack-one" /></td>
					<td>1</td>
					<td>六一童装</td>
					<td>蓝山</td>
					<td>18909006054</td>
					<td>高新区</td>
					<td>四川成都高新区XXX</td>
					<td>7878596814@163.com</td>
					<td>643001</td>
					<td>2016-12-05</td>
					<td>销售</td>
				</tr>
				<tr>
					<td><input type="checkbox" class="cheack cheack-one" /></td>
					<td>1</td>
					<td>六一童装</td>
					<td>蓝山</td>
					<td>18909006054</td>
					<td>高新区</td>
					<td>四川成都高新区XXX</td>
					<td>7878596814@163.com</td>
					<td>643001</td>
					<td>2016-12-05</td>
					<td>销售</td>
				</tr>
				<tr>
					<td><input type="checkbox" class="cheack cheack-one" /></td>
					<td>1</td>
					<td>六一童装</td>
					<td>蓝山</td>
					<td>18909006054</td>
					<td>高新区</td>
					<td>四川成都高新区XXX</td>
					<td>7878596814@163.com</td>
					<td>643001</td>
					<td>2016-12-05</td>
					<td>销售</td>
				</tr>
				<tr>
					<td><input type="checkbox" class="cheack cheack-one" /></td>
					<td>1</td>
					<td>六一童装</td>
					<td>蓝山</td>
					<td>18909006054</td>
					<td>高新区</td>
					<td>四川成都高新区XXX</td>
					<td>7878596814@163.com</td>
					<td>643001</td>
					<td>2016-12-05</td>
					<td>销售</td>
				</tr>
				<tr>
					<td><input type="checkbox" class="cheack cheack-one" /></td>
					<td>1</td>
					<td>六一童装</td>
					<td>蓝山</td>
					<td>18909006054</td>
					<td>高新区</td>
					<td>四川成都高新区XXX</td>
					<td>7878596814@163.com</td>
					<td>643001</td>
					<td>2016-12-05</td>
					<td>销售</td>
				</tr> -->
			</tbody>
		</table>
		<div style="text-align: center;"><div id="page"></div></div>
	</body>
    <script type="text/javascript" src="js/jquery.min-1.11.3.js"></script>
	<script src="layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="dome/zeroModal/zeroModal.min.js" ></script>
	<script type="text/javascript" src="js/wxq/cheack.js" ></script>
	<script type="text/javascript" src="js/wxq/kh-show.js" ></script>
</html>
    