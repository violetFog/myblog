/**
 * Created by PC on 2017/7/19.
 */
var editor;
jQuery(function($){
    KindEditor.ready(function(K) {
        editor = K.create('textarea[name="content"]', {
            resizeType : 1,
            allowPreviewEmoticons : false,
            allowImageUpload : false,
            items : [
                'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                'insertunorderedlist', '|', 'emoticons'],
            afterCreate : function() {
                this.sync();
            },
            afterBlur:function(){
                this.sync();
            }
        });
    });
    var ip=getLocalIPAddress();
    $("input[name=ip]").val(ip);
    showMessage();
});

function showMessage(){
    $.ajax({
        url:"/message/showMessage",
        type:"POST",
        data:{},
        dataType:"json",
        success:function(data){
            var message="";
            var imgs=["anonymity_blue.png","anonymity_green.png","anonymity_grey.png","anonymity_pink.png","anonymity_purple.png","anonymity_yellow.png"]
            var messageList = data.messages;
            var count = data.count;
            if(count==0){
                alert("还没有留言")
            }else{
                for(var i=messageList.length-1;i>=0;i--){
                    var random=getRandom();
                    var img=imgs[random];
                    message += "<div class='float'>"
                        +"<img src='/images/"+img+"'/>"
                        +"</div>"
                        +"<div class='float'>"
                        +"<div class='content' style='min-height: 135px'>"
                        +"<span>"+messageList[i].messager+"留言</span>"
                        +"<span hidden='hidden'>"+messageList[i].ip+"</span>"
                        +"<span style='margin-left: 10px'>第"+(i+1)+"楼</span>"
                        +"<div style='margin: 2px 0;min-height: 100px;'>"+messageList[i].content+"</div>"
                        +"<span>"+messageList[i].createDate+"</span>"
                        +"</div>"
                        +"</div>"
                        +"<div class='clear'></div>"
                }
                $("#title_count").html(count);
                $("#container").html("");
                $("#container").append(message);
            }
        },
        error:function(err){
            alert("错误")
        }
    })
}

function getLocalIPAddress() {
    var obj = null;
    var rslt = "127.0.0.1";
    try {
        obj = new ActiveXObject("rcbdyctl.Setting");
        if (!isNull(obj.GetIPAddress))
        {
            rslt = obj.GetIPAddress;
        }
        obj = null;
    } catch(e) {
        //异常发生
    }

    return rslt;
}


function writeMessage(){
    $("#dr-message-form").ajaxSubmit({
        beforeSubmit: function () {
            return true;
        },
        success: function (data) {
            if(data.error){
                alert(data.error);
                return;
            }else{
               showMessage();
                message_reset();
            }
        }
    });
}
function message_reset(){
    editor.html("");
}


function getRandom(){
    var num = Math.random()*6;
    num = parseInt(num, 10);
    return num;
}