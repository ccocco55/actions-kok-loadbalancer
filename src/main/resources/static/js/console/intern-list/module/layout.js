const internLayout = (() => {
    const contentLayout = () => {
        const contentArea = document.querySelector("#intern-list-table");
        contentArea.innerHTML = `
            <div class="list-list-head">
            </div>
            <div class="list-list-wrap">
                <div class="list-list-main">
                    <table class="list-list-table">
                        <thead class="list-list-thead">
                            <tr class="list-tr">
                                <th class="list-head-main">공고 제목</th>
                                <th class="list-head-sub">직군</th>
                                <th class="list-head-sub">지원자 수</th>
                                <th class="list-head-sub">저장 수</th>
                                <th class="list-head-sub">시작일</th>
                                <th class="list-head-sub">마감일</th>
                                <th class="list-head-sub">상태</th>
                                <th class="list-head-sub">모집 중</th>
                                <th class="list-head-sub"></th>
                            </tr>
                        </thead>
                        <tbody class="list-list-tbody">
        <!--                   여기에 데이터 들어감 - rowTemplate -->
                        </tbody>
                    </table>
                    <div class="page-div">
                        <nav class="page-nav">
                            <ul class="page-ul">
                                <!-- 페이지네이션-->
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        `
    }

    // 리스트
    const rowTemplate = (lists) => {
        const tbody = document.querySelector("#intern-list-table .list-list-tbody");
        if (!tbody) return;

        let text = "";
        if (!lists || lists.length === 0) {
            text = `
                <tr class="body-tr no-data">
                    <td class="body-td" colspan="9">
                        <div class="text">조건에 맞는 공고가 없습니다.</div>
                    </td>
                </tr>`;
            tbody.innerHTML = text;
            return;
        }

        lists.forEach(list => {
            text += `
                <tr class="body-tr" data-id="${list.id}">
                    <td class="body-td">
                        <div>
                            <div class="td-title">${list.internNoticeTitle}</div>
                            <div class="td-sub">${list.internNoticeSubtitle ?? ""}</div>
                        </div>
                    </td>
                    <td class="body-td">${list.jobCategoryName ?? "-"}</td>
                    <td class="body-td"><span class="span-number">${list.applicantCount}</span> 명</td>
                    <td class="body-td"><span class="span-number">${list.saveCount}</span> 명</td>
                    <td class="body-td">${list.internNoticeStartDate}</td>
                    <td class="body-td">${list.internNoticeEndDate}</td>
                    <td class="body-td">
                        <span class="exp-status ${list.internNoticeStatus === "active" ? "active" : "gray"}"">${list.internNoticeStatus == "inactive" ? "모집 완료" : "모집 중"}</span>
                    </td>
                    <td class="body-td">
                `;
            const endDate = new Date(list.internNoticeEndDate);
            endDate.setHours(23, 59, 59, 999);

            if(endDate.getTime() >= new Date().getTime()) {
                text += `
                    <div class="appli-active">
                        <button class="appli-active-btn ${list.internNoticeStatus === "active" ? "active" : "inactive"}"">
                            <span class="circle"></span>
                        </button>
                    </div>
                    `;
            }
                text += `
                    </td>
                    <td class="body-td">
                        <button class="hambuger">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="hambuger-svg"><circle cx="12" cy="12" r="1"></circle><circle cx="19" cy="12" r="1"></circle><circle cx="5" cy="12" r="1"></circle></svg>
                        </button>
                        <div class="hambuger-pop-wrap">
                            <div class="hambuger-pop">
                                <div class="pop-head">작업</div>
                                <a href="/enterprise-console/intern/applicate-list/${list.id}" id="detail-btn" class="hambuger-list">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="hambuger-svg"><path d="M2.062 12.348a1 1 0 0 1 0-.696 10.75 10.75 0 0 1 19.876 0 1 1 0 0 1 0 .696 10.75 10.75 0 0 1-19.876 0"></path><circle cx="12" cy="12" r="3"></circle></svg>
                                    상세보기
                                </a>
                                <a href="/enterprise-console/intern/edit/${list.id}" id="edit-btn" class="hambuger-list">
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
        tbody.innerHTML += text;
    }

    // 페이지네이션 - layout
    const renderPagination = (criteria) => {
        const paginationArea  = document.querySelector("#intern-list-table .page-ul");
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

    // 페이지네이션 - 총개수
    const listTotalCount = (data) => {
        const countArea = document.querySelector("#intern-list-table .list-list-head");
        if (!countArea) return;

        countArea.innerHTML = `
            <div class="list-list-head-text">전체 공고 <span class="count">${data.totalCount}</span>개</div>
        `
    }

    // 모집 중인 체험 공고 개수, 활성화된 공고의 지원자 개수, 누적 지원자 개수
    const totalCount = (data) => {
        const countArea = document.querySelector("#intern-total-wrap");
        if (!countArea) return;

        countArea.innerHTML = `
            <div class="cards">
                <div class="card-top">
                    <div class="card-top-text">
                    모집 중인 인턴 공고
                    </div>
                </div>
                <div class="card-body">
                    <div class="active-total">
                        <div class="card-body-title"><span class="count">${data.activeTotalCount}</span>개</div>
                            <p class="card-body-sub">전체 인턴 공고: <span class="count">${data.totalCount}</span>개</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="cards">
                <div class="card-top">
                    <div class="card-top-text">
                    활성화된 공고의 지원자
                    </div>
                </div>
                <div class="card-body">
                    <div class="request-total">
                        <div class="card-body-title"><span class="count">${data.totalRequestCount}</span>명</div>
                        <p class="card-body-sub">활성화 상태인 인턴 공고의 지원자</p>
                    </div>
                </div>
            </div>
            <div class="cards">
                <div class="card-top">
                    <div class="card-top-text">
                    누적 지원자
                    </div>
                </div>
                <div class="card-body">
                    <div>
                        <div class="card-body-title"><span class="count">${data.activeRequestCount}</span>명</div>
                        <p class="card-body-sub">전체 인턴 누적 지원자 수</p>
                    </div>
                </div>
            </div>
        `
    }

    return {contentLayout:contentLayout, rowTemplate:rowTemplate, renderPagination:renderPagination, listTotalCount:listTotalCount, totalCount:totalCount }
})();