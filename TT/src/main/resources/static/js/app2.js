const usernameEl = document.querySelector('#username');
const emailEl = document.querySelector('#email');
const passwordEl = document.querySelector('#password');
const confirmPasswordEl = document.querySelector('#confirm-password');
const nameEl = document.querySelector('#name');
const birthEl = document.querySelector('#birth');
const phoneEl = document.querySelector('#phone');


const form = document.getElementById("signup-form");




const checkBirth = () => {

    let valid = false;
    const birth = birthEl.value.trim();
    
    if (!isRequired(birth)) {
        showError(birthEl, '생년월일은 필수 항목입니다.');
    } else if (!isBirthValid(birth)) {
        showError(birthEl, '생년월일은 숫자 8자리만 가능합니다.')
    } else {
        showSuccess(birthEl);
        valid = true;
    }
    return valid;
};

const checkPhone = () => {

    let valid = false;
    const phone = phoneEl.value.trim();
    
    if (!isRequired(phone)) {
        showError(phoneEl, '핸드폰번호 필수 항목입니다.');
    } else if (!isPhoneValid(phone)) {
        showError(phoneEl, '핸드폰번호는 숫자 10~11글자만 가능합니다.')
    } else {
        showSuccess(phoneEl);
        valid = true;
    }
    return valid;
};

const checkUsername = () => {

    let valid = false;
    const username = usernameEl.value.trim();
    
    if (!isRequired(username)) {
        showError(usernameEl, '아이디는 필수 항목입니다.');
    } else if (!isUsernameValid(username)) {
        showError(usernameEl, '아이디는 영문 6~12글자만 가능합니다.')
    } else {
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

const checkPassword = () => {
    let valid = false;


    const password = passwordEl.value.trim();

    if (!isRequired(password)) {
        showError(passwordEl, '비밀번호는 필수 항목입니다.');
    } else if (!isPasswordSecure(password)) {
        showError(passwordEl, '비밀번호는 영문 소문자+대문자+특수문자(!@#$%^&*) 조합의 8~10글자만 가능합니다');
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
        showError(confirmPasswordEl, 'Please enter the password again');
    } else if (password !== confirmPassword) {
        showError(confirmPasswordEl, 'The password does not match');
    } else {
        showSuccess(confirmPasswordEl);
        valid = true;
    }

    return valid;
};

const isUsernameValid = (username) => {
    const re = /^[a-z]{6,12}$/;
    return re.test(username);
};

const isNameValid = (name) => {
    const re = /^[가-힣]{2,4}$/;
    return re.test(name);
};

const isPhoneValid = (phone) => {
    const re = /^[0-9]{10,11}$/;
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
    const re = new RegExp("^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,10})");
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
        case 'birth':
            checkBirth();
            break;      
        case 'phone':
            checkPhone();
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


form.addEventListener('submit', function (e) {
    // prevent the form from submitting
    e.preventDefault();

    // validate fields
    let isUsernameValid = checkUsername(),
        isEmailValid = checkEmail(),
        isPasswordValid = checkPassword(),
        isConfirmPasswordValid = checkConfirmPassword();

    let isFirstStepValid = isUsernameValid &&  isPasswordValid &&
        isConfirmPasswordValid;

    let isFormValid = isUsernameValid &&
        isEmailValid &&
        isPasswordValid &&
        isConfirmPasswordValid;

    // submit to the server if the form is valid
    if (isFormValid) {

    }
});

const debounce = (fn, delay = 500) => {
    let timeoutId;
    return (...args) => {
        // cancel the previous timer
        if (timeoutId) {
            clearTimeout(timeoutId);
        }
        // setup a new timer
        timeoutId = setTimeout(() => {
            fn.apply(null, args)
        }, delay);
    };
};
 


