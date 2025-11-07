const layout = (() => {
    const showList = (result) => {
        const companyListContainer = document.querySelector("tbody.company-list");
        let text = ``;

        const countAmount = document.querySelector(".count-amount");

        countAmount.innerText = result.total;

        if (result.adminCompanyDTOList !== null && result.adminCompanyDTOList.length > 0) {

            result.adminCompanyDTOList.forEach((company) => {
                text += `
                    <tr>
                        <td class="td-name">
                            <div class="member-name">${company.userName}
                                <span class="badge-label badge text-danger ml-2">기업회원</span>
                            </div>
                            <div class="member-id">${company.userEmail}</div>
                        </td>
                        <td class="td-amount pr-4 font-weight-bold">
                            <p>${company.companyName}</p>
                        </td>
                        <td class="td-email">
                            <p>${company.userEmail}</p>
                        </td>
                        <td class="td-phone">
                            <p>${company.userPhone}</p>
                        </td>
                        <td class="td-profile">
                            <p>${company.companyUrl ?? '-'}</p>
                        </td>
                        <td class="td-job">
                            <p>${company.jobName ?? '-'}</p>
                        </td>
                        <td class="td-action text-center">
                            <div class="action-btn">
                                <i class="mdi mdi-chevron-right" data-id="${company.userId}"></i>
                            </div>
                        </td>
                    </tr>
           `;
            });
            companyListContainer.innerHTML = text;

        } else {
            text = `<tr class="no-data">
                    <td colspan="7">결과가 없습니다.</td>
                </tr>`;


            companyListContainer.innerHTML = text;
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
        let text = ``;

        if (result.experienceNoticeDTO && result.experienceNoticeDTO.length > 0) {
            result.experienceNoticeDTO.forEach((experiences) => {
                let status = ``;

                if (experiences.experienceNoticeStatus === "active") {
                    status = "모집중"
                }
                else if (experiences.experienceNoticeStatus === "inactive") {
                    status = "모집완료"
                }




                experiencesText += `
                    <tr>
                        <td>${experiences.experienceNoticeTitle}</td>
                        <td>${experiences.experienceNoticeSubtitle}</td>
                        <td>${status}</td>
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

        if (result.internNoticeDTO && result.internNoticeDTO.length > 0) {
            result.internNoticeDTO.forEach((requestIntern) => {
                let status = ``;

                if (requestIntern.internNoticeStatus === "active") {
                    status = "모집중"
                }
                else if (requestIntern.internNoticeStatus === "inactive") {
                    status = "모집완료"
                }




                internsText += `
                    <tr>
                        <td>${requestIntern.internNoticeTitle}</td>
                        <td>${requestIntern.internNoticeSubtitle}</td>
                        <td>${status}</td>
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



        if (result !== null) {
            text = `
            <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <div class="modal-title">
                                    ${result.companyName}
                                    <span class="badge-label text-danger font-weight-bold ml-2">기업회원</span>
                                </div>
                                <button class="close">
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
                                                                            <td>${result.userEmail}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>대표 번호</th>
                                                                            <td>${result.userPhone}</td>
                                                                        </tr> 
                                                                        <tr>
                                                                            <th>기업 링크</th>
                                                                            <td>${result.companyUrl ?? '-'}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>팔로워</th>
                                                                            <td>${result.followCount}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>체험공고 횟수</th>
                                                                            <td>${result.experienceNoticeCount}</td>
                                                                        </tr> 
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                            <!-- 테이블 오른쪽 -->                                                        
                                                            <div class="d-table-cell">
                                                                <table class="info-table">
                                                                    <tbody>                                                                        
                                                                        <tr>
                                                                            <th>기업</th>
                                                                            <td>${result.companyName}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>대표자</th>
                                                                            <td>${result.userName}</td>
                                                                        </tr>                                                                    
                                                                        <tr>
                                                                            <th>산업분야</th>
                                                                            <td>${result.jobName ?? '-'}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>기업규모</th>
                                                                            <td>${result.companyScaleName ?? '-'}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <th>인턴공고 횟수</th>
                                                                            <td>${result.internNoticeCount}</td>
                                                                        </tr> 
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- 체험공고 내역 -->
                                                    <div class="info-layout detail-info">
                                                        <div class="info-title justify-content-between">
                                                            <div class="flex-left d-flex">
                                                                <a href="/admin/experience" class="info-detail">
                                                                    <div class="title">체험공고 내역
                                                                        <i class="mdi mdi-menu-left ml-2"></i>
                                                                    </div>
                                                                </a>
                                                            </div>
                                                            <div class="flex-right"></div>
                                                        </div>
                                                        <div class="d-table w-100">
                                                            <table class="info-table">
                                                                <thead>
                                                                    <tr>
                                                                        <th class="middle">체험공고 제목</th>
                                                                        <th class="long">체험공고 부제목</th>
                                                                        <th class="short">모집상태</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>    
                                                                ${experiencesText}                                                                                                                            
                                                                </tbody>
                                                            </table>
                                                        </div>                             
                                                    </div>
                                                    <!-- 인턴공고 내역 -->
                                                    <div class="info-layout detail-info">
                                                        <div class="info-title justify-content-between">
                                                            <div class="flex-left d-flex">
                                                                <a href="/admin/employ" class="info-detail">
                                                                    <div class="title">인턴공고 내역
                                                                        <i class="mdi mdi-menu-left ml-2"></i>
                                                                    </div>
                                                                </a>
                                                            </div>
                                                            <div class="flex-right"></div>
                                                        </div>
                                                        <div class="d-table w-100">
                                                            <table class="info-table">
                                                                <thead>
                                                                    <tr>
                                                                        <th class="middle">인턴공고 제목</th>
                                                                        <th class="long">인턴공고 부제목</th>
                                                                        <th class="short">모집상태</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>   
                                                                ${internsText}                                                                                                                             
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