const internDatailLayout = (() => {
    const contentLayout = () => {
        const contentArea = document.querySelector("#detail-list-table");
        contentArea.innerHTML = `
             <div class="card-title-wrap">
                 <div class="card-title">
                 <!-- 전체 총 명수-->
                 </div>
                <a href="#" class="download">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="download-svg"><path d="M5 12h14"></path><path d="M12 5v14"></path></svg>
                    이력서 다운로드
                </a>
            </div>
            <div class="card-content">
                <table class="list-list-table">
                    <colgroup>
                        <col style="width: 5%;">
                        <col style="width: 20%;">
                        <col style="width: 20%;">
                        <col style="width: 20%;">
                        <col style="width: 20%;">
                        <col style="width: 15%;">
                    </colgroup>
                    <thead class="list-list-thead">
                        <tr class="list-tr">
                            <th class="list-head-sub">선택</th>
                            <th class="list-head-main">이름</th>
                            <th class="list-head-sub">이메일</th>
                            <th class="list-head-sub">전화번호</th>
                            <th class="list-head-sub">지원일</th>
                            <th class="list-head-sub">상태</th>
                        </tr>
                    </thead>
                    <tbody class="list-list-tbody">
<!--                        테이블-->
                    </tbody>
                </table>
                <div class="page-div">
                    <nav class="page-nav">
                        <ul class="page-ul">
<!--                              페이지네이션-->
                        </ul>
                    </nav>
                </div>
            </div>
        `
    }

    // 리스트
    const rowTemplate = (lists) => {
        const tbody = document.querySelector("#detail-list-table .list-list-tbody");
        const downloadBtn = document.querySelector(".download");
        if (!tbody) return;

        tbody.innerHTML = "";
        if (!lists || lists.length === 0) {
            tbody.innerHTML = `
                <tr class="body-tr no-data">
                    <td class="body-td" colspan="9">
                        <div class="text">조건에 맞는 지원자가 없습니다.</div>
                    </td>
                </tr>`;
            if (downloadBtn) downloadBtn.style.display = "none";
            return;
        }

        if (downloadBtn) downloadBtn.style.display = "inline-flex";

        lists.forEach(list => {
            tbody.innerHTML += `
                <tr class="body-tr" data-user-id="${list.userId}">
                    <td class="body-td">
                        <label style="display:block; width:100%; height:100%;">
                            <input type="checkbox" name="" class="check-download" data-member-id="${list.userId}">
                        </label>
                    </td>
                    <td class="body-td">
                        <div>
                            <div class="td-title">${list.userName}</div>
                        </div>
                    </td>
                    <td class="body-td">
                        ${list.userEmail}
                    </td>
                    <td class="body-td">
                        <span class="span-number">${list.userPhone}</span>
                    </td>
                    <td class="body-td">${list.requestDatetime}</td>
                    <td class="body-td">
                        <span class="exp-status">${list.requestInternStatus === "await" ? "서류 검토 중" : list.requestInternStatus === "accept" ? "합격" : "불합격"}</span>
                    </td>
                     <td class="body-td">
                        <a href="/enterprise-console/intern/application/${list.internNoticeId}/${list.userId}" class="more-btn">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="svg-right">
                                <path d="m9 18 6-6-6-6"></path>
                            </svg>
                        </a>
                    </td>
                </tr>
            `;
        });
    }

    // 페이지네이션 - layout
    const renderPagination = (criteria) => {
        const paginationArea  = document.querySelector("#detail-list-table .page-ul");
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
        const countArea = document.querySelector("#detail-list-table .card-title");
        if (!countArea) return;

        countArea.innerHTML = `
            <div class="main-title">지원자 목록(<span class="count">${data.totalCount}</span>명)</div>
        `
    }

    return {contentLayout:contentLayout, rowTemplate:rowTemplate, renderPagination:renderPagination, listTotalCount:listTotalCount}
})();