const companyLayout = (() => {
    const showList = (companiesCriteria, search = false) => {
        const listContainer = document.querySelector('.list-container');
        let text = ``;

        if (!companiesCriteria.companies || companiesCriteria.companies.length === 0) {
            if (search) {
                listContainer.innerHTML = `
                    <div class="no-data">
                        <p class="title">조건에 맞는 기업이 없습니다.</p>
                        <p class="description">조건을 변경하여 다시 검색해 보세요.</p>
                    </div>
                `;
            }
            return;
        }

        companiesCriteria.companies.forEach((company) => {
            text += `
            <div class="list-item" data-company-id="${company.userId}">
                <button class="list-item-btn" onclick="window.location.href='/company/${company.userId}'">
                    <div class="list-item-background">
                        <img src="${company.companyBackgroundFile || '/images/mypage/banner.jpg'}">
                    </div>
                    <div class="list-item-header">
                        <div class="list-item-thumb">
                            <img src="${company.companyProfileFile || '/images/member/profile.png'}" style="background-color: #ccc; border-radius: 999px;">
                        </div>
                        <div class="list-item-content">
                            <p class="list-item-category">${company.companySectorName|| '-'}</p>
                            <p class="list-item-name">${company.companyName}</p>
                            <p class="list-item-description">${company.companyInfo || '-'}</p>
                        </div>
                    </div>
                </button>
            </div>`;
        });

        if (search) {
            listContainer.innerHTML = text;
        } else {
            listContainer.innerHTML += text;
        }
    };

    return { showList : showList };
})();
