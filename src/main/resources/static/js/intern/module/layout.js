const internLayout = (() => {
    const showList = async (interns) => {   // 👈 async 붙임
        const container = document.querySelector('.list-container');
        if (!container) return;

        if (!Array.isArray(interns) || interns.length === 0) {
            container.innerHTML = '<p class="no-results">검색 결과가 없습니다.</p>';
            return;
        }

        let html = '';

        for (const intern of interns) {
            const fileUrl = await fetch(`/api/interns/profile?companyId=${intern.companyId}`)
                .then(res => res.text());

            html += `
                <div class="list-item">
                    <button class="list-item-btn companyId-${intern.companyId} internId-${intern.id}">
                        <div class="list-item-header">
                            <div class="list-item-thumb"><img src="${fileUrl}" alt=""></div>
                            <div class="list-item-content">
                                <p class="list-item-title">${intern.companyName || ''}</p>
                                <p class="list-item-subtitle">${intern.internNoticeTitle || ''}</p>
                                <p class="list-item-description">${intern.internNoticeSubtitle || ''}</p>
                            </div>
                        </div>
                        <div class="list-item-meta">
                            <div class="list-item-meta-field">
                                <p class="list-item-label">직군</p>
                                <div class="list-item-value"><p>${intern.jobName || ''}</p></div>
                            </div>
                            <div class="list-item-meta-field">
                                <p class="list-item-label">규모</p>
                                <div class="list-item-value"><p>${intern.companyScaleName || ''}</p></div>
                            </div>
                        </div>
                    </button>
                </div>
            `;
        }

        container.innerHTML = html;

        applyFilters();
    };

    return { showList };
})();

// 필터 적용 함수
function applyFilters() {
    const listItems = document.querySelectorAll(".list-item");

    // active 직군 버튼 p 텍스트
    const activeJobs = Array.from(document.querySelectorAll(".sector-dropdown .dropdown-btn.active > p"))
        .map(p => p.innerText.trim());

    // active 규모 버튼 p 텍스트
    const activeScales = Array.from(document.querySelectorAll(".scale-dropdown .dropdown-btn.active > p"))
        .map(p => p.innerText.trim());

    // 검색 키워드 가져오기
    const keyword = document.getElementById("keyword-input")?.value.trim() || "";

    listItems.forEach(item => {
        const jobEl = item.querySelector(".list-item-meta-field:first-child .list-item-value > p");
        const jobName = jobEl ? jobEl.innerText.trim() : "";


        const scaleEl = item.querySelector(".list-item-meta-field:nth-child(2) .list-item-value > p");
        const scaleName = scaleEl ? scaleEl.innerText.trim() : "";

        const jobMatch = activeJobs.length === 0 || activeJobs.some(text => jobName.includes(text));
        const scaleMatch = activeScales.length === 0 || activeScales.some(text => scaleName.includes(text));
        const keywordMatch = true;

        item.style.display = (jobMatch && scaleMatch && keywordMatch) ? "" : "none";
    });
}
