/**
 * Created by PC on 2017/7/10.
 */
jQuery(function($){
    queryBlogsType()
});

function addNewType(){
    var flag=true;
    var newType=$("#newType").val();
    if(isNull(newType)){
        alert("请您输入分类名称");
    }
    $("#blogType option").each(function(data){
        if($(this).val()==newType){
            alert("该分类名已经存在");
            flag=false;
        }
    });
    if(flag){
        $("#blogType").prepend("<option value='"+newType+"'>"+newType+"</option>");
        $("#blogType option[value='"+newType+"']").attr("selected","selected")
        $("#addBlogTypeModal .btn-success").attr("data-dismiss","modal");
    }
}


function addBlogType(){
    $("#addBlogTypeModal").modal("show");
}


function queryBlogsType(){
    $.ajax({
        url:"/blogs/queryBlogsType",
        type:"POST",
        data:{},
        dataType: "json",
        success:function(data){
            var count=data.count;
            var typeList=data.typeList;
            var classification=""
            for(var k=0;k<typeList.length;k++){
                classification +="<option value='"+typeList[k][0]+"'>"+typeList[k][0]+"</option>"
            }
            $("#blogType").append(classification);
        },
        error:function(err){
            alert("错误");
        }
    })
}




function check(){
    var flag=true;
    var title=$("#blogTitle").val();
    if(!title){
        alert("填写标题！")
        flag=false;
    }
    if(flag){
        $("#blogForm").submit()
    }

}