const sideMenuButtons = document.querySelectorAll(".menu-btn");
const icons = document.querySelectorAll(".icon-wrapper i");
const userMenuWrapper = document.querySelector(".user-menu-wrapper");
const userMenuContent = document.querySelector(".user-menu-content");
const pageNums = document.querySelectorAll(".page-num");
const pageItemNums = document.querySelectorAll(".page-item-num");

// 목록
service.getReportList(layout.showList);

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

// 페이지 번호
// pageItemNums.forEach((pageItemNum) => {
//     pageItemNum.addEventListener("click", (e) => {
//         e.preventDefault();
//         pageNums.forEach((pageNum) => pageNum.classList.remove("active"));
//         pageItemNum.parentElement.classList.add("active");
//     });
// });

// 신고 게시글 상세 모달
const rows = document.querySelectorAll(".post-detail");
const modal = document.querySelector(".post-modal-background");
const modalContent = document.querySelector(".post-modal-wrap1");

rows.forEach((row) => {
    row.addEventListener("click", () => {
        modal.style.display = "flex";
    });
});

modal.addEventListener("click", () => {
    modal.style.display = "none";
});

modalContent.addEventListener("click", (e) => {
    e.stopPropagation();
});


// 모달 상세
const reportListContainer = document.querySelector(".table.table-notice tbody");
reportListContainer.addEventListener("click",async (e)=>{

    modal.style.display = "flex";
    const clickNum = e.target.closest("tr[data-id]");
    const id = Number(clickNum.dataset.id);

    // 신고 게시글 상세정보
    await service.getReportDetail(layout.showDetail, id);
});

// 목록 페이지 번호
const pagination = document.querySelector(".pagination.kok-pagination");
pagination.addEventListener("click", async (e) => {
    e.preventDefault();
    await service.getReportList(layout.showList, e.target.dataset.page);

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