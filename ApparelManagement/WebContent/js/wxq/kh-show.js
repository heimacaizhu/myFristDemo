$(document).ready(function(){
	//组件初始化
	khshowDomeInit();
	//数据初始化
	customerDateInit(1,"",0);
	//相关操作
	khshowOpertion();
});

/*
 * 组件初始化
 */
function khshowDomeInit(){
	//表单
	layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form()
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;
	});
}

/**
 * 初始化数据
 */
function customerDateInit(curr,name,type){
	$.post("showCustomer.do",{page:curr,cuName:name,cuType:type,rows:5},function(json){
		var pages = ((json.total%5)==0)?(json.total/5):(json.total/5+1);
		//添加分页组件
		layui.use(['laypage', 'layer','layedit','laydate'], function(){
			var laypage = layui.laypage,
			layedit = layui.layedit,
			laydate = layui.laydate,
			layer = layui.layer;
			laypage({
			    cont: 'page',
			    pages: pages,
			    curr: curr || 1,
			    skin: '#1E9FFF',
			    jump:function(obj,first){
			    	if (!first) {
			    		var cuName = $("input[name=cu_name]").val();
			    		if(!cheackStr(cuName)){
			    			cuName = "";
			    		}
			    		var cu_type = $("select[name=cu_type]").val();
			    		customerDateInit(obj.curr,cuName,cu_type);
					}
			    }
			});
		});
		showCustomers(json);
	});
}

/**
 * 解析数据
 */
function showCustomers(json){
	var str = "";
	$.each(json.rows,function(i,item){
		str += "<tr>";
		str += "<td><input type='checkbox' class='cheack cheack-one' /></td>";
		str += "<td>"+item.cu_id+"</td>";
		str += "<td>"+item.cu_name+"</td>";
		str += "<td>"+item.link_name+"</td>";
		str += "<td>"+item.link_phone+"</td>";
		str += "<td>"+item.r_name+"</td>";
		str += "<td>"+item.address+"</td>";
		str += "<td>"+item.email+"</td>";
		str += "<td>"+item.zip_code+"</td>";
		str += "<td>"+item.create_date+"</td>";
		if(item.cu_type=="1"){
			str += "<td>配套</td>";
		}else if(item.cu_type=="2"){
			str += "<td>最终</td>";
		}else{
			str += "<td>销售</td>";
		}
		str += "</tr>";
	});
	$("tbody").children().remove();
	$("tbody").html(str);
}

/*
 * 相关操作
 */
function khshowOpertion(){
	//点击搜索
	$(".khshow-search-btn").click(function(e){
		e.preventDefault();
		//客户名称
		var cu_name = $("input[name=cu_name]").val().trim();
		if (cu_name!=""&&(!cheackStr(cu_name))) {
			zeroModal.alert("请不要再搜索框中输入非法字符串");
			return false;
		}
		//客户类型
		var cu_type = $("select[name=cu_type]").val();
		//提交数据
		customerDateInit(1,cu_name,cu_type);
	});
	//点击全选
	$(".cheack-all").click(function(){
		if (this.checked) {
			$(".cheack").prop("checked",true);
		}else{
			$(".cheack").prop("checked",false);
		}
	});
	//点击单选
	$(".cheack-one").click(function(){
		var flag = this.checked;
		var ches = $(".cheack:gt(0)");
		var num = 0;
		$.each(ches, function(i,item) {
			if (this.checked) {
				num++;
			}
		});
		if (flag&&num==ches.length) {
			$(".cheack-all").prop("checked",true);
		} else{
			$(".cheack-all").prop("checked",false);
		}
	});
	//点击删除
	$(".kh-del").click(function(){
		var ches = $(".cheack-one:checked");
		if (ches.length == 0) {
			zeroModal.alert("请先选择要删除的客户");
			return false;
		}
		zeroModal.confirm("你确定要删除这些客户嘛?",function(){
			var ids = "";
			$.each(ches, function(i,item) {
				var id = $(this).parent().next().text();
				if (i==ches.length-1) {
					ids += id;
				} else{
					ids += (id + ",");
				}
			});
			//提交数据
			$.post("deleteCustomer.do",{ids:ids},function(json){
				var msg = json.msg;
				if (msg == "1") {
					zeroModal.success("删除成功!");
				}else if (msg == "2") {
					zeroModal.error("删除失败!");
				}
			},"json");
			customerDateInit(1,"",0);
		});
	});
	//点击编辑
	$(".kh-edit").click(function(){
		var ches = $(".cheack-one:checked");
		if (ches.length == 0) {
			zeroModal.alert("请先选择要修改的客户");
			return false;
		}else if (ches.length > 1) {
			zeroModal.alert("只能选择一个客户");
			return false;
		}else{
			showEditDiv(ches[0],this);
		}
	});
}

/*
 * 显示编辑框
 */
function showEditDiv(obj,btn){
	var htmlstr = createEditDiv(obj);
	layui.use('layer', function(){ //独立版的layer无需执行这一句
  		var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
  		//触发事件
        var active = {
        	notice: function(){
		      //示范一个公告层
		      layer.open({
		        type: 1
		        ,title: false //不显示标题栏
		        ,closeBtn: false
		        ,area: '500px;'
		        ,shade: 0.8
		        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
		        ,btn: ['保存', '取消']
		        ,moveType: 1 //拖拽模式，0或者1
		        ,content: htmlstr
		        ,success: function(layero){
		          var btn = layero.find('.layui-layer-btn');
		          btn.css('text-align', 'center');
		          var qd_btn =  btn.find('.layui-layer-btn0');
		          $(qd_btn).click(function(){
		          	//点击保存
		          	//判断信息是否被修改过
		          	if (!infoChanged(obj)) {
		          		zeroModal.alert("客户信息没有任何修改，请先修改相关信息后再保存");
		          		return false;
		          	}
		          	//提交数据
		          	khEditSave();
		          });
		        }
		      });
		    }
        };
        var type = $(btn).data('type');
		active[type] ? active[type].call(this) : '';
		//初始化区域下拉列表框
		editSelectHtml();
		//选中相应的选择框
		regionDomeChecked(obj);
   	});
}

/*
 * 创建编辑框
 */
function createEditDiv(obj){
	var che = $(obj);
	var cu_id = che.parent().next().text();
	var cu_name = che.parents("tr").children(":nth-of-type(3)").text();
	var link_name = che.parents("tr").children(":nth-of-type(4)").text();
	var link_phone = che.parents("tr").children(":nth-of-type(5)").text();
	var address = che.parents("tr").children(":nth-of-type(7)").text();
	var email = che.parents("tr").children(":nth-of-type(8)").text();
	var zip_code = che.parents("tr").children(":nth-of-type(9)").text();
	var str = "<div style='padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;'>";
	str += "<input type='hidden' value='"+cu_id+"' name='cu_id' />";
	str += "<form class='layui-form'>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>客户名称</label>";
	str += "<div class='layui-input-inline'>";
	str += "<input id='editcuname' name='cu_name' autocomplete='off' class='layui-input' type='text' value='"+cu_name+"'>";
	str += "</div>";
	str += "</div>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>联系人</label>";
	str += "<div class='layui-input-inline'>";
	str += "<input name='link_name' autocomplete='off' class='layui-input' type='text' value="+link_name+">";
	str += "</div>";
	str += "</div>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>联系电话</label>";
	str += "<div class='layui-input-inline'>";
	str += "<input name='link_phone' autocomplete='off' class='layui-input' type='text' value='"+link_phone+"'>";
	str += "</div>";
	str += "</div>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>通讯地址</label>";
	str += "<div class='layui-input-inline'>";
	str += "<input name='address' autocomplete='off' class='layui-input' type='text' value='"+address+"'>";
	str += "</div>";
	str += "</div>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>邮箱</label>";
	str += "<div class='layui-input-inline'>";
	str += "<input name='email' autocomplete='off' class='layui-input' type='text' value='"+email+"'>";
	str += "</div>";
	str += "</div>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>邮编</label>";
	str += "<div class='layui-input-inline'>";
	str += "<input name='zip_code' autocomplete='off' class='layui-input' type='text' value='"+zip_code+"'>";
	str += "</div>";
	str += "</div>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>区域</label>";
	str += "<div class='layui-input-inline'>";
	str += "<select name='re_id' style='text-align: center;display: block;width:190px;line-height:38px;padding-left:10px;height:38px;border-radius: 2px;' id='rrid'>";
	str += "</select>";
	str += "</div>";
	str += "</div>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>客户类型</label>";
	str += "<div class='layui-input-inline'>";
	str += "<select id='edit-cutype' name='cu_type' style='text-align: center;display: block;width:190px;line-height:38px;padding-left:10px;height:38px;border-radius: 2px;'>";
	str += "<option value='1'>配套</option>";
	str += "<option value='2'>最终</option>";
	str += "<option value='3'>销售</option>";
	str += "</select>";
	str += "</div>";
	str += "</div>";
	str += "</form>";
	str += "</div>";
	return str;
}

/**
 * 编辑框区域下拉列表
 * */
function editSelectHtml(){
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
 * 下拉列表框选择
 */
function regionDomeChecked(obj){
	var che = $(obj);
	var rename = che.parents("tr").children(":nth-of-type(6)").text();
	var typename = che.parents("tr").children(":nth-of-type(11)").text();
	var re = $("select[name='re_id']").children();
	$.each(re, function() {
		if ($(this).text()==rename) {
			$(this).prop("selected",true);
		}else{
			$(this).prop("selected",false);
		}
	});
	var type = $("select[name='cu_type']").children();
	$.each(type, function() {
		if ($(this).text()==typename) {
			$(this).prop("selected",true);
		}else{
			$(this).prop("selected",false);
		}
	});
}

/*
 * 判断信息是否被修改
 */
function infoChanged(obj){
	var che = $(obj);
	//var cu_id = che.parent().next().text().trim();
	var cu_name = che.parents("tr").children(":nth-of-type(3)").text().trim();
	var link_name = che.parents("tr").children(":nth-of-type(4)").text().trim();
	var link_phone = che.parents("tr").children(":nth-of-type(5)").text().trim();
	var address = che.parents("tr").children(":nth-of-type(7)").text().trim();
	var email = che.parents("tr").children(":nth-of-type(8)").text().trim();
	var zip_code = che.parents("tr").children(":nth-of-type(9)").text().trim();
	var rename = che.parents("tr").children(":nth-of-type(6)").text().trim();
	var typename = che.parents("tr").children(":nth-of-type(11)").text().trim();
	var str1 = cu_name+link_name+link_phone+address+email+zip_code+rename+typename;
	var str2 = "";
	str2 += $("#editcuname").val().trim();
	str2 += $("input[name='link_name']").val().trim();
	str2 += $("input[name='link_phone']").val().trim();
	str2 += $("input[name='address']").val().trim();
	str2 += $("input[name='email']").val().trim();
	str2 += $("input[name=zip_code]").val().trim();
	str2 += ($("select[name=re_id]").children(":checked").text().trim());
	str2 += ($("#edit-cutype").children(":checked").text().trim());
	if (str1 != str2) {
		return true;
	} else{
		return false;
	}
}

/*
 * 提交修改后的数据
 */
function khEditSave(){
	var cu_name = $("#editcuname").val().trim();
	if (!cheackStr(cu_name)) {
		zeroModal.alert("请不要在客户名称中输入非法字符!");
		return false;
	}
	var link_name = $("input[name=link_name]").val().trim();
	if (!cheackStr(link_name)) {
		zeroModal.alert("请不要在联系人中输入非法字符!");
		return false;
	}
	var link_phone = $("input[name='link_phone']").val().trim();
	if (!cheackPhone(link_phone)) {
		zeroModal.alert("您输入的联系电话格式不正确!");
		return false;
	}
	var address = $("input[name='address']").val().trim();
	if (!cheackStr(address)) {
		zeroModal.alert("请不要在通讯地址中输入非法字符!");
		return false;
	}
	var email = $("input[name='email']").val().trim();
	if (!cheackEmail(email)) {
		zeroModal.alert("您输入的邮箱账号格式不正确!");
		return false;
	}
	var zip_code =  $("input[name=zip_code]").val().trim();
	if (zip_code!=""&&!cheackYZCode(zip_code)) {
		zeroModal.alert("您输入的邮编不正确!");
		return false;
	}
	var re_id =  $("select[name=re_id]").val();
	var cu_type = $("#edit-cutype").val();
	var cu_id = $("input[name=cu_id]").val();
	//提交
	$.post("genxinCustomer.do",{cuId:cu_id,cuName:cu_name,linkName:link_name,linkPhone:link_phone,address:address,email:email,zipCode:zip_code,rId:re_id,cuType:cu_type},function(json){
		var msg = json.msg;
		if (msg=="1") {
			zeroModal.success("修改成功!");
		} else{
			zeroModal.error("修改失败!");
		}
	},"json");
	customerDateInit(1,"",0);
}
