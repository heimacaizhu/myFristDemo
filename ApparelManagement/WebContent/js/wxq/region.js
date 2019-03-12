$(document).ready(function(){
	//初始化
	regionDomeInit(1,"");
	//相关操作
	regionOption();
});

/*
 * 区域组件初始化
 */
function regionDomeInit(curr,rName){
	//初始化分页组件
	$.post("onRegionPage.do",{rName:rName,page:curr||1,rows:5},function(json){
		showDate(json,curr);
	},"json");
}

/**
 * 解析数据
 */
function showDate(json,curr){
	var pages = ((json.total%5)==0)?(json.total/5):(json.total/5+1);
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
		    		var rName = $("input[name=r_name]").val();
		    		if(!cheackStr(rName)){
		    			rName = "";
		    		}
		    		regionDomeInit(obj.curr,"");
				}
		    }
		});
	});
	var str = "";
	$.each(json.rows,function(i,item){
		str += "<tr>";
		str += "<td><input type='checkbox' class='cheack cheack-one'/></td>";
		str += "<td>"+item.r_id+"</td>";
		str += "<td>"+item.r_name+"</td>";
		if(item.pName==""||item.pName==undefined){
			str += "<td>(无)</td>";
		}else{
			str += "<td>"+item.pName+"</td>";
		}
		str += "<td>";
		str += "<button class='layui-btn layui-btn-small re-del'>删除</button>";
		str += "<button class='layui-btn layui-btn-small re-edit'>编辑</button>";
		str += "</td>";
	});
	$("#region-tbody").children().remove();
	$("#region-tbody").html(str);
}

/*
 * 相关操作
 */
function regionOption(){
	//点击搜索
	$(".region-search-btn").click(function(e){
		e.preventDefault();
		var r_name = $("input[name=r_name]").val();
		if(r_name == ""){
			zeroModal.alert("请先输入区域名再进行搜索");
			return false;
		}
		if (!cheackStr(r_name)) {
			zeroModal.alert("请不要在搜索框中输入非法区域名!")
			return false;
		}
		//提交数据
		regionDomeInit(1,r_name);
	});
	//点击删除
	$("#region-tbody").delegate(".re-del","click",function(){
		var r_id = $(this).parents("tr").children(":nth-of-type(2)").text();
		zeroModal.confirm("您确定要删除该区域嘛?",function(){
			$.post("delteRegion.do",{ids:r_id},function(json){
				var msg = json.msg;
				if (msg == "1") {
					zeroModal.success("删除成功!");
				} else{
					zeroModal.error("删除失败!");
				}
			});
			regionDomeInit(1,"");
		});
	});
	//点击全选
	$(".cheack-all").click(function(){
		var ches = $(".cheack");
		if (this.checked) {
			$.each(ches, function(i,item) {
				$(this).prop("checked",true);
			});
		} else{
			$.each(ches, function(i,item) {
				$(this).prop("checked",false);
			});
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
	//点击批量删除
	$(".del-more").click(function(){
		var ches = $(".cheack-one:checked");
		if (ches.length==0) {
			zeroModal.alert("请先选择要删除的区域!");
			return false;
		}
		zeroModal.confirm("你确定要删除这些区域嘛?",function(){
			var rids = "";
			$.each(ches, function(i,item) {
				if (i==(ches.length-1)) {
					rids += $(this).parent().next().text();
				} else{
					rids += ($(this).parent().next().text()+",");
				}
			});
			//提交数据
			$.post("delteRegion.do",{ids:rids},function(json){
				var msg = json.msg;
				if (msg == "1") {
					zeroModal.success("删除成功!");
				} else{
					zeroModal.error("删除失败!");
				}
			},"json");
			regionDomeInit(1,"");
		});
	});
	//点击编辑
	$("#region-tbody").delegate('.re-edit','click',function(){
		var rid = $(this).parents("tr").children(":nth-of-type(2)").text();
		var rname = $(this).parents("tr").children(":nth-of-type(3)").text();
		var pname = $(this).parents("tr").children(":nth-of-type(4)").text();
		var txt = "<div>";
		txt += "<input type='hidden' id='r_id' value='"+rid+"'/>";
		txt += "<div><span>区域名</span><input type='text' value='"+rname+"' class='edit-rname'/></div>";
		txt += "<div><span>父区域</span><select class='edit_pid'>";
		txt += "</select></div></div>";
		var option = {
			title: "编辑区域内容",
			btn: parseInt("0011",2),
			onOk: function(){
				//点击保存
				var r_id = $("#r_id").val();
				var r_pid = $(".edit_pid").val();
				var r_name = $(".edit-rname").val().trim();
				var rpname = $(".edit_pid option:checked").text().trim();
				if (pname==rpname&&rname==r_name) {
					zeroModal.alert("您的区域信息没有改变");
					return false;
				}
				//检测输入的区域名
				if (r_name == "") {
					zeroModal.alert("请先输入区域名");
					return false;
				}
				if (!cheackStr(r_name)) {
					zeroModal.alert("请不要输入非法字符串!");
					return false;
				}
				//提交数据
				$.post("editRegion.do",{rId:r_id,rPid:r_pid,rName:r_name},function(json){
					var msg = json.msg;
					if (msg == "1") {
						zeroModal.success("修改成功!");
					}else if(msg == "2"){
						zeroModal.alert("该区域已经存在");
					} else{
						zeroModal.error("修改失败!");
					}
				},"json");
				regionDomeInit(1,"");
			}
		}
		window.wxc.xcConfirm(txt, "custom", option);
		editSelectHtml();
	});
	//点击新增区域
	$(".add-region-btn").click(function(){
		var txt = "<div>";
		txt += "<div><span>区域名</span><input type='text' class='add-rname'/></div>";
		txt += "<div><span>父区域</span><select class='add_pid'>";
		txt += "<option value='0'>根区域</option>";
		txt += "</select></div></div>";
		var option = {
			title: "新增区域",
			btn: parseInt("0011",2),
			onOk: function(){
				//点击保存
				var r_pid = $(".add_pid").val();
				var r_name = $(".add-rname").val();
				//检测输入的区域名
				if (r_name == "") {
					zeroModal.alert("请先输入区域名");
					return false;
				}
				if (!cheackStr(r_name)) {
					zeroModal.alert("请不要输入非法字符串!");
					return false;
				}
				//提交数据
				$.post("addregion.do",{rPid:r_pid,rName:r_name},function(json){
					var msg = json.msg;
					if (msg == "1") {
						zeroModal.success("添加成功!");
					} else if(msg == "2"){
						zeroModal.alert("该区域已经存在");
					}else{
						zeroModal.error("添加失败!");
					}
				},"json");
				regionDomeInit(1,"");
			}
		}
		window.wxc.xcConfirm(txt, "custom", option);
		selectHtml();
	});
}

/*
 * 构造区域下拉选择框
 */
function selectHtml(){
	//请求数据
	$.post("nopageRegion.do",function(json){
		var str = "";
		$.each(json,function(i,item){
			str += "<option value='"+item.r_id+"'>"+item.r_name+"</option>";
		});
		$(".add_pid option:gt(0)").remove();
		$(".add_pid").append(str);
	},"json");
}

/**
 * 编辑框下拉列表
 * */
function editSelectHtml(){
	$.post("nopageRegion.do",function(json){
		var str = "";
		$.each(json,function(i,item){
			str += "<option value='"+item.rId+"'>"+item.rName+"</option>";
		});
		$(".edit_pid option:gt(0)").remove();
		$(".edit_pid").append(str);
	},"json");
}
