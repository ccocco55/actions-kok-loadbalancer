const layout = (() => {
    const showList = (adminAdvertisementCriteriaDTO) => {
        const amountSection = document.querySelector(".amount-section");
        let amountText = ``;

        const countStatus = adminAdvertisementCriteriaDTO.countDTO
        amountText = `
            <div class="amount-box form-info-box">
                <div class="revenue-box">
                    <div class="row">
                        <div class="col-auto title-col">
                            <span class="badge-label text-primary icon-label">
                                <i class="mdi mdi-check"></i>
                            </span>
                            <span class="badge-label">승인</span>
                        </div>
                        <div class="col text-right amount-col">
                            <span class="span-amount">${countStatus.acceptCount}</span>
                            <span class="amount-unit">건</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="amount-box form-info-box">
                <div class="revenue-box">
                    <div class="row">
                        <div class="col-auto title-col">
                            <span class="badge-label text-wait icon-label">
                                <i class="mdi mdi-timer-sand"></i>
                            </span>
                            <span class="badge-label">대기</span>
                        </div>
                        <div class="col text-right amount-col">
                            <span class="span-amount">${countStatus.awaitCount}</span>
                            <span class="amount-unit">건</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="amount-box amount-value-box">
                <div class="cancel-box">
                    <div class="row">
                        <div class="col-auto title-col">
                            <span class="badge-label text-danger icon-label">
                                <i class="mdi mdi-close"></i>
                            </span>
                            <span class="badge-label">거절</span>
                        </div>
                        <div class="col text-right amount-col">
                            <span class="span-amount">${countStatus.rejectCount}</span>
                            <span class="amount-unit">건</span>
                        </div>
                    </div>
                </div>
            </div>
        `;
        amountSection.innerHTML = amountText;

        const advertisementList = document.getElementById("advertisement-list");
        let text = ``;

        if(!adminAdvertisementCriteriaDTO.advertisements || adminAdvertisementCriteriaDTO.advertisements.length === 0) {
            text += `
            <tr class="no-data">
                <td colspan="7">
                    결과가 없습니다.
                </td>
            </tr>
            `;
        } else {
            adminAdvertisementCriteriaDTO.advertisements.forEach((advertisement) => {
                text += `
                    <tr>
                        <td class="td-name">
                            <p>${advertisement.companyName}</p>
                        </td>
                        <td class="td-main-title">
                            <p>${advertisement.advertisementMainText}</p>
                        </td>
                        <td class="td-sub-title">
                            <p>${advertisement.advertisementSubText}</p>
                        </td>
                        <td class="td-start">
                            <p>${advertisement.advertiseStartDatetime}</p>
                        </td>
                        <td class="td-end">
                            <p>${advertisement.advertiseEndDatetime}</p>
                        </td>  
                        <td class="td-status">
                            <p>`;
                    if (advertisement.advertisementRequestStatus === 'await') {
                        text += `대기`;
                    } else if (advertisement.advertisementRequestStatus === 'accept') {
                        text += `승인`;
                    } else if (advertisement.advertisementRequestStatus === 'reject') {
                        text += `거절`;
                    }
                    text += `</p>
                        </td>
                        <td class="td-action text-center">
                            <div class="action-btn" data-id="${advertisement.id}">
                                <i class="mdi mdi-chevron-right"></i>
                            </div>
                        </td>
                    </tr>
                `;
            });
        }
        advertisementList.innerHTML = text;

        // 페이지 번호
        const pagination = document.querySelector(".pagination.kok-pagination");
        let criteria = adminAdvertisementCriteriaDTO.criteria;
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
                    <a class="page-item-num" href="/admin/support/${i}" data-page="${i}">${i}</a>
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

        // 페이지 번호 색상 이벤트
        const firstNumber = pagination.querySelector("li");
        if (firstNumber) {
            firstNumber.classList.add("active");
        }
    }

    const showDetail = (adminAdvertisementDTO) => {
        const modalDialog = document.querySelector(".modal-dialog");
        let text = ``;

        text += `
            <div class="modal-content">
                <div class="modal-header">
                    <div class="modal-title">${adminAdvertisementDTO.companyName}</div>
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
                                                    <div class="title">광고 상세정보</div>
                                                </div>
                                                <div class="flex-right"></div>
                                            </div>
                                            <table class="info-table w-100">
                                                <tbody>
                                                    <tr>
                                                        <th>기업명</th>
                                                        <td>${adminAdvertisementDTO.companyName}</td>
                                                        <th>기업ID (이메일)</th>
                                                        <td>${adminAdvertisementDTO.userEmail}</td>
                                                    </tr>
                                                    <tr>
                                                        <th>대표번호</th>
                                                        <td>${adminAdvertisementDTO.userPhone}</td>
                                                        <th>기업URL</th>
                                                        <td>${adminAdvertisementDTO.companyUrl}</td>
                                                    </tr>
                                                    <tr>
                                                        <th>광고 시작일</th>
                                                        <td>${adminAdvertisementDTO.advertiseStartDatetime}</td>
                                                        <th>광고 종료일</th>
                                                        <td>${adminAdvertisementDTO.advertiseEndDatetime}</td>
                                                    </tr>
                                                    <tr>
                                                        <th>메인 텍스트</th>
                                                        <td colspan="3">${adminAdvertisementDTO.advertisementMainText}</td>
                                                    </tr>
                                                    <tr>
                                                        <th>서브 텍스트</th>
                                                        <td colspan="3">${adminAdvertisementDTO.advertisementSubText}</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="info-layout detail-info">`;
                                            if(adminAdvertisementDTO.advertisementBackgroundFiles && adminAdvertisementDTO.advertisementBackgroundFiles.length > 0) {
                                                text +=`
                                                        <div class="info-title justify-content-between">
                                                            <div class="flex-left d-flex">
                                                                <div class="title">이미지</div>
                                                            </div>
                                                            <div class="flex-right"></div>
                                                        </div>
                                            <!--  배경 이미지  -->`;
                                                adminAdvertisementDTO.advertisementBackgroundFiles.forEach((file) => {
                                                    text += `<img src="${file.filePath}" height="100%" width="100%" alt="">`;
                                                });
                                            }
        text += `
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">`;
        if(adminAdvertisementDTO.advertisementRequestStatus === 'await'){
            text += `
                <a href="/admin/advertise/accept/${adminAdvertisementDTO.id}">
                        <button class="btn-apply btn">승인</button>
                </a>
                <a href="/admin/advertise/reject/${adminAdvertisementDTO.id}">
                    <button class="btn-reject btn">거절</button>
                </a>
            `;
        }
        text += `
                </div>
            </div>
        `;
        modalDialog.innerHTML = text;
    }

    return {showList: showList, showDetail: showDetail};
})();