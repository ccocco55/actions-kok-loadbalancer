document.addEventListener("DOMContentLoaded", function () {
    const editBtn = document.getElementById("edit-btn");
    const cancelBtn = document.getElementById("cancle-btn");
    const buttonWrap = document.querySelector(".button-wrap");
    const nameInputs = document.querySelectorAll(".name");
    const imgUpBtns = document.querySelectorAll(".img-up-btn-wrap");
    const categoryBtns = document.querySelectorAll(".category-button");
    const form = document.getElementById("profile-form");
    let saveBtn = null;

    // 편집 모드로 전환
    editBtn.addEventListener("click", function () {
        toggleEditMode(true);
    });

    // 취소 버튼 클릭 시 → 원래대로
    cancelBtn.addEventListener("click", function () {
        toggleEditMode(false);
    });

    // 저장 버튼 클릭 핸들러 (한 번만 정의)
    function createSaveButton() {
        saveBtn = document.createElement("button");
        saveBtn.type = "submit";
        saveBtn.className = "save-button";
        saveBtn.id = "save-btn";
        saveBtn.innerHTML = `
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" 
                 viewBox="0 0 24 24" fill="none" stroke="currentColor" 
                 stroke-width="2" stroke-linecap="round" stroke-linejoin="round" 
                 class="edit-icon">
                <path d="M15.2 3a2 2 0 0 1 1.4.6l3.8 3.8a2 2 0 0 1 .6 1.4V19a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2z"></path>
                <path d="M17 21v-7a1 1 0 0 0-1-1H8a1 1 0 0 0-1 1v7"></path>
                <path d="M7 3v4a1 1 0 0 0 1 1h7"></path>
            </svg> 저장
        `;
        buttonWrap.appendChild(saveBtn);

        // form.addEventListener("submit", function () {
        //     setTimeout(() => toggleEditMode(false), 500);
        // });
    }

    // 편집 모드 on/off 공통 함수
    function toggleEditMode(isEditing) {
        cancelBtn.style.display = isEditing ? "block" : "none";
        editBtn.style.display = isEditing ? "none" : "flex";

        nameInputs.forEach((input) => {
            input.classList.toggle("not-allowed", !isEditing);
            input.readOnly = !isEditing;
        });

        document.querySelectorAll("input[type='file']").forEach(input => {
            input.disabled = !isEditing;
        });

        imgUpBtns.forEach((btn) => {
            btn.style.display = isEditing ? "" : "none";
        });

        categoryBtns.forEach((btn) => {
            btn.classList.toggle("not-allowed", !isEditing);
        });

        if (isEditing && !saveBtn) {
            createSaveButton();
        } else if (!isEditing && saveBtn) {
            buttonWrap.removeChild(saveBtn);
            saveBtn = null;
        }
    }

    // 카테고리 버튼(산업분야, 기업규모) 처리
    categoryBtns.forEach((cateBtn) => {
        const jobElem = cateBtn.querySelector(".job");
        const jobItems = cateBtn.querySelectorAll(".job-3");
        const selectedSpan = cateBtn.querySelector("span.selected");

        // 버튼 클릭 시 목록 토글
        cateBtn.addEventListener("click", function (e) {
            if (cateBtn.classList.contains("not-allowed")) return;
            e.stopPropagation();
            jobElem.style.display = jobElem.style.display === "block" ? "" : "block";
        });

        // 옵션 클릭 시 선택값 변경
        jobItems.forEach((item) => {
            item.addEventListener("click", function (e) {
                e.stopPropagation();
                const text = item.querySelector(".job-6").innerText;
                selectedSpan.innerText = text;

                const hiddenInput = cateBtn.parentElement.querySelector("input[type='hidden']");
                if (hiddenInput) {
                    hiddenInput.value = text;
                }

                jobElem.style.display = "";
            });
        });
    });

    // 외부 클릭 시 목록 닫기
    document.addEventListener("click", function (e) {
        document.querySelectorAll(".job").forEach((jobElem) => {
            if (!jobElem.contains(e.target) && !e.target.closest(".category-button")) {
                jobElem.style.display = "";
            }
        });
    });

    // 이미지 업로드 공통 함수
    const handleImageUpload = (fileInputSelector, imgSelector) => {
        const fileInput = document.querySelector(fileInputSelector);
        const img = document.querySelector(imgSelector);

        fileInput.addEventListener("change", (e) => {
            const file = e.target.files[0];
            if (!file) return;

            const reader = new FileReader();
            reader.onload = (event) => {
                img.src = event.target.result;
            };
            reader.readAsDataURL(file);
        });
    };

    // 버튼 클릭 시 input 클릭
    const setFileButtonTrigger = (buttonSelector, fileInputSelector) => {
        const btn = document.querySelector(buttonSelector);
        const fileInput = document.querySelector(fileInputSelector);

        btn.addEventListener("click", (e) => {
            e.preventDefault();
            fileInput.click();
        });
    };

    // 프로필 이미지
    handleImageUpload(".pro-input", ".profile-img");
    setFileButtonTrigger("#prof-file-btn", ".pro-input");

    // 배경 이미지
    handleImageUpload(".back-input", ".back-img");
    setFileButtonTrigger("#back-file-btn", ".back-input");
});
