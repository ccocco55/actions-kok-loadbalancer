const layout = (() => {
    const showList = (adminInternNoticeListCriteriaDTO) => {
        const employListContainer = document.querySelector(".table.member-table tbody");
        let text = ``;

        if(!adminInternNoticeListCriteriaDTO.internNoticeList || adminInternNoticeListCriteriaDTO.internNoticeList.length === 0){
            text += `
            <tr class="no-data">
                <td colspan="7">
                    결과가 없습니다.
                </td>
            </tr>
            `;
        } else {
            adminInternNoticeListCriteriaDTO.internNoticeList.forEach((intern) => {
                text += `
                    <tr>
                        <td class="td-name">
                            <p>${intern.companyName}</p>
                        </td>
                        <td class="td-main-title">
                            <p>${intern.internNoticeTitle}</p>
                        </td>
                        <td class="td-sub-title">
                            <p>${intern.internNoticeSubTitle}</p>
                        </td>
                        <td class="td-status">
                            <p>`;
                    if (intern.internNoticeStatus === 'active') {
                        text += `모집중`;
                    } else {
                        text += `모집 완료`;
                    }
                    text += `</p>
                        </td>
                        <td class="td-date">
                            <p>${intern.internNoticeEndDate}</p>
                        </td>  
                        <td class="td-job">
                            <p>${intern.jobName}</p>
                        </td>
                        <td class="td-action text-center">
                            <div class="action-btn" data-id="${intern.id}">
                                <i class="mdi mdi-chevron-right"></i>
                            </div>
                        </td>
                    </tr>
                `;
            });
        }
        employListContainer.innerHTML = text;

        // 페이지 번호
        const pagination = document.querySelector(".pagination.kok-pagination");
        let criteria = adminInternNoticeListCriteriaDTO.internListCriteria;
        let textNumber = ``;

        if(criteria.hasPreviousPage){
            textNumber = `
                <li class="page-item page-num">
                    <a class="page-item-link page-item-num" data-page="${criteria.page - 1}">이전</a>
                </li>
            `;
        }
        for(let i = criteria.startPage; i <= criteria.endPage; i++){
            textNumber += `
                <li class="page-item page-num">
                    <a class="page-item-num" data-page="${i}">${i}</a>
                </li>
           `;
        }
        if(criteria.hasNextPage){
            textNumber += `
                <li class="page-item page-num">
                    <a class="page-item-link page-item-num" data-page="${criteria.page + 1}">다음</a>
                </li>
            `;
        }
        pagination.innerHTML = textNumber;

        // 총 개수
        const memberCount = document.querySelector(".member-count");
        let memberCountText = ``;
        memberCountText = `<span class="member-count">총
                                <span class="count-amount">${criteria.total}</span>건
                            </span>`;
        memberCount.innerHTML = memberCountText;

        // 페이지 번호 색상 이벤트
        const firstNumber = pagination.querySelector("li");
        if (firstNumber) {
            firstNumber.classList.add("active");
        }
    }

    const showInfo = (adminInternNoticeDetailDTO) => {
        const detailInfo = document.querySelector("#info-table tbody");
        const modalTitle = document.querySelector(".modal-title");
        const employInfo = adminInternNoticeDetailDTO;
        let titleText = ``;
        let text = ``;

        titleText = `${employInfo.internNoticeTitle}
                <span class="badge-label text-danger font-weight-bold ml-2">${employInfo.companyName}</span>`;
        modalTitle.innerHTML = titleText;

        text = `
            <tr>
                <th>모집 시작일</th>
                <td>${employInfo.internNoticeStartDate}</td>
                <th>모집 종료일</th>
                <td>${employInfo.internNoticeEndDate}</td>
            </tr>
            <tr>
                <th>인턴공고 제목</th>
                <td colspan="3">${employInfo.internNoticeTitle}</td>
            </tr>
            <tr>
                <th>인턴공고 부제목</th>
                <td colspan="3">${employInfo.internNoticeSubTitle}</td>
            </tr>
            <tr>
                <th>직무소개</th>
                <td colspan="3">${employInfo.internNoticeIntroduceJob}</td>
            </tr>
            <tr>
                <th>주요업무</th>
                <td colspan="3">${employInfo.internMainTasks}</td>
            </tr>
            <tr>
                <th>참고사항</th>
                <td colspan="3">${employInfo.internNoticeEtc}</td>
            </tr>
        `;
        detailInfo.innerHTML = text;
    }

    const showRequest = (adminInternNoticeDetailCriteriaDTO) => {
        const detailRequest = document.querySelector("#info-request tbody");
        let text = ``;

        if (adminInternNoticeDetailCriteriaDTO.internNoticeDetailRequests.length === 0) {
            text += `<tr>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>`;
        } else {
            adminInternNoticeDetailCriteriaDTO.internNoticeDetailRequests.forEach((request) => {
                console.log("request.userName: " + request.userName);
                text += `<tr>
                        <td>`;
                if(!request.userName){
                    text += `-`;
                } else {
                    text += `${request.userName}`;
                }
                text += `</td>
                        <td>`;
                if(!request.userEmail){
                    text += `-`;
                } else {
                    text += `${request.userEmail}`;
                }
                text += `</td>
                        <td>`;
                if(!request.userPhone){
                    text += `-`;
                } else {
                    text += `${request.userPhone}`;
                }
                text += `</td>
                        <td style="text-align: center;">`;
                if(request.requestInternStatus === 'await') {
                    text += `서류 접수`;
                } else if(request.requestInternStatus === 'accept') {
                    text += `합격`;
                } else {
                    text += `불합격`;
                }
                text += `</td></tr>`;
            });
        }
        detailRequest.innerHTML = text;

        const pagination = document.querySelector(".pagination.kok-pagination.detail-request");
        let criteria = adminInternNoticeDetailCriteriaDTO.internDetailCriteria;
        let textNumber = ``;

        if(criteria.hasPreviousPage){
            textNumber = `
                <li class="page-item page-num">
                    <a class="page-item-link page-item-num" data-page="${criteria.page - 1}">이전</a>
                </li>
            `;
        }
        for(let i = criteria.startPage; i <= criteria.endPage; i++){
            textNumber += `
                <li class="page-item page-num number">
                    <a class="page-item-num" data-page="${i}">${i}</a>
                </li>
           `;
        }
        if(criteria.hasNextPage){
            textNumber += `
                <li class="page-item page-num">
                    <a class="page-item-link page-item-num" data-page="${criteria.page + 1}">다음</a>
                </li>
            `;
        }
        pagination.innerHTML = textNumber;
    }

    return {showList: showList, showInfo: showInfo, showRequest: showRequest}
})();