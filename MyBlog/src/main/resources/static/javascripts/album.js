jQuery(function($) {
    showAlbums();
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
                $("#createAlbum").removeAttr("hidden");
            }
        },
        error:function(err){
            alert("错误");
        }
    })
}
function showAlbums(){
    var albums="";
    $.ajax({
        url:"/albums/showAlbums",
        type:"POST",
        data:{},
        dataType: "json",
        success:function(data){
            var albumList=data.albumList;
            for(var i=0;i<albumList.length;i++){
                albums +="<div style='position: relative' class='albums left' ondrop='drop(event,this)' ondragover='allowDrop(event)' draggable='true' ondragstart='drag(event, this)'>"
                    +" <a href='"+albumList[i].src+"' target='_blank'>"
                    +" <img src='"+albumList[i].cover+"' onerror='imgError(this)'/>"
                    +"<div  title='"+albumList[i].albumName+"'>"+albumList[i].albumName+"</div>"
                    +"</a>"
                    +"<input type='button' style='position: absolute;top: 10px;right: 5px;' value='删除' onclick='deleteAlbum("+albumList[i].albumId+")'/>"
                    +"</div>"
            }
            $("#dr-albums").html("");
            $("#dr-albums").append(albums);
        },
        error:function(err){
            alert("错误");
        }
    })
}
function createAlbum(){
    $("#createAlbumModal").modal("show");
    $("#album_name").val("");
    $("#album_desc").val("");
    var file = $("#album_file");
    file.after(file.clone().val(""));
    file.remove();

}
function imgError(image){
    $(image).attr("src", "/images/default.png");
}
function addNewAlbum(){
    var album_name=$("#album_name").val().trim();
    if(!album_name){
        alert("相册名不能为空");
        return;
    }
    $("#createAlbumModal .btn-success").attr("data-dismiss","modal");
    var album_desc=$("#album_desc").val();
    var album_type=$("#album_type option:selected").val();
    var param={};
    param.albumName=album_name;
    param.albumDescription=album_desc;
    param.albumType=album_type;
    $("#album_form").ajaxSubmit({
        beforeSubmit: function () {
            return true;
        },
        success: function (data) {
            if (data && data.file_path) {
                param.albumFile=data.file_path;
                param.src=data.src
                $.ajax({
                    url:"/albums/createAlbum",
                    type:"POST",
                    data:param,
                    dataType:"json",
                    success:function(data){
                        var album=data.album;
                        var str=" <div style='position: relative' class='albums left' ondrop='drop(event,this)' ondragover='allowDrop(event)' draggable='true' ondragstart='drag(event, this)'>"
                            +" <a href='"+album.src+"' target='_blank'>"
                            +"<img src='"+album.cover+"' onerror='imgError(this)' />"
                            +"<div title='"+album.albumName+"'>"+album.albumName+"</div>"
                            +"</a>"
                            +"<input type='button' style='position: absolute;top: 10px;right: 5px;' value='删除' onclick='deleteAlbum("+album.albumId+")'/>"
                            +"</div>"
                        $("#dr-albums").append(str)
                    },
                    error:function(err){
                        alert("错误");
                    }
                })
            }
        }
    });


}

function deleteAlbum(albumId){
    $.ajax({
        url:"/albums/deleteAlbum",
        type:"POST",
        data:{albumId:albumId},
        dataType:"json",
        success:function(data){
            if(data==1){
                alert("失败")
            }
            showAlbums()
        },
        error:function(err){
            alert("错误");
        }
    })
}

function allowDrop(ev) {
    ev.preventDefault();
}
var srcdiv = null;
function drag(ev,divdom) {
    srcdiv=divdom;
    ev.dataTransfer.setData("text/html",divdom.innerHTML);
}
function drop(ev,divdom) {
    ev.preventDefault();
    if(srcdiv != divdom){
        srcdiv.innerHTML = divdom.innerHTML;
        divdom.innerHTML=ev.dataTransfer.getData("text/html");
    }
}




