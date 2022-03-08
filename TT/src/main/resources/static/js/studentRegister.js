const usernameEl = document.querySelector('#username');
const emailEl = document.querySelector('#email');
const passwordEl = document.querySelector('#password');
const confirmPasswordEl = document.querySelector('#confirm-password');
const nameEl = document.querySelector('#name');
const birthdateEl = document.querySelector('#birthdate');
const phoneEl = document.querySelector('#phone');
const codeEl = document.querySelector('#code');
const subjectEl = document.querySelector('#subject');
const majorEl = document.querySelector('#major');
const addr1El = document.querySelector('#addr1');


const form = document.getElementById("hint-form");

let checkUsernameValid = false;
let checkEmailValid = false;
let checkCodeValid = false;


const checkBirthdate = () => {

    let valid = false;
    const birthdate = birthdateEl.value.trim();
    
    if (!isRequired(birthdate)) {
        showError(birthdateEl, '생년월일은 필수 항목입니다.');
    } else {
        showSuccess(birthdateEl);
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

const checkUsername = () => {

    const username = usernameEl.value.trim();
    
    if (!isRequired(username)) {
        showError(usernameEl, '아이디는 필수 항목입니다.');
        checkUsernameValid = false;
    } else if (!isUsernameValid(username)) {
        showError(usernameEl, '아이디는 6~12자리의 영문 소문자, 숫자만 가능하며 첫글자는 반드시 영문이어야 합니다.')
        checkUsernameValid = false;
    } else {
       $.ajax({
            url: '/member/idCheck',
            type: 'POST',
            data: {teacherId :username},
            success: function(result){
                if(result == 0){
                    showError(usernameEl, '이미 사용중인 아이디입니다.');
                    checkUsernameValid = false;
                }else{
                    showSuccess(usernameEl);
                    checkUsernameValid = true;
                }
            },
            error: function(){
                console.log('에러입니다');
                checkUsernameValid = false;
            }
        })
    } 
    return checkUsernameValid;
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


const checkEmail = () => {
    
    const email = emailEl.value.trim();
    if (!isRequired(email)) {
        showError(emailEl, '이메일은 필수 항목입니다.');
        checkEmailValid = false;
    } else if (!isEmailValid(email)) {
        showError(emailEl, '유효한 이메일 형식이 아닙니다.')
        checkEmailValid = false;
    } else {
       $.ajax({
            url: '/member/emailCheck',
            type: 'POST',
            data: {teacherEmail :email},
            success: function(result){
                if(result == 0){
                    showError(emailEl, '이미 사용중인 이메일입니다.');
                    checkEmailValid = false;
                }else{
                    showSuccess(emailEl);
                    checkEmailValid = true;
                }
            },
            error: function(){
                console.log('에러입니다');
                checkEmailValid = false;
            }
        });
    } 
    return checkEmailValid;
};

const emailSend = () => {
   let valid = false;
   const email = document.querySelector('#email').value.trim();
   
   $.ajax({
       url: "/service/mail"
       ,type: "POST"
       ,data: {email :email}
       ,success: function(data) {

           alert("입력하신 메일주소로 인증코드가 전송되었습니다! \n메일을 확인하신 후 인증코드를 입력해주세요");
           valid = true;
       },

       error: function(jqXHR,textStatus,errorThrown) {
           alert("이메일 전송에 실패하였습니다.");
       }
   });
   return valid;
}

const checkCode = () => {
    
    const code = codeEl.value.trim();
    if (!isRequired(code)) {
        showError(codeEl, '인증코드입력은 필수 항목입니다.');
        checkCodeValid = false;
    } else {
       $.ajax({
            url: "/service/verifyCode"
           ,type: "POST"
           ,data: {code : code}
           ,success: function(result){
                if(result != 0){
                    showError(codeEl, '잘못된 인증코드입니다.');
                    checkCodeValid = false;
                }else{
                    showSuccess(codeEl);
                    checkCodeValid = true;
                }
            },
            error: function(){
                console.log('에러입니다');
                checkCodeValid = false;
            }
        });
    } 
    return checkCodeValid;
}; 
    

const checkPassword = () => {
    let valid = false;


    const password = passwordEl.value.trim();

    if (!isRequired(password)) {
        showError(passwordEl, '비밀번호는 필수 항목입니다.');
    } else if (!isPasswordSecure(password)) {
        showError(passwordEl, '비밀번호는 영문소문자+숫자+특수문자(!@#$%^&*) 조합의 8~16자리만 가능합니다');
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

const isUsernameValid = (username) => {
    const re = /^[a-z]+[a-z0-9]{5,11}$/;
    return re.test(username);
};

const isNameValid = (name) => {
    const re = /^[가-힣]{2,4}$/;
    return re.test(name);
};

const isPhoneValid = (phone) => {
    const re = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    return re.test(phone);
};

const isBirthValid = (birth) => {
    const re = /^[0-9]{8,8}$/;
    return re.test(birth);
};

const isEmailValid = (email) => {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
};

const isPasswordSecure = (password) => {
    const re = new RegExp("^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,16})");
    return re.test(password);
};

const isRequired = value => value === '' ? false : true;
const isBetween = (length, min, max) => length < min || length > max ? false : true;

form.addEventListener('input', function (e) {
    switch (e.target.id) {
        case 'username':
            checkUsername();
            break;
        case 'password':
            checkPassword();
            break;
        case 'confirm-password':
            checkConfirmPassword();
            break;
        case 'name':
            checkName();
            break;
        case 'birthdate':
            checkBirthdate();
            break;      
        case 'phone':
            checkPhone();
            break;   
        case 'email':
            checkEmail();
            break;
        case 'code':
            checkCode();
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


form.addEventListener('submit', function (e) {
    // prevent the form from submitting
    e.preventDefault();
    
    const username = usernameEl.value.trim();
    if (!isRequired(username)) {
        showError(usernameEl, '아이디는 필수 항목입니다.');
        checkUsernameValid = false;
        }
   
    const email = emailEl.value.trim();
    if (!isRequired(email)) {
        showError(emailEl, '이메일은 필수 항목입니다.');
        checkEmailValid = false; 
        }
        
    const code = codeEl.value.trim();
    if (!isRequired(code)) {
        showError(codeEl, '인증코드입력은 필수 항목입니다.');
        checkCodeValid = false;  
        }     

    // validate fields
    let isUsernameValid = checkUsernameValid,
        isPasswordValid = checkPassword(),
        isConfirmPasswordValid = checkConfirmPassword(),
        isEmailValid = checkEmailValid,
        isCodeValid = checkCodeValid,
        isNameValid = checkName(),
        isPhoneValid = checkPhone(),
        isMajorValid = checkMajor(),
        isSubjectValid = checkSubject(),
        isBirthdateValid = checkBirthdate();
        


    let isFormValid = 
        isUsernameValid &&
        isPasswordValid &&
        isConfirmPasswordValid &&
        isEmailValid &&
        isCodeValid &&
        isNameValid &&
        isPhoneValid &&
        isMajorValid &&
        isSubjectValid &&
        isBirthdateValid;
        
        
        
        console.log("폼"+isFormValid);
        console.log(isUsernameValid);//
        console.log(isPasswordValid);
        console.log(isConfirmPasswordValid);
        console.log(isEmailValid);//
        console.log(isCodeValid);//
        console.log(isNameValid);
        console.log(isPhoneValid);
        console.log(isMajorValid);
        console.log(isSubjectValid);

 // submit to the server if the form is valid
    if(isFormValid) {
        console.log('뭐지');
        form.submit();
        alert('가입이 완료되었습니다. 로그인을 새로 진행해주세요.');
    }   
});