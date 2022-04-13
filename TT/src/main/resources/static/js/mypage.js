/**
 * teacher
 */
const passwordEl = document.querySelector('#password');
const confirmPasswordEl = document.querySelector('#confirm-password');
const phoneEl = document.querySelector('#phone'); 
const subjectEl = document.querySelector('#subject');
const majorEl = document.querySelector('#major');

const form = document.getElementById("mypageForm");



const checkPassword = () => {
    let valid = false;


    const password = passwordEl.value.trim();

    if (!isRequired(password)) {
        showError(passwordEl, '새로운 비밀번호는 필수 항목입니다. 기존과 동일한 비밀번호도 가능합니다.');
    } else if (!isPasswordSecure(password)) {
        showError(passwordEl, '비밀번호는 영문소문자+숫자+특수문자(!@#$%^&*) 조합의 8~16자리만 가능합니다.');
    } else {
        showSuccess(passwordEl);
        valid = true;
    }

    return valid;
};

const checkConfirmPassword = () => {
    let valid = false;
    // check confirm password
    const confirmPassword = confirmPasswordEl.value.trim();
    const password = passwordEl.value.trim();

    if (!isRequired(confirmPassword)) {
        showError(confirmPasswordEl, '비밀번호 확인은 필수 항목입니다.');
    } else if (password !== confirmPassword) {
        showError(confirmPasswordEl, '비밀번호와 비밀번호 확인이 서로 일치하지 않습니다.');
    } else {
        showSuccess(confirmPasswordEl);
        valid = true;
    }

    return valid;
};

const checkPhone = () => {

    let valid = false;
    const phone = phoneEl.value.trim();
    
    if (!isRequired(phone)) {
        showError(phoneEl, '핸드폰 번호는 필수 항목입니다.');
    } else if (!isPhoneValid(phone)) {
        showError(phoneEl, '유효한 핸드폰 번호 형식이 아닙니다.')
    } else {
        showSuccess(phoneEl);
        valid = true;
    }
    return valid;
};

const checkSubject = () => {

    let valid = false;
    const subject = subjectEl.value.trim();
    
    if (!isRequired(subject)) {
        showError(subjectEl, '주요과목은 필수 항목입니다.');
    } else {
        showSuccess(subjectEl);
        valid = true;
    }
    return valid;
};

const checkMajor = () => {

    let valid = false;
    const major = majorEl.value.trim();
    
    if (!isRequired(major)) {
        showError(majorEl, '전공은 필수 항목입니다.');
    } else {
        showSuccess(majorEl);
        valid = true;
    }
    return valid;
};

const isPhoneValid = (phone) => {
    const re = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    return re.test(phone);
};

const isPasswordSecure = (password) => {
    const re = new RegExp("^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,16})");
    return re.test(password);
};

const isRequired = value => value === '' ? false : true;
const isBetween = (length, min, max) => length < min || length > max ? false : true;

form.addEventListener('input', function (e) {
    switch (e.target.id) {
        case 'password':
            checkPassword();
            break;
        case 'confirm-password':
            checkConfirmPassword();
            break;
        case 'phone':
            checkPhone();
            break;   
        case 'email':
            checkEmail();
            break;
        case 'subject':
            checkSubject();
            break;
        case 'major':
            checkMajor();
            break;  
    }
});

const showError = (input, message) => {
    // get the form-field element
    const formField = input.parentElement;
    // add the error class
    input.classList.remove('is-valid');
    input.classList.add('is-invalid');

    // show the error message
    const error = formField.querySelector('small');
    error.textContent = message;
};

const showSuccess = (input) => {
    // get the form-field element
    const formField = input.parentElement;

    // remove the error class
    input.classList.remove('is-invalid');
    input.classList.add('is-valid');

    // hide the error message
    const error = formField.querySelector('small');
    error.textContent = '';
}

$('#teacherSave').click(function(e) {
    // prevent the form from submitting
    e.preventDefault();
    
    // validate fields
    let isPasswordValid = checkPassword(),
        isConfirmPasswordValid = checkConfirmPassword(),
        isPhoneValid = checkPhone(),
        isMajorValid = checkMajor(),
        isSubjectValid = checkSubject();
        

    let isFormValid = 
        isPasswordValid &&
        isConfirmPasswordValid &&
        isPhoneValid &&
        isMajorValid &&
        isSubjectValid;

 // submit to the server if the form is valid
    if(isFormValid) {
        form.submit();
        alert('정보가 성공적으로 수정되었습니다.');
    }   
});

$('#teacherDelete').click(function(e) {
    e.preventDefault();
    
    if(confirm('탈퇴를 신청하실 경우 즉시 탈퇴처리가 진행되어 모든 서비스 이용이 중지됩니다.\n또한 해당 아이디와 이메일로는 재가입이 불가합니다.\n정말로 탈퇴하시겠습니까?')){
                        
        const username = document.querySelector('#teacherId').value.trim();
        console.log(username);
        $.ajax({
                url: "/member/teacher/deleteAccount"
               ,type: "POST"
               ,data: { username : username }
               ,success: function(result){
                    if(result == true){
                        alert('탈퇴가 정상적으로 완료되었습니다. 안녕히 가십시오.')
                        location.href = '/member/login'
                    }
                },
                error: function(){
                    console.log('에러입니다');
                }
            });
      } return;      
});

$('#studentSave').click(function(e) {
    // prevent the form from submitting
    e.preventDefault();
    
    // validate fields
    let isPasswordValid = checkPassword(),
        isConfirmPasswordValid = checkConfirmPassword(),
        isPhoneValid = checkPhone();
        

    let isFormValid = 
        isPasswordValid &&
        isConfirmPasswordValid &&
        isPhoneValid;

 // submit to the server if the form is valid
    if(isFormValid) {
        form.submit();
        alert('정보가 성공적으로 수정되었습니다.');
    }   
});

$('#studentDelete').click(function(e) {
    e.preventDefault();
    
    if(confirm('탈퇴를 신청하실 경우 즉시 탈퇴처리가 진행되어 모든 서비스 이용이 중지됩니다.\n또한 해당 아이디와 이메일로는 재가입이 불가합니다.\n정말로 탈퇴하시겠습니까?')){
                        
        const username = document.querySelector('#studentId').value.trim();
        console.log(username);
        $.ajax({
                url: "/member/student/deleteAccount"
               ,type: "POST"
               ,data: { username : username }
               ,success: function(result){
                    if(result == true){
                        alert('탈퇴가 정상적으로 완료되었습니다. 안녕히 가십시오.')
                        location.href = '/member/login'
                    }
                },
                error: function(){
                    console.log('에러입니다');
                }
            });
      } return;      
});

/**
 * 프로필 변경
 */
 $('.uploadBtn').click(function(){
        let formData = new FormData();
        let inputFile = $("input[type='file']");
        let files = inputFile[0].files;
        
        for(let i=0; i<files.length; i++){
            console.log(files[0]);
            formData.append("uploadFile", files[0]);
       }
        
        //실제 업로드 부분
        //upload ajax
        $.ajax({
            url: '/uploadAjax',
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            dataType: 'json',
            success: function(image){
                //나중에 화면 처리
                //showUploadedImage(result);
                $.ajax({
                    url: "/member/teacher/updateProfile"
                   ,type: "POST"
                   ,data: { image : image.thumbnailURL}
                   ,success: function(result){
                        if(result == 0){
                            console.log('프로필 변경 실패');
                        }
                         alert('프로필 사진이 성공적으로 변경되었습니다!')
                         location.reload();
                        
                    },
                    error: function(){
                        console.log('에러입니다');
                    }
                });
            },
            error: function(jqXHR, textStatus, errorThrown){
                console.log(textStatus);
            }
        }); //$.ajax 
        
        function showUploadedImage(image){
            
            
            console.log(image);
            
            let divArea = $(".uploadResult");
            
            let str = "";
                
                str += "<div>";
                str += "<img src='/display?fileName="+image.thumbnailURL+"'>";
                str += "<button class='removeBtn' data-name='"+image.imageURL+"' >REMOVE</button>";
                str += "</div>";
            
            divArea.append(str);
        }
    });
    
    $(".uploadResult").on("click", ".removeBtn", function(e){
        
        let target = $(this);
        let fileName = target.data("name");
        let targetDiv = $(this).closest("div");
        
        console.log(fileName);
        
        $.post('/removeFile', {fileName: fileName}, function(result){
            console.log(result);
            if(result === true){
                targetDiv.remove();
            }
        } )
    });
   
   

