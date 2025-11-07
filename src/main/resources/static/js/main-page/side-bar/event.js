// 검색창 모달 열고 닫기
const openSearchBtn = document.querySelector(".sidebar-search");
const searchModal = document.querySelector(".search-modal-wrap");
const searchModalContent = document.querySelector(".search-modal");
const searchCloseBtn = document.querySelector(".close-search-modal");

openSearchBtn.addEventListener("click", async () => {
    searchModal.classList.add("active");
    await sideService.getExperience(sideLayout.showExperience);
    await sideService.getPopularCompany(sideLayout.showPopularCompany);
});

searchCloseBtn.addEventListener("click", () => {
    searchModal.classList.remove("active");
});

searchModal.addEventListener("click", (e) => {
    if (!searchModalContent.contains(e.target)) {
        searchModal.classList.remove("active");
    }
});
// 사이드바 대카테고리
document.querySelectorAll(".sidebar-item").forEach((el) => {
    console.log(el.id);
    if(window.location.href.includes(el.id)){
        el.classList.add("active");}
    else{
        el.classList.remove("active");}
});
document.querySelectorAll(".sidebar-item-icon").forEach((el) => {

    if(window.location.href.includes(el.dataset.name)){
        el.classList.add("active");}
    else{
        el.classList.remove("active");}
});

// 사이드바 대카테고리
// document.querySelectorAll(".sidebar-menu .start-line a").forEach((link) => {
//     link.addEventListener("click", function (e) {
//         // 임시 페이지 이동 막기
//         // 서버 구상 시 제외하시면 됩니다
//         e.preventDefault();
//
//         document.querySelectorAll(".sidebar-item-active").forEach((el) => {
//             el.classList.replace("sidebar-item-active", "sidebar-item");
//         });
//         document.querySelectorAll(".sidebar-item-icon-active").forEach((el) => {
//             el.classList.replace(
//                 "sidebar-item-icon-active",
//                 "sidebar-item-icon"
//             );
//         });
//
//         const item = this.querySelector(".sidebar-item, .sidebar-item-active");
//         const icon = this.querySelector(
//             ".sidebar-item-icon, .sidebar-item-icon-active"
//         );
//
//         if (item) item.classList.replace("sidebar-item", "sidebar-item-active");
//         if (icon)
//             icon.classList.replace(
//                 "sidebar-item-icon",
//                 "sidebar-item-icon-active"
//             );
//     });
// });

// 사업자 번호 더보기/접기
const businessToggleBtn = document.querySelector(".sidebar-business-toggle");
const businessSection = businessToggleBtn.closest(".sidebar-business");

businessToggleBtn.addEventListener("click", () => {
    businessSection.classList.toggle("expanded");

    if (businessSection.classList.contains("expanded")) {
        businessToggleBtn.textContent = "접기";
    } else {
        businessToggleBtn.textContent = "더보기";
    }
});

// 검색 모달창 회원/기업 선택
const tabs = document.querySelectorAll(
    ".search-modal-group-section, .search-modal-group-section-active"
);
const memberList = document.querySelector(".member-list");
const companyList = document.querySelector(".company-list");

tabs.forEach((tab) => {
    tab.addEventListener("click", async () => {
        const selected = tab.textContent.trim();

        tabs.forEach((t) => {
            const p = t.querySelector("p");
            t.classList.remove("search-modal-group-section-active");
            t.classList.add("search-modal-group-section");
            if (p) {
                p.classList.remove("search-modal-group-context-active");
                p.classList.add("search-modal-group-context");
            }
        });

        tab.classList.remove("search-modal-group-section");
        tab.classList.add("search-modal-group-section-active");
        const p = tab.querySelector("p");
        if (p) {
            p.classList.remove("search-modal-group-context");
            p.classList.add("search-modal-group-context-active");
        }

        if (selected === "체험공고") {
            memberList.style.display = "flex";
            companyList.style.display = "none";
            await sideService.getExperience(sideLayout.showExperience);
        } else {
            memberList.style.display = "none";
            companyList.style.display = "flex";
            await sideService.getIntern(sideLayout.showIntern);
        }
    });
});

// 검색 기능
const searchSide = document.getElementById("search");
searchSide.addEventListener("keyup",async (e)=>{

    if(e.key==="Enter"){
        let keyword ='';
        keyword = searchSide.value;
        console.log(tabs[0].classList.value.includes("active"));
        if(tabs[0].classList.value.includes("active")){
            console.log(keyword);
            document.getElementById("experienceWarp").innerHTML='';
            const result = await sideService.getExperience(sideLayout.showExperience,keyword);
            console.log(result);
            if(result.id===null){
                alert("검색하신 내용이 없습니다.")
            }
        }else{
            document.getElementById("internWarp").innerHTML='';
            const result = await sideService.getIntern(sideLayout.showIntern,keyword);
            if(result.id===null) {
                alert("검색하신 내용이 없습니다.")
            }
        }
    }
})
sideService.getAlarm();
// 사이드바 설정 모달
const settingBtn = document.querySelector(".sidebar-setting");
const settingModal = document.querySelector(".sidebar-setting-modal");
const settingCloseBtn = document.querySelector(
    ".sidebar-setting-modal-close-button"
);
sideService.getLink(sideLayout.showLink);
if (settingBtn && settingModal) {
    settingBtn.addEventListener("click",  () => {
        settingModal.style.display = "flex";
    });
}

if (settingCloseBtn && settingModal) {
    settingCloseBtn.addEventListener("click", (e) => {
        settingModal.style.display = "none";
    });
}

if (settingModal) {
    settingModal.addEventListener("click", (e) => {
        if (e.target === settingModal) {
            settingModal.style.display = "none";
        }
    });
}

// 알림 설정 토글
document.querySelectorAll(".setting-modal-alarm-button").forEach((button) => {
    const check = button.querySelector(".setting-modal-alarm-button-check");

    button.addEventListener("click", async () => {
        button.classList.toggle("off");
        check.classList.toggle("off");
        await  sideService.getLink(sideLayout.showLink);
        if(button.classList.contains("off")){
            console.log(button.dataset.keyword);
            await sideService.setInactive({keyword:button.dataset.keyword});

        }else{ await sideService.setActive({keyword:button.dataset.keyword});
            }
    });
});

const profileWrap = document.querySelector(
    ".setting-modal-member-profile-wrap"
);
if (profileWrap) {
    const alarmWrap = document.querySelector(".setting-modal-alarm-wrap");
    const rightLists = document.querySelectorAll(
        ".setting-modal-right-list-wrap"
    );
    const profileRightList = rightLists[0];
    const alarmRightList = rightLists[1];
    const activeBg = "rgba(86, 105, 143, 0.08)";
    const inactiveBg = "transparent";

    profileRightList.style.display = "block";
    alarmRightList.style.display = "none";
    profileWrap.style.background = activeBg;
    alarmWrap.style.background = inactiveBg;

    profileWrap.addEventListener("click", () => {
        profileRightList.style.display = "block";
        alarmRightList.style.display = "none";

        profileWrap.style.background = activeBg;
        alarmWrap.style.background = inactiveBg;
    });

    alarmWrap.addEventListener("click", () => {
        alarmRightList.style.display = "block";
        profileRightList.style.display = "none";

        alarmWrap.style.background = activeBg;
        profileWrap.style.background = inactiveBg;
    });
}

// 고객지원창
const supportButton = document.querySelector(".sidebar-support-wrap");
const supportModal = document.getElementById("crisp-chatbox-chat");
const closeButton = document.querySelector(
    ".customer-support-top-close-button"
);
const scrollContainer = document.querySelector(".customer-support-body-wrap6");
const scrollWrap = document.getElementById("support-wrap");
const supportLoading = document.getElementById("supportLoading");
supportLoading.style.display = "block";

let supportPage = 1;
const showSupportList = async (supportPage = 1) => {
    return await sideService.getSupportList(sideLayout.showSupport, supportPage);
}

if (supportButton && supportModal && closeButton) {
    supportButton.addEventListener("click", () => {
        supportModal.classList.add("active");
        setTimeout(() => {
            document.getElementById("supportLoading").remove();
            showSupportList();
        }, 1000);
    });

    closeButton.addEventListener("click", () => {
        supportModal.classList.remove("active");
    });
}

let checkSupportScroll = true;
let noticeCriteria;

scrollWrap.addEventListener("scroll", async (e) => {
    const supportScrollTop = e.target.scrollTop;
    const supportClientHeight = e.target.clientHeight;
    const supportScrollHeight = e.target.scrollHeight;
    const supportWrap = document.getElementById("support-wrap");

    if(supportScrollTop + supportClientHeight >= supportScrollHeight) {
        if(checkSupportScroll) {
            supportWrap.innerHTML += `
                <li id="supportLoading" style="display: block; width: 100px; height: 100px; margin: 0 auto;">
                    <img src="/images/experience/loading.gif" style="width: 100%; height: 100%" alt="loading">
                </li>
            `;
            setTimeout(async () => {
                document.getElementById("supportLoading").remove();
                noticeCriteria = await showSupportList(++supportPage);
            }, 1000);
            checkSupportScroll = false;
        }
        setTimeout(() => {
            if(noticeCriteria != null && noticeCriteria.noticeCriteria.hasMore) {
                checkSupportScroll = true;
            }
        }, 1500);
    }
});

// 고객지원 상세창
// const faqItems = document.querySelectorAll(".customer-support-list-section");
// const detailModal = document.getElementById("crisp-chatbox-chat-detail");
// const detailCloseBtn = document.querySelector(".detail-top-close-button");
// const detailBackBtn = document.querySelector(".detail-back-wrap");
// const supportTab = document.querySelector(".detail-top-title-section2");
//
// faqItems.forEach((item) => {
//     item.addEventListener("click", () => {
//         if (detailModal) detailModal.classList.add("active");
//     });
// });
//
// if (detailCloseBtn) {
//     detailCloseBtn.addEventListener("click", () => {
//         if (detailModal) detailModal.classList.remove("active");
//         if (supportModal) supportModal.classList.remove("active");
//     });
// }
//
// if (detailBackBtn) {
//     detailBackBtn.addEventListener("click", () => {
//         if (detailModal) detailModal.classList.remove("active");
//     });
// }
//
// if (supportTab) {
//     supportTab.addEventListener("click", () => {
//         if (detailModal) detailModal.classList.remove("active");
//     });
// }

// 고객지원 상세
scrollContainer.addEventListener("click", async (e) => {
    const detailModal = document.getElementById("crisp-chatbox-chat-detail");
    if (detailModal) detailModal.classList.add("active");

    const listData = e.target.closest(".customer-support-list-section");
    const id = Number(listData.dataset.id);
    console.log(id);

    await sideService.getSupportDetail(sideLayout.showSupportDetail, id);

    // 닫기
    const detailCloseBtn = document.querySelector(".detail-top-close-button");
    const detailBackBtn = document.querySelector(".detail-back-wrap");
    const supportTab = document.querySelector(".detail-top-title-section2");

    if (detailCloseBtn) {
        detailCloseBtn.addEventListener("click", () => {
            if (detailModal) detailModal.classList.remove("active");
            if (supportModal) supportModal.classList.remove("active");
        });
    }

    if (detailBackBtn) {
        detailBackBtn.addEventListener("click", () => {
            if (detailModal) detailModal.classList.remove("active");
        });
    }

    if (supportTab) {
        supportTab.addEventListener("click", () => {
            if (detailModal) detailModal.classList.remove("active");
        });
    }
});

// 고객지원 검색창
// const supportSearch = document.querySelector("form[name=form_helpdesk]");
// const searchInput = document.querySelector("input[name=helpdesk_search]");
// supportSearch.addEventListener("submit", async (e) => {
//     e.preventDefault();
//     const page = 1;
//     const keyword = searchInput.value;
//
//     await sideService.getSupportList(sideLayout.showSupport, page, keyword)
// });