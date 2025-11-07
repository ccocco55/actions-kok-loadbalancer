const layout = (() => {
    const showList = (result) => {
        const noticeListContainer = document.querySelector("tbody.table-notice");
        let text = ``;

        const countAmount = document.querySelector(".count-amount");

        countAmount.innerText = result.total;

        if (result.userMemberDTOList !== null && result.userMemberDTOList.length > 0) {

            result.userMemberDTOList.forEach((userMemberDTO) => {
                text += `

                    <tr>
                        <td class="td-name">
                            <div class="member-name">${userMemberDTO.userName}
                                <span class="badge-label badge text-danger ml-2">일반회원</span>
                            </div>
                            <div class="member-id">${userMemberDTO.userEmail ?? '-'}</div>
                        </td>
                        <td class="td-amount pr-4 font-weight-bold">${userMemberDTO.userName}
                            <span class="amount-unit"> 님</span>
                        </td>
                        <td class="td-email">
                            <p>${userMemberDTO.userEmail ?? '-'}</p>
                        </td>
                        <td class="td-phone">
                            <p>${userMemberDTO.userPhone}</p>
                        </td>
                        <td class="td-profile">
                            <p>${userMemberDTO.memberProfileUrl ?? '-'}</p>
                        </td>
                        <td class="td-job">
                            <p>${userMemberDTO.jobName ?? '-'}</p>
                        </td>
                        <td class="td-action text-center">
                            <div class="action-btn">
                                <i class="mdi mdi-chevron-right" data-id="${userMemberDTO.id}"></i>
                            </div>
                        </td>
                    </tr> 
           `;
            });
            noticeListContainer.innerHTML = text;

        } else {
            text = `<tr class="no-data">
                    <td colspan="7">결과가 없습니다.</td>
                </tr>`;


            noticeListContainer.innerHTML = text;
        }

        const pagination = document.querySelector(".pagination.kok-pagination");
        let criteria = result.criteria;
        let textNumber = ``;


        if (criteria.hasPreviousPage) {
            textNumber += `
        <li class="page-item page-num">
            <a class="page-item-link page-item-num" data-page="${criteria.page - 1}">이전</a>
        </li>
    `;
        }

        for (let i = criteria.startPage; i <= criteria.endPage; i++) {
            // 현재 페이지면 <li>에 active 클래스 추가
            const activeClass = i === criteria.page ? "active" : "";

            textNumber += `
        <li class="page-item page-num page-number ${activeClass}">
            <a class="page-item-link page-item-num" data-page="${i}">${i}</a>
        </li>
    `;
        }

        if (criteria.hasNextPage) {
            textNumber += `
        <li class="page-item page-num">
            <a class="page-item-link page-item-num" data-page="${criteria.page + 1}">다음</a>
        </li>
    `;
        }

        pagination.innerHTML = textNumber;

        const firstNumber = pagination.querySelector("li");
        if (firstNumber) {
            firstNumber.classList.add("active");
        }

    }

    const showDetail = (result) => {

        const memberModal = document.querySelector(".member-modal");

        let experiencesText = ``;
        let internsText = ``;
        let postsText = ``;
        let text = ``;

        if (result.requestExperiences && result.requestExperiences.length > 0) {
            result.requestExperiences.forEach((experiences) => {
                let status = ``;

                if (experiences.requestExperienceStatus === "await") {
                    status = "서류접수"
                }
                else if (experiences.requestExperienceStatus === "accept") {
                    status = "합격"
                }
                else if (experiences.requestExperienceStatus === "reject") {
                    status = "불합격"
                }




                experiencesText += `
                    <tr>
                        <td>${experiences.companyName}</td>
                        <td>${experiences.experienceNoticeTitle}</td>
                        <td style="text-align: center;">${status}</td>
                        <td style="text-align: center;">${experiences.evaluationAvgScore ?? '-'}</td>
                    </tr>
                `
            })
        } else {
            experiencesText += `
                    <tr>
                        <td>-</td>
                        <td>-</td>
                        <td>-</td>
                    </tr>
                `
        }

        if (result.requestInterns && result.requestInterns.length > 0) {
            result.requestInterns.forEach((requestIntern) => {
                let status = ``;

                if (requestIntern.requestInternStatus === "await") {
                    status = "서류접수"
                }
                else if (requestIntern.requestInternStatus === "accept") {
                    status = "합격"
                }
                else if (requestIntern.requestInternStatus === "reject") {
                    status = "불합격"
                }




                internsText += `
                    <tr>
                        <td>${requestIntern.companyName}</td>
                        <td>${requestIntern.internNoticeTitle}</td>
                        <td style="text-align: center;">${status}</td>
                    </tr>
                `
            })
        } else {
            internsText += `
                    <tr>
                        <td>-</td>
                        <td>-</td>
                        <td>-</td>
                    </tr>
                `
        }

        if (result.posts && result.posts.length > 0) {
            result.posts.forEach((post) => {
                let status = ``;

                if (post.postStatus === "active") {
                    status = "게시중"
                }
                else if (post.postStatus === "inactive") {
                    status = "삭제"
                }

                postsText += `
                    <tr>
                        <td>${status}</td>
                        <td>${post.postContent}</td>
                        <td style="text-align: center;">${post.createdDateTime ? post.createdDateTime.split(" ")[0] : '-'}</td>
                    </tr>
                `
            })
        } else {
            postsText += `
                    <tr>
                        <td>-</td>
                        <td>-</td>
                        <td>-</td>
                    </tr>
                `
        }




        if (result !== null) {
            text = `
            <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div class="modal-title">
                                    ${result.userName}
                                    <span class="badge-label text-danger font-weight-bold ml-2">일반회원</span>
                                </div>
                                <button class="close close-button">
                                    <i class="mdi mdi-close"></i>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="divider">
                                    <div class="tab-view">
                                        <div class="tab-view-header"></div>
                                        <div class="tab-view-body">
                                            <div style="display: block;">
                                                <div class="tab-inner tab-detail">
                                                    <div class="info-layout detail-info">
                                                        <div class="info-title justify-content-between">
                                                            <div class="flex-left d-flex">
                                                                <div class="title">회원 상세정보</div>
                                                            </div>
                                                            <div class="flex-right"></div>
                                                        </div>
                                                        <div class="d-table w-100">
                                                            <!-- 테이블 왼쪽 -->
                                                            <div class="d-table-cell">
                                                                <table class="info-table">
                                                                    <tbody>
                                                                        <tr>
                                                                            <th>회원ID (이메일)</th>
                                                                            <td>${result.userEmail ?? '-'}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>핸드폰 번호</th>
                                                                            <td>${result.userPhone}</td>
                                                                        </tr>  
                                                                        <tr>
                                                                            <th>직군</th>
                                                                            <td>${result.jobName ?? '-'}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>체험신청 횟수</th>
                                                                            <td>${result.requestExperienceCount}</td>
                                                                        </tr> 
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                            <!-- 테이블 오른쪽 -->                                                        
                                                            <div class="d-table-cell">
                                                                <table class="info-table">
                                                                    <tbody>                                                                        
                                                                        <tr>
                                                                            <th>이름</th>
                                                                            <td>${result.userName}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>프로필 URL</th>
                                                                            <td>${result.memberProfileUrl ?? '-'}</td>
                                                                        </tr>   
                                                                        <tr>
                                                                            <th>평균 평점</th>
                                                                            <td>${result.avgScore ?? '-'}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>인턴신청 횟수</th>
                                                                            <td>${result.requestInternCount}</td>
                                                                        </tr>                                                                        
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- 체험 신청 내역 -->
                                                    <div class="info-layout detail-info">
                                                        <div class="info-title justify-content-between">
                                                            <div class="flex-left d-flex">
                                                                <div class="title">체험신청 내역</div>
                                                            </div>
                                                            <div class="flex-right"></div>
                                                        </div>
                                                        <div class="d-table w-100">
                                                            <table class="info-table">
                                                                <thead>
                                                                    <tr>
                                                                        <th class="middle">회사명</th>
                                                                        <th class="long">체험공고 제목</th>
                                                                        <th class="short" style="text-align: center;">지원상태</th>
                                                                        <th class="short" style="text-align: center;">평가점수</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    ${experiencesText}                                                                                                                                 
                                                                </tbody>
                                                            </table>
                                                        </div>                             
                                                    </div>
                                                    <!-- 인턴 신청 내역 -->
                                                    <div class="info-layout detail-info">
                                                        <div class="info-title justify-content-between">
                                                            <div class="flex-left d-flex">
                                                                <div class="title">인턴신청 내역</div>
                                                            </div>
                                                            <div class="flex-right"></div>
                                                        </div>
                                                        <div class="d-table w-100">
                                                            <table class="info-table">
                                                                <thead>
                                                                    <tr>
                                                                        <th class="middle">회사명</th>
                                                                        <th class="long">인턴공고 제목</th>
                                                                        <th class="middle" style="text-align: center;">지원상태</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    ${internsText}                                                                                                                                   
                                                                </tbody>
                                                            </table>
                                                        </div>                             
                                                    </div>
                                                    <!-- 회원 작성 게시글 -->
                                                    <div class="info-layout detail-info">
                                                        <div class="info-title justify-content-between">
                                                            <div class="flex-left d-flex">
                                                                <div class="title">작성 게시글</div>
                                                            </div>
                                                            <div class="flex-right"></div>
                                                        </div>
                                                        <div class="d-table w-100">
                                                            <table class="info-table">
                                                                <thead>
                                                                    <tr>
                                                                        <th class="long">게시글 제목</th>
                                                                        <th>게시글 내용</th>
                                                                        <th class="long" style="text-align: center;">게시글 작성 일자</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    ${postsText}                                                                                                                             
                                                                </tbody>
                                                            </table>
                                                        </div>                             
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button class="btn-close btn btn-outline-filter">닫기</button>
                            </div>
                        </div>
                    </div>
        `;

            memberModal.innerHTML = text;
        }


    }

    return {showList: showList, showDetail:showDetail};
})();