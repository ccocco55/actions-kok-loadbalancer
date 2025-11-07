// 비밀번호 확인
const inputPassword = document.getElementById('input-password');
const inputPasswordCheck = document.getElementById('input-password-check');
const submitButton = document.querySelector('.btn-submit');
const toastPassword = document.getElementById('toast-password');
const toastPasswordCheck = document.getElementById('toast-wrong-password');

// 비밀번호 공란 토스트
function showPasswordErrorToast() {
    toastPassword.classList.add("show");
    setTimeout(() => {
        toastPassword.classList.remove("show");
    }, 3000);
}
// 비밀번호 입력 오류 토스트
function showPasswordCheckErrorToast() {
    toastPasswordCheck.classList.add("show");
    setTimeout(() => {
        toastPasswordCheck.classList.remove("show");
    }, 3000);
}

// 토스트
submitButton.addEventListener("click", (e) => {
    if (inputPassword.value.length < 4) {
        showPasswordErrorToast();
    } else if (inputPassword.value !== inputPasswordCheck.value) {
        showPasswordCheckErrorToast();
    }
});

// 버튼 활성화/비활성화
function submitButtonAction() {
    const password = inputPassword.value.trim().length > 3;
    const passwordCheck = (inputPasswordCheck.value.trim() === inputPassword.value.trim());

    if (password && passwordCheck) {
        submitButton.classList.remove("inactive");
    } else {
        submitButton.classList.add("inactive");
        submitButton.removeAttribute("disabled")
    }
}
inputPassword.addEventListener("input", submitButtonAction);
inputPasswordCheck.addEventListener("input", submitButtonAction);
const email = document.getElementById("email");
console.log(`${userDTO.userEmail}`);
email.setAttribute("value",`${userDTO.userEmail}`);