/**
 * Created by PC on 2017/7/10.
 */
function check(){
    var flag=true;
    var text=$("#blogText").html();
    var tes=$("#blogText").val();
    console.log(text);
    console.log(tes);
    if(text){
        alert(text)
    }else{
        alert("填写正文！")
        flag=false;
    }
    if(flag){
        $("#blogForm").submit()
    }

}