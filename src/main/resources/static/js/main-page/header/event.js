const detail = document.getElementsByClassName("detail");
// 헤더 프로필 아이콘
const profileIcon = document.querySelector(".account-info-popover");

const profileModal = document.querySelector(".profile-modal");

profileIcon.addEventListener("click", (e) => {
    const isVisible = profileModal.style.display === "flex";
    profileModal.style.display = isVisible ? "none" : "flex";
});

document.addEventListener("click", (e) => {
    if (!profileModal.contains(e.target) && e.target !== profileIcon) {
        profileModal.style.display = "none";
    }
});
// 사이드바 설정 모달
const settingHeaderBtn = document.querySelector(
    ".profile-modal-list-section-setting"
);
const settingHeaderModal = document.querySelector(".sidebar-setting-modal");

const settingHeaderCloseBtn = document.querySelector(
    ".sidebar-setting-modal-close-button"
);
if (settingHeaderBtn && settingHeaderModal) {
    settingHeaderBtn.addEventListener("click", () => {
        if (profileModal) profileModal.style.display = "none";
        settingHeaderModal.style.display = "flex";
    });

}
if (settingHeaderCloseBtn && settingHeaderModal) {
    settingHeaderCloseBtn.addEventListener("click", (e) => {
        settingHeaderModal.style.display = "none";
    });

}
if (settingHeaderModal) {
    settingHeaderModal.addEventListener("click", (e) => {
        if (e.target === settingHeaderModal) {
            settingHeaderModal.style.display = "none";
        }
    });

}
// main-ex.html 구상 시 중복된 js로 주석처리
// 따로 쓰게되면 주석 해제하시면 됩니다!
// 알림 설정 토글
// document.querySelectorAll(".setting-modal-alarm-button").forEach((button) => {

//     const check = button.querySelector(".setting-modal-alarm-button-check");
//     button.addEventListener("click", () => {
//         button.classList.toggle("off");
//         check.classList.toggle("off");
//     });

// });
// const profileHeaderWrap = document.querySelector(
//     ".setting-modal-member-profile-wrap"
// );
// const alarmWrap = document.querySelector(".setting-modal-alarm-wrap");
// const rightLists = document.querySelectorAll(".setting-modal-right-list-wrap");
// const profileRightList = rightLists[0];
// const alarmRightList = rightLists[1];
// const activeBg = "rgba(86, 105, 143, 0.08)";

// const inactiveBg = "transparent";
// profileRightList.style.display = "block";
// alarmRightList.style.display = "none";
// profileHeaderWrap.style.background = activeBg;

// alarmWrap.style.background = inactiveBg;
// profileHeaderWrap.addEventListener("click", () => {
//     profileRightList.style.display = "block";

//     alarmRightList.style.display = "none";
//     profileHeaderWrap.style.background = activeBg;
//     alarmWrap.style.background = inactiveBg;

// });
// alarmWrap.addEventListener("click", () => {
//     alarmRightList.style.display = "block";

//     profileRightList.style.display = "none";
//     alarmWrap.style.background = activeBg;
//     profileHeaderWrap.style.background = inactiveBg;

// });
// 지원 내역 모달
const historyBtn = document.querySelector(
    ".profile-modal-list-section-history"
);
const historyModal = document.querySelector(".history-modal-background");
const historyCloseBtn = document.querySelector(".history-modal-close-button");
const experienceContent = document.querySelector(
    ".history-modal-main-experience"
);

const employContent = document.querySelector(".history-modal-main-employ");
if (historyBtn && historyModal) {
    historyBtn.addEventListener("click", async () => {
        if (profileModal) profileModal.style.display = "none";
        if (settingHeaderModal) settingHeaderModal.style.display = "none";
        console.log(detail[0].className.includes("active"));
        historyModal.style.display = "flex";
        await  headerService.getRequestExperience(headerLayout.showExperienceList);
    });

}
if (historyCloseBtn && historyModal) {
    historyCloseBtn.addEventListener("click", () => {
        historyModal.style.display = "none";
    });

}
if (historyModal) {
    historyModal.addEventListener("click", (e) => {
        if (e.target === historyModal) {
            historyModal.style.display = "none";
        }
    });

}
const experienceTab = document.querySelector(
    ".history-modal-top-section-active"
);
const experienceText = experienceTab.querySelector("p");
const employmentTab = document.querySelector(".history-modal-top-section");

const employmentText = employmentTab.querySelector("p");

experienceTab.addEventListener("click", async () => {
    experienceTab.classList.add("history-modal-top-section-active");
    experienceTab.classList.remove("history-modal-top-section");
    experienceText.classList.add("history-modal-top-section-text-active");
    experienceText.classList.remove("history-modal-top-section-text");

    employmentTab.classList.remove("history-modal-top-section-active");
    employmentTab.classList.add("history-modal-top-section");
    employmentText.classList.remove("history-modal-top-section-text-active");
    employmentText.classList.add("history-modal-top-section-text");
    employContent.style.display = "none";
    experienceContent.style.display = "flex";
    console.log(detail[0].className.includes("active"));
    await  headerService.getRequestExperience(headerLayout.showExperienceList);
});

employmentTab.addEventListener("click", async() => {
    employmentTab.classList.add("history-modal-top-section-active");
    employmentTab.classList.remove("history-modal-top-section");
    employmentText.classList.add("history-modal-top-section-text-active");
    employmentText.classList.remove("history-modal-top-section-text");

    experienceTab.classList.remove("history-modal-top-section-active");
    experienceTab.classList.add("history-modal-top-section");
    experienceText.classList.remove("history-modal-top-section-text-active");
    experienceText.classList.add("history-modal-top-section-text");
    employContent.style.display = "flex";
    experienceContent.style.display = "none";
    console.log(detail[0].className.includes("active"));
    await headerService.getRequestIntern(headerLayout.showInternList);
});
// 지원내역 상세 모달
const historyDetailModal = document.querySelector(
    ".history-detail-modal-background"
);
// 체험 상세 모달 서비스
const requestExperienceWarp = document.getElementById("requestExperienceWarp");
requestExperienceWarp.addEventListener("click",async (e)=>{
    if(e.target.closest(".fail")){
        return;
    }
    if(e.target.closest(".history-modal-main-section")){
        const experienceId = e.target.closest(".history-modal-main-section").dataset.id;
        console.log("체험 이벤트"+experienceId);
        await headerService.getRequestExperience(headerLayout.showExperienceDetail,experienceId);
        historyDetailModal.style.display = "flex";
    }

})
historyDetailModal.addEventListener("click",(e)=>{
    if(e.target.closest(".history-detail-modal-top-right-button")){
        historyDetailModal.style.display = "none";
        historyModal.style.display = "none";
    }
    if(e.target.closest(".history-detail-modal-top-left-button")){
        historyDetailModal.style.display = "none";
    }
})

// 인턴 상세모달 서비스
const requestInternWarp = document.getElementById("requestInternWarp");
requestInternWarp.addEventListener("click",async (e)=>{
    if(e.target.closest(".fail")){
        return;
    }
    if(e.target.closest(".history-modal-main-section")){
        const internId = e.target.closest(".history-modal-main-section").dataset.id;
        console.log("인턴 이벤트"+internId);
        await headerService.getRequestIntern(headerLayout.showInternDetail,internId);
        historyDetailModal.style.display = "flex";
    }

})
if (historyDetailModal) {
    historyDetailModal.addEventListener("click", (e) => {
        if (e.target === historyDetailModal) {
            historyDetailModal.style.display = "none";
        }
    });
}
const logout = document.getElementById("logout");
logout.addEventListener("click",async (e)=>{
    console.log("로그아웃 누름")
    await headerService.logout();

    location.href='/member/login';
});
