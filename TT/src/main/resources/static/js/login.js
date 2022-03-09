/**
 * 
 */
 
$('.input100').each(function(){
    $(this).on('blur', function(){
        if($(this).val().trim() != "") {
            $(this).addClass('has-val');
        }
        else {
            $(this).removeClass('has-val');
        }
    })    
})
 
 let teacherForm = document.getElementById('teacherForm');
 let studentForm = document.getElementById('studentForm');
 
 document.querySelector('.img__btn').addEventListener('click', function() {
 document.querySelector('.cont').classList.toggle('s--signup');
});

teacherForm.addEventListener('submit', function (e) {
    e.preventDefault();
    
    const teacherId = document.querySelector('#teacherId').value.trim();
    const teacherPassword = document.querySelector('#teacherPassword').value.trim();
   
    $.ajax({
            url: "/member/teacher/loginProc"
           ,type: "POST"
           ,data: { teacherId : teacherId, teacherPassword : teacherPassword}
           ,success: function(result){
                if(result == "fail"){
                    alert('일치하는 회원정보가 없습니다.\n아이디나 비밀번호를 다시 한번 확인해주세요.')
                }else{
                    teacherForm.submit();
                }
            },
            error: function(){
                console.log('에러입니다');
            }
        });
});

studentForm.addEventListener('submit', function (e) {
    e.preventDefault();
    
    const studentId = document.querySelector('#studentId').value.trim();
    const studentPassword = document.querySelector('#studentPassword').value.trim();
   
    $.ajax({
            url: "/member/student/loginProc"
           ,type: "POST"
           ,data: { studentId : studentId, studentPassword : studentPassword}
           ,success: function(result){
                if(result == "fail"){
                    alert('일치하는 회원정보가 없습니다.\n아이디나 비밀번호를 다시 한번 확인해주세요.')
                }else{
                    studentForm.submit();
                }
            },
            error: function(){
                console.log('에러입니다');
            }
        });
});