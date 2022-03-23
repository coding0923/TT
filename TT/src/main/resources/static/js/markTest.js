var length = $('[name="checkbox"]').length;
var qlist = [];
        
$(document).ready(function(){
    for(var o =1;o<=length;o++){
        if(document.getElementById("studentAnswer"+o).value == 
        document.getElementById("questionAnswer"+o).value){
            document.getElementById("chk"+o).checked = true;
            }
        }
    });
        
function marking(){
    console.log('채점버튼 진입');
    for(var e=1;e<=length;e++){
                
    var data = {
        studentId : "studentId",
        questionId : "questionId",
        testPaperId : "testPaperId",
        teacherId : "teacherId"
        };
                
    if($('#chk'+e).is(":checked")){
        data.studentId = $('#studentId'+e).val();
        data.questionId = $('#questionId'+e).val();
        data.testPaperId = $('#testPaperId'+e).val();
        data.teacherId = $('#teacherId').val();
               
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
                    location.href= "testMain";
                }else{
                        
                }
            },
        error:function(){
        alert('에러입니다.');
        }
    });
}