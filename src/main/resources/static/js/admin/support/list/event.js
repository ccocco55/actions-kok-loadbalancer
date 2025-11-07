const sideMenuButtons = document.querySelectorAll(".menu-btn");
const icons = document.querySelectorAll(".icon-wrapper i");
const userMenuWrapper = document.querySelector(".user-menu-wrapper");
const userMenuContent = document.querySelector(".user-menu-content");

// 목록
service.getNotice(layout.showList);

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

// 목록
const pagination = document.querySelector(".pagination.kok-pagination");
pagination.addEventListener("click", async (e) => {
    e.preventDefault();
    await service.getNotice(layout.showList, e.target.dataset.page);

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


