const layout = (() => {
    const showList = (adminExperienceListDTO) => {
        const experienceListContainer = document.querySelector(".table.member-table tbody");
        let text = ``;
        // 목록
        if(!adminExperienceListDTO.experienceList || adminExperienceListDTO.experienceList.length === 0) {
            text += `
            <tr class="no-data">
                <td colspan="7">
                    결과가 없습니다.
                </td>
            </tr>
            `;
        } else {
            adminExperienceListDTO.experienceList.forEach((experience) => {
                text += `
                    <tr>
                        <td class="td-name">
                            <p>${experience.companyName}</p>
                        </td>
                        <td class="td-main-title">
                            <p>${experience.experienceNoticeTitle}</p>
                        </td>
                        <td class="td-sub-title">
                            <p>${experience.experienceNoticeSubtitle}</p>
                        </td>
                        <td class="td-status">
                            <p>`;
                    text += experience.experienceNoticeStatus === 'active' ? '모집 중' : '모집 완료';
                    text += `</p>
                        </td>
                        <td class="td-date">
                            <p>${experience.experienceEndDate}</p>
                        </td>  
                        <td class="td-job">
                            <p>${experience.jobName}</p>
                        </td>
                        <td class="td-action text-center">
                            <div class="action-btn" data-id="${experience.id}">
                                <i class="mdi mdi-chevron-right"></i>
                            </div>
                        </td>
                    </tr>
                `;
            });
        }
        experienceListContainer.innerHTML = text;

        // 페이지 번호
        const pagination = document.querySelector(".pagination.kok-pagination");
        let criteria = adminExperienceListDTO.listCriteria;
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

    const showInfo = (adminExperienceDTO) => {
        const detailInfo = document.querySelector("#info-table tbody");
        const modalTitle = document.querySelector(".modal-title");
        const experienceInfo = adminExperienceDTO;
        let titleText = ``;
        let text = ``;

        titleText = `${experienceInfo.experienceNoticeTitle}
                <span class="badge-label text-danger font-weight-bold ml-2">${experienceInfo.companyName}</span>`;
        modalTitle.innerHTML = titleText;

        text = `
            <tr>
                <th>모집 시작일</th>
                <td>${experienceInfo.experienceNoticeStartDate}</td>
                <th>모집 종료일</th>
                <td>${experienceInfo.experienceNoticeEndDate}</td>
            </tr>
            <tr>
                <th>체험 시작일</th>
                <td>${experienceInfo.experienceStartDate}</td>
                <th>체험 종료일</th>
                <td>${experienceInfo.experienceEndDate}</td>
            </tr>
            <tr>
                <th>체험공고 제목</th>
                <td colspan="3">${experienceInfo.experienceNoticeTitle}</td>
            </tr>
            <tr>
                <th>체험공고 부제목</th>
                <td colspan="3">${experienceInfo.experienceNoticeSubtitle}</td>
            </tr>
            <tr>
                <th>직무소개</th>
                <td colspan="3">${experienceInfo.experienceNoticeIntroduceJob}</td>
            </tr>
            <tr>
                <th>주요업무</th>
                <td colspan="3">${experienceInfo.experienceMainTasks}</td>
            </tr>
            <tr>
                <th>참고사항</th>
                <td colspan="3">${experienceInfo.experienceNoticeEtc}</td>
            </tr>
        `;
        detailInfo.innerHTML = text;
    }

    const showRequest = (adminExperienceDetailDTO) => {
        const detailRequest = document.querySelector("#info-request tbody");
        let text = ``;
        if (adminExperienceDetailDTO.userRequestExperience.length === 0) {
            text += `<tr>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>`;
        } else {
            adminExperienceDetailDTO.userRequestExperience.forEach((request) => {
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
                if(request.requestExperienceStatus === 'await') {
                    text += `서류 접수`;
                } else if(request.requestExperienceStatus === 'accept') {
                    text += `합격`;
                } else {
                    text += `불합격`;
                }
                text += `</td></tr>`;
            });
        }
        detailRequest.innerHTML = text;

        const pagination = document.querySelector(".pagination.kok-pagination.detail-request");
        let criteria = adminExperienceDetailDTO.adminExperienceRequestCriteria;
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

    const showEvaluation = (adminExperienceDetailDTO) => {
        const detailEvaluation = document.querySelector("#info-evaluation");
        let text = ``;

        if (adminExperienceDetailDTO.userEvaluation.length === 0) {
            text += `
                <table class="info-table w-100">
                    <tbody>
                        <tr>
                            <th>이름</th>
                            <td>-</td>
                            <th>회원ID (이메일)</th>
                            <td>-</td>
                        </tr>
                        <tr>
                            <th>전화번호</th>
                            <td>-</td>
                            <th>평가점수</th>
                            <td>-</td>
                        </tr>
                        <tr>
                            <th>총 평</th>
                            <td colspan="4">-</td>
                        </tr>
                    </tbody>
                </table>`;
        } else {
            adminExperienceDetailDTO.userEvaluation.forEach((evaluation) => {
                text += `
                    <table class="info-table w-100">
                        <tbody>
                            <tr>
                                <th>이름</th>
                                <td>`;
                                if(!evaluation.userName){
                                    text += `-`;
                                } else if (evaluation.userName) {
                                    text += `${evaluation.userName}`;
                                }
                text += `</td>
                                <th>회원ID (이메일)</th>
                                <td>`;
                                if(evaluation.userEmail == null){
                                    text += `-`;
                                } else {
                                    text += `${evaluation.userEmail}`;
                                }
                text += `</td>
                            </tr>
                            <tr>
                                <th>전화번호</th>
                                <td>`;
                                if(evaluation.userPhone == null){
                                    text += `-`;
                                } else {
                                    text += `${evaluation.userPhone}`;
                                }
                text += `</td>
                                <th>평가점수</th>
                                <td>`;
                                if(evaluation.evaluationAvgScore == null){
                                    text += `-`;
                                } else {
                                    text += `${evaluation.evaluationAvgScore}`;
                                }
                text += `</td>
                            </tr>
                            <tr>
                                <th>총 평</th>
                                <td colspan="4">`;
                                if(evaluation.evaluationContent == null){
                                    text += `-`;
                                } else {
                                    text += `${evaluation.evaluationContent}`;
                                }
                text += `</td>
                            </tr>    
                        </tbody>
                    </table>
                `;
            });
        }
        detailEvaluation.innerHTML = text;

        const pagination = document.querySelector(".pagination.kok-pagination.detail-evaluation");
        let criteria = adminExperienceDetailDTO.adminExperienceCriteria;
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

    return {showList: showList, showInfo: showInfo, showRequest: showRequest, showEvaluation: showEvaluation}
})();