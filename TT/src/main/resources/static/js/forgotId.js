const nameEl = document.querySelector('#name');
const emailEl = document.querySelector('#email');

const form = document.getElementById("hint-form");



const checkName = () => {

    let valid = false;
    const name = nameEl.value.trim();
    
    if (!isRequired(name)) {
        showError(nameEl, '이름은 필수 항목입니다.');
    } else if (!isNameValid(name)) {
        showError(nameEl, '이름은 한글 2~4글자만 가능합니다.')
    } else {
        showSuccess(nameEl);
        valid = true;
    }
    return valid;
};

const checkEmail = () => {
    let valid = false;
    const email = emailEl.value.trim();
    if (!isRequired(email)) {
        showError(emailEl, '이메일은 필수 항목입니다.');
    } else if (!isEmailValid(email)) {
        showError(emailEl, '유효한 이메일 형식이 아닙니다.')
    } else {
       showSuccess(emailEl);
       valid = true;
    } 
    return valid;
};


const isNameValid = (name) => {
    const re = /^[가-힣]{2,4}$/;
    return re.test(name);
};

const isEmailValid = (email) => {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
};

const isRequired = value => value === '' ? false : true;
const isBetween = (length, min, max) => length < min || length > max ? false : true;

form.addEventListener('input', function (e) {
    switch (e.target.id) {
        case 'name':
            checkName();
            break;
        case 'email':
            checkEmail();
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

// submit to the server if the form is valid
$('#teacherSubmit').click(function(e) {
    e.preventDefault();
    
     // validate fields
    let isNameValid = checkName(),
        isEmailValid = checkEmail();
       
    let isFormValid = 
        isNameValid && isEmailValid;
    
    if(isFormValid) {
    const name = nameEl.value.trim();
    const email = emailEl.value.trim();
        $.ajax({
            url: "/member/findTeacherId"
           ,type: "POST"
           ,data: { name : name, email : email}
           ,success: function(result){
                if(result == 0){
                    alert('일치하는 회원정보가 없습니다.\n입력하신 정보를 다시 한번 확인해주세요.')
                }else{
                    if(confirm('회원님의 아이디는 '+result+' 입니다.\n로그인 화면으로 돌아가시겠습니까?')){
                        location.href='/member/login'
                    } return;
                }
            },
            error: function(){
                console.log('에러입니다');
            }
        });
    }
});

$('#studentSubmit').click(function(e) {
    e.preventDefault();
    
     // validate fields
    let isNameValid = checkName(),
        isEmailValid = checkEmail();
       
    let isFormValid = 
        isNameValid && isEmailValid;
    
    if(isFormValid) {
    const name = nameEl.value.trim();
    const email = emailEl.value.trim();
        $.ajax({
            url: "/member/findStudentId"
           ,type: "POST"
           ,data: { name : name, email : email}
           ,success: function(result){
                if(result == 0){
                    alert('일치하는 회원정보가 없습니다.\n입력하신 정보를 다시 한번 확인해주세요.')
                }else{
                    if(confirm('회원님의 아이디는 '+result+' 입니다.\n로그인 화면으로 돌아가시겠습니까?')){
                        location.href='/member/login'
                    } return;
                }
            },
            error: function(){
                console.log('에러입니다');
            }
        });
    }
});