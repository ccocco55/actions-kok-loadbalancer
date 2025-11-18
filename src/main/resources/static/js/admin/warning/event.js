const sideMenuButtons = document.querySelectorAll(".menu-btn");
const icons = document.querySelectorAll(".icon-wrapper i");
const modal = document.querySelector(".member-modal");
const userMenuWrapper = document.querySelector(".user-menu-wrapper");
const userMenuContent = document.querySelector(".user-menu-content");
const actionButtons = document.querySelectorAll(".action-btn");
const closeButtons = document.querySelectorAll(".close");
const pageNums = document.querySelectorAll(".page-num");
const pageItemNums = document.querySelectorAll(".page-item-num");
const filterBtn = document.getElementById("btn-filter-pg");
const filterWrapper = document.getElementById("filter-pg");
const popMenu = filterWrapper.querySelector(".kok-pop-menu");
const popBack = popMenu.querySelector(".kok-pop-menu-back");
const popContext = popMenu.querySelector(".kok-pop-menu-context");
const checkItems = popContext.querySelectorAll(".kok-check");
const confirmBtn = popContext.querySelector("button.btn-outline-primary");

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

document.addEventListener("DOMContentLoaded", async () => {
    const page = 1;

    let warnings = []
    let postContent = []

    postContent += warningService.allPosts();
    warnings += await warningService.warningList(postContent);
    console.log(postContent)


    const modal = document.querySelector(".member-modal.modal");

    document.addEventListener("click", async (e) => {
        // 상세 모달 열기 (이벤트 위임)
        const target = e.target.closest(".action-btn, .mdi-chevron-right");
        if (!target) {
            return;
        }

        const id = target.dataset.id;
        modal.style.display = "block";
        setTimeout(() => {
            modal.classList.add("show");
            modal.style.background = "rgba(0,0,0,0.5)";
            document.body.classList.add("modal-open");
        }, 100);
    });

    document.addEventListener("click", async (e) => {
        const pageButton = e.target.closest(".page-item-num");
        if (!pageButton) return;

        e.preventDefault();
        const page = pageButton.dataset.page;

        if (page) {
            document.querySelectorAll(".page-number").forEach((li) => {
                li.classList.remove("active");
            });

            const parentLi = pageButton.closest(".page-number");
            if (
                parentLi &&
                !["이전", "다음"].includes(pageButton.textContent.trim())
            ) {
                parentLi.classList.add("active");
            }
       }
    });

    // 닫기 버튼 / 푸터 닫기 버튼 / 배경 클릭 → 모두 위임 처리
    document.addEventListener("click", (e) => {
        // 닫기 버튼(X)
        if (e.target.closest(".close")) {
            closeModal();
            return;
        }

        // 푸터 닫기 버튼
        if (e.target.closest(".btn-close.btn-outline-filter")) {
            closeModal();
            return;
        }

        // 모달 배경 클릭 시
        if (
            e.target.classList.contains("member-modal") &&
            e.target.classList.contains("modal")
        ) {
            closeModal();
        }
    });

    function closeModal() {
        modal.classList.remove("show");
        document.body.classList.remove("modal-open");
        setTimeout(() => {
            modal.style.display = "none";
        }, 100);
    }
});

// 체크박스 클릭 이벤트 (라디오 버튼처럼 하나만 선택)
// if (confirmBtn) {
//     confirmBtn.style.display = "none";
// }

filterBtn.addEventListener("click", function () {
    console.log("111");

    popBack.classList.toggle("show");
    popContext.classList.toggle("show");
});

document.addEventListener("click", function (e) {
    if (!filterWrapper.contains(e.target)) {
        popBack.classList.remove("show");
        popContext.classList.remove("show");
    }
});

checkItems.forEach((item) => {
    const checkBox = item.querySelector(".kok-check-box");
    const checkIcon = checkBox.querySelector("i");

    item.addEventListener("click", function () {
        const currentLi = item.closest("li");
        const isActive = currentLi.classList.contains("active");

        checkItems.forEach((otherItem) => {
            const otherLi = otherItem.closest("li");
            const otherBox = otherItem.querySelector(".kok-check-box");
            const otherIcon = otherBox.querySelector("i");

            otherIcon.style.display = "none";
            if (otherLi) otherLi.classList.remove("active");
        });

        if (!isActive) {
            checkIcon.style.display = "inline-block";
            currentLi.classList.add("active");
            // confirmBtn.style.display = "block";
        } else {
            checkIcon.style.display = "none";
            currentLi.classList.remove("active");
            // confirmBtn.style.display = "none";
        }
    });
});

if (confirmBtn) {
    confirmBtn.addEventListener("click", function () {
        popBack.classList.remove("show");
        popContext.classList.remove("show");

        if (pagination) {
            delete pagination.dataset.category;
        }
    });
}
