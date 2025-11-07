// 이메일 검사
const isValidEmail = (value) => {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value.trim());
};
const inputName = document.getElementById('input-name');
const inputEmail = document.getElementById('input-email');
const toastName = document.getElementById('toast-name');
const toastEmail = document.getElementById('toast-email');
const toastFailMember = document.getElementById('toast-fail-member');
const buttonSubmit = document.querySelector('.btn-submit');

// 이름 오류 토스트
function showNameErrorToast() {
    toastName.classList.add("show");
    setTimeout(() => {
        toastName.classList.remove("show");
    }, 3000);
}
// 이메일 오류 토스트
function showEmailErrorToast() {
    toastEmail.classList.add("show");
    setTimeout(() => {
        toastEmail.classList.remove("show");
    }, 3000);
}
// 회원정보 오류 토스트
function showMemberErrorToast() {
    toastFailMember.classList.add("show");
    setTimeout(() => {
        toastFailMember.classList.remove("show");
    });
}

// 버튼 활성화/비활성화
function submitButtonAction() {
    const nameCheck = inputName.value.trim().length > 0;
    const emailCheck = isValidEmail(inputEmail.value.trim());

    if (nameCheck && emailCheck) {
        buttonSubmit.classList.remove("inactive");
    } else {
        buttonSubmit.classList.add("inactive");
    }
}
inputName.addEventListener("input", submitButtonAction);
inputEmail.addEventListener("input", submitButtonAction);

// 회원정보가 일치할 경우 아래 이벤트 실행
// buttonSubmit.addEventListener("click", (e) => {
//     buttonSubmit.classList.add("check-inactive");
// });

// 토스트
buttonSubmit.addEventListener("click", (e) => {
    if (inputName.value.length < 1 && inputEmail.value.length < 1) {
        showNameErrorToast();
    } else if (inputName.value.length < 1 && inputEmail.value.length !== 0) {
        showNameErrorToast();
    } else if (inputName.value.length !== 0 && !isValidEmail(inputEmail.value)) {
        showEmailErrorToast();
    }
});