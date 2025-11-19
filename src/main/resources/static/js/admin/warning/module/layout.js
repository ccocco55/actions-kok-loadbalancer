const layout = (() => {
    const showList = (result) => {
        const noticeListContainer = document.querySelector("tbody.table-notice");
        let text = ``;

        if (result.postWarningDTOList !== null && result.postWarningDTOList.length > 0) {

            result.postWarningDTOList.forEach((postWarningDTO) => {
                let status = null;

                if (postWarningDTO.postWarningStatus === "wait") {
                    status = "대기"
                } else if (postWarningDTO.postWarningStatus === "delete") {
                    status = "삭제 처리"
                } else {
                    status = "보류"
                }


                text += `

                    <tr>
                        <td class="td-name">
                            <div class="member-name">${postWarningDTO.userName}
                                <span class="badge-label badge text-danger ml-2">일반회원</span>
                            </div>
                            <div class="member-id">${postWarningDTO.userEmail ?? '-'}</div>
                        </td>
                        <td class="td-amount pr-4 font-weight-bold">${postWarningDTO.userName}
                            <span class="amount-unit"> 님</span>
                        </td>
                        <td class="td-phone">
                            <p>${postWarningDTO.postContent}</p>
                        </td>
                        <td class="td-profile">
                            <p>${status}</p>
                        </td>
                        <td class="td-job">
                            <p>${postWarningDTO.wraningCount}</p>
                        </td>
                        <td class="td-action text-center">
                            <div class="action-btn">
                                <i class="mdi mdi-chevron-right" data-id="${postWarningDTO.id}"></i>
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

        let text = ``;



        if (result !== null) {

            let status = null;
            let modalButtonText = ``

            if (result.postWarningStatus === "wait") {
                status = "대기"
                modalButtonText = `
                    <button type="button" class="btn-modal" id="btn-warning">보류</button>
                    <button type="button" class="btn-modal" id="btn-warning-remove">삭제</button>
                `
            } else if (result.postWarningStatus === "delete") {
                status = "삭제 처리"
                modalButtonText = `
                    <button type="button" class="btn-modal" id="btn-warning">보류</button>
                `
            } else {
                status = "보류"
                modalButtonText = `
                    <button type="button" class="btn-modal" id="btn-warning-remove">삭제</button>
                `
            }



            const createdDate = new Date(result.createdDateTime);
            const createdFormatted = createdDate.toISOString().slice(0, 19).replace("T", " ");

            const updatedDate = new Date(result.updatedDateTime);
            const updatedFormatted = updatedDate.toISOString().slice(0, 19).replace("T", " ");

            text = `
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <div class="modal-title">
                                ${result.userName}
                                <span class="badge-label text-danger font-weight-bold ml-2">일반회원</span>
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
                                                        <!-- &lt;!&ndash; 테이블 왼쪽 &ndash;&gt; -->
                                                        <div class="d-table-cell">
                                                            <table class="info-table">
                                                                <tbody>
                                                                <tr>
                                                                    <th>회원ID (이메일)</th>
                                                                    <td>${result.userEmail}</td>
                                                                </tr>
                                                                <tr>
                                                                    <th>주의 게시물 수</th>
                                                                    <td>${result.wraningCount}</td>
                                                                </tr>
                                                                <tr>
                                                                    <th>게시일</th>
                                                                    <td>${createdFormatted}</td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <!-- &lt;!&ndash; 테이블 오른쪽 &ndash;&gt;                                                        -->
                                                        <div class="d-table-cell">
                                                            <table class="info-table">
                                                                <tbody>
                                                                <tr>
                                                                    <th>이름</th>
                                                                    <td>${result.userName}</td>
                                                                </tr>
                                                                <tr>
                                                                    <th>상태</th>
                                                                    <td>${status}</td>
                                                                </tr>
                                                                <tr>
                                                                    <th>수정일</th>
                                                                    <td>${updatedFormatted}</td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- &lt;!&ndash; 회원 작성 게시글 &ndash;&gt; -->
                                                <div class="info-layout detail-info">
                                                    <div class="info-title justify-content-between">
                                                        <div class="flex-left d-flex">
                                                            <div class="title">주의 게시글</div>
                                                        </div>
                                                        <div class="flex-right"></div>
                                                    </div>
                                                    <div class="d-table w-100">
                                                        <div class="text-div">
                                                            <textarea id="content-post" readonly>${result.postContent}</textarea>
                                                        </div>
                                                    </div>
                                                    <div id="modal-button"></div>
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
            document.querySelector("#modal-button").innerHTML = modalButtonText;
        }


    }

    return {showList: showList, showDetail:showDetail};
})();