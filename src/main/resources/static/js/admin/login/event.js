const auto_login_checkbox = document.getElementById("auto-login-checkbox");
const auto_login_icon = document.querySelector(".kok-check-box i");
const modal = document.querySelector(".kok-modal");
const modal_Inner = document.querySelector(".modal-inner");
const modalButton = document.getElementById("kok-modal-check-button");
const loginButton = document.querySelector(".btn-login");
const email = document.querySelector('input[name="kok"]');
const pw = document.querySelector('input[name="password"]');
const modalBody = document.querySelector(".modal-body");
if(window.location.search.includes("error")) {
    modalBody.textContent =
        "가입된 회원정보가 없거나 비밀번호가 잘못 입력되었습니다.";
    modal.classList.add("active");
    modal_Inner.style.animation = "popUp 0.1s";
}

// 자동로그인 클릭 이벤트
auto_login_checkbox.addEventListener("change", () => {
    auto_login_icon.style.display = auto_login_checkbox.checked
        ? "block"
        : "none";
});
loginButton.addEventListener("click", async (e) => {
    // js 확인용
    e.preventDefault();

    if (!email.value.trim()) {
        modalBody.textContent = "이메일을 입력해주세요.";
        modal.classList.add("active");
        modal_Inner.style.animation = "popUp 0.1s";
        return;
    }

    if (!pw.value.trim()) {
        modalBody.textContent = "비밀번호를 입력해주세요.";
        modal.classList.add("active");
        modal_Inner.style.animation = "popUp 0.1s";
        return;
    }

    if(email.value.trim()&&pw.value.trim()){
        const userEmail = email.value;
        const password = pw.value;
        const role = 'admin'
        await adminService.login({userEmail: userEmail,userPassword:password,userRole: role});

        location.href='/admin'
    }

});

// 모달 확인 버튼 닫기
modalButton.addEventListener("click", (e) => {
    e.preventDefault();
    modal.classList.remove("active");
});
