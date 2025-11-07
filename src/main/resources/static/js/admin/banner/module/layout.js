const layout = (()=> {
    const showList = (bannerFileCriteriaDTO) => {
        const bannerContainer = document.querySelector("#banner-table tbody");
        let text = ``;

        console.log(bannerFileCriteriaDTO.bannerFileList);

        if(!bannerFileCriteriaDTO.bannerFileList || bannerFileCriteriaDTO.bannerFileList.length === 0){
            text += `
            <tr class="no-data">
                <td colspan="5">
                    등록된 배너가 없습니다.
                </td>
            </tr>
            `;
        } else {
            bannerFileCriteriaDTO.bannerFileList.forEach((file) => {
                text += `
                    <tr class="banner-row">
                        <td>${file.bannerFileOriginName}</td>
                        <td>
                            <img src="${file.bannerFilePath}" alt="현수막 이미지" style="width:120px; height:60px; object-fit:cover; border-radius:6px;">
                        </td>
                        <td>${file.bannerFileContentType}</td>
                        <td>${file.relativeDate}</td>
                        <td>
                             <form action="/admin/banner/delete/${file.id}" method="post">
                                <button type="submit" class="banner-delete-btn">삭제</button>
                             </form>
                        </td>
                    </tr>
                `;
            });
        }
        bannerContainer.innerHTML = text;

        // 페이지 번호
        const pagination = document.querySelector(".pagination.kok-pagination");
        let criteria = bannerFileCriteriaDTO.criteria;
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

    return {showList: showList}
})();