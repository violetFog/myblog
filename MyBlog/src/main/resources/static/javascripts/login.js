/**
 * Created by PC on 2017/7/26.
 */
jQuery(function($){
    createCode();
});


var code;
function createCode(){
     code = "";
    var codeLength = 6; //验证码的长度
    var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
    for(var i = 0; i < codeLength; i++) {
        var charNum = Math.floor(Math.random() * 52);
        code += codeChars[charNum];
    }
    $("#checkCode").val(code);
}

function user_reset(){
    $("#loginName").val("");
    $("#loginPsd").val("");
    $("#inputCode").val("");
    createCode();
}

function validateCode() {
    var inputCode=$("#inputCode").val();
    if(inputCode.length <= 0) {
        alert("请输入验证码！");
    } else if(inputCode.toUpperCase() != code.toUpperCase()) {
        alert("验证码输入有误！");
        createCode();
    } else {
        $("#loginForm").ajaxSubmit({
            beforeSubmit: function () {
                return true;
            },
            success: function (data) {
                if(data.success){
                    top.location='/';
                }else{
                    alert(data.failed);
                }

            }
        });
    }
}