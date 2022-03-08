
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
        "<label for='questionContent'>문제 내용</label>"+
        "<input class='form-control' type='text' id='questionContent"+i+"' name='questionContent' placeholder='문제를 입력해 주세요.'/>"+
        "<label for='questionScore'>배점</label>"+
        "<input class='form-control' type='number' id='questionScore"+i+"' name='questionScore' min='1'>"+
        "<label for='questionAnswer'>문제의 답</label>"+
        "<input class='form-control' type='text' id='questionAnswer"+i+"' name='questionAnswer' placeholder='답을 입력해 주세요.'/>"+
        "<label for='questionImage'>이미지</label>"+
        "<input class='form-control' type='text' id='questionImage"+i+"' name='questionImage' placeholder='이미지 추가용'/>";;
            
    questionForm.appendChild(addDiv);
            
    i++;
    }
    
        
/* 문제등록 기능 */
function insertQuestion(){
    for(var e=0;e<=i-1;e++){
                
        var list = {
            questionContent : "questionContent",
            questionScore : "score",
            questionAnswer : "answer",
            questionImage : "image",
            insertUser : "teacherId"
        };
        // 문제에 빈칸있는지 검사
        if(document.getElementById("questionContent"+e).value !== "" &&
           document.getElementById("questionScore"+e).value !== "" &&
           document.getElementById("questionAnswer"+e).value !== "")
            {   list.insertUser = document.getElementById("insertUser").value;
                list.questionContent = document.getElementById("questionContent"+e).value;
                list.questionScore = document.getElementById("questionScore"+e).value;
                list.questionAnswer = document.getElementById("questionAnswer"+e).value;
                list.questionImage = document.getElementById("questionImage"+e).value;
                    
                
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
                    location.href= "testMain";
                }else{
                        
                }
            },
            error:function(){
            alert('에러입니다.');
            }
        });
            
}