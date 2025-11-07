const layout = (() => {
    const showList = (adminPaymentAdvertiseCriteriaDTO) => {
        const acceptTotal = document.getElementById("acceptTotal");
        const acceptCount = document.getElementById("acceptCount");
        let totalText = ``;
        let countText = ``;

        totalText += `
            <span class="span-amount">${adminPaymentAdvertiseCriteriaDTO.acceptCountTotal.acceptTotalText}</span>
            <span class="amount-unit">원</span>
        `;
        acceptTotal.innerHTML = totalText;

        countText += `
            <span class="span-amount">${adminPaymentAdvertiseCriteriaDTO.acceptCountTotal.acceptCount}</span>
            <span class="amount-unit">건</span>
        `;
        acceptCount.innerHTML = countText;

        const paymentListContainer = document.querySelector(".member-table tbody");
        let text = ``;

        if(!adminPaymentAdvertiseCriteriaDTO.paymentAdvertiseList || adminPaymentAdvertiseCriteriaDTO.paymentAdvertiseList.length === 0){
            text += `
            <tr class="no-data">
                <td colspan="7">
                    결제 내역이 없습니다.
                </td>
            </tr>
            `;
        } else {
            adminPaymentAdvertiseCriteriaDTO.paymentAdvertiseList.forEach((payment) => {
                text += `
                    <tr>
                        <td class="td-name">
                            <p>${payment.companyName}</p>
                        </td>
                        <td class="td-id">
                            <p>${payment.userEmail}</p>
                        </td>
                        <td class="td-start">
                            <p>${payment.advertiseStartDatetime}</p>
                        </td>
                        <td class="td-end">
                            <p>${payment.advertiseEndDatetime}</p>
                        </td>  
                        <td class="td-status">
                            <p>`;
                    if (payment.advertisementStatus === 'await') {
                        text += `대기중`;
                    } else if (payment.advertisementStatus === 'accept') {
                        text += `진행중`;
                    } else {
                        text += `완료`;
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
        let criteria = adminPaymentAdvertiseCriteriaDTO.criteria;
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