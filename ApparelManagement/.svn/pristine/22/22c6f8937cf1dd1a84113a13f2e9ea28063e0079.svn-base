$(document).ready(function(){
	$(".type-btn").click(function(){
		getCountByType();
	});
	$(".region-btn").click(function(){
		getRegionCustomer();
	});
	getRegionCustomer();
});


/*
 * 获取区域客户数据
 */
function getRegionCustomer(){
	$.post("countByRegion.do",function(json){
		var allnum = json.nums;
		var data = new Array();
		$.each(json.cnums,function(i,item){
			var str = {};
			str["name"] = item.r_name;
			str["value"] = item.sn;
		  data.push(str);
		});
		showRegion(data);
	},"json");
}

/*
 * 显示区域客户统计信息
 * */
function showRegion(dd){
	option = {
		    title : {
		        text: '客户分布图',
		        /*subtext: '纯属虚构',*/
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item'
		    },
		    legend: {
		        orient: 'vertical',
		        x:'left',
		        data:['客户数量']
		    },
		    dataRange: {
		        x: 'left',
		        y: 'bottom',
		        splitList: [
		            {start: 1500},
		            {start: 500, end: 1500},
		            {start: 101, end: 500},
		            {start: 20, end: 100},
		            {start: 10, end: 20, label: '10 到 20'},
		            {start: 5, end: 5, label: '5', color: 'black'},
		            {end: 10}
		        ],
		        color: ['#E0022B', '#E09107', '#A3E00B']
		    },
		    toolbox: {
		        show: true,
		        orient : 'vertical',
		        x: 'right',
		        y: 'center',
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    roamController: {
		        show: true,
		        x: 'right',
		        mapTypeControl: {
		            'china': true
		        }
		    },
		    series : [
		        {
		            name: '客户数量',
		            type: 'map',
		            mapType: 'china',
		            roam: false,
		            itemStyle:{
		                normal:{
		                    label:{
		                        show:true,
		                        textStyle: {
		                           color: "rgb(249, 249, 249)"
		                        }
		                    }
		                },
		                emphasis:{label:{show:true}}
		            },
		            data:dd
		        }
		    ]};
	var myChart = echarts.init(document.getElementById('main'));
	myChart.setOption(option);
}

/*
 * 根据客户类型统计数据
 * */
function getCountByType(){
	$.post("countByType.do",function(json){
		var dd = [];
		$.each(json.cnums,function(i,item){
			var d = {};
			d["value"] = item.sn;
			if(item.cu_type=="1"){
				d["name"] = "配套";
			}else if(item.cu_type=="2"){
				d["name"] = "最终";
			}else{
				d["name"] = "销售";
			}
			dd.push(d);
		});
		showCountByType(dd);
	},"json");
}

/**
 * 显示类型统计图表
 */
function showCountByType(dd){
	option = {
		    title : {
		        text: '客户类型',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: ['配套','最终','销售']
		    },
		    series : [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:dd,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]};
	var myChart = echarts.init(document.getElementById('main'));
	myChart.setOption(option);
}
