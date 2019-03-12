$(document).ready(function(){
	//初始化
	khaddInit();
	//区域数据初始化
	regionDateInit();
	//输入检测
	khCheack();
	//数据提交检测
	khSubmit();
});
/*
 * 初始化
 */
function khaddInit(){
	layui.use(['form', 'layedit', 'laydate'], function(){
	    var form = layui.form(),
	    layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate;
	});
	$(".layui-form-mid").addClass("myerror");
}

/**
 * 初始化区域数据
 */
function regionDateInit(){
	$.post("nopageRegion.do",function(json){
		var str = "";
		$.each(json,function(i,item){
			str += "<option value='"+item.r_id+"'>"+item.r_name+"</option>";
		});
		$("#rrid option:gt(0)").remove();
		$("#rrid").append(str);
	},"json");
}

/*
 * 输入检测
 */
function khCheack(){
	//检测客户名称
	$("input[name=cu_name]").blur(function(){
		$(this).parent().next().removeClass("myerror");
		$(this).parent().next().removeClass("myright");
		var cu_name = $(this).val();
		if(cu_name==""){
			$(this).parent().next().text("客户名称不能为空");
			$(this).parent().next().addClass("myerror");
			return false;
		}
		if (!cheackStr(cu_name)) {
			$(this).parent().next().text("请不要输入非法字符串");
			$(this).parent().next().addClass("myerror");
		} else{
			$(this).parent().next().text("√");
			$(this).parent().next().addClass("myright");
		}
	});
	//检查联系人
	$("input[name=link_name]").blur(function(){
		$(this).parent().next().removeClass("myerror");
		$(this).parent().next().removeClass("myright");
		var linkman = $(this).val();
		if(linkman==""){
			$(this).parent().next().text("联系人不能为空");
			$(this).parent().next().addClass("myerror");
			return false;
		}
		if (!cheackStr(linkman)) {
			$(this).parent().next().text("请不要输入非法字符串");
			$(this).parent().next().addClass("myerror");
		} else{
			$(this).parent().next().text("√");
			$(this).parent().next().addClass("myright");
		}
	});
	//检查联系电话
	$("input[name=link_phone]").blur(function(){
		$(this).parent().next().removeClass("myerror");
		$(this).parent().next().removeClass("myright");
		var phone = $(this).val();
		if(phone==""){
			$(this).parent().next().text("联系电话不能为空");
			$(this).parent().next().addClass("myerror");
			return false;
		}
		if (!cheackPhone(phone)) {
			$(this).parent().next().text("您输入的手机格式不正确");
			$(this).parent().next().addClass("myerror");
		} else{
			$(this).parent().next().text("√");
			$(this).parent().next().addClass("myright");
		}
	});
	//检测区域
	$("select[name=re_id]").blur(function(){
		$(this).parent().next().removeClass("myerror");
		$(this).parent().next().removeClass("myright");
		var reid = $(this).val();
		if (reid == "0") {
			$(this).parent().next().text("请选择客户区域");
			$(this).parent().next().addClass("myerror");
		}else{
			$(this).parent().next().text("√");
			$(this).parent().next().addClass("myright");
		}
	});
	//检测通讯地址
	$("input[name=address]").blur(function(){
		$(this).parent().next().removeClass("myerror");
		$(this).parent().next().removeClass("myright");
		var address = $(this).val();
		if(address==""){
			$(this).parent().next().text("通讯地址不能为空");
			$(this).parent().next().addClass("myerror");
			return false;
		}
		if (!cheackStr(address)) {
			$(this).parent().next().text("请不要输入非法字符串");
			$(this).parent().next().addClass("myerror");
		} else{
			$(this).parent().next().text("√");
			$(this).parent().next().addClass("myright");
		}
	});
	//检测邮箱
	$("input[name=email]").blur(function(){
		$(this).parent().next().removeClass("myerror");
		$(this).parent().next().removeClass("myright");
		var email = $(this).val();
		if(email==""){
			$(this).parent().next().text("邮箱不能为空");
			$(this).parent().next().addClass("myerror");
			return false;
		}
		if (!cheackEmail(email)) {
			$(this).parent().next().text("您输入的邮箱格式不正确");
			$(this).parent().next().addClass("myerror");
		} else{
			$(this).parent().next().text("√");
			$(this).parent().next().addClass("myright");
		}
	});
	//检测邮政编码
	$("input[name=zip_code]").blur(function(){
		$(this).parent().next().removeClass("myerror");
		$(this).parent().next().removeClass("myright");
		var yzcode = $(this).val();
		if(yzcode!=""){
			if (!cheackYZCode(yzcode)) {
				$(this).parent().next().text("您输入的邮编格式不正确");
				$(this).parent().next().addClass("myerror");
			} else{
				$(this).parent().next().text("√");
				$(this).parent().next().addClass("myright");
			}
		}
	});
	//检测时间
	$("input[name=create_date]").blur(function(){
		$(this).parent().next().removeClass("myerror");
		$(this).parent().next().removeClass("myright");
		var createDate = $(this).val();
		if (!cheackDate(createDate)) {
			$(this).parent().next().text("请选择时间");
			$(this).parent().next().addClass("myerror");
		} else{
			$(this).parent().next().text("√");
			$(this).parent().next().addClass("myright");
		}
	});
	//检测客户类型
	$("select[name=cu_type]").blur(function(){
		$(this).parent().next().removeClass("myerror");
		$(this).parent().next().removeClass("myright");
		var cutype = $(this).val();
		if (cutype=="0") {
			$(this).parent().next().text("请选择用户类型");
			$(this).parent().next().addClass("myerror");
		} else{
			$(this).parent().next().text("√");
			$(this).parent().next().addClass("myright");
		}
	});
}

/*
 * 数据提交检测
 */
function khSubmit(){
	$(".kh-submit").click(function(){
		//检测客户名称
		var cu_name = $("input[name=cu_name]").val();
		if (cu_name == "") {
			zeroModal.alert("亲，客户名称不能为空哦!");
			return false;
		}
		if (!cheackStr(cu_name)) {
			zeroModal.alert("亲，客户名称不能有非法字符串哦!");
			return false;
		}
		//检测联系人
		var link_name = $("input[name=link_name]").val();
		if (link_name == "") {
			zeroModal.alert("亲，联系人不能为空哦!");
			return false;
		}
		if (!cheackStr(link_name)) {
			zeroModal.alert("亲，联系人中不能有非法字符串哦!");
			return false;
		}
		//检测联系电话
		var link_phone = $("input[name=link_phone]").val();
		if(link_phone == ""){
			zeroModal.alert("亲，联系电话不能为空哦!");
			return false;
		}
		if (!cheackPhone(link_phone)) {
			zeroModal.alert("亲，您输入的联系电话不正确哦!");
			return false;
		}
		//检测客户区域
		var re_id = $("select[name=re_id]").val();
		if (re_id == "0") {
			zeroModal.alert("亲，请先选择客户区域!");
			return false;
		}
		//检测通讯地址
		var address = $("input[name=address]").val();
		if (address == "") {
			zeroModal.alert("亲，通讯地址不能为空哦!");
			return false;
		}
		if (!cheackStr(address)) {
			zeroModal.alert("亲，通讯地址中不能有非法字符串哦!");
			return false;
		}
		//检测邮箱
		var email = $("input[name=email]").val();
		if (email == "") {
			zeroModal.alert("亲，邮箱不能为空哦!");
			return false;
		}
		if (!cheackEmail(email)) {
			zeroModal.alert("亲，您输入的邮箱不正确哦!")
			return false;
		}
		//检测邮政编码
		var zip_code = $("input[name=zip_code]").val();
		if (zip_code!=""&&!cheackYZCode(zip_code)) {
			zeroModal.alert("亲，您输入的邮政编码不正确哦!")
			return false;
		}
		//检测创建时间
		var create_date = $("input[name=create_date]").val();
		if (create_date == "") {
			zeroModal.alert("亲，请先选择客户创建时间!");
			return false;
		}
		//检测用户类型
		var cu_type = $("select[name=cu_type]").val();
		if (cu_type == "0") {
			zeroModal.alert("亲，请先选择用客户类型!");
			return false;
		}
		
		//提交数据
		$.post("addCustomer.do",{cuName:cu_name,linkName:link_name,linkPhone:link_phone,
			rId:re_id,address:address,email:email,zipCode:zip_code,
			createDate:create_date,cuType:cu_type},function(msg){
			var msg = msg.msg;
			if (msg == "1") {
				zeroModal.success("添加成功!");
			}else if (msg == "2") {
				zeroModal.alert("该用户已经存在!");
			}else{
				zeroModal.error("客户添加失败!");
			}
		},"json");
		
	});
}
