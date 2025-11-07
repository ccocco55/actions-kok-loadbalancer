const adLayout = (() => {
    const contentLayout = () => {
        const contentArea = document.querySelector("#ad-list-table");
        contentArea.innerHTML = `
            <div class="table-wrap">
                <table class="list">
                    <thead class="list-thead">
                        <tr class="list-tr">
                            <th class="list-th">광고 메인 텍스트</th>
                            <th class="list-th">기간</th>
                            <th class="list-th">금액</th>
                            <th class="list-th">승인상태</th>
                            <th class="list-th">상태</th>
                            <th class="list-th">작업</th>
                        </tr>
                    </thead>
                    <tbody class="list-tbody">
<!--                       데이터 rowTemplate -->
                    </tbody>
                </table>
            </div>
            <div class="page-div">
                <nav class="page-nav">
                    <ul class="page-ul">
<!--                        페이지네이션-->
                    </ul>
                </nav>
            </div>
        `
    }

    // 리스트
    const rowTemplate = (lists) => {
        const tbody = document.querySelector("#ad-list-table .list-tbody");
        if (!tbody) return;

        tbody.innerHTML = "";
        if (!lists || lists.length === 0) {
            tbody.innerHTML = `
                <tr class="body-tr no-data">
                    <td class="body-td" colspan="6">
                        <div class="text">광고가 없습니다.</div>
                    </td>
                </tr>`;
            return;
        }

        lists.forEach(list => {
            tbody.innerHTML += `
                <tr class="list-tr ing" data-id="${list.id}">
                    <td class="list-list-td">${list.advertisementMainText}</td>
                    <td class="list-list-td">
                        <div class="table-date">
                            <div class="table-date-start">
                                ${list.advertiseStartDatetime}
                            </div>
                            <div class="table-date-end">
                                ~ ${list.advertiseEndDatetime}
                            </div>
                        </div>
                    </td>
                    <td class="list-list-td">₩<span class="price">${Number(list.paymentPrice).toLocaleString()}</span></td>
                    <td class="list-list-td">
                        <span class="list-table-span ${list.advertisementRequestStatus === "await" ? "await" : list.advertisementRequestStatus === "accept" ? "accept" : list.advertisementRequestStatus === "reject" ? "reject" : ""}">
                            ${list.advertisementRequestStatus === "await" ? "대기중" : list.advertisementRequestStatus === "accept" ? "진행가능" : list.advertisementRequestStatus === "reject" ? "반려" : ""}
                        </span>
                    </td>
                    <td class="list-list-td">
                        <div class="appli-active">
                            ${list.advertisementRequestStatus === "accept" ? `
                                <button class="appli-active-btn ${list.advertisementStatus === "active" ? "active" : "inactive"}">
                                    <span class="circle"></span>
                                </button>
                            ` : ``}
                        </div>
                    </td>
                    <td class="list-list-td">
                        <button class="hambuger">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="ham-svg"><circle cx="12" cy="12" r="1"></circle><circle cx="19" cy="12" r="1"></circle><circle cx="5" cy="12" r="1"></circle></svg>
                        </button>
                        <div class="hambuger-pop-wrap">
                            <div class="hambuger-pop">
                                <div class="pop-head">작업</div>
                                <a href="/enterprise-console/ad/edit/${list.id}" class="hambuger-list">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="hambuger-svg"><path d="M12 3H5a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path d="M18.375 2.625a1 1 0 0 1 3 3l-9.013 9.014a2 2 0 0 1-.853.505l-2.873.84a.5.5 0 0 1-.62-.62l.84-2.873a2 2 0 0 1 .506-.852z"></path></svg>
                                    수정하기
                                </a>
                                <div class="bar-hambuger"></div>
                                <button type="button" class="red-ham-list">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="hambuger-svg"><path d="M3 6h18"></path><path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6"></path><path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2"></path></svg>
                                    삭제하기
                                </button>
                            </div>
                        </div>
                    </td>
                </tr>
            `;
        });
    }

    // 페이지네이션 - layout
    const renderPagination = (criteria) => {
        const paginationArea  = document.querySelector("#ad-list-table .page-ul");
        if (!paginationArea) return;

        let html = ``;

        for (let i = criteria.startPage; i <= criteria.endPage; i++) {
            html += `
                <li class="page-li page-num">
                    <a href="#" data-page="${i}" class="page-a ${i === criteria.page ? "active" : ""}">${i}</a>
                </li>
            `;
        }

        paginationArea.innerHTML = html;
    };

    // 모집 중인 체험 공고 개수, 활성화된 공고의 지원자 개수, 누적 지원자 개수
    const totalCount = (data) => {
        const countArea = document.querySelector("#ad-total-wrap");
        if (!countArea) return;

        countArea.innerHTML = `
            <div class="card">
                <div class="card-title">
                    <div class="card-title-text">총 광고</div>
                </div>
                <div class="card-content">
                    <div class="card-main">${data.totalCount}</div>
                    <p class="card-content-p">전체 광고 개수</p>
                </div>
            </div>
            <div class="card">
                <div class="card-title">
                    <div class="card-title-text">진행중 상태인 광고</div>
                </div>
                <div class="card-content">
                    <div class="card-main">${data.activeTotalCount}</div>
                    <p class="card-content-p">진행 중인 광고 개수</p>
                </div>
            </div>
            <div class="card">
                <div class="card-title">
                    <div class="card-title-text">총 금액</div>
                </div>
                <div class="card-content">
                    <div class="card-main">￦<span class="price">${Number(data.activeTotalPrice).toLocaleString()}</span></div>
                    <p class="card-content-p">진행중인 총 광고 비용</p>
                </div>
            </div>
        `
    }

    return {contentLayout:contentLayout, rowTemplate:rowTemplate, renderPagination:renderPagination, totalCount:totalCount }
})();