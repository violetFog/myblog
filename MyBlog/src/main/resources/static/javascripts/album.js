$(document).ready(function(){



})

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


function funtest(){
    $("#maxH").style.filter="alpha(opacity=50)"
    $("#maxH").style.opactiy="0.5"
    $("#test").css("display","block");
}