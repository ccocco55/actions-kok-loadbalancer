const searchBtn = document.querySelector(".search-btn");
const searchSpan = document.querySelector(".search-span");
const dropdownMenu = document.querySelector(".dropdown-menu");
const jobButtons = document.querySelectorAll(".dropdown-btn");
const dropdownContainer = document.querySelector(".dropdown-container");

if(notice.experienceNoticeTitle) {
    dropdownContainer.setAttribute("data-id", notice.jobCategoryId);
}

// 검색 버튼 클릭 시 드롭다운 열기/닫기
searchBtn.addEventListener("click", (e) => {
    e.stopPropagation();
    dropdownMenu.classList.toggle("active");
});

// 옵션 클릭 시 active 처리 & 버튼 텍스트 변경
jobButtons.forEach((btn) => {
    btn.addEventListener("click", (e) => {
        e.stopPropagation();

        // 모든 버튼 active 제거
        jobButtons.forEach((b) => b.classList.remove("active"));

        // 클릭한 버튼에 active 추가
        btn.classList.add("active");

        // 선택된 텍스트를 상단 버튼에 반영
        const jobCategoryName = btn.querySelector(".job-6").innerText;
        const jobCategoryId = btn.dataset.id;

        searchSpan.innerText = jobCategoryName;
        searchSpan.style.color = "#000";
        searchSpan.dataset.id = jobCategoryId;
        dropdownContainer.dataset.id = jobCategoryId;

        // 드롭다운 닫기
        dropdownMenu.classList.remove("active");
    });
});

// 외부 클릭 시 닫기
document.addEventListener("click", (e) => {
    if (!dropdownMenu.contains(e.target) && !searchBtn.contains(e.target)) {
        dropdownMenu.classList.remove("active");
    }
});

// 글씨 길이 제한 있는 인풋 - validate-length
const validateLengthInputs = document.querySelectorAll(".validate-length");
validateLengthInputs.forEach((input) => {
    input.addEventListener("input", () => {
        const max = Number(input.dataset.max || 0);

        if (max > 0 && input.value.length > max) {
            input.value = input.value.substring(0, max);
        }

        if (max > 0 && input.value.length > max) {
            input.style.border = "2px solid red";
        } else {
            input.style.border = "";
        }
    });
});

// 필수값 유효성 검사
const openBtn = document.querySelector(".popup-trigger"); // 팝업열기 하단 버튼
const popup = document.querySelector(".popup-container"); // 팝업
const btnNo = document.querySelector(".btn-no"); // 팝업 취소
const btnSubmit = document.querySelector(".btn-yes"); // 팝업 등록 확인
const dateInputs = document.querySelectorAll(".date-input"); // 달력 인풋
const textareas = document.querySelectorAll(".contents .textarea"); // 텍스트박스
const dropdownBtns = document.querySelectorAll(".contents .dropdown-btn"); //드롭다운 버튼
const searchBtns = document.querySelectorAll(".contents .search-btn");
const contentInput =document.querySelectorAll(".contents .content-input"); // 기본 인풋


// 팝업 열기
if(openBtn) {
    openBtn.addEventListener("click", () => {
        popup.style.display = "flex";
    });
}
// 팝업 취소
btnNo.addEventListener("click", () => {
    popup.style.display = "none";
});

// 유효성 검사
function formValidate(){
    let isValid = true;

    // 입력 필드 검사
    contentInput.forEach(input => {
        const hasMust = input.closest(".contents")?.querySelector(".must");

        if (hasMust) {
            if (input.value.trim() === "") {
                input.style.border = "2px solid red";
                isValid = false;
            } else {
                input.style.border = "";
            }
        }
    });

    // 드롭다운 검사
    searchBtns.forEach(btn => {
        const hasMust = btn.closest(".contents")?.querySelector(".must");
        const span = btn.querySelector(".search-span");

        if (hasMust) {
            if (!span || span.textContent.includes("선택")) {
                btn.style.border = "2px solid red";
                isValid = false;
            } else {
                btn.style.border = "";
            }
        }
    });

    // textarea 검사
    textareas.forEach(textarea => {
        const hasMust = textarea.closest(".contents")?.querySelector(".must");
        if (hasMust) {
            if (textarea.value.trim() === "") {
                textarea.style.border = "2px solid red";
                isValid = false;
            } else {
                textarea.style.border = "";
            }
        }
    });

    // date-input 검사 (yyyy-mm-dd 형식)
    dateInputs.forEach(input => {
        const hasMust = input.closest(".contents")?.querySelector(".must");
        const datePattern = /^\d{4}-\d{2}-\d{2}$/;

        if (hasMust) {
            if (!datePattern.test(input.value.trim())) {
                input.style.border = "2px solid red";
                isValid = false;
            } else {
                input.style.border = "";
            }
        }
    });

    return isValid;
}

function parseYmd(str) {
    const [y, m, d] = str.split("-").map(Number);
    // 월은 0부터 시작
    return new Date(y, m - 1, d, 0, 0, 0, 0).getTime();
}

// 팝업 등록 및 수정 확인
btnSubmit.addEventListener("click", async () => {
    const isValid = formValidate();
    const jobCategoryId = document.querySelector("input[name=jobCategoryId]");
    const exForm = document.querySelector("#ex-form");
    const url = !notice.experienceNoticeTitle ? "/create" : `/edit`;
    jobCategoryId.value = dropdownContainer.dataset.id;

    const expStartInput = document.querySelector("#experience-start-date");
    const expEndInput = document.querySelector("#experience-end-date");
    const postStartInput = document.querySelector("#experience-notice-start-date");
    const postEndInput = document.querySelector("#experience-notice-end-date");

    const expStartTS = parseYmd(expStartInput.value);
    const expEndTS   = parseYmd(expEndInput.value);
    const postStartTS = parseYmd(postStartInput.value);
    const postEndTS   = parseYmd(postEndInput.value);

    const today = new Date();
    today.setHours(0,0,0,0);

    if (expStartTS < today.getTime()) {
        alert("체험 시작일은 오늘 이후 날짜여야 합니다.");
        document.querySelector("#experience-start-date").style.border = "2px solid red";
        return;
    }

    if (expEndTS < today.getTime()) {
        alert("체험 종료일은 오늘 이후 날짜여야 합니다.");
        document.querySelector("#experience-end-date").style.border = "2px solid red";
        return;
    }

    if (postStartTS < today.getTime()) {
        alert("게시 시작일은 오늘 이후 날짜여야 합니다.");
        document.querySelector("#experience-notice-start-date").style.border = "2px solid red";
        return;
    }

    if (postEndTS < today.getTime()) {
        alert("게시 종료일은 오늘 이후 날짜여야 합니다.");
        document.querySelector("#experience-notice-end-date").style.border = "2px solid red";
        return;
    }

    // 체험 시작 > 체험 종료
    if (expStartTS > expEndTS) {
        alert("체험 시작일은 체험 종료일과 같거나 이전이어야 합니다.");
        expStartInput.style.border = "2px solid red";
        expEndInput.style.border = "2px solid red";
        return;
    }

    // 게시 시작 > 게시 종료
    if (postStartTS > postEndTS) {
        alert("게시 시작일은 게시 종료일과 같거나 이전이어야 합니다.");
        postStartInput.style.border = "2px solid red";
        postEndInput.style.border = "2px solid red";
        return;
    }

    // 체험 시작 < 게시 시작
    if (expStartTS < postStartTS) {
        alert("체험 시작일은 게시 시작일 이후여야 합니다.");
        expStartInput.style.border = "2px solid red";
        postStartInput.style.border = "2px solid red";
        return;
    }

    if (isValid) {
        exForm.setAttribute("action", "/enterprise-console/experience" + url);
        exForm.submit();
        return;

    }
    console.log("유효성 실패!!!!!");

});

// 입력 중 border 해제 (실시간)
contentInput.forEach(input => {
    input.addEventListener("input", () => {
        if (input.value.trim() !== "" && input.value.length <= 30) {
            input.style.border = "";
        }
    });
});

// 드롭다운 선택 시 border 해제
dropdownBtns.forEach(btn => {
    btn.addEventListener("click", (e) => {
        const selectedText = e.currentTarget.querySelector(".job-6")?.textContent || "";
        const container = e.currentTarget.closest(".dropdown-container");
        const searchBtn = container.querySelector(".search-btn");
        const span = searchBtn.querySelector(".search-span");

        if (span) span.textContent = selectedText;
        searchBtn.style.border = "";
    });
});

// 달력 input
dateInputs.forEach(input => {
    input.addEventListener("input", (e) => {
        let value = e.target.value.replace(/[^0-9]/g, ""); // 숫자만 허용
        if (value.length > 8) value = value.substring(0, 8);

        if (value.length >= 5) {
            value = value.substring(0,4) + "-" + value.substring(4,6) +
                (value.length > 6 ? "-" + value.substring(6) : "");
        } else if (value.length >= 4) {
            value = value.substring(0,4) + "-" + value.substring(4);
        }

        e.target.value = value;
    });
});

// 입력 중 border 해제 (textarea)
textareas.forEach(textarea => {
    textarea.addEventListener("input", () => {
        if (textarea.value.trim() !== "") {
            textarea.style.border = "";
        }
    });
});