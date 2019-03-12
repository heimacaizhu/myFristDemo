<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>客户报单管理</title>
		<style type="text/css">
			caption{
				margin-bottom: 10px;
				text-align: left;
			}
			td{
				text-align: center;
			}
		</style>
		 <link rel="stylesheet" href="layui/css/layui.css" media="all">
		<link rel="stylesheet" href="dome/zeroModal/zeroModal.css" />
	</head>
	<body>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>客户报单申请</legend>
		</fieldset>
		<div>
			<form class="layui-form">
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 60px;">申请时间</label>
					<div class="layui-input-inline">
						<input name="s_ap_date" id="date" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})" type="text" readonly="readonly">
					</div>
					<label class="layui-form-label" style="width: 60px;">报单状态</label>
					<div class="layui-input-inline">
						<select name="s_ap_state">
							<option value="0">不限</option>
							<option value="1">待处理</option>
							<option value="2">未通过审核</option>
							<option value="3">已通过审核</option>
						</select>
					</div>
					<label class="layui-form-label" style="width: 60px;">加急等级</label>
					<div class="layui-input-inline">
						<select name="s_ap_lever">
							<option value="0">不限</option>
							<option value="1">加急</option>
							<option value="2">普通</option>
						</select>
					</div>
					<div class="layui-input-inline">
						<button class="layui-btn layui-btn-normal layui-btn-normal bd-search-btn">搜索</button>
					</div>
				</div>
			</form>
		</div>
		<table class="layui-table">
			<caption>
				<button class="layui-btn layui-btn-normal bditems" data-type="notice">报单详情</button>
				<button class="layui-btn layui-btn-normal fj-btn" data-type="notice">添加附件</button>
				<button class="layui-btn layui-btn-normal  showfjj">查看附件</button>
				<button class="layui-btn layui-btn-normal  bd-send-msg" data-type="notice">发送回馈消息</button>
				<button class="layui-btn layui-btn-normal bd-add" data-type="setTop">新增客户报单</button>
			</caption>
			<thead>
				<tr>
					<th style="display: none;"></th><!--申请编号-->
					<th>
						<!--<input type="checkbox" class="cheack cheack-all" />-->
					</th>
					<th style="text-align: center;">申请人</th>
					<th style="text-align: center;">申请时间</th>
					<th style="text-align: center;">客户</th>
					<th style="text-align: center;">处理人</th>
					<th style="text-align: center;">处理时间</th>
					<th style="text-align: center;">处理结果</th>
					<th style="text-align: center;">申请状态</th>
					<th style="text-align: center;">加载状态</th>
					<th style="display: none;">
					</th><!-- 电话 -->
				</tr>
			</thead>
			<tbody id="tbody">
				<!-- <tr>
					<td style="display: none;">0001</td>
					<td>
						<input type="checkbox" class="cheack cheack-one" />
					</td>
					<td>蓝山</td>
					<td>2016-12-06</td>
					<td>麦士威尔</td>
					<td>拿铁</td>
					<td>2016-12-06</td>
					<td>缺少相关合同以及详细资料，无法审核</td>
					<td>未通过审核</td>
					<td>普通</td>
					<td style="display: none;">18909009077</td>
				</tr>
				<tr>
					<td style="display: none;">0001</td>
					<td>
						<input type="checkbox" class="cheack cheack-one" />
					</td>
					<td>蓝山</td>
					<td>2016-12-06</td>
					<td>麦士威尔</td>
					<td>拿铁</td>
					<td>2016-12-06</td>
					<td>缺少相关合同以及详细资料，无法审核</td>
					<td>未通过审核</td>
					<td>普通</td>
					<td style="display: none;">18909009077</td>
				</tr>
				<tr>
					<td style="display: none;">0001</td>
					<td>
						<input type="checkbox" class="cheack cheack-one" />
					</td>
					<td>蓝山</td>
					<td>2016-12-06</td>
					<td>麦士威尔</td>
					<td>拿铁</td>
					<td>2016-12-06</td>
					<td>缺少相关合同以及详细资料，无法审核</td>
					<td>未通过审核</td>
					<td>普通</td>
					<td style="display: none;">18909009077</td>
				</tr>
				<tr>
					<td style="display: none;">0001</td>
					<td>
						<input type="checkbox" class="cheack cheack-one" />
					</td>
					<td>蓝山</td>
					<td>2016-12-06</td>
					<td>麦士威尔</td>
					<td>拿铁</td>
					<td>2016-12-06</td>
					<td>缺少相关合同以及详细资料，无法审核</td>
					<td>未通过审核</td>
					<td>普通</td>
					<td style="display: none;">18909009077</td>
				</tr>
				<tr>
					<td style="display: none;">0001</td>
					<td>
						<input type="checkbox" class="cheack cheack-one" />
					</td>
					<td>蓝山</td>
					<td>2016-12-06</td>
					<td>麦士威尔</td>
					<td>拿铁</td>
					<td>2016-12-06</td>
					<td>缺少相关合同以及详细资料，无法审核</td>
					<td>未通过审核</td>
					<td>普通</td>
					<td style="display: none;">18909009077</td>
				</tr>
				<tr>
					<td style="display: none;">0001</td>
					<td>
						<input type="checkbox" class="cheack cheack-one" />
					</td>
					<td>蓝山</td>
					<td>2016-12-06</td>
					<td>麦士威尔</td>
					<td>拿铁</td>
					<td>2016-12-06</td>
					<td>缺少相关合同以及详细资料，无法审核</td>
					<td>未通过审核</td>
					<td>普通</td>
					<td style="display: none;">18909009077</td>
				</tr> -->
			</tbody>
		</table>
		<div style="text-align: center;"><div id="page"></div></div>
	</body>
	 <script type="text/javascript" src="js/jquery.min-1.11.3.js"></script>
	 <script src="layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="dome/zeroModal/zeroModal.min.js" ></script>
	<script type="text/javascript" src="js/wxq/kh-bd.js" ></script>
</html>
    