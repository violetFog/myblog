/**
 * Created by PC on 2017/6/27.
 */
jQuery(function ($) {
    var count = queryBlogs(null,1);
    queryclassification(null);
    $("#page").initPage(count,1);

});


function queryBlogs(blogTypes,page){
    var params={};
    var title=$("#search").val();

    if(!isNull(blogTypes)){
        params.blogType=blogTypes;
    }else{
        var blogType=$("#dr-classification .active span").html();
        if(blogType=="全部日志"){
            blogType=null;
        }
        params.blogType=blogType;
    }

    if(isNull(page)){
        params.page=1;
    }else{
        params.page=page;
    }
    params.title=title;
    params.limit=10;
    var article="";
    $.ajax({
        url:"/blogs/queryBlogs",
        type:"POST",
        data:params,
        dataType: "json",
        success:function(data){
            var blogs=data.blogs;
            var count=data.count;
            for(var i=0;i<blogs.length;i++){
                if(blogs[i].isTop==1){
                    article +=" <div class='blog-list'>"
                        +"<div class='blog-text'>"
                        +"<span style='color: red;'>[置顶]</span>"
                        +"<a href='/blogs/view/"+blogs[i].blogId+"'target='_blank'>"
                        +"<span>"+blogs[i].title+"</span>"
                        +"</a>"
                        +"</div>"
                        +"<div class='blog-operation'>"
                        +"<span>"+blogs[i].updateTime+"</span>"
                        +"<span>("+blogs[i].comment+"/"+blogs[i].number+")</span>"
                        +"<span hidden='hidden'>删除</span>"
                        +"</div>"
                        +"</div>"
                }else{
                    article +=" <div class='blog-list'>"
                        +"<div class='blog-text'>"
                        +"<a href='/blogs/view/"+blogs[i].blogId+"'target='_blank'>"
                        +"<span>"+blogs[i].title+"</span>"
                        +"</a>"
                        +"</div>"
                        +"<div class='blog-operation'>"
                        +"<span>"+blogs[i].updateTime+"</span>"
                        +"<span>("+blogs[i].comment+"/"+blogs[i].number+")</span>"
                        +"<span hidden='hidden'>删除</span>"
                        +"</div>"
                        +"</div>"
                }


            }
            $("#dr-blog-main").html("");
            $("#dr-blog-main").append(article);
            $("#page").initPage(count,params.page);
            return count;
        },
        error:function(err){
            alert("错误");
        }
    })

}



function queryclassification(){
    $.ajax({
        url:"/blogs/queryBlogsType",
        type:"POST",
        data:{},
        dataType: "json",
        success:function(data){
            var count=data.count;
            var classification=""
            var typeList=data.typeList;
            classification="<li class='classification active'><a href='javascript:queryBlogs()' onclick='actives(this)'><span class='left'>全部日志</span><span class='right'>("+count+")</span></a></li>";
            for(var k=0;k<typeList.length;k++){
                classification +="<li class='classification'><a onclick='actives(this)' href='javascript:queryBlogs(\""+typeList[k][0]+"\""+","+1+")'><span class='left'>"+typeList[k][0]+"</span><span class='right'>("+typeList[k][1]+")</span></a></li>"
            }
            $("#dr-classification").append(classification);
        },
        error:function(err){
            alert("错误");
        }
    })
}


function actives(obj){
    $(obj).parents("#dr-classification").find(".active").removeClass("active");
    $(obj).parent().addClass("active");
    $("#search").val("");
}

function switchToSummary(){

}