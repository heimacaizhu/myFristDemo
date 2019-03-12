/**
 * Created by Administrator on 2016/12/5.
 */
$(function() {
	// 初始化layui模块
	initlayui();
	// 查看条目
	showapplyitem();
});

// 初始化layui模块
function initlayui() {
	layui.use([ 'laypage', 'layedit', 'layer', 'form' ], function() {
		laypage = layui.laypage, layer = layui.layer, layedit = layui.layedit,
				form = layui.form();
		// 分页设置
		setpage();
	});
}

// 分页
function setpage(curr) {
	$.post("getAllApply.do", {
		page : curr || 1,
		rows : 10,
		type : 1
	}, function(data) {
		laypage({
			cont : 'apply_page',
			pages : data.pages,
			curr : curr || 1,
			skip : true,
			jump : function(obj, first) {
				if (!first) {
					setpage(obj.curr);
				}
			}
		});
		loadDataApply(data);
	});
}

// 加载报单数据
function loadDataApply(data) {
	$(".apply tbody tr").remove();
	var str = "";
	$(data.rows)
			.each(
					function(i, v) {
						str += " <tr>";
						str += "<td><span class='btn_applyitem' value='1'>+</span></td>";
						str += "<td>" + (i + 1) + "</td>";
						str += "<td>" + v.customer.cuName + "</td>";
						str += "<td>" + v.applicant.employeeInfo.emName
								+ "</td>";
						str += "<td>" + applyType(v.apType) + "</td>";
						str += "<td>" + v.apDate + "</td>";
						str += "<td>" + applyLever(v.apLever) + "</td>";
						str += "<td>" + applyState(v.apState) + "</td>";
						str += "<td>" + v.remark + "</td>";
						str += "<td>";
						str += "<a href=" + "javascript:lookword('"
								+ v.accessory + "\');>查看文档</a>";
						str += "<span>|</span>";
						str += "<a href=" + "javascript:applypass('" + v.apId
								+ "\');>通过审核</a>";
						str += "<span>|</span>";
						str += "<a href=" + "javascript:applycancle('" + v.apId
								+ "\');>驳回</a>";
						str += "</td>";
						str += "</tr>";
						str += "<tr class='titem' style='display: none'>";
						str += "<td colspan='10'>";
						str += "<div class='item' style='display: none'>";
						str += "<div>";
						str += " <table class='layui-table' lay-skin='line'>";
						str += " <tbody>";
						$(v.list)
								.each(
										function(ii, vv) {
											if(vv.goodsItem!=null){
												str += "<tr>";
												str += "<td>" + (ii + 1) + "</td>";
												str += "<td><img src='source/upload/"
														+ setdefaultimg(vv)
														+ "' style='height: 40px;width: 40px'></td>";
												str += " <td>"
														+ vv.goodsItem.goods.gName
														+ "</td>";
												str += " <td>"
														+ vv.goodsItem.goods.fabrics
														+ "</td>";
												str += " <td>"
														+ vv.goodsItem.goods.brandName
														+ "</td>";
												str += " <td>" + vv.goodsItem.color
														+ "</td>";
												str += " <td>" + vv.goodsItem.size
														+ "</td>";
												str += " <td>" + vv.num + "</td>";
												str += "  </tr>";
											}									
										});
						str += " </tbody>"
						str += "</table>";
						str += "</div>";
						str += "</div>";
						str += "</td>";
						str += "</tr>";
					});
	$(".apply tbody").append(str);
}

// 没有图片的时候设置默认图片
function setdefaultimg(vv) {
	if (typeof (vv.imgList) == "undefined") {
		return "22.jpg"
	} else {
		return vv.imgList[0].imgUrl;
	}
}

// 申请类别转化
function applyType(type) {
	if (type == 1) {
		return "用户报单";
	} else if (type == 2) {
		return " 材料采购计划";
	} else if (type == 3) {
		return "产品生产计划";
	} else {
		return "其他";
	}
}

// 处理级别转化
function applyLever(lever) {
	if (lever == 1) {
		return "加急";
	} else if (lever == 2) {
		return " 普通";
	} else {
		return "其他";
	}
}

// 状态转化
function applyState(state) {
	if (state == 0) {
		return "不可用";
	} else if (state == 1) {
		return "待处理";
	} else if (state == 2) {
		return "未通过审批";
	} else if (state == 3) {
		return "审批通过";
	} else {
		return "其他";
	}
}

// 查看条目
function showapplyitem() {

	$("body").delegate(
			".btn_applyitem",
			"click",
			function() {
				if ($(this).attr("value") == 1) {
					$(this).attr("value", 0);
					$(this).text("-");
					$(this).parent().parent().next().show();
					$(this).parent().parent().next().children("td:eq(0)")
							.children(".item").slideDown();
				} else {
					$(this).attr("value", 1);
					$(this).text("+");
					$(this).parent().parent().next().children("td:eq(0)")
							.children(".item").slideUp(function() {
								$(this).parent().parent().hide();
							});
				}

			});
}

// 查看文档
function lookword(url) {
	location.href="lookWord.do?url=" + url;
}

// 审核通过
function applypass(id) {
	$.post("updateDealApply.do", {
		state : 3,
		id : id
	}, function(data) {
		if (data.result == 0) {
			layer.msg('服务器出错！');
		} else {
			layer.msg('处理成功！');
			setpage();
		}
	});

}

// 审核不通过
function applycancle(id) {
	$.post("updateDealApply.do", {
		state : 2,
		id : id
	}, function(data) {
		if (data.result == 0) {
			layer.msg('服务器出错！');
		} else {
			layer.msg('处理成功！');
			setpage();
		}
	});

}
