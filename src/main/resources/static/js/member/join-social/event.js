// 토스트 - 입력 오류
HTMLCollection.prototype.forEach = Array.prototype.forEach;
const submitButton = document.querySelector(".submit-button");

const toastName = document.getElementById("toast-name");
const toastNumber = document.getElementById("toast-number");
const toastEmail = document.getElementById("toast-email");
const toastPassword = document.getElementById("toast-password");

const inputName = document.getElementById("input-name");
const inputNumber = document.getElementById("input-number");
const inputEmail = document.getElementById("input-email");
const inputPassword = document.getElementById("input-password");

// 전화번호 포맷팅
// function formatPhoneNumber(input) {
//     input.value = input.value
//         .replace(/[^0-9]/g, '')
//         .replace(/(^02|^01[0-9]|[0-9]{3})([0-9]+)?([0-9]{4})$/, '$1 $2 $3');
// }

// 이름 오류 토스트
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


submitButton.addEventListener("click", (e) => {
    // 이름 공란
    if (inputName.value.length === 0) {
        showNameErrorToast();
    // 전화번호 공란 또는 길이 오류
    } else if (inputNumber.value.trim().length < 11) {
        showNumberErrorToast();
    }
});

const socialBOx = document.getElementsByClassName("social-box");
const provider = location.search.split("=")[1];
socialBOx.forEach((social)=>{
    const id = social.id.split("-")[0];
    console.log(id)
    console.log(provider)
    if(id === provider){
        social.classList.add("active");
    }else{
        social.classList.remove("active");
    }
})