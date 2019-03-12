<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>客户报单</title>
		<style type="text/css">
			td{
				text-align: center;
			}
			caption{
				text-align: left;
				margin-bottom: 5px;
			}
		</style>
		<link rel="stylesheet" href="layui/css/layui.css" media="all">
		<link rel="stylesheet" href="dome/zeroModal/zeroModal.css" />
	</head>
	<body>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		  <legend>客户订单</legend>
		</fieldset>
		<form class="layui-form">
				<div class="layui-form-item">
					<label class="layui-form-label">订单号:</label>
				    <div class="layui-input-inline">
				      <input name="s_o_id" autocomplete="off" placeholder="请输入订单编号" class="layui-input" type="text">
				    </div>
				    <label class="layui-form-label">下单时间:</label>
				    <div class="layui-input-inline">
				       <input name="s_o_date" id="date" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})" type="text" readonly="readonly">
				    </div>
				    <label class="layui-form-label">客户名称:</label>
				    <div class="layui-input-inline">
				      <input name="s_cu_name" autocomplete="off" placeholder="请输入客户名称" class="layui-input" type="text">
				    </div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">订单状态:</label>
					<div class="layui-input-inline">
				    	<select name="s_o_state">
					        <option value="3">待发货</option>
					        <option value="4">已发货</option>
					        <option value="5">已收货</option>
					    </select>
				    </div>
				    <label class="layui-form-label">付款状态:</label>
				    <div class="layui-input-inline">
				    	<select name="s_payment_state">
					        <option value="0">不限</option>
					        <option value="1">未付款</option>
					        <option value="2">已经交付定金</option>
					        <option value="3">已付完款</option>
					    </select>
				    </div>
				    <div class="layui-input-inline">
				    	<button class="layui-btn layui-btn-normal layui-btn-normal order-search-btn">搜索</button>
				    </div>
				</div>
		</form>
		<table class="layui-table">
			<caption>
				<button class="layui-btn layui-btn-normal layui-btn-small o-del">删除</button>
				<button class="layui-btn layui-btn-normal layui-btn-small o-pay-state site-demo-layer" data-type="notice">编辑</button>
				<button class="layui-btn layui-btn-normal layui-btn-small o-msg" data-type="notice">发送消息</button>
			</caption>
			<thead>
				<tr>
					<th style="display: none;"></th>
					<th style="text-align: center;">
						<input type="checkbox" class="cheack cheack-all" />
					</th>
					<th style="text-align: center;">订单号</th>
					<th style="text-align: center;">下单时间</th>
					<th style="text-align: center;">客户</th>
					<th style="text-align: center;">客服人员</th>
					<th style="text-align: center;">金额</th>
					<th style="text-align: center;">已付款</th>
					<th style="text-align: center;">未付款</th>
					<th style="text-align: center;">付款状态</th>
					<th style="text-align: center;">订单状态</th>
					<th style="text-align: center;">最迟交货时间</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<!-- <tr>
					<td style="display: none;">18909006077</td>
					<td>
						<input type="checkbox" class="cheack cheack-one" />
					</td>
					<td>201612011206123400014321</td>
					<td>2016-15-05</td>
					<td>六一童装</td>
					<td>蓝山</td>
					<td>300000</td>
					<td>200000</td>
					<td>100000</td>
					<td>有余款未付</td>
					<td>已收货</td>
					<td>2016-15-07</td>
				</tr>
				<tr>
					<td style="display: none;">18909006077</td>
					<td>
						<input type="checkbox" class="cheack cheack-one" />
					</td>
					<td>201612011206123400014321</td>
					<td>2016-15-05</td>
					<td>六一童装</td>
					<td>蓝山</td>
					<td>300000</td>
					<td>200000</td>
					<td>100000</td>
					<td>有余款未付</td>
					<td>已收货</td>
					<td>2016-15-07</td>
				</tr>
				<tr>
					<td style="display: none;">18909006077</td>
					<td>
						<input type="checkbox" class="cheack cheack-one" />
					</td>
					<td>201612011206123400014321</td>
					<td>2016-15-05</td>
					<td>六一童装</td>
					<td>蓝山</td>
					<td>300000</td>
					<td>200000</td>
					<td>100000</td>
					<td>有余款未付</td>
					<td>已收货</td>
					<td>2016-15-07</td>
				</tr>
				<tr>
					<td style="display: none;">18909006077</td>
					<td>
						<input type="checkbox" class="cheack cheack-one" />
					</td>
					<td>201612011206123400014321</td>
					<td>2016-15-05</td>
					<td>六一童装</td>
					<td>蓝山</td>
					<td>300000</td>
					<td>200000</td>
					<td>100000</td>
					<td>有余款未付</td>
					<td>已收货</td>
					<td>2016-15-07</td>
				</tr>
				<tr>
					<td style="display: none;">18909006077</td>
					<td>
						<input type="checkbox" class="cheack cheack-one" />
					</td>
					<td>201612011206123400014321</td>
					<td>2016-15-05</td>
					<td>六一童装</td>
					<td>蓝山</td>
					<td>300000</td>
					<td>200000</td>
					<td>100000</td>
					<td>有余款未付</td>
					<td>已收货</td>
					<td>2016-15-07</td>
				</tr> -->
			</tbody>
		</table>
		<div style="text-align: center;"><div id="page"></div></div>
	</body>
	<script type="text/javascript" src="js/jquery.min-1.11.3.js"></script>
	<script src="layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="dome/zeroModal/zeroModal.min.js" ></script>
	<script type="text/javascript" src="js/wxq/cheack.js" ></script>
	<script type="text/javascript" src="js/wxq/order-kf.js" ></script>
</html>
