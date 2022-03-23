var length = document.getElementById("tbody").getElementsByTagName("tr").length;
var array = [];
        
function testbutton(){
    alert('제출 버튼 진입');
    for(var i=1;i<=length;i++){
                
    var list = {
        studentId : "studentId",
        testPaperId : "testPapertId",
        questionId : "questionId",
        studentAnswer : "studentAnswer"
        };

        list.studentId = document.getElementById("studentId").value;
        list.testPaperId = document.getElementById("testPaperId").value;
        list.questionId = document.getElementById("questionId"+i).value;
        list.studentAnswer = document.getElementById("studentAnswer"+i).value;
        console.log(list);
        array.push(list);
      
    }   
    $.ajax({
        url:'/studentAnswer',
        type:'post',
        contentType:'application/json',
        data: JSON.stringify(array),
    success:function(data){
        console.log(data);
        if(data==1){
            alert('제출 완료');
            location.href= "testMain";
        }else{
                        
            }
        },
        error:function(){
        alert('에러입니다.');
        }
    });
}