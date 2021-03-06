/**
 * Created by PC on 2017/6/9.
 */
$(document).ready(function () {
    findArticle();
    checkLogin()
});
function checkLogin(){
    $.ajax({
        url:"/login/checkLogin",
        type:"POST",
        data:{},
        dataType:"json",
        success:function(data){
            if(data.success){
                $("#loginDiv").attr("hidden","hidden");
            }
        },
        error:function(err){
            alert("错误");
        }
    })
}

function findArticle(){
    $.ajax({
        url: "/blogs/queryRecentBlogs",
        type: 'POST',
        data: {},
        dataType: "json",
        success:function(data){
            var str="";
            for(var i=0;i<data.length;i++){
                if(data[i].isTop==1){
                    str+="<article class='excerpt' style='padding:10px;width: 650px;'>"
                        +"<div class='tm-related-post'>"
                        +  " <div class='media-body'>"
                        +  " <a href='/blogs/view/"+data[i].blogId+"' target='_blank'><h4 class='index_title' align='center'><span style='color: red;'>[置顶]</span>"+data[i].title+"</h4></a>"
                        +"<div class='tm-small-font tm-media-description' style='width: 100%;height: 150px;overflow:hidden'>"+data[i].text+"</div>"
                        + "  <div class='data' style='margin-top: 10px'>"
                        +  "  <span class='time_n artfont1'>"+data[i].updateTime+"</span>"
                        +   " <span class='read_n artfont2'>浏览("+data[i].number+")</span>"
                        +"<span class='comment_n artfont3'>" + data[i].blogType+" </span>"
                        +   " <a href='/blogs/view/"+data[i].blogId+"' target='_blank' class='btn readall'>阅读全文&gt;&gt;</a>"
                        +"</div>"
                        +"</div>"
                        +"</div>"
                        +"</article>"
                }

            }
            for(var i=0;i<data.length;i++){
                if(data[i].isTop==0){
                    str+="<article class='excerpt' style='padding:10px; width: 650px;'>"
                        +"<div class='tm-related-post'>"
                        +  " <div class='media-body'>"
                        +  " <a href='/blogs/view/"+data[i].blogId+"' target='_blank'><h4 class='index_title' align='center'>"+data[i].title+"</h4></a>"
                        +"<div class='tm-small-font tm-media-description' style='width: 100%;height: 150px;overflow:hidden'>"+data[i].text+"</div>"
                        + "  <div class='data' style='margin-top: 10px'>"
                        +  "  <span class='time_n artfont1'>"+data[i].updateTime+"</span>"
                        +   " <span class='read_n artfont2'>浏览("+data[i].number+")</span>"
                        +"<span class='comment_n artfont3'>"+data[i].blogType +"</span>"
                        +   " <a href='/blogs/view/"+data[i].blogId+"' target='_blank' class='btn readall'>阅读全文&gt;&gt;</a>"
                        +"</div>"
                        +"</div>"
                        +"</div>"
                        +"</article>"
                }

            }

            $("#dr-main-article").append(str);
        },
        error:function(){
            alert("错误");
        }
    })
}

