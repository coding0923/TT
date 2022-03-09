const usernameEl = document.querySelector('#username');
const nameEl = document.querySelector('#name');
const emailEl = document.querySelector('#email');

const form = document.getElementById("hint-form");

const checkUsername = () => {

    let valid = false;
    const username = usernameEl.value.trim();
    
    if (!isRequired(username)) {
        showError(usernameEl, '아이디는 필수 항목입니다.');
    } else if (!isUsernameValid(username)) {
        showError(usernameEl, '아이디는 6~12자리의 영문 소문자, 숫자만 가능하며 첫글자는 반드시 영문이어야 합니다.')
    } else{
        showSuccess(usernameEl);
        valid = true;
    } 
    return valid;
};

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

const isUsernameValid = (username) => {
    const re = /^[a-z]+[a-z0-9]{5,11}$/;
    return re.test(username);
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
        case 'username':
            checkUsername();
            break;
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
    // prevent the form from submitting
    e.preventDefault();

    // validate fields
    let isUsernameValid = checkUsername(),
        isNameValid = checkName(),
        isEmailValid = checkEmail();
       
    let isFormValid = 
         isUsernameValid && isNameValid && isEmailValid;

    if(isFormValid) {
    const username = usernameEl.value.trim();
    const name = nameEl.value.trim();
    const email = emailEl.value.trim();
        $.ajax({
            url: "/member/findTeacherPw"
           ,type: "POST"
           ,data: { username : username, name : name, email : email}
           ,success: function(result){
                if(result == 0){
                    alert('일치하는 회원정보가 없습니다. \n입력하신 정보를 다시 한번 확인해주세요.')
                }else{
                    alert('비밀번호가 초기화되어 해당 이메일로 임시 비밀번호가 발급되었습니다. \n임시 비밀번호로 로그인을 진행하신 후 비밀번호를 새로 변경해주세요.')
                    location.href = '/member/login'
                }
            },
            error: function(){
                console.log('에러입니다');
            }
        });
    }   
});

// submit to the server if the form is valid
$('#studentSubmit').click(function(e) {
    // prevent the form from submitting
    e.preventDefault();

    // validate fields
    let isUsernameValid = checkUsername(),
        isNameValid = checkName(),
        isEmailValid = checkEmail();
       
    let isFormValid = 
         isUsernameValid && isNameValid && isEmailValid;

    if(isFormValid) {
    const username = usernameEl.value.trim();
    const name = nameEl.value.trim();
    const email = emailEl.value.trim();
        $.ajax({
            url: "/member/findStudentPw"
           ,type: "POST"
           ,data: { username : username, name : name, email : email}
           ,success: function(result){
                if(result == 0){
                    alert('일치하는 회원정보가 없습니다. \n입력하신 정보를 다시 한번 확인해주세요.')
                }else{
                    alert('비밀번호가 초기화되어 해당 이메일로 임시 비밀번호가 발급되었습니다. \n임시 비밀번호로 로그인을 진행하신 후 비밀번호를 새로 변경해주세요.')
                    location.href = '/member/login'
                }
            },
            error: function(){
                console.log('에러입니다');
            }
        });
    }   
});
