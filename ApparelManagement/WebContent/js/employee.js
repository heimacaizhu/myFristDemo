/**
 * Created by Administrator on 2016/12/5.
 */
$(function() {
	// 初始化layui模块
	initlayui();
	// 增加员工
	addemp();
	// 增加验证
	addemployee();
	// 修改验证
	updateemp();
	//搜索
	seachemp();

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
	$.post("getAllEmp.do", {
		page : curr || 1,
		rows : 10
	}, function(data) {
		laypage({
			cont : 'emp_page',
			pages : data.pages,
			curr : curr || 1,
			skip : true,
			jump : function(obj, first) {
				if (!first) {
					setpage(obj.curr);
				}
			}
		});
		loadData(data);
	});
}

// 加载数据
function loadData(data) {
	$(".emp tbody tr").remove();
	$(data.rows).each(function(i, v) {
		var str = "";
		str += " <tr>";
		str += "<td>" + (i + 1) + "</td>";
		str += "<td>" + v.employeeInfo.emName + "</td>";
		str += "<td>" + v.employeeInfo.emPhone + "</td>";
		str += "<td>" + v.emAcount + "</td>";
		str += "<td>" + empType(v.emType) + "</td>";
		str += "<td>" + empState(v.emState) + "</td>";
		str += "<td>";
		str += "<a href='javascript:delemp(" + v.emId + ");'>删除</a>";
		str += "<span>|</span>";
		str += "<a href='javascript:modifymsg(" + v.emId + ");'>修改详情</a>";
		str += "</td>";
		str += "</tr>";
		$(".emp tbody").append(str);
	});
}

// 对员工类型进行转换
function empType(type) {
	if (type == 1) {
		return "管理员";
	} else if (type == 2) {
		return "客服人员";
	} else if (type == 3) {
		return "办公室人员";
	} else if (type == 4) {
		return "车间主任";
	} else if (type == 5) {
		return "仓库管理员";
	} else {
		return "其他";
	}
}

// 对员工状态进行转换
function empState(state) {
	if (state == 0) {
		return "不可用";
	} else if (state == 1) {
		return "可用";
	} else {
		return "其他";
	}
}

// 搜索
function seachemp() {
	$("body").delegate(".btn_search_emp", "click", function(e) {
		e.preventDefault();
		seachpage();
	});
}

//搜索分页
function seachpage(curr){
	$.post("getEmpByLike.do", {
		page : curr || 1,
		rows : 10,
		p:$(".in_search_emp").val()
	}, function(data) {
		laypage({
			cont : 'emp_page',
			pages : data.pages,
			curr : curr || 1,
			skip : true,
			jump : function(obj, first) {
				if (!first) {
					seachpage(obj.curr);
				}
			}
		});
		loadData(data);
	});
}

// 点击删除
function delemp(id) {
	// 询问框
	layer.confirm('是否确认删除？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		// 此处异步操作
		$.post("delEmp.do", {
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
				setpage();
			}
		});
	}, function() {
	});
}

// 增加验证
function addemployee() {

	$("body").delegate(
			".repwd",
			"blur",
			function() {
				if ($(this).val().trim() == ""
						|| $(this).val().trim() != $(".newpwd").val().trim()) {
					layer.msg('密码不一致');
				}
			});

	$("body").delegate(".btn_add", "click", function(e) {
		e.preventDefault();
		if ($(".newpwd").val().trim() != "" && $(".repwd").val().trim() != "") {
			if ($(".repwd").val().trim() == $(".newpwd").val().trim()) {
				// 提交
				$.post("addEmp.do", {
					name : $(".emp_name").val(),
					phone : $(".emp_phone").val(),
					type : $(".emp_type").val(),
					pwd : $(".repwd").val()
				}, function(data) {
					if (data.result == 0) {
						layer.msg('增加失败！');
					} else {
						layer.msg('增加成功！');
						setpage();
					}
				});
			} else {
				layer.msg('密码不一致');
				return true;
			}
		}
	});
}

// 修改验证
function updateemp() {
	$("body").delegate(".btn_modify", "click", function(e) {
		e.preventDefault();
		if ($(".modify_phone").val() != "") {
			$.post("updateEmp.do", {
				phone : $(".modify_phone").val(),
				type : $(".modify_type").val(),
				state : $(".modify_state").val(),
				id : $(".modify_id").val()
			}, function(data) {
				if (data.result == 0) {
					layer.msg('修改失败！');
				} else {
					layer.msg('修改成功！');
					setpage();
				}
			});
		}
	});
}

// 增加员工
function addemp() {
	$("body")
			.delegate(
					".btn_add_emp",
					"click",
					function() {
						// 页面层
						layer
								.open({
									type : 1,
									title : '添加员工',
									skin : 'layui-layer-rim', // 加上边框
									area : [ '420px', '450px' ], // 宽高
									content : '<form class="layui-form" action="">'
											+ '<div class="layui-form-item">'
											+ '<label class="layui-form-label">员工姓名</label>'
											+ '<div class="layui-input-block">'
											+ '<input type="text" lay-verify="required" autocomplete="off" placeholder="请输入员工姓名..." class="layui-input emp_name" style="width: 250px;margin-top: 20px">'
											+ '</div>'
											+ '</div>'
											+ '<div class="layui-form-item">'
											+ '<label class="layui-form-label">联系方式</label>'
											+ '<div class="layui-input-block">'
											+ '<input type="text" lay-verify="required" autocomplete="off" placeholder="请输入新电话..." class="layui-input emp_phone" style="width: 250px;margin-top: 20px">'
											+ '</div>'
											+ '</div>'
											+ '<div class="layui-form-item">'
											+ '<label class="layui-form-label">选择类型</label>'
											+ '<div class="layui-input-block">'
											+ '<select class="emp_type" lay-verify="required" style="width: 250px;height: 40px;display: block;border-color: gainsboro">'
											+ '<option value="1">管理员</option>'
											+ '<option value="2">客服人员</option>'
											+ '<option value="3">办公室人员</option>'
											+ '<option value="4">车间主任</option>'
											+ '<option value="5">仓库管理员</option>'
											+ '</select>'
											+ '</div>'
											+ '</div>'
											+ '<div class="layui-form-item">'
											+ '<label class="layui-form-label">设置密码</label>'
											+ '<div class="layui-input-block">'
											+ '<input type="password"  lay-verify="required"  placeholder="请输入密码..." class="layui-input newpwd" style="width: 250px;margin-top: 20px">'
											+ '</div>'
											+ '</div>'
											+ '<div class="layui-form-item">'
											+ '<label class="layui-form-label">确认密码</label>'
											+ '<div class="layui-input-block">'
											+ '<input type="password"  lay-verify="required"  placeholder="请再次输入密码..." class="layui-input repwd" style="width: 250px;margin-top: 20px">'
											+ '</div>'
											+ '</div>'
											+ '<div style="text-align: center;margin-top: 40px"">'
											+ '<input class="layui-btn layui-btn-radius btn_add" type="submit" value="增加" lay-submit="" style="width: 100px">'
											+ '</div>' + '</form>'
								});
					});
}

// 修改详情信息
function modifymsg(id) {
	$
			.post(
					"getEmpById.do",
					{
						id : id
					},
					function(data) {
						// 页面层
						layer
								.open({
									type : 1,
									title : '修改详情信息',
									skin : 'layui-layer-rim', // 加上边框
									area : [ '420px', '420px' ], // 宽高
									content : '<form class="layui-form" action="">'
											+ '<div class="layui-form-item">'
											+ '<label class="layui-form-label">员工工号</label>'
											+ '<div class="layui-input-block">'
											+ '<input class="modify_id" type="hidden" value="'
											+ id
											+ '">'
											+ '<input type="text" value="'
											+ data.emAcount
											+ '" disabled="disabled" class="layui-input" style="width: 250px;margin-top: 20px">'
											+ '</div>'
											+ '</div>'
											+ '<div class="layui-form-item">'
											+ '<label class="layui-form-label">员工姓名</label>'
											+ '<div class="layui-input-block">'
											+ '<input type="text" value="'
											+ data.employeeInfo.emName
											+ '" disabled="disabled" class="layui-input" style="width: 250px;margin-top: 20px">'
											+ '</div>'
											+ '</div>'
											+ '<div class="layui-form-item">'
											+ '<label class="layui-form-label">联系方式</label>'
											+ '<div class="layui-input-block">'
											+ '<input type="text" value="'
											+ data.employeeInfo.emPhone
											+ '" lay-verify="required" autocomplete="off" class="layui-input modify_phone" style="width: 250px;margin-top: 20px">'
											+ '</div>'
											+ '</div>'
											+ '<div class="layui-form-item">'
											+ '<label class="layui-form-label">选择类型</label>'
											+ '<div class="layui-input-block">'
											+ '<select class="modify_type" lay-verify="required" style="width: 250px;height: 40px;display: block;border-color: gainsboro">'
											+ '<option value="1">管理员</option>'
											+ '<option value="2">客服人员</option>'
											+ '<option value="3">办公室人员</option>'
											+ '<option value="4">车间主任</option>'
											+ '<option value="5">仓库管理员</option>'
											+ '</select>'
											+ '</div>'
											+ '</div>'
											+ '<div class="layui-form-item">'
											+ '<label class="layui-form-label">选择状态</label>'
											+ '<div class="layui-input-block">'
											+ '<select class="modify_state" lay-verify="required" style="width: 250px;height: 40px;display: block;border-color: gainsboro">'
											+ '<option value="1">可用</option>'
											+ '<option value="0">不可用</option>'
											+ '</select>'
											+ '</div>'
											+ '</div>'
											+ '<div style="text-align: center;margin-top: 40px"">'
											+ '<input class="layui-btn layui-btn-radius btn_modify" type="button" value="修改" lay-submit="" style="width: 100px">'
											+ '</div>' + '</form>'
								});
					});
}