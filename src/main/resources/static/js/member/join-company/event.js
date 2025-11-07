// 토스트 - 입력 오류
const submitButton = document.querySelector(".submit-button");

const toastCompanyName = document.getElementById("toast-company-name");
const toastName = document.getElementById("toast-name");
const toastNumber = document.getElementById("toast-number");
const toastEmail = document.getElementById("toast-email");
const toastPassword = document.getElementById("toast-password");
const toastCompanyNumber = document.getElementById("toast-company-number");

const inputCompanyName = document.getElementById("input-company-name");
const inputName = document.getElementById("input-name");
const inputNumber = document.getElementById("input-number");
const inputEmail = document.getElementById("input-email");
const inputPassword = document.getElementById("input-password");
const inputCompanyNumber = document.getElementById("input-company-number");

// 이메일 검사
const isValidEmail = (value) => {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value.trim());
};
// 전화번호 포맷팅
// function formatPhoneNumber(input) {
//     input.value = input.value
//         .replace(/[^0-9]/g, '')
//         .replace(/(^02|^01[0-9]|[0-9]{3})([0-9]+)?([0-9]{4})$/, '$1 $2 $3');
// }




// 기업 이름 오류 토스트
function showCompanyNameErrorToast() {
    toastCompanyName.classList.add("show");
    setTimeout(() => {
        toastCompanyName.classList.remove("show");
    }, 3000);
}
// 대표자 이름 오류 토스트
function showNameErrorToast() {
    toastName.classList.add("show");
    setTimeout(() => {
        toastName.classList.remove("show");
    }, 3000);
}
// 전화번호 오류 토스트
function showNumberErrorToast() {
    toastNumber.classList.add("show");
    setTimeout(() => {
        toastNumber.classList.remove("show");
    }, 3000);
}
// 이메일 오류 토스트
function showEmailErrorToast() {
    toastEmail.classList.add("show");
    setTimeout(() => {
        toastEmail.classList.remove("show");
    }, 3000);
}
// 비밀번호 오류 토스트
function showPasswordErrorToast() {
    toastPassword.classList.add("show");
    setTimeout(() => {
        toastPassword.classList.remove("show");
    }, 3000);
}
// 사업자 번호 오류 토스트
function showCompanyNumberErrorToast() {
    toastCompanyNumber.classList.add("show");
    setTimeout(() => {
        toastCompanyNumber.classList.remove("show");
    }, 3000);
}


submitButton.addEventListener("click", (e) => {
    // 기업 이름 공란
    if (inputCompanyName.value.length === 0) {
        showCompanyNameErrorToast();
    // 대표자 이름 공란
    } else if (inputName.value.length === 0) {
        showNameErrorToast();
    // 전화번호 공란 또는 길이 오류
    } else if (inputNumber.value.trim().length < 11) {
        showNumberErrorToast();
    // 이메일 공란 또는 형식 오류
    } else if (inputEmail.value.length === 0 || !isValidEmail(inputEmail.value)) {
        showEmailErrorToast();
    // 비밀번호 공란 또는 길이 오류
    } else if (inputPassword.value.trim().length < 4) {
        showPasswordErrorToast();
    // 사업자 번호 공란 또는 형식 오류
    } else if (inputCompanyNumber.value.trim().length < 12) {
        showCompanyNumberErrorToast();
    }
});