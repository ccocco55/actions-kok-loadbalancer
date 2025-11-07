memberService.reset();

// 로그인 방법 선택
const inputContainer = document.querySelector('.input-container');
const generalMember = document.querySelector('.general-member');
const companyMember = document.querySelector('.company-member');
companyMember.classList.add('unselected');
companyMember.classList.remove('select-login-type');

const socialLoginTitle = document.querySelector('.social-login-title');
const socialLoginContainer = document.querySelector('.social-login-container');
const joinMember = document.querySelector('.join-member');
const joinCompany = document.querySelector('.join-company');
const checkRole = document.getElementById("role");
const closeOutline = document.getElementById("close-outline");

closeOutline.addEventListener("click",(e)=>{
    location.href='/experience/list';
})

if(window.location.search.includes("fail")){
    alert("입력하신 정보가 없습니다.")
}

// 일반 로그인
generalMember.addEventListener("click", (e) => {
    inputContainer.classList.add('member');
    inputContainer.classList.remove('company');
    generalMember.classList.add('select-login-type');
    generalMember.classList.remove('unselected');
    companyMember.classList.remove('select-login-type');
    companyMember.classList.add('unselected');
    socialLoginTitle.classList.remove('hide');
    socialLoginContainer.classList.remove('hide');
    joinMember.classList.add('show');
    joinCompany.classList.remove('show');
    checkRole.setAttribute("value","member")
});

// 기업 로그인
companyMember.addEventListener("click", (e) => {
    inputContainer.classList.remove('member');
    inputContainer.classList.add('company');
    generalMember.classList.remove('select-login-type');
    generalMember.classList.add('unselected');
    companyMember.classList.add('select-login-type');
    companyMember.classList.remove('unselected');
    socialLoginTitle.classList.add('hide');
    socialLoginContainer.classList.add('hide');
    joinMember.classList.remove('show');
    joinCompany.classList.add('show');
    checkRole.setAttribute("value","company")
});


// 로그인 버튼 활성화/비활성화
const emailInput = document.querySelector('.email-input');
const passwordInput = document.querySelector('.password-input');
const loginButton = document.querySelector('.login-button');

emailInput.addEventListener("keyup", (e) => {
    if (emailInput.value.length > 0 && passwordInput.value.length > 0) {
        loginButton.classList.remove('login-disabled');
    } else if (emailInput.value.length === 0 || passwordInput.value.length === 0) {
        loginButton.classList.add('login-disabled');
    }
});

passwordInput.addEventListener("keyup", (e) => {
    if (emailInput.value.length > 0 && passwordInput.value.length > 0) {
        loginButton.classList.remove('login-disabled');
    } else if (emailInput.value.length === 0 || passwordInput.value.length === 0) {
        loginButton.classList.add('login-disabled');
    }
});

loginButton.addEventListener("click",async (e)=>{
    const email = emailInput.value;
    const password = passwordInput.value;
    const role = checkRole.value;

    const result = await memberService.login({userEmail:email,userPassword:password,userRole:role});

        if(result.accessToken){
            if(checkRole.value==='member'){location.href='/experience/list';}
            else{location.href='/enterprise-console';}

        }else{showLoginErrorToast();}

});

// 토스트 - 로그인 오류(이메일)
const isValidEmail = (value) => {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value.trim());
};
const toastLogin = document.querySelector("#toast-login");
const toastEmail = document.querySelector("#toast-email");
const toastPassword = document.querySelector("#toast-password");
// 로그인 오류 토스트
function showLoginErrorToast() {
    toastLogin.classList.add("show");
    setTimeout(() => {
        toastLogin.classList.remove("show");
    }, 3000);
}

loginButton.addEventListener("click", (e) => {
    // 이메일 또는 비밀번호가 비어 있을 때
    if (emailInput.value.length === 0 || passwordInput.value.length === 0) {
        return;
    }

    // 이메일 형식&비밀번호 길이 검사
    if (!isValidEmail(emailInput.value) && passwordInput.value.length < 4) {
        showLoginErrorToast();

        // 이메일 오류 토스트 표시
        toastLogin.addEventListener("mouseover", (e) => {
            toastEmail.classList.add("show-red");
            toastPassword.classList.remove("show-red");
        });
        toastLogin.addEventListener("mouseout", (e) => {
            toastEmail.classList.remove("show-red");
        });

    } else if (!isValidEmail(emailInput.value) && passwordInput.value.length >= 4) {
        showLoginErrorToast();

        // 이메일 오류 토스트 표시
        toastLogin.addEventListener("mouseover", (e) => {
            toastEmail.classList.add("show-red");
            toastPassword.classList.remove("show-red");
            setTimeout(() => {
                toastEmail.classList.remove("show-red");
            }, 2000);
        });
        toastLogin.addEventListener("mouseout", (e) => {
            toastEmail.classList.remove("show-red");
        });

    // 비밀번호가 4자 미만일 때
    } else if (passwordInput.value.length < 4) {
        showLoginErrorToast();

        // 비밀번호 오류 토스트 표시
        toastLogin.addEventListener("mouseover", (e) => {
            toastPassword.classList.add("show-red");
            toastEmail.classList.remove("show-red");
            setTimeout(() => {
                toastPassword.classList.remove("show-red");
            }, 2000);
        });
        toastLogin.addEventListener("mouseout", (e) => {
            toastPassword.classList.remove("show-red");
        });
    }

});
// 카카오 로그인
const kakaoLoginButton = document.getElementById("kakao-login");
kakaoLoginButton.addEventListener("click", (e) => {
    if(window.innerWidth<1023){
        console.log("모바일 로그인")
        window.location.href = "/oauth2/authorization/kakao";
    }else{window.location.href = "/oauth2/authorization/kakao";}
});
// 네이버 로그인
const naverLoginButton = document.getElementById("naver-login");
naverLoginButton.addEventListener("click", (e) => {
    if(window.innerWidth<1023){
        window.location.href = "/oauth2/authorization/naver";
    }else{window.location.href = "/oauth2/authorization/naver";}
});

// 구글 로그인
// const googleLoginButton = document.getElementById("google-login");
// googleLoginButton.addEventListener("click", (e) => {
//     if(window.innerWidth<1023){
//         window.location.href = "/oauth2/authorization/google";
//     }else{window.location.href = "/oauth2/authorization/google";}
// });
