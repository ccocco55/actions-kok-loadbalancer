const submitBtn=document.querySelector(".submit-btn");
const radioBtns = document.querySelectorAll(".radio-label");

submitBtn.addEventListener("click", async () => {
    const checkedCount = document.querySelectorAll('input[type="radio"]:checked');
    if (checkedCount.length < 16) {
        alert("모든 항목을 선택해주세요.")
    } else {
        const text = document.getElementById("text-review");
        let sum = 0;
        // console.log(text.textContent.length);
        // console.log(text.textContent);
        // console.log(text.value);
        // console.log(text.value.length);
        checkedCount.forEach(radio => {
          sum += Number(radio.value);
        });
        const avg = sum / 16;
        if (text.value.length < 1) {
            alert("총평을 반드시 입력해주세요.")
        } else {
            const evaluation = {
                requestExperienceId: requestExperienceId,
                memberId: memberId,
                companyId: companyId,
                evaluationContent: text.value,
                evaluationAvgScore: avg
            };
            const response = await fetch(`/api/enterprise-console/experience/go-review`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(evaluation)
            })
            if (response.ok) {
                alert("평가가 등록되었습니다!");
                window.location.href = "/enterprise-console/experience/list";
            } else {
                alert("평가 등록 중 오류가 발생했습니다.");
            }
        }
    }
});