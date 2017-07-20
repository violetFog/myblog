/**
 * Created by PC on 2017/7/10.
 */
jQuery(function($){
    queryBlog(document.URL.split("\/")[5]);

})

function queryBlog(blogId){
    $.ajax({
        url:"/blogs/queryBlogById",
        type:"POST",
        data:{blogId:blogId},
        datatype:"json",
        success:function(data){
            var blog=data.blog;
            $("#dr-main-title").html(blog.title);
            $("#dr-main-updateTime").html(blog.createTime);
            $("#dr-main-look").html("浏览("+blog.number+")");
            $("#dr-main-type").html("类别:"+blog.blogType);
            $("#dr-main-text").append(blog.text);
        },
        error:function(err){
            alert("错误")
        }
    })
}
