
var array = [];
var i = 1;

/* 문제추가 기능 */
function addQuestion(){
    var c = "count"+(i-1);
    var addDiv = document.createElement("div");
    addDiv.setAttribute("id","addQuestion"+i);
    addDiv.setAttribute("class","card-details");
    addDiv.innerHTML = 
        "<p id='count"+i+"'>"+(parseInt($('#'+c).text())+1)+"</p>"+
        "<label for='testQuestionContent'>문제 내용</label>"+
        "<input class='form-control' type='text' id='testQuestionContent"+i+"' name='testQuestionContent' placeholder='문제를 입력해 주세요.'/>"+
        "<label for='testQuestionScore'>배점</label>"+
        "<input class='form-control' type='number' id='testQuestionScore"+i+"' name='testQuestionScore'>"+
        "<label for='testQuestionAnswer'>문제의 답</label>"+
        "<input class='form-control' type='text' id='testQuestionAnswer"+i+"' name='testQuestionAnswer' placeholder='답을 입력해 주세요.'/>"+
        "<label for='testQuestionImage'>이미지</label>"+
        "<input class='form-control' type='text' id='testQuestionImage"+i+"' name='testQuestionImage' placeholder='이미지 추가용'/>";;
            
    questionForm.appendChild(addDiv);
            
    i++;
    }
    
        
/* 문제등록 기능 */
function insertQuestion(){
    for(var e=0;e<=i-1;e++){
                
        var list = {
            testListId : "testListId",
            classId : "classId",
            academyId : "academyId",
            testQuestionContent : "questionContent",
            testQuestionScore : "score",
            testQuestionAnswer : "answer",
            testQuestionImage : "image"
        };
        // 문제에 빈칸있는지 검사
        if(document.getElementById("testListId").value !== "" &&
            document.getElementById("classId").value !== "" &&
            document.getElementById("academyId").value !== "" &&
            document.getElementById("testQuestionContent"+e).value !== "" &&
            document.getElementById("testQuestionScore"+e).value !== "" &&
            document.getElementById("testQuestionAnswer"+e).value !== "")
            {
                list.testListId = document.getElementById("testListId").value;
                list.classId = document.getElementById("classId").value;
                list.academyId = document.getElementById("academyId").value;
                list.testQuestionContent = document.getElementById("testQuestionContent"+e).value;
                list.testQuestionScore = document.getElementById("testQuestionScore"+e).value;
                list.testQuestionAnswer = document.getElementById("testQuestionAnswer"+e).value;
                list.testQuestionImage = document.getElementById("testQuestionImage"+e).value;
                    
                array.push(list);
            }else {
                alert('빈칸을 입력해주세요.');
                return false;
            }
    }
            
    $.ajax({
        url:'/questionRegister',
        type:'post',
        contentType:'application/json',
        data: JSON.stringify(array),
            success:function(data){
                console.log(data);
                if(data==1){
                    alert('문제 등록 완료');
                    /*location.href= "main.html";*/
                }else{
                        
                }
            },
            error:function(){
            alert('에러입니다.');
            }
        });
            
}