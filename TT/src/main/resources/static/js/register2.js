

const userIdEl = document.querySelector('#userId');
const passwordEl = document.querySelector('#password');

const form = document.querySelector('#register_form');

form.addEventListener('submit', function (e) {
    // prevent the form from submitting
    e.preventDefault();
});    

const isRequired = value => value === '' ? false : true;
const isBetween = (length, min, max) => length < min || length > max ? false : true;
const isPasswordSecure = (password) => {
    const re = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,16})");
    return re.test(password);
};


const showError = (input, message) => {
    // get the form-field element
    const formField = input.parentElement;
    // add the error class
    formField.classList.remove('success');
    formField.classList.add('error');

    // show the error message
    const error = formField.querySelector('small');
    error.textContent = message;
};

const showSuccess = (input) => {
    // get the form-field element
    const formField = input.parentElement;

    // remove the error class
    formField.classList.remove('error');
    formField.classList.add('success');

    // hide the error message
    const error = formField.querySelector('small');
    error.textContent = '';
}

const checkUserId = () => {
    
    let valid = false;
    const min = 6,
        max = 12;
    const userId = userIdEl.value;

    if (!isRequired(userId)) {
        showError(userIdEl, '아이디를 입력해주세요');
    } else if (!isBetween(userId.length, min, max)) {
        showError(userIdEl, `아이디는 ${min} ~ ${max} 자리여야 합니다`)
    } else {
        showSuccess(userIdEl);
        valid = true;
    }
    console.log(valid);
    return valid;
}

const checkPassword = () => {

    let valid = false;

    const password = passwordEl.value.trim();

    if (!isRequired(password)) {
        showError(passwordEl, '비밀번호를 입력해주세요');
    } else if (!isPasswordSecure(password)) {
        showError(passwordEl, '비밀번호는 대문자+소문자+숫자+특수문자 조합의 8~16자리여야 합니다');
    } else {
        showSuccess(passwordEl);
        valid = true;
    }

    return valid;
};


const button = document.querySelector("#register_form");
form.addEventListener('submit', function (e) {
    // prevent the form from submitting
    e.preventDefault();

    // validate forms
    let isUserIdValid = checkUserId(),
        isPasswordValid = checkPassword();

    let isFormValid = isUserIdValid &&
        isPasswordValid;

    // submit to the server if the form is valid
    if (isFormValid) {
    }
});

form.addEventListener('input', function (e) {
    switch (e.target.id) {
        case 'userId':
            checkUserId();
            break;
        case 'password':
            checkPassword();
            break;
    }
});

