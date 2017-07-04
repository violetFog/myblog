jQuery(function($) {
    /*showAlbums();*/
});

function allowDrop(ev)
{
    ev.preventDefault();
}

var srcdiv = null;
function drag(ev,divdom)
{
    srcdiv=divdom;
    ev.dataTransfer.setData("text/html",divdom.innerHTML);
}

function drop(ev,divdom)
{
    ev.preventDefault();
    if(srcdiv != divdom){
        srcdiv.innerHTML = divdom.innerHTML;
        divdom.innerHTML=ev.dataTransfer.getData("text/html");
    }
}
function showAlbums(){
    var albums="";
    $.ajax({
        url:"",
        type:"POST",
        data:{},
        dataType: "json",
        success:function(data){
            for(var i=0;i<data.length;i++){
                albums +="<div class='albums left' ondrop='drop(event,this)' ondragover='allowDrop(event)' draggable='true' ondragstart='drag(event, this)'>"
                    +" <a href='javascript:showPictures("+data[i].albumId+")'>"
                    +" <img src="+data[i].cover+"/>"
                    +"<div>"+data[i].albumDescription+"</div>"
                    +"</a>"
                    +"</div>"
            }
            $("#dr-albums").append(albums);
        },
        error:function(err){
            alert("错误");
        }
    })
}

function showPictures(albumId){
    var pictures="";
    $("#dr-albums").css("display","none");
    $.ajax({
        url:"",
        type:"POST",
        data:{},
        dataType: "json",
        success:function(data){
            for(var i=0;i<data.length;i++){
                pictures +="<div class='albums left' ondrop='drop(event,this)' ondragover='allowDrop(event)' draggable='true' ondragstart='drag(event, this)'>"
                    +" <img src="+data[i].img+"/>"
                    +"<div>"+data[i].pictureDescription+"</div>"
                    +"<a href='javascript:setCover("+data[i].img+")'>置为封面</a>"
                    +"</div>"
            }
            $("#dr-main").append(pictures);
        },
        error:function(err){
            alert("错误");
        }
    })
}

function setCover(img){
    $.ajax({
        url:"",
        type:"POST",
        data:{img:img},
        dataType: "json",
        success:function(data){
            alert("设置成功")
        },
        error:function(err){
            alert("错误");
        }
    })
}