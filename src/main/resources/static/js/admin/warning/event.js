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
    const page = 1;

    const features = await warningService.allPosts();
    console.log(features);

    const warnings = await warningService.warningList(features);
    console.log(warnings.isSlang);

    await warningService.warningPostList(page, layout.showList, warnings.isSlang);

    document.querySelectorAll('.custom-radio-group input[type="radio"]')
        .forEach(radio => {
            radio.addEventListener('change', async () => {
                let keyword = radio.dataset.keyword; // ← 여기
                console.log(keyword);

                if (keyword === "all") {
                    keyword = null;
                }

                await warningService.warningPostList(page, layout.showList, warnings.isSlang, keyword);
            });
        });



    const modal = document.querySelector(".member-modal.modal");

    document.addEventListener("click", async (e) => {
        // 상세 모달 열기 (이벤트 위임)
        const target = e.target.closest(".action-btn, .mdi-chevron-right");
        if (!target) {
            return;
        }

        const id = target.dataset.id;
        await warningService.postDetail(id, layout.showDetail);

        modal.style.display = "block";
        setTimeout(() => {
            modal.classList.add("show");
            modal.style.background = "rgba(0,0,0,0.5)";
            document.body.classList.add("modal-open");
        }, 100);

        document.addEventListener("click", async (e) => {
            // 삭제 버튼
            if (e.target.closest("#btn-warning-remove")) {
                await warningService.deletePost(id)
            }

            // 보류 버튼
            if (e.target.closest("#btn-warning")) {
                await warningService.changeStatusPost(id)
            }

            await warningService.postDetail(id, layout.showDetail);
            await warningService.warningPostList(page, layout.showList, warnings.isSlang);
        });
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

