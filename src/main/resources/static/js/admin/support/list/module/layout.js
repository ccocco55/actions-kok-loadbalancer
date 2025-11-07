const layout = (() => {
    const showList = (adminNoticeCriteriaDTO) => {
        const noticeListContainer = document.querySelector(".table-notice tbody");
        let text = ``;

        if(!adminNoticeCriteriaDTO.noticeList || adminNoticeCriteriaDTO.noticeList.length === 0) {
            text += `
            <tr class="no-data">
                <td colspan="3">
                    결과가 없습니다.
                </td>
            </tr>
            `;
        } else {
            adminNoticeCriteriaDTO.noticeList.forEach((notice) => {
                text += `
                    <tr class="support-notice-list" onclick="window.location.href='/admin/support/detail/${notice.id}';">
                        <td class="td-date text-grey">${notice.relativeDate}</td>
                        <td class="td-title">
                            <span class="notice-title">${notice.adminNoticeTitle}</span>
                        </td>
                        <td class="td-writer">주) 콕(kok)</td>
                    </tr>
               `;
            });
        }
        noticeListContainer.innerHTML = text;

        const pagination = document.querySelector(".pagination.kok-pagination");
        let criteria = adminNoticeCriteriaDTO.noticeCriteria;
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
                    <a class="page-item-num" href="/admin/support/${i}" data-page="${i}" class="page-item-num">${i}</a>
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

    return {showList: showList};
})();