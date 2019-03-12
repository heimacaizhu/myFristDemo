$(document).ready(function(){
	//加载组件
	bdaddDome();
	//相关操作
	bdaddOperate();
	//初始化客户数据
	customerDateInit();
});

/*
 * 加载组件
 */
function bdaddDome(){
	//关闭订单条目显示
	$(".addbdd").hide();
	//加载表单组件
	layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form()
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;
	});
}

/*
 * 相关操作
 */
function bdaddOperate(){
	//点击添加报单
	$(".dbadd-btn").click(function(e){
		e.preventDefault();
		addApply();
	});
	//点击添加客户报单条目
	$(".additembtn").click(function(){
		var ai_id = $(".ad").text();
		createDome(this,ai_id);
	});
}

/*
 * 添加客户报单
 * */
function addApply(){
	var cu_id = $("select[name=cu_id]").val();
	$.post("addaply.do",{cuId:cu_id},function(json){
		if(json.msg!=""&&json.msg!="2"){
			//添加成功
			zeroModal.success("客户报单添加成功");
			//显示计划申请号
			$(".ad").text(json.msg);
			//显示
			$(".addbdd").show();
		}else{
			//添加失败
			zeroModal.error("客户报单添加失败");
		}
	},"json");
}

/*
 * 初始化客户数据
 * */
function customerDateInit(){
	$.post("allcu.do",function(json){
		var str = "";
		$.each(json,function(i,item){
			str += "<option value='"+item.cu_id+"'>"+item.cu_name+"</option>";
		});
		$("#cuidd option").remove();
		$("#cuidd").append(str);
	},"json");
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
		$("#tbody").html(str);
	},"json");
}

/*
 * 创建弹出框
 */
function createDome(btn,aaid){
	var htmlstr = createHtmlstr(aaid);
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
		        ,btn: ['添加', '取消']
		        ,moveType: 1 //拖拽模式，0或者1
		        ,content: htmlstr
		        ,success: function(layero){
		          var btn = layero.find('.layui-layer-btn');
		          btn.css('text-align', 'center');
		          var qd_btn =  btn.find('.layui-layer-btn0');
		          $(qd_btn).click(function(){
		          	//点击保存
		          	bdItemSave();
		          });
		        }
		      });
		    }
        };
        var type = $(btn).data('type');
		active[type] ? active[type].call(this) : '';
		//初始化下拉列表
		getGoods();
   	});
}

/*
 * 构造弹出层htmlstr
 */
function createHtmlstr(aiid){
	var str = "<div style='padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;'>";
	str += "<input type='hidden' value='"+aiid+"' name='ai_id' />";
	str += "<form class='layui-form'>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>选择产品</label>";
	str += "<div class='layui-input-inline'>";
	str += "<select id='edit-cutype' name='gi_id' style='text-align: center;display: block;width:190px;line-height:38px;padding-left:10px;height:38px;border-radius: 2px;'>";
	str += "</select>";
	str += "</div>";
	str += "</div>";
	str += "<div class='layui-form-item'>";
	str += "<label class='layui-form-label'>数量</label>";
	str += "<div class='layui-input-inline'>";
	str += "<input name='num' autocomplete='off' class='layui-input' type='text'>";
	str += "</div>";
	str += "</div>";
	str += "</form>";
	str += "</div>";
	return str;
}

/**
 * 获取所有的产品
 */
function getGoods(){
	$.post("showgoods.do",function(json){
		var str = "";
		$.each(json,function(i,item){
			str += ("<option value='"+item.gi_id+"'>"+item.g_name+"("+item.fabrics+"  "+item.color+" "+item.size+")</option>");
		});
		$("#edit-cutype").html(str);
	},"json");
}

/*
 * 添加条目
 */
function bdItemSave(){
	var ai_id = $("input[name=ai_id]").val();
	var gi_id = $("select[name=gi_id]").val();
	var num = $("input[name=num]").val();
	//提交数据
	$.post("addapplyitem.do",{aid:ai_id,gid:gi_id,num:num},function(json){
		var msg = json.msg;
		if (msg == "1") {
			zeroModal.success("添加成功");
			showapItem($(".ad").text());
		} else{
			zeroModal.error("添加失败");
		}
	},"json");
}
