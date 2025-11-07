const sideMenuButtons = document.querySelectorAll(".menu-btn");
const icons = document.querySelectorAll(".icon-wrapper i");
const modal = document.querySelector(".member-modal");
const actionButtons = document.querySelectorAll(".action-btn");
const closeButtons = document.querySelectorAll(".close");
const userMenuWrapper = document.querySelector(".user-menu-wrapper");
const userMenuContent = document.querySelector(".user-menu-content");
const pageNums = document.querySelectorAll(".page-num");
const pageItemNums = document.querySelectorAll(".page-item-num");
const filterBtn = document.getElementById("btn-filter-pg");
const filterWrapper = document.getElementById("filter-pg");
const popMenu = filterWrapper.querySelector(".kok-pop-menu");
const popBack = popMenu.querySelector(".kok-pop-menu-back");
const popContext = popMenu.querySelector(".kok-pop-menu-context");
const checkItems = popContext.querySelectorAll(".kok-check");
const confirmBtn = popContext.querySelector("button.btn-outline-primary");

// 목록
service.getAdvertisementList(layout.showList);

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

modal.addEventListener("click", (e) => {
    if (e.target === modal) {
        modal.classList.remove("show");
        document.body.classList.remove("modal-open");
        setTimeout(() => {
            modal.style.display = "none";
        }, 100);
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

// 페이지 번호
pageItemNums.forEach((pageItemNum) => {
    pageItemNum.addEventListener("click", (e) => {
        e.preventDefault();
        pageNums.forEach((pageNum) => pageNum.classList.remove("active"));
        pageItemNum.parentElement.classList.add("active");
    });
});

// 체크박스 클릭 이벤트 (라디오 버튼처럼 하나만 선택)
// if (confirmBtn) {
//     confirmBtn.style.display = "none";
// }

filterBtn.addEventListener("click", function () {
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

// 모달 상세
const advertiseListContainer = document.querySelector(".table.member-table tbody");
advertiseListContainer.addEventListener("click",async (e)=>{

    if(e.target.closest(".action-btn")){
        modal.style.display = "block";
        setTimeout(() => {
            modal.classList.add("show");
            modal.style.background = "rgba(0,0,0,0.5)";
            document.body.classList.add("modal-open");
        }, 100);

        const actionButton = e.target.closest(".action-btn");
        const id = Number(actionButton.dataset.id);
        console.log(id);

        // 광고 신청 상세정보
        await service.getAdvertisementDetail(layout.showDetail, id);
        const closeButtons = document.querySelectorAll(".close");

        closeButtons.forEach((closeButton) => {
            closeButton.addEventListener("click", () => {
                modal.classList.remove("show");
                document.body.classList.remove("modal-open");
                setTimeout(() => {
                    modal.style.display = "none";
                }, 100);
            });
        });
    }
});


// 카테고리 선택
const listItem = document.querySelectorAll(".list-item");
const content = document.querySelector("input[name=keyword]");
const pagination = document.querySelector(".pagination.kok-pagination");

confirmBtn.addEventListener("click", (e) => {
    listItem.forEach(async (listItem) => {
        const page = 1;
        const keyword = content.value;
        const categoryTag = listItem.querySelector(".active input[name=category]");

        if(categoryTag){
            const category = categoryTag.value;
            pagination.dataset.category = category;

            console.log(keyword);
            console.log(category);

            return await service.getAdvertisementList(layout.showList, page, keyword, category);
        }
        // else if(!categoryTag) {
        //     if (pagination) {
        //         delete pagination.dataset.category;
        //     }
        //     const keyword = content.value ? content.value : '';
        //     await service.getAdvertisementList(layout.showList, keyword);
        // }
    });
});

// 검색창
const search = document.querySelector(".btn.btn-search");
search.addEventListener("click", async (e) => {
    const page = 1;
    const keyword = content.value;
    let category = pagination.dataset.category

    console.log(keyword);
    console.log(category);

    await service.getAdvertisementList(layout.showList, page, keyword, category);
});

// 목록 페이지 번호
pagination.addEventListener("click", async (e) => {
    e.preventDefault();

    const keyword = content.value;
    let category = pagination.dataset.category

    await service.getAdvertisementList(layout.showList, e.target.dataset.page, keyword, category);

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

