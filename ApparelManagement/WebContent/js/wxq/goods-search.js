$(document).ready(function(){
	//控件初始化
	gsDome();
	//点击搜索
	goodsSearch();
});

/*
 * 控件初始化
 */
function gsDome(){
	//表单
	layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form()
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;
	});
	//分页
	layui.use(['laypage', 'layer','layedit','laydate'], function(){
		var laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		layer = layui.layer;
		laypage({
		    cont: 'page',
		    pages: 100,
		    skin: '#1E9FFF',
		    jump:function(obj,first){
		    	//获取当前页
			    var nowpage =  obj.curr;
			    //获取总页数
			    var allpage = obj.pages;
		    	if (first) {
		    		
		    	}else{
		    		/*alert("----");*/
		    	}
		    }
		});
	});
}

/*
 * 点击搜索
 */
function goodsSearch(){
	$(".goods-search-btn").click(function(){
		var g_name = $("input[name=g_name]").val();
		if (g_name == "") {
			zeroModal.alert("请输入产品名再进行搜索!");
			return false;
		}
		if (!cheackStr(g_name)) {
			zeroModal.alert("请不要输入非法字符串!");
			return false;
		}
		//提交数据
		$.post("",{g_name:g_name},function(json){},"json");
	});
}
