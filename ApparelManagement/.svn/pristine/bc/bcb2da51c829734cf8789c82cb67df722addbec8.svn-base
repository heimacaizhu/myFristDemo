//当前页数
var page = 1;
//总数目
var nums ;
//数据
var data ;
$(function () {
    //初始化数据
    show();
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage
            ,layer = layui.layer;

        laypage({
            cont: 'demo'
            ,pages: 100
            ,skip: true
        });

    });
})
function show() {
    $.post("jhsqShow.do",{"page":page},function (data) {
        //解析数据
        dataParse(data);
        //初始化分页插件
        fenye();
    },"json");
}

//分页插件
function fenye() {
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage
            ,layer = layui.layer;

        laypage({
            cont: 'demo'
            ,pages: nums
            ,skip: true
        });

    });
}
// {"total":1,"rows":[{"ap_id":"1","result":"2","ap_state":2,"ap_type":3,"deal_person":2,"remark":"2","accessory":"3","applicant":2}]}
//数据解析
function dataParse(data) {
    nums = data.total;
    $("tbody").empty();
    var content = "";
    var rows = data.rows;
    $(rows).each(function (i , val) {
        content += "<tr><td>"
        content += isEmpty(val.applicant);
        content += "</td><td>";
        content += isEmpty(val.deal_person);
        content += "</td><td>";
        content += isEmpty(val.result);
        content += "</td><td>";
        content += isEmpty(val.ap_state);
        content += "</td><td>";
        content += isEmpty(val.ap_type);
        content += "</td><td>";
        content += isEmpty(val.ap_date);
        content += "</td><td>";
        content += isEmpty(val.ap_dealdate);
        content += "</td><td>";
        content += isEmpty(val.ap_lever);
        content += "</td><td>";
        content += isEmpty(val.num);
        content += "</td><td>";
        content += isEmpty(val.remark);
        content += "</td></tr>";
    });
    $("tbody").append(content);
}
function isEmpty(param) {
    if (param == undefined){
        param = "空" ;
    }
    return param ;
}