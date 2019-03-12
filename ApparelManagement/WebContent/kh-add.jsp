<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>添加客户</title>
		<style type="text/css">
		   .myright{
		   	  color: blue;
		   	  font-weight: bold;
		   	  font-size: 20px;
		   }
		   .myerror{
		   	 color: orangered;
		   }
		</style>
		<link rel="stylesheet" href="layui/css/layui.css" media="all">
		<link rel="stylesheet" href="dome/zeroModal/zeroModal.css" />
	</head>
	<body>
		<div style="width: 600px;margin: 10px auto;padding: 10px;">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
			  	<legend>添加客户</legend>
			</fieldset>
			<div class="layui-form-item">
				<label class="layui-form-label">客户名称</label>
				<div class="layui-input-inline" style="width: 300px;">
				    <input name="cu_name" autocomplete="off" placeholder="请输入客户名称" class="layui-input" type="text">
				</div>
				<div class="layui-form-mid">(*必填)</div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">联系人</label>
			    <div class="layui-input-inline" style="width: 300px;">
			      <input name="link_name" autocomplete="off" placeholder="请输入联系人" class="layui-input" type="text">
			    </div>
			    <div class="layui-form-mid">(*必填)</div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">联系电话</label>
			    <div class="layui-input-inline" style="width: 300px;">
			      <input name="link_phone" autocomplete="off" class="layui-input" type="tel" placeholder="请输入联系电话">
			    </div>
			    <div class="layui-form-mid">(*必填)</div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">客户区域</label>
			    <div class="layui-input-inline" style="width: 300px;">
			    	<select name="re_id" style="width: 100%;padding-left: 10px;border: 1px solid #e6e6e6;line-height: 38px;height: 38px;border-radius: 2px;" id="rrid">
			    		<option value="0">请选择区域</option>
			    		<!-- <option value="1">成都市</option>
			    		<option value="1">自贡市</option>
			    		<option value="1">宜宾市</option>
			    		<option value="1">乐山市</option> -->
			    	</select>
			    </div>
			    <div class="layui-form-mid">(*必填)</div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">通讯地址</label>
			    <div class="layui-input-inline" style="width: 300px;">
			      <input name="address" autocomplete="off" class="layui-input" type="text" placeholder="请输入通讯地址">
			    </div>
			    <div class="layui-form-mid">(*必填)</div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">邮箱</label>
			    <div class="layui-input-inline" style="width: 300px;">
			      <input name="email" autocomplete="off" class="layui-input" type="email" placeholder="请输入邮箱账号">
			    </div>
			    <div class="layui-form-mid">(*必填)</div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">邮编</label>
			    <div class="layui-input-inline" style="width: 300px;">
			      <input name="zip_code" autocomplete="off" class="layui-input" type="text" placeholder="请输入邮编">
			    </div>
			    <div class="layui-form-mid"></div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">创建日期</label>
			    <div class="layui-input-inline" style="width: 300px;">
			       <input name="create_date" id="date" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})" type="text" placeholder="请选择当前日期" readonly="readonly">
			    </div>
			    <div class="layui-form-mid">(*必填)</div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">客户类型</label>
			    <div class="layui-input-inline" style="width: 300px;">
			    	<select name="cu_type" style="width: 100%;padding-left: 10px;border: 1px solid #e6e6e6;line-height: 38px;height: 38px;border-radius: 2px;">
			    		<option value="0">请选择客户类型</option>
			    		<option value="1">配套</option>
			    		<option value="2">最终</option>
			    		<option value="3">销售</option>
			    	</select>
			    </div>
			    <div class="layui-form-mid">(*必填)</div>
			</div>
			<div class="layui-form-item" style="text-align: center;">
			    <button class="layui-btn layui-btn-big kh-submit" style="width: 300px;"><i class="layui-icon">+</i>添加</button>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="js/jquery.min-1.11.3.js"></script>
	<script src="layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="dome/zeroModal/zeroModal.min.js" ></script>
	<script type="text/javascript" src="js/wxq/cheack.js" ></script>
	<script type="text/javascript" src="js/wxq/kh-add.js" ></script>
</html>