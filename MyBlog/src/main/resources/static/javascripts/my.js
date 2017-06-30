function search() {
    var val=$("#china").val();
    alert(val);
    $.ajax({
        url: '/login/check',
        type: 'POST',
        success: function (msg) {

            alert(msg.name);
        },
        error: function () {
            alert("失败");
        }
    });
}











