var length = $('[name="checkbox"]').length-1;
var testpaper = [];

function insert(){
    for(var e=1;e<=length;e++){
        
    var data = {
        questionId : "questionId",
        questionScore : "questionScore",
        questionContent : "questionContent",
        questionAnswer : "questionAnswer",
        questionImage : "questionImage",
        academyId : $('#academyId').val(),
        classId : $('#classId').val(),
        subjectCode : $('#subjectCode').val(),
        insertUser : $('#teacherId').val()
        };
                
    if($('#chk'+e).is(":checked")){
        data.questionId = $('#questionId'+e).val();
        data.questionScore = $('#questionScore'+e).val();
        data.questionContent = $('#questionContent'+e).val();
        data.questionImage = $('#questionImage'+e).val();
        data.questionAnswer = $('#questionAnswer'+e).val();
               
        testpaper.push(data);
        }
    }
            
    $.ajax({
        url:'/registerTestPaper',
        type:'post',
        contentType:'application/json',
        data: JSON.stringify(testpaper),
            success:function(data){
                if(data==1){
                    alert('등록 완료');
                    window.close();
                }else{
                        
                }
            },
        error:function(){
        alert('에러입니다.');
        }
    });
};

function selectAll(selectAll)  {
  const checkboxes = document.getElementsByName('checkbox');
  
  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked;
  })
};