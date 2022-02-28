var length = $('[name="checkbox"]').length;
var qlist = [];
        
$(document).ready(function(){
    for(var o =1;o<=length;o++){
        if(document.getElementById("studentTestAnswer"+o).value == 
        document.getElementById("testQuestionAnswer"+o).value){
            document.getElementById("chk"+o).checked = true;
            }
        }
    });
        
function marking(){
    for(var e=1;e<=length;e++){
                
    var data = {
        studentId : "studentId",
        testQuestionId : "questionId"
        };
                
    if($('#chk'+e).is(":checked")){
        data.studentId = $('#studentId').val();
        data.testQuestionId = $('#testQuestionId'+e).val();
               
        qlist.push(data);
        }
    }
            
    $.ajax({
        url:'/checkCON',
        type:'post',
        contentType:'application/json',
        data: JSON.stringify(qlist),
            success:function(data){
                console.log(data);
                if(data==1){
                    alert('채점 완료');
                    /*location.href= "main.html";*/
                }else{
                        
                }
            },
        error:function(){
        alert('에러입니다.');
        }
    });
}