<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>物品管理</title>
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<link rel="stylesheet" href="css/goods.css" media="all">
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
				<input type="text" lay-verify="required" placeholder="请输入物品名或物品编号..."
					class="layui-input in_search_goods">
				<button class="layui-btn layui-btn-radius btn_search_goods" lay-submit="">搜索</button>
				<button class="layui-btn layui-btn-radius btn_add_goods">增加</button>
			</div>
		</div>
	</div>
	<!--工具栏开始-->
	<!--物品信息展示选项卡开始-->
	<div class="gtab">
		<fieldset class="layui-elem-field layui-field-title">
			<legend>物品信息</legend>
		</fieldset>
		<div class="layui-tab layui-tab-card">
			<ul class="layui-tab-title ttab">
				<li class="layui-this">产品</li>
				<li>原材料</li>
				<li id="tab_search">搜索显示</li>
			</ul>
			<div class="layui-tab-content" style="min-height: 100px;">
				<div class="layui-tab-item layui-show g_cp">
					<table class="layui-table" lay-skin="line">
						<colgroup>
							<col width="30">
							<col width="80">
						</colgroup>
						<thead>
							<tr>
								<th></th>
								<th>编号</th>
								<th>名称</th>
								<th>品牌</th>
								<th>材料</th>
								<th>出库价格</th>
								<th>产品状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<!-- 产品信息 -->
						</tbody>
					</table>
					<!-- 分页开始-->
					<div class="g_page">
						<div id="cp_page"></div>
					</div>
					<!-- 分页结束-->
				</div>

				<div class="layui-tab-item g_yc">
					<table class="layui-table" lay-skin="line">
						<colgroup>
							<col width="30">
							<col width="80">
						</colgroup>
						<thead>
							<tr>
								<th></th>
								<th>编号</th>
								<th>名称</th>
								<th>品牌</th>
								<th>材料</th>
								<th>入库价格</th>
								<th>产品状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<!-- 原材料信息 -->
						</tbody>
					</table>
					<!-- 分页开始-->
					<div class="g_page">
						<div id="yc_page"></div>
					</div>
					<!-- 分页结束-->
				</div>

				<div class="layui-tab-item g_search">
					<table class="layui-table" lay-skin="line">
						<colgroup>
							<col width="30">
							<col width="80">
						</colgroup>
						<thead>
							<tr>
								<th></th>
								<th>编号</th>
								<th>名称</th>
								<th>品牌</th>
								<th>材料</th>
								<th>价格</th>
								<th>类型</th>
								<th>产品状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<!-- 原材料信息 -->
						</tbody>
					</table>
					<!-- 分页开始-->
					<div class="g_page">
						<div id="yp_page"></div>
					</div>
					<!-- 分页结束-->
				</div>

			</div>
		</div>
	</div>
	<!--物品信息展示选项卡结束-->


	<script src="layui/layui.js" charset="utf-8"></script>
	<script src="js/jquery.min-1.11.3.js" charset="utf-8"></script>
	<script src="js/goods.js" charset="utf-8"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>