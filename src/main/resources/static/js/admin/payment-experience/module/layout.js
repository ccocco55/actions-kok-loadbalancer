const layout = (() => {
    const showList = (adminPaymentExperienceCriteriaDTO) => {
        const acceptTotal = document.getElementById("acceptTotal");
        const acceptCount = document.getElementById("rejectTotal");
        let acceptText = ``;
        let rejectText = ``;

        acceptText += `
            <span class="span-amount">${adminPaymentExperienceCriteriaDTO.paymentExperienceCount.acceptTotalText}</span>
            <span class="amount-unit">원</span>
        `;
        acceptTotal.innerHTML = acceptText;

        rejectText += `
            <span class="span-amount">${adminPaymentExperienceCriteriaDTO.paymentExperienceCount.rejectTotalText}</span>
            <span class="amount-unit">원</span>
        `;
        acceptCount.innerHTML = rejectText;

        const paymentListContainer = document.querySelector(".member-table tbody");
        let text = ``;

        if(!adminPaymentExperienceCriteriaDTO.paymentExperienceList || adminPaymentExperienceCriteriaDTO.paymentExperienceList.length === 0){
            text += `
            <tr class="no-data">
                <td colspan="7">
                    결제 내역이 없습니다.
                </td>
            </tr>
            `;
        } else {
            adminPaymentExperienceCriteriaDTO.paymentExperienceList.forEach((payment) => {
                text += `
                <tr>
                    <td class="td-name">
                        <p>${payment.userName}</p>
                    </td>
                    <td class="td-id">
                        <p>${payment.userEmail}</p>
                    </td>
                    <td class="td-start">
                        <p>${payment.companyName}</p>
                    </td>
                    <td class="td-end">
                        <p>${payment.experienceNoticeTitle}</p>
                    </td>  
                    <td class="td-status">
                        <p>`;

                if (payment.requestExperienceStatus === 'await') {
                    text += `서류 접수`;
                } else if (payment.requestExperienceStatus === 'accept') {
                    text += `합격`;
                } else {
                    text += `불합격`;
                }

                text += `</p>
                    </td>  
                    <td class="td-pay">
                        <p>${payment.paymentPriceText}</p>
                    </td>
                    <td class="td-date">
                        <p>${payment.relativeDate}</p>
                    </td>
                </tr>
            `;
            });
        }
        paymentListContainer.innerHTML = text;

        // 페이징 숫자
        const pagination = document.querySelector(".pagination.kok-pagination");
        let criteria = adminPaymentExperienceCriteriaDTO.criteria;
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

        // 페이징 숫자 색 변화 이벤트
        const firstNumber = pagination.querySelector("li");
        if (firstNumber) {
            firstNumber.classList.add("active");
        }
    }

    return {showList: showList};
})();