const sideMenuButtons = document.querySelectorAll(".menu-btn");
const icons = document.querySelectorAll(".icon-wrapper i");
const modal = document.querySelector(".member-modal");
const actionButtons = document.querySelectorAll(".action-btn");
const closeButtons = document.querySelectorAll(".close");
const closeFooterButton = document.querySelector(".btn-close");
const userMenuWrapper = document.querySelector(".user-menu-wrapper");
const userMenuContent = document.querySelector(".user-menu-content");
const pageNums = document.querySelectorAll(".page-num");
const pageItemNums = document.querySelectorAll(".page-item-num");

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

// // 상세 모달
// actionButtons.forEach((actionButton) => {
//     actionButton.addEventListener("click", () => {
//         modal.style.display = "block";
//         setTimeout(() => {
//             modal.classList.add("show");
//             modal.style.background = "rgba(0,0,0,0.5)";
//             document.body.classList.add("modal-open");
//         }, 100);
//     });
// });
//
// closeButtons.forEach((closeButton) => {
//     closeButton.addEventListener("click", () => {
//         modal.classList.remove("show");
//         document.body.classList.remove("modal-open");
//         setTimeout(() => {
//             modal.style.display = "none";
//         }, 100);
//     });
// });
//
// modal.addEventListener("click", (e) => {
//     if (e.target === modal) {
//         modal.classList.remove("show");
//         document.body.classList.remove("modal-open");
//         setTimeout(() => {
//             modal.style.display = "none";
//         }, 100);
//     }
// });
//
// closeFooterButton.addEventListener("click", () => {
//     modal.classList.remove("show");
//     document.body.classList.remove("modal-open");
//     setTimeout(() => {
//         modal.style.display = "none";
//     }, 100);
// });

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

// 페이지 번호
pageItemNums.forEach((pageItemNum) => {
    pageItemNum.addEventListener("click", (e) => {
        e.preventDefault();
        pageNums.forEach((pageNum) => pageNum.classList.remove("active"));
        pageItemNum.parentElement.classList.add("active");
    });
});

// 이벤트 위임



document.addEventListener("DOMContentLoaded", async() => {
    const page = 1;
    await companyService.companyList(page, layout.showList);

    const modal = document.querySelector(".member-modal.modal");

    document.addEventListener("click", async (e) => {
        // 상세 모달 열기 (이벤트 위임)
        const target = e.target.closest(".action-btn, .mdi-chevron-right");
        if (!target) {return}

        const id = target.dataset.id;
        await companyService.companyDetail(id, layout.showDetail);

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
            document.querySelectorAll(".page-number").forEach(li => {
                li.classList.remove("active");
            });

            const parentLi = pageButton.closest(".page-number");
            if (parentLi && !["이전", "다음"].includes(pageButton.textContent.trim())) {
                parentLi.classList.add("active");
            }

            await companyService.companyList(page, layout.showList);
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
        if (e.target.classList.contains("member-modal") && e.target.classList.contains("modal")) {
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

//  검색

const searchBtn = document.querySelector(".btn-search");

searchBtn.addEventListener("click", async (e) => {
    const keyword = document.querySelector(".form-control").value.trim();
    const page = 1;

    await companyService.companyList(page, layout.showList, keyword);
})

