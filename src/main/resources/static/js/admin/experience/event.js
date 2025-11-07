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

// 목록
service.getExperience(layout.showList);

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

// 상세 모달
actionButtons.forEach((actionButton) => {
    actionButton.addEventListener("click", () => {
        modal.style.display = "block";
        setTimeout(() => {
            modal.classList.add("show");
            modal.style.background = "rgba(0,0,0,0.5)";
            document.body.classList.add("modal-open");
        }, 100);
    });
});

closeButtons.forEach((closeButton) => {
    closeButton.addEventListener("click", () => {
        modal.classList.remove("show");
        document.body.classList.remove("modal-open");
        setTimeout(() => {
            modal.style.display = "none";
        }, 100);
    });
});

modal.addEventListener("click", (e) => {
    if (e.target === modal) {
        modal.classList.remove("show");
        document.body.classList.remove("modal-open");
        setTimeout(() => {
            modal.style.display = "none";
        }, 100);
    }
});

closeFooterButton.addEventListener("click", () => {
    modal.classList.remove("show");
    document.body.classList.remove("modal-open");
    setTimeout(() => {
        modal.style.display = "none";
    }, 100);
});

// 모달 상세
const experienceListContainer = document.querySelector(".table.member-table tbody");
experienceListContainer.addEventListener("click",async (e)=>{

    if(e.target.closest(".action-btn")){
        modal.style.display = "block";
        setTimeout(() => {
            modal.classList.add("show");
            modal.style.background = "rgba(0,0,0,0.5)";
            document.body.classList.add("modal-open");
        }, 100);

        const actionButton = e.target.closest(".action-btn");
        const page = 1;
        const id = Number(actionButton.dataset.id);
        console.log(id);

        // 체험공고 상세정보
        await service.getExperienceDetail(layout.showInfo, id);

        // 신청자 내역
        await service.getExperienceDetailList(layout.showRequest, page, id);

        const detailPagination = document.querySelector(".pagination.kok-pagination.detail-request");
        const firstNumber = detailPagination.querySelector("li.number");
        if (firstNumber) {
            firstNumber.classList.add("active");
        }
        detailPagination.onclick = async (e) => {
            e.preventDefault()

            // 페이지 번호
            const clickNum = e.target.closest("a[data-page]");
            const pageNumber = Number(clickNum.dataset.page);

            await service.getExperienceDetailList(layout.showRequest, pageNumber, id);

            const pageNumsList = detailPagination.querySelectorAll("li.page-num");
            pageNumsList.forEach((pageNum) => {
                pageNum.classList.remove("active");
            });
            const currentList = Array.from(pageNumsList).find((pageNum) => {
                const activeList = pageNum.querySelector("a.page-item-num");
                return activeList && Number(activeList.dataset.page) === pageNumber;
            });

            if(currentList){
                currentList.classList.add("active");
            }
        }

        // 평가자 목록
        await service.getExperienceDetailList(layout.showEvaluation, page, id);

        const evaluationPagination = document.querySelector(".pagination.kok-pagination.detail-evaluation");
        const firstEvaluationNumber = evaluationPagination.querySelector("li.number");
        if (firstEvaluationNumber) {
            firstEvaluationNumber.classList.add("active");
        }
        evaluationPagination.onclick = async (e) => {
            e.preventDefault()

            // 페이지 번호
            const clickNum = e.target.closest("a[data-page]");
            const pageNumber = Number(clickNum.dataset.page);

            await service.getExperienceDetailList(layout.showEvaluation, pageNumber, id);

            const pageNumsList = evaluationPagination.querySelectorAll("li.page-num");
            pageNumsList.forEach((pageNum) => {
                pageNum.classList.remove("active");
            });
            const currentList = Array.from(pageNumsList).find((pageNum) => {
                const activeList = pageNum.querySelector("a.page-item-num");
                return activeList && Number(activeList.dataset.page) === pageNumber;
            });

            if(currentList){
                currentList.classList.add("active");
            }
        }
    }
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

// 목록
const content = document.querySelector("input[name=keyword]");
const pagination = document.querySelector(".pagination.kok-pagination");
pagination.addEventListener("click", async (e) => {
    e.preventDefault();
    const keyword = content.value;
    await service.getExperience(layout.showList, e.target.dataset.page, keyword);

    // 페이지 번호
    const clickNum = e.target.closest("a[data-page]");
    const pageNumber = parseInt(clickNum.dataset.page);

    const pageNumsList = pagination.querySelectorAll("li.page-num");
    pageNumsList.forEach((pageNum) => {
        pageNum.classList.remove("active");
    });

    const currentList = Array.from(pageNumsList).find((pageNum) => {
        const activeList = pageNum.querySelector("a.page-item-num");
        return activeList && parseInt(activeList.dataset.page) === pageNumber;
    });

    if(currentList){
        currentList.classList.add("active");
    }
});

// 검색창
const search = document.querySelector(".btn.btn-search");
search.addEventListener("click", async (e) => {
    const page = 1;
    const keyword = content.value;
    console.log(keyword);
    await service.getExperience(layout.showList, page, keyword);
});

// 페이지 번호
// pageItemNums.forEach((pageItemNum) => {
//     pageItemNum.addEventListener("click", (e) => {
//         e.preventDefault();
//         pageNums.forEach((pageNum) => pageNum.classList.remove("active"));
//         pageItemNum.parentElement.classList.add("active");
//     });
// });
