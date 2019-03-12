<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>客户区域</title>
		<style type="text/css">
			.region-add-p{
				margin: 0px auto;
			}
			th,td{
				text-align: center;
			}
		</style>
		 <link rel="stylesheet" href="layui/css/layui.css" media="all">
		<link rel="stylesheet" href="dome/zeroModal/zeroModal.css" />
		<link rel="stylesheet" href="dome/xcConform/css/xcConfirm.css" />
	</head>
	<body>
		<div class="region-add-p">			            
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
			  <legend>客户区域</legend>
			</fieldset>
			<div>
				<form class="layui-form">
					<div class="layui-form-item">
						<div class="layui-input-inline">
							<input name="r_name" placeholder="请输入区域名" autocomplete="off" class="layui-input" type="text">
						</div>
						<div class="layui-input-inline">
							<button class="layui-btn layui-btn-normal layui-btn-radius region-search-btn" style="width: 100px;">搜索</button>
						</div>
					</div>
				</form>
			</div>
			<table class="layui-table" id="regions">
				<caption style="text-align: left;padding: 5px;">
					<button class="layui-btn layui-btn-small del-more">批量删除</button>
					<button class="layui-btn layui-btn-small add-region-btn">新增区域</button>
				</caption>
				<colgroup>
				    <col width="50"/>
				    <col width="150"/>
				    <col width="300"/>
				    <col width="300"/>
				    <col width="300" />
				</colgroup>
				<thead>
					<tr>
						<th style="text-align: center;">
							<input class="cheack cheack-all" type="checkbox">
						</th>
						<th style="text-align: center;">区域编号</th>
						<th style="text-align: center;">区域名</th>
						<th style="text-align: center;">父区域</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody id="region-tbody">
					<!-- <tr>
						<td>
							<input type="checkbox" class="cheack cheack-one"/>
						</td>
						<td>1</td>
						<td>金牛区</td>
						<td>成都市</td>
						<td>
							<button class="layui-btn layui-btn-small re-del">删除</button>
							<button class="layui-btn layui-btn-small re-edit">编辑</button>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" class="cheack cheack-one"/>
						</td>
						<td>2</td>
						<td>成华区</td>
						<td>成都市</td>
						<td>
							<button class="layui-btn layui-btn-small re-del">删除</button>
							<button class="layui-btn layui-btn-small re-edit">编辑</button>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" class="cheack cheack-one"/>
						</td>
						<td>3</td>
						<td>高新区</td>
						<td>成都市</td>
						<td>
							<button class="layui-btn layui-btn-small re-del">删除</button>
							<button class="layui-btn layui-btn-small re-edit">编辑</button>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" class="cheack cheack-one"/>
						</td>
						<td>4</td>
						<td>武侯区</td>
						<td>成都市</td>
						<td>
							<button class="layui-btn layui-btn-small re-del">删除</button>
							<button class="layui-btn layui-btn-small re-edit">编辑</button>
						</td>
					</tr>
					<tr>
						<td>
							<input type="checkbox" class="cheack cheack-one"/>
						</td>
						<td>5</td>
						<td>青羊区</td>
						<td>成都市</td>
						<td>
							<button class="layui-btn layui-btn-small re-del">删除</button>
							<button class="layui-btn layui-btn-small re-edit">编辑</button>
						</td>
					</tr> -->
				</tbody>
			</table>
			<div style="text-align: center;"><div id="page"></div></div>
		</div>
	</body>
	<script type="text/javascript" src="js/jquery.min-1.11.3.js"></script>
	<script src="layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="dome/zeroModal/zeroModal.min.js" ></script>
	<script type="text/javascript" src="dome/xcConform/js/xcConfirm.js" ></script>
	<script type="text/javascript" src="js/wxq/cheack.js" ></script>
	<script type="text/javascript" src="js/wxq/region.js" ></script>
</html>
    