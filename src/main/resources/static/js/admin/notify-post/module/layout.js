const layout = (() => {
    const showList = (adminReportCriteriaDTO) => {
        const noticeListContainer = document.querySelector(".table-notice tbody");
        let text = ``;

        if(!adminReportCriteriaDTO.adminReportDTO || adminReportCriteriaDTO.adminReportDTO.length === 0){
            text += `
            <tr class="no-data">
                <td colspan="3">
                    결과가 없습니다.
                </td>
            </tr>
            `;
        } else {
            adminReportCriteriaDTO.adminReportDTO.forEach((report) => {
                text += `
                    <tr class="post-detail" data-id="${report.id}">
                        <td class="td-date text-grey">${report.relativeDate}</td>
                        <td class="td-title">
                            <span class="notice-text">
                                ${report.postContent}
                            </span>
                        </td>
                        <td class="td-writer">${report.userEmail}</td>
                    </tr>
               `;
            });
        }
        noticeListContainer.innerHTML = text;

        const pagination = document.querySelector(".pagination.kok-pagination");
        let criteria = adminReportCriteriaDTO.criteria;
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
                    <a class="page-item-link page-item-num" data-page="${i}">${i}</a>
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

        const firstNumber = pagination.querySelector("li");
        if (firstNumber) {
            firstNumber.classList.add("active");
        }

    }

    const showDetail = (adminReportDTO) => {
        const reportModalContainer = document.querySelector(".post-modal-wrap4");
        let text = ``;

        console.log(adminReportDTO.id);

        text += `
            <div class="post-modal-top">
                <div>
                    <img alt="image" width="40" height="40" srcset="" src="/images/main-page/image.png" style="color: transparent; border-radius: 999px; cursor: pointer; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                </div>
                <div class="post-modal-top-section">
                    <div class="post-modal-top-name">
                        <div class="post-modal-name-wrap">
                            <div>
                                <p class="post-modal-name-text">${adminReportDTO.userName}</p>
                            </div>
                        </div>                                   
                    </div>
                    <div class="post-modal-info">
                        <p class="post-modal-job">${adminReportDTO.userEmail}</p> <!-- 작성자ID (이메일) -->
                        <p class="post-modal-center">·</p>
                        <p class="post-modal-time">${adminReportDTO.relativeDate}</p> <!-- 작성날짜 -->
                    </div>
                </div>
                <div>
                    <a href="/admin/notify/post/delete/${adminReportDTO.id}" class="post-modal-top-button">
                        <div class="post-modal-top-icon">
                            <p class="post-modal-delete">삭제하기</p>
                        </div>
                    </a>
                </div>
            </div>
            <div class="post-modal-middle">
                <div class="post-modal-body-wrap">
                    <div class="post-modal-body">
                        <div class="post-modal-text">
                            <span>${adminReportDTO.postContent}<br></span>
                        </div>
                    </div>
                    <div class="post-modal-file-wrap">`;
        if(adminReportDTO.postFiles && adminReportDTO.postFiles.length > 0) {
            adminReportDTO.postFiles.forEach((file) => {
                text += `
                    <div class="post-modal-file">
                        <img width="0" height="0" sizes="100vw" src="${file.postFilePath}">
                    </div>
                `;
            });
            text += `
                        </div>
                    </div>
                </div>
           `;
        }

        reportModalContainer.innerHTML = text;
    }

    return {showList: showList, showDetail: showDetail};
})();