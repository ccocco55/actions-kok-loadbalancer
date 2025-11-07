const postLayout = (() => {
    // 게시글 목록
    const showList = (postsCriteria) => {
        const postContainer = document.querySelector("#post-container");
        let text = ``;

        postsCriteria.posts.forEach((post) => {
            text += `
            <div class="post-8" data-post-id="${post.id}">
                <div class="post-9">
                    <div>
                        <img alt="image" width="40" height="40"
                             src="${post.memberProfileUrl}"
                             style="color: transparent; border-radius: 999px; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain; background-color: #ccc;">
                    </div>
                    <div class="post-10">
                        <div class="post-11">
                            <div class="post-12">
                                <div>
                                    <p class="post-13">${post.userName}</p>
                                </div>
                            </div>
                        </div>
                        <div class="post-14">
                            <p class="post-15">${post.jobName || '-'}</p>
                            <p class="post-15">${post.relativeDate}</p>
                        </div>
                    </div>
                    <div class="report">
                        <button class="post-28" style="width: 70px; color: #e0e0e0; font-size: 15px; font-weight: 500;">
                            <svg class="btn" color="foregrounds.neutral.tertiary" fill="currentColor" height="20" role="img" width="20" >
                                <path clip-rule="evenodd" d="M12 10.5c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5m0-4c.83 0 1.5-.67 1.5-1.5s-.67-1.5-1.5-1.5-1.5.67-1.5 1.5.67 1.5 1.5 1.5m0 11c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5" fill-rule="evenodd"></path>
                            </svg>
                            <div class="report-1">
                                <div class="report-2">
                                    <div class="report-3">
                                        <div class="report-4">
                                            <div class="report-5">`;
            if (post.owner) {
                text += `
                    <p class="delete-post-btn">게시글 삭제</p>
                    <br>
                    <p class="update-post-btn">게시글 수정</p>`;
            }
            else {
                text += `
                    <p class="report-6">게시글 신고</p>`;
                        }

                        text += `
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </button>
                    </div>
                </div>
                <div class="post-16 check-detail-post" style="cursor: pointer">
                    <div class="post-17">
                        <div class="post-18">
                            <div class="post-19">
                                <span>${post.postContent}</span>
                            </div>
                        </div>
                    </div>`;

            if (post.postFiles && post.postFiles.length > 0) {
                text += `
                    <div class="file">
                        <div class="file-1">
                            <div class="file-2">
                                <div class="file-9">`;
                post.postFiles.forEach((file) => {
                    text += `
                                    <div class="file-10">
                                        <img class="img" src="${file.postFilePath}" alt="첨부 이미지" style="color: transparent; height: 320px; object-fit: cover; width: auto;">
                                    </div>`;
                });
                text += `
                                </div>
                            </div>
                        </div>
                    </div>`;
            }

            text += `
                </div>
                <div class="post-20">
                    <div class="post-21">
                        <div class="post-22">
                            <button class="post-23 like-btn" data-post-id="${post.id}" data-liked="${post.liked}">
                                <div class="post-24">
                                    <svg class="heart" viewBox="0 0 24 24" style="fill: ${post.liked ? 'red' : 'white'}; stroke: red;" height="20" role="img" width="20">
                                        <path d="M17.668 16.4c2.028-1.76 4.328-3.755 4.328-6.902a6.07 6.07 0 0 0-5.831-6.274 5.25 5.25 0 0 0-2.296.447l-.267.128a5.3 5.3 0 0 0-1.606 1.274 5.26 5.26 0 0 0-4.161-1.85 6.07 6.07 0 0 0-4.211 1.934l-.2.225a6.07 6.07 0 0 0-1.42 4.116l.005.29c.132 2.968 2.278 4.833 4.353 6.637v.001l.742.65.893.797a39 39 0 0 0 3.057 2.623l.109.065c.22.12.462.193.712.21l.125.005c.293 0 .58-.075.837-.215l.108-.065a39 39 0 0 0 3.151-2.707l.802-.716z"></path>
                                    </svg>
                                </div>
                                <div style="margin-left: 5px;">
                                    <p class="post-25">${post.likesCount}</p>
                                </div>
                            </button>
                            <div class="post-23 replys">
                                <div class="post-24">
                                    <svg class="icons" fill="currentColor" height="20" role="img" width="20">
                                        <path clip-rule="evenodd" d="M2.2 6.8a3.6 3.6 0 0 1 3.6-3.6h12.4a3.6 3.6 0 0 1 3.6 3.6v8.4a3.6 3.6 0 0 1-3.6 3.6h-3.372a1.2 1.2 0 0 0-.848.352l-1.868 1.867c-1.075 1.075-2.912.314-2.912-1.206 0-.56-.454-1.013-1.013-1.013H5.8a3.6 3.6 0 0 1-3.6-3.6zm3.6-2a2 2 0 0 0-2 2v8.4a2 2 0 0 0 2 2h2.387a2.61 2.61 0 0 1 2.613 2.613c0 .035.008.05.015.06a.1.1 0 0 0 .05.038q.038.014.063.008a.1.1 0 0 0 .053-.031l1.867-1.868a2.8 2.8 0 0 1 1.98-.82H18.2a2 2 0 0 0 2-2V6.8a2 2 0 0 0-2-2z" fill-rule="evenodd"></path>
                                    </svg>
                                </div>
                                <div style="margin-left: 5px;">
                                    <p class="post-25">${post.commentsCount}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>`;
        });
        postContainer.innerHTML += text;
    }

    // 게시글 상세 모달
    const showDetail = (post) => {
        const detailModal = document.getElementById("post-detail-modal");
        const detailContainer = detailModal.querySelector(".reply-left");

        let detail = `
            <div class="post-9">
                <div>
                    <img alt="image" width="40" height="40" src="${post.memberProfileUrl || '/images/member/profile.png'}"
                    style="color: transparent; border-radius: 999px; cursor: pointer; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain; background-color: #ccc;">
                </div>
                <div class="post-10">
                    <div class="post-11">
                        <div class="post-12">
                            <div>
                                <p class="post-13">${post.userName}</p>
                            </div>
                        </div>
                    </div>
                    <div class="post-14">
                        <p class="post-15">${post.jobName || '-'}</p>
                        <p class="post-15">${post.relativeDate}</p>
                    </div>
                </div>
                <div class="report">
                    <button class="post-28" style="width: 70px; color: #e0e0e0; font-size: 15px; font-weight: 500;">
                        <svg class="btn" aria-label="icon" color="foregrounds.neutral.tertiary" fill="currentColor" height="20" role="img" width="20" >
                            <path clip-rule="evenodd" d="M12 10.5c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5m0-4c.83 0 1.5-.67 1.5-1.5s-.67-1.5-1.5-1.5-1.5.67-1.5 1.5.67 1.5 1.5 1.5m0 11c-.83 0-1.5.67-1.5 1.5s.67 1.5 1.5 1.5 1.5-.67 1.5-1.5-.67-1.5-1.5-1.5" fill-rule="evenodd"></path>
                        </svg>`;

                    if (post.owner) {
                        detail += `
                            <div class="report-1">
                                <div class="report-2">
                                    <div class="report-3">
                                        <div class="report-4">
                                            <div class="report-5">
                                                <p class="delete-post-btn">게시글 삭제</p>
                                                <br>
                                                <p class="update-post-btn">게시글 수정</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>`;
                    }

                    else {
                        detail += `
                            <div class="report-1">
                                <div class="report-2">
                                    <div class="report-3">
                                        <div class="report-4">
                                            <div class="report-5">
                                                <p class="report-6">게시글 신고</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>`;
                    }
        detail += `

                    </button>
                </div>
            </div>
            <div class="post-16">
                <div class="post-17">
                    <div class="post-18">
                        <div class="post-19">
                            <span>${post.postContent}</span>
                        </div>
                    </div>
                </div>`;

        if (post.postFiles && post.postFiles.length > 0) {
            detail += `
                <div class="file">
                    <div class="file-1">
                        <div class="file-2">
                            <div class="file-9">`;
            post.postFiles.forEach((file) => {
                detail += `
                                <div class="file-10">
                                    <img class="img" src="${file.postFilePath}" alt="첨부 이미지" sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw" style="color: transparent; height: 320px; object-fit: cover; width: auto;">
                                </div>`;
            });
            detail += `
                            </div>
                        </div>
                    </div>
                </div>`;
        }

        detail += `
            </div>
            <div class="post-20">
                <div class="post-21">
                    <div class="post-22">
                        <button class="post-23 like-btn" data-post-id="${post.id}" data-liked="${post.liked}">
                            <div class="post-24">
                                <svg class="heart" viewBox="0 0 24 24" style="fill:${post.liked ? 'red' : 'white'}; stroke: red;" height="20" role="img" width="20">
                                    <path d="M17.668 16.4c2.028-1.76 4.328-3.755 4.328-6.902a6.07 6.07 0 0 0-5.831-6.274 5.25 5.25 0 0 0-2.296.447l-.267.128a5.3 5.3 0 0 0-1.606 1.274 5.26 5.26 0 0 0-4.161-1.85 6.07 6.07 0 0 0-4.211 1.934l-.2.225a6.07 6.07 0 0 0-1.42 4.116l.005.29c.132 2.968 2.278 4.833 4.353 6.637v.001l.742.65.893.797a39 39 0 0 0 3.057 2.623l.109.065c.22.12.462.193.712.21l.125.005c.293 0 .58-.075.837-.215l.108-.065a39 39 0 0 0 3.151-2.707l.802-.716z"></path>
                                </svg>
                            </div>
                            <div style="margin-left: 5px;">
                                <p class="post-25">${post.likesCount}</p>
                            </div>
                        </button>
                    </div>
                </div>
            </div>`;

        detailContainer.innerHTML = detail;
        detailModal.style.display = "flex";
    };

    return { showList : showList, showDetail : showDetail };

})();

// 취업률 모달
const employmentLayout = (() => {
    const tbody = document.getElementById("employment-table-body");
    const pagination = document.createElement("div");
    pagination.classList.add("employment-pagination");

    let rows = [];
    let currentPage = 1;
    const rowCount = 10;

    const employDataTable = (page = 1) => {
        if (!tbody) return;
        tbody.innerHTML = "";
        currentPage = page;

        const start = (page - 1) * rowCount;
        const end = start + rowCount;
        const pageData = rows.slice(start, end);

        if (pageData.length === 0) {
            tbody.innerHTML = `<tr><td colspan="2">데이터가 없습니다.</td></tr>`;
            return;
        }

        pageData.forEach((row) => {
            const tr = document.createElement("tr");
            tr.innerHTML = `
                <td>${row.MJR_NM}</td>
                <td>${row.EMPLYMT_RT}</td>
            `;
            tbody.appendChild(tr);
        });

        renderPagination();
    };

    // 페이징 버튼
    const renderPagination = () => {
        pagination.innerHTML = "";
        const total = rows.length;
        const realEnd = Math.ceil(total / rowCount);
        const pageCount = 10;
        const endPage = Math.min(Math.ceil(currentPage / pageCount) * pageCount, realEnd);
        const startPage = endPage - pageCount + 1;
        const hasPrev = startPage > 1;
        const hasNext = endPage < realEnd;

        if (hasPrev) {
            const prev = document.createElement("button");
            prev.textContent = "<";
            prev.addEventListener("click", () => employDataTable(startPage - 1));
            pagination.appendChild(prev);
        }

        for (let i = startPage; i <= endPage; i++) {
            const btn = document.createElement("button");
            btn.textContent = i;
            btn.className = i === currentPage ? "active" : "";
            btn.addEventListener("click", () => employDataTable(i));
            pagination.appendChild(btn);
        }

        if (hasNext) {
            const next = document.createElement("button");
            next.textContent = ">";
            next.addEventListener("click", () => employDataTable(endPage + 1));
            pagination.appendChild(next);
        }

        const modalContent = document.querySelector(".employment-modal-content");
        if (modalContent && !modalContent.querySelector(".employment-pagination")) {
            modalContent.appendChild(pagination);
        }
    };

    const setEmployData = (data) => {
        rows = data;
        employDataTable(1);
    };

    return { setEmployData : setEmployData, employDataTable : employDataTable };
})();
