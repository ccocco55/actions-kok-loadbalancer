const sideMenuButtons = document.querySelectorAll(".menu-btn");
const icons = document.querySelectorAll(".icon-wrapper i");
const userMenuWrapper = document.querySelector(".user-menu-wrapper");
const userMenuContent = document.querySelector(".user-menu-content");

// 사이드바 펼침/접힘
sideMenuButtons.forEach((menu) => {
    menu.addEventListener("click", function () {
        const submenu = this.nextElementSibling;
        const icon = this.querySelector(".icon-wrapper i");
        if (submenu && submenu.classList.contains("menu-sub-list")) {
            submenu.classList.toggle("show");
            if (submenu.classList.contains("show")) {
                icon.classList.remove("mdi-chevron-right");
                icon.classList.add("mdi-chevron-down");
            } else {
                icon.classList.remove("mdi-chevron-down");
                icon.classList.add("mdi-chevron-right");
            }
        }
    });
});

// 관리자 이메일 토글
userMenuWrapper.addEventListener("click", () => {
    userMenuContent.classList.toggle("show");
});

document.addEventListener("click", (e) => {
    if (
        !userMenuWrapper.contains(e.target) &&
        !userMenuContent.contains(e.target)
    ) {
        userMenuContent.classList.remove("show");
    }
});

// 게시글 등록 시 공란 방지
const writeForm = document.getElementById("faq-form");
const noticeTitle = document.getElementById("faq-title");
const noticeContent = document.getElementById("faq-content");
const submitButton = document.getElementById("writeButton");

submitButton.addEventListener("click", (e) => {
    if(noticeTitle.value.length === 0) {
        alert("제목을 입력해주세요");
    } else if(noticeContent.value.length === 0) {
        alert("내용을 입력해주세요");
    } else {
        writeForm.submit();
    }
});

