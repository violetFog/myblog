/**
 * Created by PC on 2017/6/27.
 */
jQuery(function ($) {
    var count = queryBlogs(null,1);
    queryclassification(null);
    $("#page").initPage(count,1);
    checkLogin();
});

function checkLogin(){
    $.ajax({
        url:"/login/checkLogin",
        type:"POST",
        data:{},
        dataType:"json",
        success:function(data){
            if(data.success){
                $("#writeBlog").removeAttr("hidden");
            }
        },
        error:function(err){
            alert("错误");
        }
    })
}


function queryBlogs(blogTypes,page){
    var params={};
    var type;
    var title=$("#search").val();

    if(blogTypes){
        params.blogType=blogTypes;
    }else{
        var blogType=$("#dr-classification .active span").html();
        if(blogType=="全部日志"){
            blogType=null;
        }
        params.blogType=blogType;
    }

    if(!page){
        params.page=1;
    }else{
        params.page=page;
    }
    var spam=$("#querySwitch span").html();
    if(spam=="切换到摘要"){
        type=0;
    }else{
        type=1;
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
            if(type==0){
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
            }else{
                for(var i=0;i<blogs.length;i++){
                    if(blogs[i].isTop==1){
                        article+="<article class='excerpt' style='padding:10px; width: 740px;'>"
                            +"<div class='tm-related-post'>"
                            +  " <div class='media-body'>"
                            +  " <a href='/blogs/view/"+blogs[i].blogId+"' target='_blank'><h4 class='index_title' align='center'><span style='color: red;'>[置顶]</span>"+blogs[i].title+"</h4></a>"
                            +"<div class='tm-small-font tm-media-description' style='width: 100%;height: 150px;overflow:hidden'>"+blogs[i].text+"</div>"
                            + "  <div class='data' style='margin-top: 10px'>"
                            +  "  <span class='time_n artfont1'>"+blogs[i].updateTime+"</span>"
                            +   " <span class='read_n artfont2'>浏览("+blogs[i].number+")</span>"
                            +"<span class='comment_n artfont3'>" + blogs[i].blogType+" </span>"
                            +   " <a href='/blogs/view/"+blogs[i].blogId+"' target='_blank' class='btn readall'>阅读全文&gt;&gt;</a>"
                            +"</div>"
                            +"</div>"
                            +"</div>"
                            +"</article>"
                    }
                }
                for(var i=0;i<blogs.length;i++){
                    if(blogs[i].isTop==0){
                        article+="<article class='excerpt' style='padding:10px; width: 740px;'>"
                            +"<div class='tm-related-post'>"
                            +  " <div class='media-body'>"
                            +  " <a href='/blogs/view/"+blogs[i].blogId+"' target='_blank'><h4 class='index_title' align='center'>"+blogs[i].title+"</h4></a>"
                            +"<div class='tm-small-font tm-media-description' style='width: 100%;height: 150px;overflow:hidden'>"+blogs[i].text+"</div>"
                            + "  <div class='data' style='margin-top: 10px'>"
                            +  "  <span class='time_n artfont1'>"+blogs[i].updateTime+"</span>"
                            +   " <span class='read_n artfont2'>浏览("+blogs[i].number+")</span>"
                            +"<span class='comment_n artfont3'>"+blogs[i].blogType +"</span>"
                            +   " <a href='/blogs/view/"+blogs[i].blogId+"' target='_blank' class='btn readall'>阅读全文&gt;&gt;</a>"
                            +"</div>"
                            +"</div>"
                            +"</div>"
                            +"</article>"
                    }

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
            var typeList=data.typeList;
            var classification="<li class='classification active'><a href='javascript:queryBlogs()' onclick='actives(this)'><span class='left'>全部日志</span><span class='right'>("+count+")</span></a></li>";
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
    var spam=$("#querySwitch span").html();
    var page=$(".pageItemActive").attr("page-data");
    if(spam=="切换到摘要"){
        $("#querySwitch span").html("切换到列表");
    }else{
        $("#querySwitch span").html("切换到摘要");
    }
    var blogType=$("#dr-classification .active span").html();
    if(blogType=="全部日志"){
        blogType=null;
    }
    queryBlogs(blogType,page);
}