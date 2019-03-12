<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>物品图片管理</title>
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<link rel="stylesheet" href="css/goodsimg.css" media="all">
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
				<input type="text" placeholder="请输入物品名或物品编号..."
					class="layui-input in_search_goodsimg">
				<button class="layui-btn layui-btn-radius btn_search_goodsimg">搜索</button>
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
				<div class="layui-tab-item layui-show g_imgcp">
					<table class="layui-table" lay-skin="line">
						<thead>
							<tr>
								<th>编号</th>
								<th>名称</th>
								<th>品牌</th>
								<th>材料</th>
								<th>价格</th>
								<th>颜色</th>
								<th>尺寸</th>
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
				<div class="layui-tab-item g_imgyc">
					<table class="layui-table" lay-skin="line">
						<thead>
							<tr>
								<th>编号</th>
								<th>名称</th>
								<th>品牌</th>
								<th>材料</th>
								<th>价格</th>
								<th>颜色</th>
								<th>尺寸</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<!-- 产品信息 -->
						</tbody>
					</table>
					<!-- 分页开始-->
					<div class="g_page">
						<div id="yc_page"></div>
					</div>
					<!-- 分页结束-->
				</div>
				<div class="layui-tab-item g_imgsearch">
						<table class="layui-table" lay-skin="line">
							<thead>
								<tr>
									<th>编号</th>
									<th>名称</th>
									<th>品牌</th>
									<th>材料</th>
									<th>价格</th>
									<th>颜色</th>
									<th>尺寸</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<!-- 产品信息 -->
							</tbody>
						</table>
						<!-- 分页开始-->
						<div class="g_page">
							<div id="yc_search"></div>
						</div>
						<!-- 分页结束-->
					</div>
			</div>
		</div>
		<!--物品信息展示选项卡结束-->
		<!--图片展示区开始-->
		<div class="gimgarae">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>图片展示</legend>
			</fieldset>
			<div class="piczs">
				<div class="gpicsbtn">
					<input type="hidden" id="item_id" value=""> <input
						type="file" name="file" id="upload"
						class="layui-upload-file uploadbtn">
				</div>

			</div>
		</div>
		<!--图片展示区结束-->


		<script src="layui/layui.js" charset="utf-8"></script>
		<script src="js/jquery.min-1.11.3.js" charset="utf-8"></script>
		<script src="js/goodsimg.js" charset="utf-8"></script>
		<script type="text/javascript">
			
		</script>
</body>
</html>