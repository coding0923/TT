var length = document.getElementById("tbody").getElementsByTagName("tr").length;
var array = [];
        
function testbutton(){
    for(var i=1;i<=length;i++){
                
    var list = {
        studentId : "studentId",
        testListId : "testlistId",
        classId : "classId",
        academyId : "academyId",
        testQuestionId : "questionId",
        testQuestionContent : "questionContent",
        testQuestionImage : "image",
        testQuestionScore : "score",
        studentTestAnswer : "studentAnswer"
        };
        // 빈칸있는지 검사
    if(document.getElementById("studentId").value !== "" &&
    document.getElementById("testlistId").value !== "" &&
    document.getElementById("classId").value !== "" &&
    document.getElementById("academyId").value !== "" &&
    document.getElementById("testQuestionId"+i).value !== "" &&
    document.getElementById("testQuestionContent"+i).value !== "" &&
    document.getElementById("testQuestionScore"+i).value !== "" &&
    document.getElementById("answerInput"+i).value !== "")
    {
        list.studentId = document.getElementById("studentId").value;
        list.testListId = document.getElementById("testListId").value;
        list.classId = document.getElementById("classId").value;
        list.academyId = document.getElementById("academyId").value;
        list.testQuestionId = document.getElementById("testQuestionId"+i).value;
        list.testQuestionContent = document.getElementById("testQuestionContent"+i).value;
        list.testQuestionImage = document.getElementById("testQuestionImage"+i).value;
        list.testQuestionScore = document.getElementById("testQuestionScore"+i).value;
        list.studentTestAnswer = document.getElementById("answerInput"+i).value;
                
        array.push(list);
    }else{
        alert('빈칸을 입력해주세요.');
        return false;
        }   
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
            /*location.href= "main.html";*/
        }else{
                        
            }
        },
        error:function(){
        alert('에러입니다.');
        }
    });
}