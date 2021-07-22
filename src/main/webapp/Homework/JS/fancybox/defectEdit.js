$(document).ready(function(){
    //alert("abc");

    /** 新增   **/
    $("#addBtn").click(function (){
        window.location.href = 'Defect_add.html';
    })


});

/** 用户角色   **/
var userRole = '';






/** 批量删除 **/
function batchDel(){
    if($("input[name='IDCheck']:checked").size()<=0){
        art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'至少选择一条', ok:true,});
        return;
    }
    // 1）取出用户选中的checkbox放入字符串传给后台,form提交
    var allIDCheck = "";
    $("input[name='IDCheck']:checked").each(function(index, domEle){
        bjText = $(domEle).parent("td").parent("tr").last().children("td").last().prev().text();
// 			alert(bjText);
        // 用户选择的checkbox, 过滤掉“已审核”的，记住哦
        if($.trim(bjText)=="已审核"){
// 				$(domEle).removeAttr("checked");
            $(domEle).parent("td").parent("tr").css({color:"red"});
            $("#resultInfo").html("已审核的是不允许您删除的，请联系管理员删除！！！");
// 				return;
        }else{
            allIDCheck += $(domEle).val() + ",";
        }
    });
    // 截掉最后一个","
    if(allIDCheck.length>0) {
        allIDCheck = allIDCheck.substring(0, allIDCheck.length-1);
        // 赋给隐藏域
        $("#allIDCheck").val(allIDCheck);
        if(confirm("您确定要批量删除这些记录吗？")){
            // 提交form
            $("#submitForm").attr("action", "/xngzf/archives/batchDelFangyuan.action").submit();
        }
    }
}

