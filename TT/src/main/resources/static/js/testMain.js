function popup(url,name){
    var option = "width = 400, height = 430, top = 100, left = 200, location = no";
    window.open(url,name,option).focus();
}

function popup2(url,name,width,height){
    var option = "width = "+width+", height = "+height+", top = 100, left = 200, location = no";
    window.open(url,name,option).focus();
}