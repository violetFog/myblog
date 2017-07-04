/**
 * Created by PC on 2017/6/9.
 */
$(document).ready(function () {
    $('.nav-list a').each(function (n, v) {
        $(v).unbind("click").click(function (e) {
            $('.nav-list .active').each(function (nn, vv) {
                $(vv).removeClass("active");
            });
            $(this).parents("li").each(function (nn, vv) {
                $(vv).addClass("active");
            });
        });
    });
    findArticle();
});


function findArticle(){
    $.ajax({
        url: "/blogs/queryRecentBlogs",
        type: 'POST',
        data: {},
        dataType: "json",
        success:function(data){
            console.log(data);
            var str="";
            for(var i=0;i<data.length;i++){
                if(data[i].isTop==1){
                    str+="<article class='excerpt' style='padding: 10px 0 10px 10px'>"
                        +"<div class='tm-related-post'>"
                        +"<div class='media-left media-middle'>"
                        +"<a href='/topic/view/13' target='_blank'>"
                        +  " <img class='media-object' src='/images/nanjo.jpg' style='width:200px;height: 150px;' alt='"+data[i].title+"' title='"+data[i].title+"' data-bd-imgshare-binded='1'/>"
                        +  " </a>"
                        +  " </div>"
                        +  " <div class='media-body'>"
                        +  " <a href='/topic/view/13' target='_blank'><h4 class='index_title' align='center'><span style='color: red;'>[置顶]</span>"+data[i].title+"</h4></a>"
                        +"<p class='tm-small-font tm-media-description' style='width: 100%;height: 150px;overflow:hidden'>"+data[i].text+"</p>"
                        + "  <div class='data' style='margin-top: 10px'>"
                        +  "  <span class='time_n artfont1'>"+data[i].updateTime+"</span>"
                        +   " <span class='read_n artfont2'>浏览("+data[i].number+")</span>"
                        +"<span class='comment_n artfont3'>"
                        +  "  <a class='dateview3' href='/article/aplit/1?cate=%e6%8c%81%e7%bb%ad%e9%9b%86%e6%88%90' target=’_blank'>"+data[i].blogType+"</a>"
                        +  "  </span>"
                        +   " <a href='/topic/view/13' target='_blank' class='btn readall'>阅读全文&gt;&gt;</a>"
                        +"</div>"
                        +"</div>"
                        +"</div>"
                        +"</article>"
                }

            }
            for(var i=0;i<data.length;i++){
                if(data[i].isTop==0){
                    str+="<article class='excerpt' style='padding: 10px 0 10px 10px'>"
                        +"<div class='tm-related-post'>"
                        +"<div class='media-left media-middle'>"
                        +"<a href='/topic/view/13' target='_blank'>"
                        +  " <img class='media-object' src='/images/nanjo.jpg' style='width:200px;height: 150px;' alt='"+data[i].title+"' title='"+data[i].title+"' data-bd-imgshare-binded='1'/>"
                        +  " </a>"
                        +  " </div>"
                        +  " <div class='media-body'>"
                        +  " <a href='/topic/view/13' target='_blank'><h4 class='index_title' align='center'>"+data[i].title+"</h4></a>"
                        +"<p class='tm-small-font tm-media-description' style='width: 100%;height: 150px;overflow:hidden'>"+data[i].text+"</p>"
                        + "  <div class='data' style='margin-top: 10px'>"
                        +  "  <span class='time_n artfont1'>"+data[i].updateTime+"</span>"
                        +   " <span class='read_n artfont2'>浏览("+data[i].number+")</span>"
                        +"<span class='comment_n artfont3'>"
                        +  "  <a class='dateview3' href='/article/aplit/1?cate=%e6%8c%81%e7%bb%ad%e9%9b%86%e6%88%90' target=’_blank'>"+data[i].blogType+"</a>"
                        +  "  </span>"
                        +   " <a href='/topic/view/13' target='_blank' class='btn readall'>阅读全文&gt;&gt;</a>"
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