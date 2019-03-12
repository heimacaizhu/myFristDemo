$(document).ready(function(){
	//加载相关组件
	bdDomeInit();
	//相关操作
	bdOperate();
	//初始化数据
	applyDataInit(1);
});

/*
 * 加载dome组件
 */
function bdDomeInit(){
	//加载表单组件
	layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form()
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;
	});
}

/*
 * 请求初始化数据
 * */
function applyDataInit(curr){
	//获取搜索条件数据
	var apDate = $("input[name=s_ap_date]").val();//时间
	var apState = $("select[name=s_ap_state]").val();//报单状态
	var apLever = $("select[name=s_ap_lever]").val();//处理等级
	$.post("showapply.do",{page:curr,rows:5,apDate:apDate,apState:apState,apLever:apLever,apType:1},function(json){
		showApplys(json,curr);
	});
}

/*
 * 显示报单数据
 * */
function showApplys(json,curr){
	var pages = (json.total%5==0)?(json.total/5):(json.total/5+1);
	//加载分页组件
	layui.use(['laypage', 'layer'], function(){
		var laypage = layui.laypage,
		layer = layui.layer;
		laypage({
		    cont: 'page',
		    pages: pages,
		    curr: curr || 1,
		    skin: '#1E9FFF',
		    jump:function(obj,first){
		    	if (!first) {
		    		applyDataInit(obj.curr);
				}
		    }
		});
	});
	//解析数据
	var str = "";
	$.each(json.rows,function(i,item){
		str += "<tr>";
		str += "<td style='display: none;'>"+item.ap_id+"</td>";
		str += "<td><input type='checkbox' class='cheack cheack-one' /></td>";
		str += "<td>"+item.em_name+"</td>";
		str += "<td>"+item.ap_date+"</td>";
		str += "<td>"+item.cu_name+"</td>";
		//处理人
		if(item.person==undefined){
			str += "<td>(暂无)</td>";
		}else{
			str += "<td>"+item.person+"</td>";
		}
		//处理时间
		if(item.ap_dealdate==undefined){
			str += "<td></td>";
		}else{
			str += "<td>"+item.ap_dealdate+"</td>";
		}
		//处理结果
		if(item.result==undefined){
			str += "<td>(暂无)</td>";
		}else{
			str += "<td>"+item.result+"</td>";
		}
		var state = item.ap_state;//状态
		if(state == "1"){
			str += "<td>待处理</td>";
		}else if(state == "2"){
			str += "<td>未通过审核</td>";
		}else{
			str += "<td>通过审核</td>";
		}
		var lever = item.ap_lever;//处理级别
		if(lever == "1"){
			str += "<td style='color: red;'>紧急</td>";
		}else{
			str += "<td>普通</td>";
		}
		str += "<td style='display: none;'>"+item.link_phone+"</td>";
		str += "</tr>";
	});
	$("#tbody").html(str);
}

/*
 * 相关操作
 */
function bdOperate(){
	//查看订单详情
	showApplyItem();
	//新增客户报单
	addApply();
	//上传附件
	applyLoad();
	//点击搜索
	searchApplys();
	//查看附件
	showfj();
	//点击发送消息
	bdSendMsg();
}

/*
 * 查看附件
 * */
function showfj(){
	$(".showfjj").click(function(){
		var ches = $(".cheack-one:checked");
		if (ches.length==0) {
			zeroModal.alert("请选择你要查看附件的客户报单!");
			return false;
		}
		if (ches.length > 1) {
			zeroModal.alert("亲，一次只能查看一个报单的附件哦!");
			return false;
		}
		var id = $(ches[0]).parent().prev().text();
		$.post("showfilename.do",{id:id},function(json){
			var filen = json.file;
			//alert(filen);
			if(filen == "" || filen == undefined){
				zeroModal.alert("亲，该报单没有附件!");
			}else{
				window.location.href="openword.do?file="+filen;
			}
		},"json");
	});
}

/*
 * 搜索
 * */
function searchApplys(){
	$(".bd-search-btn").click(function(e){
		e.preventDefault();
		applyDataInit(1);
	});
}

/*
 * 查看订单详情
 */
function showApplyItem(){
	$(".bditems").click(function(){
		var ches = $(".cheack-one:checked");
		if (ches.length==0) {
			zeroModal.alert("请选择你要查看的客户报单!");
			return false;
		}
		if (ches.length > 1) {
			zeroModal.alert("亲，一次只能查看一个报单的详情哦!");
			return false;
		}
		createApplyItemDome(this,ches[0]);
		var id = $(ches[0]).parent().prev().text();
		showapItem(id);
	});
}

/*
 * 创建客户报单详情dom
 */
function createApplyItemDome(btn,che){
	var htmlstr = createApplyItemHtml(che);
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
		          //var qd_btn =  btn.find('.layui-layer-btn0');
		        }
		      });
		    }
        };
        var type = $(btn).data('type');
		active[type] ? active[type].call(this) : '';
	});
}

/*
 * 构造客户报单详情htmlStr
 */
function createApplyItemHtml(che){
	var str = "<div style='padding: 50px; line-height: 22px; background-color: #393D49; font-weight: 300;'>";
	str += "<table class='layui-table'>";
	str += "<thead><tr>";
	str += "<td>产品名</td><td>颜色</td><td>尺寸</td><td>数量</td>";
	str += "</tr></thead>";
	str += "<tbody id='bttbody'>";
	str += "</tbody>";
	str += "</table>";
	str += "</div>";
	return str;
}

/*
 * 显示订单条目
 * */
function showapItem(id){
	$.post("showapplyitem.do",{id:id},function(json){
		var str = "";
		$.each(json,function(i,item){
			str += "<tr>";
			str += "<td>"+item.g_name+"</td>";
			str += "<td>"+item.color+"</td>";
			str += "<td>"+item.size+"</td>";
			str += "<td>"+item.num+"</td>";
			str += "</tr>";
		});
		$("#bttbody").html(str);
	},"json");
}

/*
 * 新增客户报单
 */
function addApply(){
	$(".bd-add").click(function(){
		createApplyDome(this);
	});
}

/*
 * 创建客户报单dome
 */
function createApplyDome(obj){
	layui.use('layer', function(){ //独立版的layer无需执行这一句
	  	var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
	    //触发事件
	    var active = {
		    setTop: function(){
		      var that = this; 
		      //多窗口模式，层叠置顶
		      layer.open({
		        type: 2 //此处以iframe举例
		        ,title: '客户申请报单'
		        ,area: ['500px', '500px']
		        ,shade: 0
		        ,maxmin: true
		        ,offset: [ //为了演示，随机坐标
		          200
		          ,400
		        ] 
		        ,content: 'bd-add.html'
		        ,zIndex: layer.zIndex //重点1
		        ,success: function(layero){
		          layer.setTop(layero); //重点2
		        }
		    	});
		    }
		}
	    var type = $(obj).data('type');
    	active[type] ? active[type].call(this) : '';
	});
}

/*
 * 上传附件
 */
function applyLoad(){
	$(".fj-btn").click(function(){
		var ches = $(".cheack-one:checked");
		if (ches.length==0) {
			zeroModal.alert("请先选择你要添加附件的报单");
			return false;
		}else if (ches.length >1) {
			zeroModal.alert("一次只能选择一条报单数据");
			return false;
		} else{
			
		}
		createLoadDome(this,ches[0]);
	});
}

/*
 * 创建附件添加弹出框
 */
function createLoadDome(btn,obj){
	var ap_id = $(obj).parent().prev().text();
	var htmlstr = createLoadHtml(obj);
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
        		        ,area: '300px;'
        		        ,shade: 0.8
        		        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        		        ,btn: [ '取消']
        		        ,moveType: 1 //拖拽模式，0或者1
        		        ,content:htmlstr
        		        ,success: function(layero){
        		          var btn = layero.find('.layui-layer-btn');
        		          btn.css('text-align', 'center');
        		        }
        		      });
        		    }
        }
        var type = $(btn).data('type');
        active[type] ? active[type].call(this) : '';
        loadddd(ap_id);
	});
}

/*
 * 添加附件html
 */
function createLoadHtml(obj){
	var str = "<div style='padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;'>";
	str += "<input type='file' name='file' class='layui-upload-file'>";
	str += "</div>";
	return str;
}

/**
 * 上传
 */
function loadddd(id){
	layui.use('upload', function(){
		layui.upload({
  		  url: 'fileUpload.do'
  		  ,title: '上传附件'
  		  ,ext: 'docx'
  		  ,success: function(json){
  		      var msg = json.msg;
  		      if(msg == "2"){
  		    	  //上传失败
  		    	zeroModal.error("文件上传失败");
  		      }else{
  		    	  //上传成功
  		    	saveFFF(msg,id)
  		      }
  		  }
  		});      
  });
}

/**
 * 保存文件
 */
function saveFFF(accessory,apId){
	$.post("saveFile.do",{accessory:accessory,apId:apId},function(ss){
		var mg = ss.msg;
		if(mg == "1"){
			zeroModal.success("文件上传成功");
		}else{
			zeroModal.error("文件上传失败");
		}
	});
}

/**
 * 点击发送消息
 */
function bdSendMsg(){
	$(".bd-send-msg").click(function(){
		var ches = $(".cheack-one:checked");
		if (ches.length==0) {
			zeroModal.alert("请先选择你要添加附件的报单");
			return false;
		}else if (ches.length >1) {
			zeroModal.alert("一次只能选择一条报单数据");
			return false;
		} else{
			
		}
		var states = $(ches[0]).parents("tr").children("td:nth-of-type(9)").text();
		if(states == "待处理"){
			zeroModal.alert("该报单还没有处理，暂时不能发送回馈消息");
			return false;
		}
		//创建弹出框
		var id = $(ches[0]).parent().prev().text();
		//alert(id);
		var phone = $(ches[0]).parents("tr").children("td:last-child").text();
		//alert(phone);
		sendMsgDome(this,id);
	});
}

/*
 * 创建消息发送弹出框
 * */
function sendMsgDome(btn,id,phone){
	var htmlstr = createSendMsgHtml(id,phone);
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
        		        ,area: '300px;'
        		        ,shade: 0.8
        		        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        		        ,btn: ['发送', '取消']
        		        ,moveType: 1 //拖拽模式，0或者1
        		        ,content:htmlstr
        		        ,success: function(layero){
        		          var btnn = layero.find('.layui-layer-btn');
        		          btnn.css('text-align', 'center');
        		          //点击保存提交数据
        		          $(btnn).click(function(e){
        		        	  e.preventDefault();
        		        	  ssMsg();
        		          });
        		        }
        		      });
        		    }
        }
        var type = $(btn).data('type');
        active[type] ? active[type].call(this) : '';
	});
}

/*
 * 消息发送弹出框htmlstr
 * */
function createSendMsgHtml(id,phone){
	var str = "<div style='padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;'>";
	str += "<input type='hidden'  class='idhide'  value='"+id+"'/>";
	str += "<input type='hidden'  class='msgphone'  value='"+phone+"'/>";
	str += "<form class='layui-form'>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>消息类型</label>";
	str += "<div class='layui-input-inline'>";
	str += "<select id='edit-msgid' name='msgtempid' style='text-align: center;display: block;width:190px;line-height:38px;padding-left:10px;height:38px;border-radius: 2px;'>";
	str += "<option value='880'>审核通过消息</option>";
	str += "<option value='881'>审核未通过消息</option>";
	str += "</select>";
	str += "</div>";
	str += "</div>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>消息内容</label>";
	str += "<div class='layui-input-inline'>";
	str += " <textarea width='300px'  min-height='50px'  class='msg-content'></textarea>";
	str += "</div>";
	str += "</div>";
	str += "</form>";
	str += "</div>";
	return str;
}

/*
 * 发送消息
 * */
function ssMsg(){
	var msgid = $("#edit-msgid").val();//短信模板编号
	var oid = $(".idhide").val();
	var result = $(".msg-content").val();
	var phone = $(".msgphone").val();
	//提交数据
	$.post("bdmsg.do",{id:oid,msgid:msgid,phone:phone,result:result},function(json){
		var msg = json.msg;
		if(msg == "1"){
			zeroModal.success("消息发送成功！");
		}else{
			zeroModal.error("消息发送失败！");
		}
	},"json");
}
