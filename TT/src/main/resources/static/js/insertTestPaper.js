var length = $('[name="checkbox"]').length-1;
var testpaper = [];

function insert(){
    for(var e=1;e<=length;e++){
        
    var data = {
        questionId : "questionId",
        academyId : $('#academyId').val(),
        classId : $('#classId').val(),
        subjectCode : $('#subjectCode').val(),
        insertUser : $('#teacherId').val()
        };
                
    if($('#chk'+e).is(":checked")){
        data.questionId = $('#questionId'+e).val();
               
        console.log(data);
        testpaper.push(data);
        }
    }
            
    $.ajax({
        url:'/insertTestPaper',
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