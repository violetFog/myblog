/**
 * Created by PC on 2017/7/14.
 */
jQuery(function($){
    showPictures(document.URL.split("\/")[5]);

})

function showModal(){
    $("#addPictureModal").modal("show");
}

function showPictures(albumId){
    var pictures="";
    $("#dr-albums").html("");
    $.ajax({
        url:"/albums/showPicture",
        type:"POST",
        data:{albumId:albumId},
        dataType: "json",
        success:function(data){
            var pictureList=data.pictureList;
            for(var i=0;i<pictureList.length;i++){
                pictures +="<div class='albums left' ondrop='drop(event,this)' ondragover='allowDrop(event)' draggable='true' ondragstart='drag(event, this)'>"
                    +" <img src="+pictureList[i].img+"/>"
                    +"<div>"+pictureList[i].pictureName+"</div>"
                    +"<a href='javascript:setCover("+pictureList[i].img+","+pictureList[i].albumId+")'>置为封面</a>"
                    +"</div>"
            }
            $("#dr-albums").append(pictures);
        },
        error:function(err){
            alert("错误");
        }
    })
}


function setCover(img,albumId){
    $.ajax({
        url:"/albums/setCover",
        type:"POST",
        data:{img:img,
            albumId:albumId
        },
        dataType: "json",
        success:function(data){
            alert("设置成功")
        },
        error:function(err){
            alert("错误");
        }
    })
}