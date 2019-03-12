<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据分析</title>
    <script type="text/javascript" src="js/jquery.min-1.11.3.js"></script>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="css/ysh/aCSS.css">
    <style type="text/css">

    </style>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title site-title">
    <legend>数据分析</legend>
</fieldset>

<div class="layui-tab layui-tab-card">
    <ul class="layui-tab-title">
        <li onclick="toStart()" class="layui-this">客户类型统计</li>
        <li onclick="countCUname()">客户订单统计</li>
        <li onclick="countApplicant()">客服报单统计</li>
        <li onclick="countDealPerson()">处理数量统计</li>
    </ul>
    <div class="layui-tab-content" style="height: 550px;">
    	<!-- 客户类型统计 -->
        <div class="layui-tab-item layui-show">
            <div id="main" style="width: 900px;height:600px;"></div>
        </div>
        <!--客户订单统计-->
        <div class="layui-tab-item">

            <div id="main2" style="width: 600px;height:500px;"></div>
        </div>
        <!-- 客服报单统计 -->
        <div class="layui-tab-item">

            <div id="main3" style="width: 600px;height:500px;"></div>
        </div>
        <!-- 处理数量统计 -->
        <div class="layui-tab-item">

            <div id="main4" style="width: 600px;height:500px;"></div>
        </div>
    </div>
</div>
</body>
<script src="layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="js/echarts.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	toStart();
})

function toStart(){
	$.ajax({
		type : "post",
		url : "apply/countCUtype.do",
		dataType : "json",
		success : function(data) {
			var str = [];
			var str01 = [];
			$(data).each(
				function(index, content) {
					var strr = {};
					strr["value"] = content.nums;
					if(content.cu_type == 1){
						strr["name"] = "配套";
						str01.push("配套");
					}else if(content.cu_type == 2){
						strr["name"] = "最终";
						str01.push("最终");
					}else{
						strr["name"] = "销售";
						str01.push("销售");
					}
					str.push(strr);
					str01.push(content.cu_type);
				})

			var myChart = echarts.init(document.getElementById('main'));
			option = {
			    title : {
			        text: '客户类型比例 ',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: str01
			    },
			    series : [
			        {
			            name: '访问来源',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:str,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
			myChart.setOption(option);
		}
	})
}

function countCUname(){
	$.ajax({
		type : "post",
		url : "apply/countCUname.do",
		dataType : "json",
		success : function(data) {
			var str = [];
			var str01 = [];
			$(data).each(
				function(index, content) {
					var strr = {};
					strr["value"] = content.nums;
					strr["name"] = content.cu_name;
					str.push(strr);
					str01.push(content.cu_name);
				})
			var myChart = echarts.init(document.getElementById('main'));
			option = {
				    title : {
				        text: '客户订单统计',
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        x : 'center',
				        y : 'bottom',
				        data:str01
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {
				                show: true,
				                type: ['pie', 'funnel']
				            },
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    series : [
				        {
				            name:'半径模式',
				            type:'pie',
				            radius : [20, 110],
				            center : ['25%', '50%'],
				            roseType : 'radius',
				            label: {
				                normal: {
				                    show: false
				                },
				                emphasis: {
				                    show: true
				                }
				            },
				            lableLine: {
				                normal: {
				                    show: false
				                },
				                emphasis: {
				                    show: true
				                }
				            },
				            data:str
				        },
				        {
				            name:'面积模式',
				            type:'pie',
				            radius : [30, 110],
				            center : ['75%', '50%'],
				            roseType : 'area',
				            data:str
				        }
				    ]
				};
			myChart.setOption(option);
		}
	})
}

function countApplicant(){
	$.ajax({
		type : "post",
		url : "apply/countApplicant.do",
		dataType : "json",
		success : function(data) {
			var str = [];
			var str01 = [];
			$(data).each(
				function(index, content) {
					var strr = {};
					strr["value"] = content.nums;
					strr["name"] = content.em_name;
					str.push(strr);
					str01.push(content.em_name);
				})

			var myChart = echarts.init(document.getElementById('main'));
			

			option = {
			    tooltip: {
			        trigger: 'item',
			        formatter: "{a} <br/>{b}: {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        x: 'left',
			        data:str01
			    },
			    series: [
			        {
			            name:'客服报单统计',
			            type:'pie',
			            radius: ['50%', '70%'],
			            avoidLabelOverlap: false,
			            label: {
			                normal: {
			                    show: false,
			                    position: 'center'
			                },
			                emphasis: {
			                    show: true,
			                    textStyle: {
			                        fontSize: '30',
			                        fontWeight: 'bold'
			                    }
			                }
			            },
			            labelLine: {
			                normal: {
			                    show: false
			                }
			            },
			            data:str
			        }
			    ]
			};

			myChart.setOption(option);
		}
	})
}

function countDealPerson(){
	$.ajax({
		type : "post",
		url : "apply/countDealPerson.do",
		dataType : "json",
		success : function(data) {
			var str = [];
			var str01 = [];
			$(data).each(
				function(index, content) {
					var strr = {};
					strr["value"] = content.nums;
					strr["name"] = content.em_name;
					str.push(strr);
					str01.push(content.em_name);
				})

			var myChart = echarts.init(document.getElementById('main'));
			option = {
			    title : {
			        text: '处理数量统计',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: str01
			    },
			    series : [
			        {
			            name: '访问来源',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:str,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
			myChart.setOption(option);
		}
	})
}
    
</script>
</html>