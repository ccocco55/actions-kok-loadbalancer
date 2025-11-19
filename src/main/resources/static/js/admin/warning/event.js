const sideMenuButtons = document.querySelectorAll(".menu-btn");
const icons = document.querySelectorAll(".icon-wrapper i");
const modal = document.querySelector(".member-modal");
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
document.addEventListener("DOMContentLoaded", async () => {
    let page = 1;
    let keyword = null;

    const modal = document.querySelector(".member-modal.modal");

    // 초기 데이터 로딩
    const features = await warningService.allPosts();
    console.log(features);

    const warnings = await warningService.warningList(features);
    console.log(warnings.isSlang);

    await warningService.warningPostList(page, layout.showList, warnings.isSlang);

    // 라디오 버튼 필터 이벤트
    document.querySelectorAll('.custom-radio-group input[type="radio"]')
        .forEach(radio => {
            radio.addEventListener('change', async () => {
                keyword = radio.dataset.keyword;
                console.log("Selected keyword:", keyword);

                if (keyword === "all") keyword = null;

                page = 1; // 필터 바뀌면 페이지 초기화
                await warningService.warningPostList(page, layout.showList, warnings.isSlang, keyword);
            });
        });

    // 페이지 버튼 이벤트 (이벤트 위임)
    document.addEventListener("click", async (e) => {
        const pageButton = e.target.closest(".page-item-num");
        if (pageButton) {
            e.preventDefault();
            const selectedPage = pageButton.dataset.page;
            if (selectedPage) {
                page = selectedPage;

                // 페이지 active 클래스 토글
                document.querySelectorAll(".page-number").forEach(li => li.classList.remove("active"));
                const parentLi = pageButton.closest(".page-number");
                if (parentLi && !["이전", "다음"].includes(pageButton.textContent.trim())) {
                    parentLi.classList.add("active");
                }

                // 리스트 호출
                await warningService.warningPostList(page, layout.showList, warnings.isSlang, keyword);
            }
            return;
        }

        // 모달 열기 이벤트
        const modalTarget = e.target.closest(".action-btn, .mdi-chevron-right");
        if (modalTarget) {
            const id = modalTarget.dataset.id;
            await warningService.postDetail(id, layout.showDetail);

            modal.style.display = "block";
            setTimeout(() => {
                modal.classList.add("show");
                modal.style.background = "rgba(0,0,0,0.5)";
                document.body.classList.add("modal-open");
            }, 100);

            // 모달 내 삭제/보류 이벤트
            const modalClickHandler = async (e) => {
                if (e.target.closest("#btn-warning-remove")) {
                    await warningService.deletePost(id);
                }

                if (e.target.closest("#btn-warning")) {
                    await warningService.changeStatusPost(id);
                }

                await warningService.postDetail(id, layout.showDetail);
                await warningService.warningPostList(page, layout.showList, warnings.isSlang, keyword);
            };

            // 한 번만 이벤트 등록
            modal.addEventListener("click", modalClickHandler, { once: true });
            return;
        }

        // 모달 닫기 이벤트
        if (
            e.target.closest(".close") ||
            e.target.closest(".btn-close.btn-outline-filter") ||
            (e.target.classList.contains("member-modal") && e.target.classList.contains("modal"))
        ) {
            closeModal();
        }
    });

    // 모달 닫기 함수
    function closeModal() {
        modal.classList.remove("show");
        document.body.classList.remove("modal-open");
        setTimeout(() => {
            modal.style.display = "none";
        }, 100);
    }
});
