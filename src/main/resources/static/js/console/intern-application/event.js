document.addEventListener("DOMContentLoaded", () => {
    const selectContainer = document.querySelector(".select-container");
    const statusButton = selectContainer.querySelector(".status-button");
    const dropdown = selectContainer.querySelector(".job");
    const statusSpan = selectContainer.querySelector(".status-button-span");
    const options = dropdown.querySelectorAll(".job-3");
    const popup = document.querySelector(".popup-container");
    const popupTitle = popup.querySelector(".pop-title");
    const btnNo = popup.querySelector(".btn-no");
    const btnYes = popup.querySelector(".btn-yes");
    let selectedStatus = "";
    let confirmedStatus = "";

    // 버튼 클릭 시 드롭다운 열기 / 닫기
    statusButton.addEventListener("click", (e) => {
        e.stopPropagation();
        dropdown.classList.toggle("active");
    });

    // 옵션 클릭 시 상태 변경
    options.forEach((option) => {
        option.addEventListener("click", () => {
            // 모든 옵션 active 제거
            options.forEach((opt) => opt.classList.remove("active"));
            option.classList.add("active");

            // 선택한 상태 텍스트 임시 저장
            selectedStatus = option.querySelector(".job-6").textContent.trim();

            // 팝업 문구 갱신
            const currentHTML = popupTitle.innerHTML;
            const newHTML = currentHTML.replace(/'(.*?)'/, `'${selectedStatus}'`);
            popupTitle.innerHTML = newHTML;

            // 드롭다운 닫고 팝업 열기
            dropdown.classList.remove("active");
            popup.classList.add("active");
        });
    });

    // 팝업 취소 버튼
    btnNo.addEventListener("click", async() => {
        popup.classList.remove("active");
        selectedStatus = "";
    });

    // 팝업 확인(진행 상태 변경) 버튼
    btnYes.addEventListener("click", async () => {
        if (!selectedStatus) return;

        // 선택한 상태 반영
        confirmedStatus = selectedStatus;
        statusSpan.textContent = confirmedStatus;
        popup.classList.remove("active");

        const statusMap = {
            "합격": "accept",
            "불합격": "reject",
            "서류 검토 중": "await"
        };

        const statusValue = statusMap[confirmedStatus] || confirmedStatus.toLowerCase();

        try {
            await applicationInternService.updateStatus(memberId, internNoticeId, statusValue);
        } catch (error) {
            alert("상태 변경에 실패했습니다.");
        }
    });

    // 바깥 클릭 시 닫기
    document.addEventListener("click", (e) => {
        if (!selectContainer.contains(e.target)) {
            dropdown.classList.remove("active");
        }
    });

});

document.addEventListener("DOMContentLoaded", () => {
    const downloadBtn = document.querySelector(".download-btn");
    if (!downloadBtn) return;

    downloadBtn.addEventListener("click", async () => {
        try {
            const result = await applicationInternService.downloadResume(internNoticeId, memberId);

            console.log(result);
            const downloadUrl = result.urls[0];

            if (!downloadUrl) {
                alert("다운로드 가능한 파일이 없습니다.");
                return;
            }

            window.open(downloadUrl, "_blank");
        } catch (error) {
            console.error("다운로드 중 오류 발생:", error);
            alert("다운로드 중 오류가 발생했습니다.");
        }
    });
});
