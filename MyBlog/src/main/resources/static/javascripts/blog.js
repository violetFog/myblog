/**
 * Created by PC on 2017/6/27.
 */
jQuery(function ($) {
    $("#page").initPage(1000,1);
    queryAllBlog();
})


function queryAllBlog(){
    var article="";
    $.ajax({
        url:"/blogs/queryAllBlogs",
        type:"POST",
        data:{},
        dataType: "json",
        success:function(data){
            console.log(data);
            var isTop=data.isTop;
            var nTop=data.isNotTop;
            for(var i=0;i<isTop.length;i++){
                article +=" <div class='blog-list'>"
                    +"<div class='blog-text'>"
                    +"<span style='color: red;'>[置顶]</span>"
                    +"<a href='/blogs/"+isTop[i].blogId+"'target='_blank'>"
                    +"<span>"+isTop[i].title+"</span>"
                    +"</a>"
                    +"</div>"
                    +"<div class='blog-operation'>"
                    +"<span>"+isTop[i].updateTime+"</span>"
                    +"<span>("+isTop[i].comment+"/"+isTop[i].number+")</span>"
                    +"<span hidden='hidden'>删除</span>"
                    +"</div>"
                    +"</div>"
            }
            for(var j=0;j<nTop.length;j++){
                article +=" <div class='blog-list'>"
                    +"<div class='blog-text'>"
                    +"<a href='/article/"+nTop[j].blogId+"'target='_blank'>"
                    +"<span>"+nTop[j].title+"</span>"
                    +"</a>"
                    +"</div>"
                    +"<div class='blog-operation'>"
                    +"<span>"+nTop[j].updateTime+"</span>"
                    +"<span>("+nTop[j].comment+"/"+nTop[j].number+")</span>"
                    +"<span hidden='hidden'>删除</span>"
                    +"</div>"
                    +"</div>"
            }
            $("#dr-blog-main").append(article);
        },
        error:function(err){
            alert("错误");
        }
    })
}