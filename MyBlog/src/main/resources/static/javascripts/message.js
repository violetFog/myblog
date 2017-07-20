/**
 * Created by PC on 2017/7/19.
 */
jQuery(function($){
    var editor;
    KindEditor.ready(function(K) {
        editor = K.create('textarea[name="content"]', {
            resizeType : 1,
            allowPreviewEmoticons : false,
            allowImageUpload : false,
            items : [
                'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                'insertunorderedlist', '|', 'emoticons']
        });
    });
    var ip=getLocalIPAddress();
    console.log(ip);
});
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