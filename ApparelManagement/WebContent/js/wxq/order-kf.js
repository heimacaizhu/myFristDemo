$(document).ready(function(){
	//加载组件
	ordersDomeInit();
	//相关操作
	orderOperite();
	//数据初始化
	orderDataInit(1,"");
});

/*
 * 加载dome组件
 */
function ordersDomeInit(){
	//表单
	layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form()
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;
	});
}

/*
 * 初始化数据
 * */
function orderDataInit(page){
	var o_id = $("input[name=s_o_id]").val();
	if (o_id!=""&&!cheackOrder(o_id)) {
		o_id = "";
	}
	var cu_name = $("input[name=s_cu_name]").val();
	if (cu_name!=""&&!cheackStr(cu_name)) {
		cu_name = "";
	}
	var o_date = $("input[name=s_o_date]").val();
	var o_state = $("select[name=s_o_state]").val();
	var payment_state = $("select[name=s_payment_state]").val();
	$.post("showod.do",{rows:5,page:page,cuName:cu_name,oId:o_id,oDate:o_date,oState:o_state,paymentState:payment_state},function(json){
		showods(json,page);
	},"json");
}

/*
 * 显示数据
 * */
function showods(json,curr){
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
		    		orderDataInit(obj.curr);
		    	}
		    }
		});
	});
	var str = "";
	$.each(json.rows,function(i,item){
		str += "<tr>";
		str += "<td style='display: none;'>"+item.link_phone+"</td>";
		str += "<td><input type='checkbox' class='cheack cheack-one' /></td>";
		str += "<td>"+item.o_id+"</td>";
		str += "<td>"+item.o_date+"</td>";
		str += "<td>"+item.cu_name+"</td>";
		str += "<td>"+item.em_name+"</td>";
		str += "<td>"+item.total+"</td>";
		str += "<td>"+item.spend+"</td>";
		str += "<td>"+item.not_payment+"</td>";
		if(item.payment_state=="1"){
			str += "<td>未付款</td>";
		}else if(item.payment_state=="2"){
			str += "<td>已交定金</td>";
		}else{
			str += "<td>已付清</td>";
		}
		if(item.o_state =="3"){
			str += "<td>待发货</td>";
		}else if(item.o_state =="4"){
			str += "<td>待收货</td>";
		}else{
			str += "<td>已收货</td>";
		}
		str += "<td>"+item.latest+"</td>";
		str += "</tr>";
	});
	$("#tbody").html(str);
}

/*
 * 相关操作
 */
function orderOperite(){
	//点击搜索
	$(".order-search-btn").click(function(e){
		e.preventDefault();
		//检测订单号
		var o_id = $("input[name=s_o_id]").val();
		if (o_id!=""&&!cheackOrder(o_id)) {
			zeroModal.alert("您输入的订单号不正确!");
			return false;
		}
		//检测客户名称
		var cu_name = $("input[name=s_cu_name]").val();
		if (cu_name!=""&&!cheackStr(cu_name)) {
			zeroModal.alert("请不要在客户名称中输入非法字符串!");
			return false;
		}
		var o_date = $("input[name=s_o_date]").val();
		var o_state = $("select[name=s_o_state]").val();
		var payment_state = $("select[name=s_payment_state]").val();
		//提交数据
		orderDataInit(1);
	});
	//点击全选
	$(".cheack-all").click(function(){
		if (this.checked) {
			$(".cheack-one").prop("checked",true);
		} else{
			$(".cheack-one").prop("checked",false);
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
	delOrder();
	//点击发送消息
	sendMsg();
	//点击编辑
	orderEdit();
}

/*
 * 点击删除
 */
function delOrder(){
	$(".o-del").click(function(){
		var ches = $(".cheack-one:checked");
		if (ches.length==0) {
			zeroModal.alert("请选择要删除的订单!");
			return false;
		}
		zeroModal.confirm("你确定要删除这些订单嘛?",function(){
			var ids = "";
			$.each(ches, function(i,item) {
				if (i==ches.length-1) {
					ids += $(this).parent().next().text();
				} else{
					ids += ($(this).parent().next().text() + ",");
				}
			});
			//提交数据
			$.post("",{ids:ids},function(json){
				var msg = json.msg;
				if (msg == "1") {
					zeroModal.success("删除成功!");
				}else if (msg == "2") {
					zeroModal.error("删除失败，有不能删除的订单!");
				}else{
					zeroModal.error("删除失败!");
				}
			},"json");
		});
	});
}

/*
 * 点击发送消息
 */
function sendMsg(){
	$(".o-msg").click(function(){
		var ches = $(".cheack-one:checked");
		if (ches.length==0) {
			zeroModal.alert("请选择要发送消息的订单!");
			return false;
		}
		if (ches.length>1) {
			zeroModal.alert("一次只能选择一条数据哦!");
			return false;
		}
		createMsgDom(this,ches[0]);
	});
}

/*
 * 点击编辑,改变订单的付款状态
 */
function orderEdit(){
	$(".o-pay-state").click(function(){
		var ches = $(".cheack-one:checked");
		if (ches.length==0) {
			zeroModal.alert("请选择要修改付款的订单!");
			return false;
		}
		if (ches.length>1) {
			zeroModal.alert("一次只能选择一条订单哦!");
			return false;
		}
		creatEditDome(this,ches[0]);
	});
}

/*
 * 创建编辑框
 */
function creatEditDome(obj,che){
	var htmlStr = editHtmlStr(che);
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
		        	,btn: ['确定', '取消']
		        	,moveType: 1 //拖拽模式，0或者1
		        	,content: htmlStr
		        	,success: function(layero){
		          		var btn = layero.find('.layui-layer-btn');
		         		btn.css('text-align', 'center');
		          	    var qd = btn.find('.layui-layer-btn0');
		          	    //保存数据
		          	    $(qd).click(function(){
		          	    	editSave();
		          	    });
		        	}
		       });
			}
	  	}
	  	var type = $(obj).data('type');
    	active[type] ? active[type].call(this) : '';
	});
}

/*
 * 创建修改输入框
 */
function editHtmlStr(che){
	var id = $(che).parent().next().text();
	var money = $(che).parents("tr").children(":nth-of-type(7)").text();
	var spend = $(che).parents("tr").children(":nth-of-type(8)").text();
	var str = "<div style='padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;'>";
	str += "<form class='layui-form'>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>订单编号</label>";
	str += "<div class='layui-input-inline'>";
	str += "<input type='text' name='e_o_id' readonly='readonly' value='"+id+"' style='width: 250px;height: 38px;'/>";
	str += "</div>";
	str += "</div>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>金额</label>";
	str += "<div class='layui-input-inline'>";
	str += "<input type='text' name='s_total' readonly='readonly' style='width: 250px;height: 38px;' value='"+money+"'/>";
	str += "</div>";
	str += "</div>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>已付款</label>";
	str += "<div class='layui-input-inline'>";
	str += "<input type='text' name='e_spend' style='width: 250px;height: 38px;' value='"+spend+"' />";
	str += "</div>";
	str += "</div>";
	str += "</form>";
	str += "</div>";
	return str;
}

/*
 * 保存修改的数据
 */
function editSave(){
	var spend = $("input[name=e_spend]").val();
	if (spend == "") {
		zeroModal.alert("请输入已付款！");
		return false;
	}
	if (!cheackNumber3(spend)) {
		zeroModal.alert("您输入的已付款金额不正确!");
		return false;
	}
	spend = parseFloat(spend);
	var o_id = $("input[name=e_o_id]").val();
	var money = $("input[name=s_total]").val();
	money = parseFloat(money);
	money = Math.ceil(money);
	if (spend<=0||spend>money) {
		zeroModal.alert("您输入的已付款金额不正确!");
		return false;
	}
	
	//提交数据
	$.post("upod.do",{oId:o_id,spend:spend},function(json){
		var msg = json.msg;
		if (msg == "1" ) {
			zeroModal.success("操作成功！");
		} else{
			zeroModal.error("操作失败!");
		}
	},"json");
	orderDataInit(1);
}

/*
 * 创建消息发送框
 */
function createMsgDom(obj,che){
	var phone = $(che).parent().prev().text();
	var oid = $(che).parent().next().text();
	var htmlStr = createMsgHtmlStr(phone,oid);
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
		        	,btn: ['发送', '取消']
		        	,moveType: 1 //拖拽模式，0或者1
		        	,content: htmlStr
		        	,success: function(layero){
		          		var btn = layero.find('.layui-layer-btn');
		         		btn.css('text-align', 'center');
		          	    var qd = btn.find('.layui-layer-btn0');
		          	    //保存数据
		          	    $(qd).click(function(){
		          	    	//发送消息
		          	    	sendMsgInfo(phone,oid);
		          	    });
		        	}
		       });
			}
	  	}
	  	var type = $(obj).data('type');
    	active[type] ? active[type].call(this) : '';
	});
}

/*
 * 构造msg发送编辑框
 */
function createMsgHtmlStr(phone,oid){
	var str = "<div style='padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;'>";
	str += "<form class='layui-form'>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>电话号码</label>";
	str += "<div class='layui-input-inline'>";
	str += "<input type='text' name='cu_phone' readonly='readonly' value='"+phone+"' style='width: 250px;height: 38px;'/>";
	str += "</div>";
	str += "</div>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>订单编号</label>";
	str += "<div class='layui-input-inline'>";
	str += "<input type='text' name='msg_o_id' readonly='readonly' value='"+oid+"' style='width: 250px;height: 38px;'/>";
	str += "</div>";
	str += "</div>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>消息类型</label>";
	str += "<div class='layui-input-inline'>";
	str += "<select name='msgid' style='width: 250px;height: 38px;display: block;'>";
	str += "<option value='895'>催款消息</option>";
	str += "</select>";
	str += "</div>";
	str += "</div>";
	str += "</form>";
	str += "</div>";
	return str;
}

/*
 * 发送消息数据
 */
function sendMsgInfo(phone,oid){
	var msgtype = $("select[name=msgid]").val();
	$.post("sendMsg.do",{phone:phone,oid:oid,msgtype:msgtype},function(json){
		var msg = json.msg;
		if (msg == "1") {
			zeroModal.success("消息发送成功！");
		} else{
			zeroModal.error("消息发送失败!");
		}
	},"json");
}
