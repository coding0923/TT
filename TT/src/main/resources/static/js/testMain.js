function popupTestList(){
    var option = "width = 400, height = 362, top = 100, left = 200, location = no";
    window.open('/test/insertTestList2','insertTestList',option).focus();
}

function popupQuestion(){
    var option = "width = 400, height = 793, top = 100, left = 200, location = no";
    window.open('/test/insertQuestion2','insertQuestion',option).focus();
}

function popupList(url,name){
    var option = "width = 900, height = 830, top = 100, left = 200, location = no";
    window.open(url,name,option).focus();
}