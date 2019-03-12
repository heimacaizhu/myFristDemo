/**
 * Created by Administrator on 2016/12/5.
 */
$(function () {
    //初始化layui模块
    initlayui();
    //上传图片接口
    uploadimg();
    //搜索
    btngoodsimgsearch();
});

//上传图片接口
function uploadimg() {
    layui.use('upload', function () {
        layui.upload({
            url: 'upLoadGoodsItemImg.do'
            ,elem:"#upload"
            ,ext: 'jpg|png|gif'
            , success: function (res) {
                if(res.result==0){ 
                	layer.msg('上传失败！');
                }else{
                	$.post("addGoodsImg.do",{url:res.url,id:$("#item_id").val()},function(data){
                		if(data.result==0){
                			layer.msg('上传失败！');
                		}else{
                			layer.msg('上传成功！');
                			seegoodsimg($("#item_id").val());
                		}
                	});
                }
            }
        });
    });
}

//初始化layui模块
function initlayui() {
    layui.use(['laypage', 'layer', 'form', 'element', 'upload'], function () {
        laypage = layui.laypage
            , layer = layui.layer,
            form = layui.form(),
            element = layui.element(),
            upload = layui.upload; //Tab的切换功能，切换事件监听等，需要依赖element模块

        //分页设置
        setpagec();
        setpagey();
    });
}

//分页
function setpagec(curr) {
    $.post("getGoodsItems.do", {
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

//分页
function setpagey(curr) {
    $.post("getGoodsItems.do", {
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
		isimg:"y",
		p:$(".in_search_goodsimg").val().trim()
	}, function(data) {
		laypage({
			cont : 'yc_search',
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
		$(".g_imgsearch").addClass("layui-show");
		loadDataypimg(data);
	});
}

//加载产品数据
function loadDatac(data) {
	$(".g_imgcp tbody tr").remove();
	var str = "";
	$(data.rows).each(function(i, v) {
		$(v.list).each(function(ii, vv) {		
		str += " <tr>";
		str += "<td>" + (i+1) +"-"+ (ii+1) + "</td>";
		str += "<td>" + v.gName + "</td>";
		str += "<td>" + v.brandName + "</td>";
		str += "<td>" + v.fabrics + "</td>";
		str += "<td>" + v.gPrice + "</td>";
		str += "<td>" + vv.color + "</td>";
		str += "<td>"+vv.size+"</td>";
		str += "<td>";
		str += "<a href='javascript:seegoodsimg("+ vv.giId + ");'>查看图片</a>";
		str += "</td>";
		str += "</tr>";
		});
	});
	$(".g_imgcp tbody").append(str);
}

//加载原材料数据
function loadDatay(data) {
	$(".g_imgyc tbody tr").remove();
	var str = "";
	$(data.rows).each(function(i, v) {
		$(v.list).each(function(ii, vv) {		
		str += " <tr>";
		str += "<td>" + (i+1) +"-"+ (ii+1) + "</td>";
		str += "<td>" + v.gName + "</td>";
		str += "<td>" + v.brandName + "</td>";
		str += "<td>" + v.fabrics + "</td>";
		str += "<td>" + v.gPrice + "</td>";
		str += "<td>" + vv.color + "</td>";
		str += "<td>"+vv.size+"</td>";
		str += "<td>";
		str += "<a href='javascript:seegoodsimg("+ vv.giId + ");'>查看图片</a>";
		str += "</td>";
		str += "</tr>";
		});
	});
	$(".g_imgyc tbody").append(str);
}

//点击删除
function delgoodsimg(id) {
    //询问框
    layer.confirm('是否确认删除？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        //此处异步操作
    	$.post("delGoodsImgById.do", {
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
				seegoodsimg($("#item_id").val());
			}
		});
    }, function () {
    });
}

//查看图片
function seegoodsimg(id) {
    $(".gimgarae").show();
    $("#item_id").val(id);
    $(".gpics").remove();
    $.post("getGoodsImgById.do",{id:id},function(data){
    	str="";
    	$(data).each(function(i,v){
    		str+="<div class='gpics'>"
    	    	str+="<img src='source/upload/"+v.imgUrl+"'>";
    	    	str+="<div class='opimg'>";
    	    	str+="<a href='javascript:delgoodsimg("+v.imgId+");'>删除</a>";
    	    	str+="</div>";
    	    	str+="</div>";
    	});
    	$(".gpicsbtn").before(str);
    });
}

//搜索按钮
function btngoodsimgsearch(curr){
	$("body").delegate(".btn_search_goodsimg","click",function(e) {
		e.preventDefault();
		if($(".in_search_goodsimg").val().trim()==""){
			return;
			}
		setpageyp();
	});
}

//加载搜索物品数据
function loadDataypimg(data) {
	$(".g_imgsearch tbody tr").remove();
	var str = "";
	$(data.rows).each(function(i, v) {
		$(v.list).each(function(ii, vv) {		
		str += " <tr>";
		str += "<td>" + (i+1) +"-"+ (ii+1) + "</td>";
		str += "<td>" + v.gName + "</td>";
		str += "<td>" + v.brandName + "</td>";
		str += "<td>" + v.fabrics + "</td>";
		str += "<td>" + v.gPrice + "</td>";
		str += "<td>" + vv.color + "</td>";
		str += "<td>"+vv.size+"</td>";
		str += "<td>";
		str += "<a href='javascript:seegoodsimg("+ vv.giId + ");'>查看图片</a>";
		str += "</td>";
		str += "</tr>";
		});
	});
	$(".g_imgsearch tbody").append(str);
}

