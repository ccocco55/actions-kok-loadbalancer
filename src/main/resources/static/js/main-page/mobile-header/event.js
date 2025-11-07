const topLeftBtns = document.querySelectorAll(".top-left");
const mobileSidebar = document.querySelector(".mobile-sidebar");
const sidebarList = document.querySelector(".sidebar-list");

const overlay = document.createElement("div");
overlay.classList.add("sidebar-overlay");
mobileSidebar.appendChild(overlay);

topLeftBtns.forEach((btn) => {
    btn.addEventListener("click", () => {
        sidebarList.classList.add("active");
        overlay.classList.add("active");
    });
});

overlay.addEventListener("click", () => {
    sidebarList.classList.remove("active");
    overlay.classList.remove("active");
});

const noticeDetail = document.querySelector(".sidebar-list-notice-detail");
const sideToggleBtn = noticeDetail.querySelector(".sidebar-list-text-button");
const extraText = noticeDetail.querySelector(".extra-text");

sideToggleBtn.addEventListener("click", () => {
    noticeDetail.classList.toggle("active");

    if (noticeDetail.classList.contains("active")) {
        extraText.style.display = "inline";
        sideToggleBtn.textContent = "접기";
    } else {
        extraText.style.display = "none";
        sideToggleBtn.textContent = "더보기";
    }

});

const moblogout = document.getElementsByClassName("sidebar-logout")[0];
moblogout.addEventListener("click",async (e)=>{
    await service.logout();

    location.href='/member/login';
})