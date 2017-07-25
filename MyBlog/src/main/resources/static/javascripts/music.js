/**
 * Created by PC on 2017/7/17.
 */
jQuery(function($){
    setDiv('#k_on','2096055','1');
})




function setDiv(divId,musicId,type){
    var str=" <div id="+divId+" class='music_div'>"
        +"<iframe frameborder='no' border='0' marginwidth='0' marginheight='0' width='100%' height='600px' src='//music.163.com/outchain/player?type="+type+"&amp;id="+musicId+"&amp;auto=1&amp;height=600px'></iframe>"
        +"</div>";
    $("#music_right").html("");
    $("#music_right").append(str);

}
function showUl(id2){
    $(id2).find("div a").removeAttr("href");
    $(id2).find("div a").attr("href","javascript:closeUl(\'"+id2+"\')");
    $(id2).find("span").html("↑");
    $(id2).find("ul").css("display","block");
    $(id2).find("ul").removeClass("nav-hide");
    $(id2).find("ul").addClass("nav-show");
}

function closeUl(id2){
    $(id2).find("ul").css("display","none");
    $(id2).find("div a").removeAttr("href");
    $(id2).find("div a").attr("href","javascript:showUl(\'"+id2+"\')");
    $(id2).find("ul").removeClass("nav-show");
    $(id2).find("ul").addClass("nav-hide");
    $(id2).find("span").html("↓");
}