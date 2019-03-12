/**
 * Created by Administrator on 2016/12/5.
 */
$(function() {
	// 初始化layui模块
	initlayui();
	// 增加员工
	addgoods();
	// 增加员工验证
	goodsAdd();
	//查看条目
    showgooditem();
    //物品条目提交到数据库
    goodsItemAdd();
    //修改物品
    modifygoods();
  //修改物品条目
    modifygoodsItem();
    //搜索按钮
    btngoodssearch();

});

// 初始化layui模块
function initlayui() {
	layui.use([ 'laypage', 'layer', 'form', 'element' ], function() {
		laypage = layui.laypage, layer = layui.layer, form = layui.form(),
				element = layui.element(); // Tab的切换功能，切换事件监听等，需要依赖element模块

		// 分页设置
		setpagec();
		setpagey();
	
	});
}

//搜索按钮
function btngoodssearch(){
	$("body").delegate(".btn_search_goods","click",function(e) {
		e.preventDefault();
		if($(".in_search_goods").val().trim()==""){
			return;
			}
		setpageyp();
	});
}

// 分页
function setpagec(curr) {
	$.post("getAllgoods.do", {
		page : curr || 1,
		rows : 10,
		type : 1
	}, function(data) {
		laypage({
			cont : 'cp_page',
			pages : data.pages,
			curr : curr || 1,
			skip : true,
			jump : function(obj, first) {
				if (!first) {
					setpagec(obj.curr);
				}
			}
		});
		loadDatac(data);
	});
}

function setpagey(curr) {
	$.post("getAllgoods.do", {
		page : curr || 1,
		rows : 10,
		type : 2
	}, function(data) {
		laypage({
			cont : 'yc_page',
			pages : data.pages,
			curr : curr || 1,
			skip : true,
			jump : function(obj, first) {
				if (!first) {
					setpagey(obj.curr);
				}
			}
		});
		loadDatay(data);
	});
}

function setpageyp(curr) {
	$.post("getGoodsByLike.do", {
		page : curr || 1,
		rows : 10,
		isimg:"n",
		p:$(".in_search_goods").val().trim()
	}, function(data) {
		laypage({
			cont : 'yp_page',
			pages : data.pages,
			curr : curr || 1,
			skip : true,
			jump : function(obj, first) {
				if (!first) {
					setpageyp(obj.curr);
				}
			}
		});
		$(".ttab li").removeClass("layui-this");
		$(".layui-tab-item").removeClass("layui-show");
		$("#tab_search").addClass("layui-this");
		$(".g_search").addClass("layui-show");
		loadDatayp(data);
	});
}

//对物品状态进行转换
function goodsState(state) {
	if (state == 0) {
		return "不可用";
	} else if (state == 1) {
		return "新品";
	} else if (state == 2) {
		return "老产品 ";
	}else{
		return "其他 ";
	}
}

//对物品条目状态进行转换
function goodsStateitem(state) {
	if (state == 0) {
		return "不可用";
	} else if (state == 1) {
		return "可用";
	} else{
		return "其他 ";
	}
}

//物品类型转化
function goodsType(type){
	if (type == 1) {
		return "产品";
	} else if (type == 2) {
		return "原材料";
	} else{
		return "其他 ";
	}
}

//加载搜索物品数据
function loadDatayp(data) {
	$(".g_search tbody tr").remove();
	var str = "";
	$(data.rows).each(function(i, v) {
		str += " <tr>";
		str += "<td><span class='btn_goodsitem' value='1'>+</span></td>";
		str += "<td>" + (i + 1) + "</td>";
		str += "<td>" + v.gName + "</td>";
		str += "<td>" + v.brandName + "</td>";
		str += "<td>" + v.fabrics + "</td>";
		str += "<td>" + v.gPrice + "</td>";
		str += "<td>" + goodsType(v.gType) + "</td>";
		str += "<td>" + goodsState(v.gState) + "</td>";
		str += "<td>";
		str += "<a href='javascript:delgoods("+ v.gId + ");'>删除</a>";
		str += "<span>|</span>";
		str += "<a href='javascript:modifymsg("+ v.gId + ");'>修改详情</a>";
		str += "<span>|</span>";
		str += "<a href='javascript:addgoodsitem("+ v.gId + ");'>增加条目</a>";
		str += "</td>";
		str += "</tr>";
		str += "<tr class='titem' style='display: none'>";
		str += "<td colspan='9'>";
		str += "<div class='item' style='display: none'>";
		str += "<div>";
		str += " <table class='layui-table' lay-skin='line'>";
		str += " <tbody>";
		$(v.list).each(function(ii, vv) {				
			str += "<tr>";
			str += "<td>"+(ii+1)+"</td>";
			str += "<td><img src='source/upload/"+setdefaultimg(vv)+"' style='height: 40px;width: 40px'></td>";
			str += " <td>"+vv.color+"</td>";
			str += " <td>"+vv.size+"</td>";
			str += "<td>"+goodsStateitem(vv.giState)+"</td>";
			str += "<td>";
			str += "<a href='javascript:delgoodsitem("+ vv.giId + ");'>删除</a>";
			str += "<span>|</span>";
			str += "<a href='javascript:modifymsgitem("+ vv.giId + ");'>修改</a>";
			str += "</td>";
			str += "  </tr>";
				});
		str += " </tbody>"
		str += "</table>";
		str += "</div>";
		str += "</div>";
		str += "</td>";
		str += "</tr>";
		});
	$(".g_search tbody").append(str);
}

// 加载产品数据
function loadDatac(data) {
	$(".g_cp tbody tr").remove();
	var str = "";
	$(data.rows).each(function(i, v) {
		str += " <tr>";
		str += "<td><span class='btn_goodsitem' value='1'>+</span></td>";
		str += "<td>" + (i + 1) + "</td>";
		str += "<td>" + v.gName + "</td>";
		str += "<td>" + v.brandName + "</td>";
		str += "<td>" + v.fabrics + "</td>";
		str += "<td>" + v.gPrice + "</td>";
		str += "<td>" + goodsState(v.gState) + "</td>";
		str += "<td>";
		str += "<a href='javascript:delgoods("+ v.gId + ");'>删除</a>";
		str += "<span>|</span>";
		str += "<a href='javascript:modifymsg("+ v.gId + ");'>修改详情</a>";
		str += "<span>|</span>";
		str += "<a href='javascript:addgoodsitem("+ v.gId + ");'>增加条目</a>";
		str += "</td>";
		str += "</tr>";
		str += "<tr class='titem' style='display: none'>";
		str += "<td colspan='8'>";
		str += "<div class='item' style='display: none'>";
		str += "<div>";
		str += " <table class='layui-table' lay-skin='line'>";
		str += " <tbody>";
		$(v.list).each(function(ii, vv) {				
			str += "<tr>";
			str += "<td>"+(ii+1)+"</td>";
			str += "<td><img src='source/upload/"+setdefaultimg(vv)+"' style='height: 40px;width: 40px'></td>";
			str += " <td>"+vv.color+"</td>";
			str += " <td>"+vv.size+"</td>";
			str += "<td>"+goodsStateitem(vv.giState)+"</td>";
			str += "<td>";
			str += "<a href='javascript:delgoodsitem("+ vv.giId + ");'>删除</a>";
			str += "<span>|</span>";
			str += "<a href='javascript:modifymsgitem("+ vv.giId + ");'>修改</a>";
			str += "</td>";
			str += "  </tr>";
				});
		str += " </tbody>"
		str += "</table>";
		str += "</div>";
		str += "</div>";
		str += "</td>";
		str += "</tr>";
		});
	$(".g_cp tbody").append(str);
}

//加载原材料数据
function loadDatay(data) {
	$(".g_yc tbody tr").remove();
	var str = "";
	$(data.rows).each(function(i, v) {
		str += " <tr>";
		str += "<td><span class='btn_goodsitem' value='1'>+</span></td>";
		str += "<td>" + (i + 1) + "</td>";
		str += "<td>" + v.gName + "</td>";
		str += "<td>" + v.brandName + "</td>";
		str += "<td>" + v.fabrics + "</td>";
		str += "<td>" + v.gPrice + "</td>";
		str += "<td>" + goodsState(v.gState) + "</td>";
		str += "<td>";
		str += "<a href='javascript:delgoods("+ v.gId + ");'>删除</a>";
		str += "<span>|</span>";
		str += "<a href='javascript:modifymsg("+ v.gId + ");'>修改</a>";
		str += "<span>|</span>";
		str += "<a href='javascript:addgoodsitem("+ v.gId + ");'>增加条目</a>";
		str += "</td>";
		str += "</tr>";
		str += "<tr class='titem' style='display: none'>";
		str += "<td colspan='8'>";
		str += "<div class='item' style='display: none'>";
		str += "<div>";
		str += " <table class='layui-table' lay-skin='line'>";
		str += " <tbody>";
		$(v.list).each(function(ii, vv) {				
			str += "<tr>";
			str += "<td>"+(ii+1)+"</td>";
			str += "<td><img src='source/upload/"+setdefaultimg(vv)+"' style='height: 40px;width: 40px'></td>";
			str += " <td>"+vv.color+"</td>";
			str += " <td>"+vv.size+"</td>";
			str += "<td>"+goodsStateitem(vv.giState)+"</td>";
			str += " <td>";
			str += "<a href='javascript:delgoodsitem("+ vv.giId + ");'>删除</a>";
			str += "<span>|</span>";
			str += "<a href='javascript:modifymsgitem("+ vv.giId + ");'>修改</a>";
			str+="</td>";
			str += "  </tr>";
				});
		str += " </tbody>"
		str += "</table>";
		str += "</div>";
		str += "</div>";
		str += "</td>";
		str += "</tr>";
		});
	$(".g_yc tbody").append(str);
}

//没有图片的时候设置默认图片
function setdefaultimg(vv){
	if(typeof(vv.imgList[0]) == "undefined"){
		return "img/22.jpg"
	}else{
		return vv.imgList[0].imgUrl;
	}
}

//物品提交到数据库
function goodsAdd() {
	$("body").delegate(".gAdd","click",function(e) {
		e.preventDefault();
		if ($(".g_name").val().trim() != ""
				&& $(".g_fabrics").val().trim() != ""
				&& $(".g_brand").val().trim() != ""
				&& $(".g_price").val().trim() != "") {
			// 提交
			$.post("addGoods.do", {
				type : $(".g_type").val(),
				name : $(".g_name").val(),
				fabrics : $(".g_fabrics").val(),
				brand : $(".g_brand").val(),
				price : $(".g_price").val()
			}, function(data) {
				if (data.result == 0) {
					layer.msg('增加失败！');
				} else {
					layer.msg('增加成功！');
					setpagec();
					setpagey();
				}
			});
		}
	});
}


//物品条目提交到数据库
function goodsItemAdd() {
	$("body").delegate(".giAdd","click",function(e) {
		e.preventDefault();
		if ( $(".gi_color").val().trim() != ""&& $(".gi_size").val().trim() != "") {
			// 提交
			$.post("addGoodsItem.do", {
				id : $(".gi_id").val(),
				color : $(".gi_color").val(),
				size : $(".gi_size").val(),
				state : $(".gi_state").val(),
			}, function(data) {
				if (data.result == 0) {
					layer.msg('增加失败！');
				} else {
					layer.msg('增加成功！');
					setpagec();
					setpagey();
				}
			});
		}
	});
}

//修改物品
function modifygoods(){
	$("body").delegate(".modifygoodsbtn","click",function(e) {
		e.preventDefault();
		if ($(".md_name").val().trim() != ""
				&& $(".md_fabrics").val().trim() != ""
				&& $(".md_brand").val().trim() != ""
				&& $(".md_price").val().trim() != "") {
			// 提交
			$.post("updateGoods.do", {
				id : $(".md_id").val(),
				name : $(".md_name").val(),
				fabrics : $(".md_fabrics").val(),
				brand : $(".md_brand").val(),
				state : $(".md_state").val(),
				price : $(".md_price").val()
			}, function(data) {
				if (data.result == 0) {
					layer.msg('修改失败！');
				} else {
					layer.msg('修改成功！');
					setpagec();
					setpagey();
				}
			});
		}
	});
}

//修改物品条目
function modifygoodsItem(){
	$("body").delegate(".mdi_btn","click",function(e) {
		e.preventDefault();
		if ($(".mdi_color").val().trim() != ""
				&& $(".mdi_size").val().trim() != "") {
			// 提交
			$.post("updateGoodsItem.do", {
				id : $(".mdi_id").val(),
				color : $(".mdi_color").val(),
				size : $(".mdi_size").val(),
				state : $(".mdi_state").val()
			}, function(data) {
				if (data.result == 0) {
					layer.msg('修改失败！');
				} else {
					layer.msg('修改成功！');
					setpagec();
					setpagey();
				}
			});
		}
	});
}


// 点击删除物品
function delgoods(id) {
	// 询问框
	layer.confirm('所有和该物品相关的条目和图片都将被删除？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		// 此处异步操作
		$.post("delGoods.do", {
			id : id
		}, function(data) {
			if (data.result == 0) {
				layer.msg('删除失败', {
					icon : 1,
					time : 2000
				});
			} else {
				layer.msg('删除成功', {
					icon : 1,
					time : 2000
				});
				setpagec();
				setpagey();
			}
		});
	}, function() {
	});
}

//点击删除物品条目
function delgoodsitem(id) {
	// 询问框
	layer.confirm('是否确认删除？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		// 此处异步操作
		$.post("delGoodsItem.do", {
			id : id
		}, function(data) {
			if (data.result == 0) {
				layer.msg('删除失败', {
					icon : 1,
					time : 2000
				});
			} else {
				layer.msg('删除成功', {
					icon : 1,
					time : 2000
				});
				setpagec();
				setpagey();
			}
		});
	}, function() {
	});
}

//查看条目
function showgooditem() {

    $("body").delegate(".btn_goodsitem", "click", function () {
        if($(this).attr("value")==1){
            $(this).attr("value",0);
            $(this).text("-");
            $(this).parent().parent().next().show();
            $(this).parent().parent().next().children("td:eq(0)").children(".item").slideDown();
        }else{
            $(this).attr("value",1);
            $(this).text("+");
            $(this).parent().parent().next().children("td:eq(0)").children(".item").slideUp(function(){
                $(this).parent().parent().hide();
            });
        }

    });
}

// 修改物品信息窗口
function modifymsg(id) {
	$.post("getGoodsById.do",{id:id},function(data){
		// 页面层
		layer.open({
				type : 1,
				title : '修改物品信息',
				skin : 'layui-layer-rim', // 加上边框
				area : [ '420px', '450px' ], // 宽高
				content : '<form class="layui-form" action="">'
						+ '<div class="layui-form-item">'
						+ '<label class="layui-form-label">物品名称</label>'
						+ '<div class="layui-input-block">'
						+ '<input class="md_id" type="hidden" value="'+id+'">'
						+ '<input type="text" lay-verify="required" class="layui-input md_name" value="'+data.gName+'" placeholder="输入物品名称..." style="width: 250px;margin-top: 20px">'
						+ '</div>'
						+ '</div>'
						+ '<div class="layui-form-item">'
						+ '<label class="layui-form-label">物品品牌</label>'
						+ '<div class="layui-input-block">'
						+ '<input type="text" lay-verify="required" class="layui-input md_brand" value="'+data.brandName+'" placeholder="输入物品品牌..." style="width: 250px;margin-top: 20px">'
						+ '</div>'
						+ '</div>'
						+ '<div class="layui-form-item">'
						+ '<label class="layui-form-label">材料</label>'
						+ '<div class="layui-input-block">'
						+ '<input type="text" lay-verify="required" autocomplete="off" class="layui-input md_fabrics" value="'+data.fabrics+'" placeholder="输入物品别名..." style="width: 250px;margin-top: 20px">'
						+ '</div>'
						+ '</div>'
						+ '<div class="layui-form-item">'
						+ '<label class="layui-form-label">价格</label>'
						+ '<div class="layui-input-block">'
						+ '<input type="text" lay-verify="required" autocomplete="off" class="layui-input md_price" value="'+data.gPrice+'" placeholder="输入物品价格..." style="width: 250px;margin-top: 20px">'
						+ '</div>'
						+ '</div>'
						+ '<div class="layui-form-item">'
						+ '<label class="layui-form-label">产品状态</label>'
						+ '<div class="layui-input-block">'
						+ '<select class="md_state" lay-verify="required" style="width: 250px;height: 40px;display: block;border-color: gainsboro">'
						+ '<option value="0">可用</option>'
						+ '<option value="1">新品</option>'
						+ '<option value="2">老品牌</option>'
						+ '</select>'
						+ '</div>'
						+ '</div>'
						+ '<div style="text-align: center;margin-top: 40px"">'
						+ '<input class="layui-btn layui-btn-radius modifygoodsbtn" type="submit" value="修改" lay-submit="" style="width: 100px">'
						+ '</div>' + '</form>'
				});
	});
}

//修改物品条目信息窗口
function modifymsgitem(id) {
	$.post("getGoodsItemById.do",{id:id},function(data){
	// 页面层
	layer.open({
				type : 1,
				title : '修改物品信息',
				skin : 'layui-layer-rim', // 加上边框
				area : [ '420px', '350px' ], // 宽高
				content : '<form class="layui-form" action="">'
						+ '<div class="layui-form-item">'
						+ '<label class="layui-form-label">颜色</label>'
						+ '<div class="layui-input-block">'
						+ '<input class="mdi_id" type="hidden" value="'+id+'">'
						+ '<input type="text" value="'+data.list[0].color+'" lay-verify="title" autocomplete="off" class="layui-input mdi_color" placeholder="输入物品颜色..." style="width: 250px;margin-top: 20px">'
						+ '</div>'
						+ '</div>'
						+ '<div class="layui-form-item">'
						+ '<label class="layui-form-label">尺寸</label>'
						+ '<div class="layui-input-block">'
						+ '<input type="text" value="'+data.list[0].size+'" lay-verify="title" autocomplete="off" class="layui-input mdi_size" placeholder="输入物品尺寸（S、M、L...）"  style="width: 250px;margin-top: 20px">'
						+ '</div>'
						+ '</div>'
						+ '<div class="layui-form-item">'
						+ '<label class="layui-form-label">产品状态</label>'
						+ '<div class="layui-input-block">'
						+ '<select class="mdi_state" lay-verify="required" style="width: 250px;height: 40px;display: block;border-color: gainsboro">'
						+ '<option value="1">可用</option>'
						+ '<option value="0">不可用</option>'
						+ '</select>'
						+ '</div>'
						+ '</div>'
						+ '<div style="text-align: center;margin-top: 40px"">'
						+ '<button class="layui-btn layui-btn-radius mdi_btn" style="width: 100px">修改</button>'
						+ '</div>' + '</form>'
			});
	});
}

//增加物品窗口
function addgoods() {
	$("body").delegate(".btn_add_goods","click",
function() {
	// 页面层
	layer.open({
	type : 1,
	title : '添加物品',
	skin : 'layui-layer-rim', // 加上边框
	area : [ '420px', '500px' ], // 宽高
	content : '<form class="layui-form" action="">'
			+ '<div class="layui-form-item">'
			+ '<label class="layui-form-label">选择类型</label>'
			+ '<div class="layui-input-block">'
			+ '<select class="g_type" lay-verify="required" style="width: 250px;height: 40px;margin-top: 20px;display: block;border-color: gainsboro">'
			+ '<option value="1">产品</option>'
			+ '<option value="2">原材料</option>'
			+ '</select>'
			+ '</div>'
			+ '</div>'
			+ '<div class="layui-form-item">'
			+ '<label class="layui-form-label">物品名</label>'
			+ '<div class="layui-input-block">'
			+ '<input type="text" lay-verify="required" autocomplete="off" placeholder="请输入物品名..." class="layui-input g_name" style="width: 250px;margin-top: 20px">'
			+ '</div>'
			+ '</div>'
			+ '<div class="layui-form-item">'
			+ '<label class="layui-form-label">材料</label>'
			+ '<div class="layui-input-block">'
			+ '<input type="text" lay-verify="required" autocomplete="off" placeholder="请输入材料..." class="layui-input g_fabrics" style="width: 250px;margin-top: 20px">'
			+ '</div>'
			+ '</div>'
			+ '<div class="layui-form-item">'
			+ '<label class="layui-form-label">品牌</label>'
			+ '<div class="layui-input-block">'
			+ '<input type="text" lay-verify="required" autocomplete="off" placeholder="请输入品牌..." class="layui-input g_brand" style="width: 250px;margin-top: 20px">'
			+ '</div>'
			+ '</div>'
			+ '<div class="layui-form-item">'
			+ '<label class="layui-form-label">价格</label>'
			+ '<div class="layui-input-block">'
			+ '<input type="text" lay-verify="required" autocomplete="off" placeholder="请输入价格..." class="layui-input g_price" style="width: 250px;margin-top: 20px">'
			+ '</div>'
			+ '</div>'
			+ '<div class="layui-form-item">'
			+ '<label class="layui-form-label">选择类型</label>'
			+ '<div class="layui-input-block">'
			+ '<select class="g_state" lay-verify="required" style="width: 250px;height: 40px;display: block;border-color: gainsboro">'
			+ '<option value="1">不可用</option>'
			+ '<option value="2">新品</option>'
			+ '<option value="3">老品牌</option>'
			+ '</select>'
			+ '</div>'
			+ '</div>'
			+ '<div style="text-align: center;margin-top: 40px"">'
			+ '<button class="layui-btn layui-btn-radius gAdd" lay-submit="" style="width: 100px">增加</button>'
			+ '</div>' + '</form>'
			});
});
}

//增加物品条目窗口
function addgoodsitem(id) {
	// 页面层
	layer.open({
				type : 1,
				title : '添加物品',
				skin : 'layui-layer-rim', // 加上边框
				area : [ '420px', '350px' ], // 宽高
				content : '<form class="layui-form" action="">'
						+ '<div class="layui-form-item">'
						+ '<label class="layui-form-label">颜色</label>'
						+ '<div class="layui-input-block">'
						+ '<input class="gi_id" type="hidden" value="'+id+'">'
						+ '<input type="text" lay-verify="required" autocomplete="off" class="layui-input gi_color" placeholder="输入物品颜色..." style="width: 250px;margin-top: 20px">'
						+ '</div>'
						+ '</div>'
						+ '<div class="layui-form-item">'
						+ '<label class="layui-form-label">尺寸</label>'
						+ '<div class="layui-input-block">'
						+ '<input type="text" lay-verify="required" autocomplete="off" class="layui-input gi_size" placeholder="输入物品尺寸（S、M、L...）"  style="width: 250px;margin-top: 20px">'
						+ '</div>'
						+ '</div>'
						+ '<div class="layui-form-item">'
						+ '<label class="layui-form-label">状态</label>'
						+ '<div class="layui-input-block">'
						+ '<select class="gi_state" lay-verify="required" style="width: 250px;height: 40px;display: block;border-color: gainsboro">'
						+ '<option value="0">不可用</option>'
						+ '<option value="1">可用</option>'
						+ '</select>'
						+ '</div>'
						+ '</div>'
						+ '<div style="text-align: center;margin-top: 40px"">'
						+ '<button class="layui-btn layui-btn-radius giAdd" lay-submit="" style="width: 100px">增加</button>'
						+ '</div>' + '</form>'
			});
}