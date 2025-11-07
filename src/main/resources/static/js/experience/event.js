

const noData=document.querySelector(".no-data");
const contentMainList=document.querySelector(".content-main");



// function categoryTagFn() {
//     const box = document.querySelector(".categoty-tag-box");
//     if (!box) return;

//     const previewInner = box.querySelector(".popup-category-preview-inner");
//     const dropdownBtns = box.querySelectorAll(".dropdown-btn");

//     dropdownBtns.forEach((btn) => {
//         btn.addEventListener("click", () => {
//             const text = btn.innerText.trim();

//             // 이미 같은 태그가 있으면 추가 안 함
//             const exists = [
//                 ...previewInner.querySelectorAll(".preview-category-item p"),
//             ].some((p) => p.innerText === text);
//             if (exists) return;

//             // 새 태그 요소 만들기
//             const item = document.createElement("div");
//             item.className = "preview-category-item";
//             item.innerHTML = `
//                 <p>${text}</p>
//                 <button class="preview-remove-btn">
//                     <svg viewBox="0 0 24 24" aria-label="icon" fill="currentColor" height="16" role="img" width="16">
//                         <path clip-rule="evenodd" fill-rule="evenodd"
//                               d="M6.434 6.435a.8.8 0 0 1 1.132 0L12 10.869l4.434-4.434a.8.8 0 1 1 1.132 1.13L13.13 12l4.435 4.435a.8.8 0 1 1-1.132 1.13L12 13.133l-4.434 4.434a.8.8 0 0 1-1.132-1.131L10.87 12 6.434 7.566a.8.8 0 0 1 0-1.131"/>
//                     </svg>
//                 </button>
//             `;

//             previewInner.appendChild(item);

//             // 삭제 버튼 이벤트
//             const removeBtn = item.querySelector(".preview-remove-btn");
//             removeBtn.addEventListener("click", () => {
//                 item.remove();
//             });
//         });
//     });
// }
// categoryTagFn();





document.addEventListener("DOMContentLoaded", () => {

    // 서치 드롭다운 옵션 버튼 클릭 시 active 클래스 토글 및 전체 선택 상태 업데이트
    function searchDropdownFn() {
        let isAllSelected = false;
        const dropdown = document.querySelectorAll(".dropdown-container");
        const dropdownBtns = document.querySelectorAll(".dropdown-btn");

        if (!dropdown) return;

        dropdownBtns.forEach((btn) => {
            btn.addEventListener("click", () => {
                btn.classList.toggle("active");

                let allActive = true;
                dropdownBtns.forEach((btn) => {
                    if (!btn.classList.contains("active")) {
                        allActive = false;
                    }
                });

                isAllSelected = allActive;
            });
        });

        // 드롭다운 열기/닫기, 전체 선택, 적용 버튼 이벤트
        dropdown.forEach((box) => {
            const dropdownOpenBtns = box.querySelectorAll(".dropdown-open-btn");
            const dropdownOpenTextBtns = box.querySelectorAll(
                ".dropdown-open-btn:not(.circle)"
            );
            const dropdownBtns = box.querySelectorAll(".dropdown-btn");
            const selectAllBtn = box.querySelector(".select-all-btn");
            const applyBtn = box.querySelector(".apply-btn");

            // 드롭다운 열기/닫기 버튼 클릭 시 active 클래스 토글
            dropdownOpenBtns.forEach((btn) => {
                btn.addEventListener("click", () => {
                    const isActive = btn.classList.contains("active");

                    dropdown.forEach((otherBox) => {
                        const otherBtns =
                            otherBox.querySelectorAll(".dropdown-open-btn");
                        otherBtns.forEach((b) => b.classList.remove("active"));
                    });

                    if (!isActive) {
                        btn.classList.add("active");
                    }
                });
            });

            // 전체 선택 버튼 클릭 시 모든 옵션 선택 또는 선택 해제
            selectAllBtn.addEventListener("click", () => {
                if (!isAllSelected) {
                    dropdownBtns.forEach((btn) => btn.classList.add("active"));
                    isAllSelected = true;
                } else {
                    dropdownBtns.forEach((btn) => btn.classList.remove("active"));
                    isAllSelected = false;
                }
            });

            // 적용 버튼 클릭 시 드롭다운 닫기 및 선택된 옵션에 따라 드롭다운 열기 버튼 텍스트 변경
            applyBtn.addEventListener("click", () => {
                let firstText = null;
                let count = 0;

                dropdownBtns.forEach((btn) => {
                    if (btn.classList.contains("active")) {
                        count++;
                        if (firstText === null) {
                            firstText = btn.textContent.trim();
                        }
                    }
                });

                // 선택된 옵션에 따라 드롭다운 열기 버튼 텍스트 변경
                if (count === 0) {
                    dropdownOpenTextBtns.forEach(
                        (btn) => (btn.textContent = "전체")
                    );
                } else if (count === 1) {
                    dropdownOpenTextBtns.forEach(
                        (btn) => (btn.textContent = firstText)
                    );
                } else {
                    dropdownOpenTextBtns.forEach(
                        (btn) =>
                            (btn.textContent = `${firstText} 외 ${count - 1} 개`)
                    );
                }

                // 태그박스가 있을때
                const previewBox = document.querySelector(
                    ".popup-category-preview-inner"
                );
                if (previewBox) {
                    dropdownBtns.forEach((btn) => {
                        var text = btn.textContent.trim();
                        var existsItem = null;

                        // 이미 있는 태그 찾기
                        var items = previewBox.querySelectorAll(
                            ".preview-category-item"
                        );
                        items.forEach((item) => {
                            var p = item.querySelector("p");
                            if (p && p.innerText === text) {
                                existsItem = item;
                            }
                        });

                        if (btn.classList.contains("active")) {
                            // active인데 태그 없으면 추가
                            if (!existsItem) {
                                var item = document.createElement("div");
                                item.className = "preview-category-item";
                                item.innerHTML = `
                            <p>${text}</p>
                            <button class="preview-remove-btn">✕</button>
                        `;
                                previewBox.appendChild(item);

                                // 삭제 버튼 이벤트
                                var removeBtn = item.querySelector(
                                    ".preview-remove-btn"
                                );
                                removeBtn.addEventListener("click", function () {
                                    item.remove();
                                    btn.classList.remove("active"); // 동기화
                                });
                            }
                        } else {
                            // active 해제된 경우 → 태그 있으면 삭제
                            if (existsItem) {
                                existsItem.remove();
                            }
                        }
                    });
                }

                // 드롭다운 닫기
                dropdownOpenBtns.forEach((btn) => btn.classList.remove("active"));
            });
        });

        // 드롭다운 외부 클릭 시 드롭다운 닫기
        document.addEventListener("click", (e) => {
            dropdown.forEach((box) => {
                if (!box.contains(e.target)) {
                    const openBtns = box.querySelectorAll(".dropdown-open-btn");
                    openBtns.forEach((btn) => btn.classList.remove("active"));
                }
            });
        });
    }
    searchDropdownFn();

    // 키워드 입력 시 50자 초과하면 토스트 메시지 노출
    function keywordInputValidate() {
        const keywordInput = document.querySelector("#keyword-input");
        const toast = document.querySelector("#toast-red");
        const deleteBtn = document.querySelector(".keyword-delete");
        const resetBtns = document.querySelectorAll(".btn-reset");

        if (!keywordInput) return;

        function checkKeywordLength(e) {
            if (keywordInput && toast) {
                if (keywordInput.value.length > 50) {
                    toast.classList.add("show");

                    setTimeout(() => {
                        toast.classList.remove("show");
                    }, 2000);

                    if (e) e.preventDefault();
                    return false;
                } else {
                    toast.classList.remove("show");
                    return true;
                }
            }
            return false;
        }

        // 검색 버튼 클릭 시
        const searchButton = document.querySelector(".search-button");
        if (searchButton) {
            searchButton.addEventListener("click", (e) => {
                if (checkKeywordLength(e)) {
                    fetchExperiences();
                }
            });
        }

        // 엔터 입력 시
        keywordInput.addEventListener("keydown", (e) => {
            if (e.key === "Enter") {
                e.preventDefault();
                if (checkKeywordLength(e)) {
                    fetchExperiences();
                }
            }
        });

        // 모바일 인풋 삭제
        deleteBtn.style.display = "none";

        keywordInput.addEventListener("input", () => {
            if (keywordInput.value.trim() !== "") {
                deleteBtn.style.display = "inline-block";
            } else {
                deleteBtn.style.display = "none";
            }
        });

        resetBtns.forEach((btn) => {
            btn.addEventListener("click", () => {
                keywordInput.value = "";
                deleteBtn.style.display = "none";
                keywordInput.focus();
            });
        });
    }

    keywordInputValidate();

    const container = document.querySelector(".list-container");
    const contentDetail = document.querySelector(".content-detail");
    const contentSide = document.querySelector(".content-side");
    const triggers = document.querySelectorAll(".popup-trigger");
    const popups = document.querySelectorAll(".popup-container");
    const dropdowns = document.querySelectorAll(".option-menu");
    const saveStorageFilePop=document.getElementById("resume-upload-popup");

    // 채용상세
    const showDetailByShare = async (e) => {
        let companyId = null;
        let experienceId = null;
        contentDetail.classList.remove('active');
        if(sharedCompanyId && sharedExperienceId) {
            companyId = sharedCompanyId;
            experienceId = sharedExperienceId;
        }else {
            const btn = e.target.closest(".list-item-btn");
            if (!btn) return;

            contentDetail.classList.remove('active');

            const companyClass = Array.from(btn.classList)
                .find(c => c.startsWith("companyId-"));
            if (!companyClass) return;

            companyId = companyClass.split("-")[1];

            const experienceClass = Array.from(btn.classList)
                .find(c => c.startsWith("experienceId-"));
            if (!experienceClass) return;

            experienceId = experienceClass.split("-")[1];
        }



        // fetch로 상세 데이터 가져오기
        const response = await fetch(`/api/experiences/detail?companyId=${companyId}&experienceId=${experienceId}`);
        const data = await response.json();
        const detailData = data;
        // console.log(detailData);

        const fileUrlPre = await fetch(`/api/experiences/profile?companyId=${companyId}`);
        const fileUrl = await fileUrlPre.text();

        const startDate=new Date(detailData.notice.experienceStartDate);
        const endDate = new Date(detailData.notice.experienceEndDate);
        const formattedStart = `${startDate.getFullYear()}년 ${startDate.getMonth() + 1}월 ${startDate.getDate()}일`;
        const formattedEnd = `${endDate.getFullYear()}년 ${endDate.getMonth() + 1}월 ${endDate.getDate()}일`;

        // const expNoticeStartDate=new Date(detailData.notice.experienceNoticeStartDate);
        const expNoticeEndDate=new Date(detailData.notice.experienceNoticeEndDate);
        // const expNoticeStartFormatted=`${expNoticeStartDate.getFullYear()}년 ${expNoticeStartDate.getMonth() + 1}월 ${expNoticeStartDate.getDate()}일`;
        const expNoticeEndFormatted=`${expNoticeEndDate.getFullYear()}년 ${expNoticeEndDate.getMonth() + 1}월 ${expNoticeEndDate.getDate()}일`;

        const isSavedPre= await fetch(`/api/experiences/is-saved?experienceId=${experienceId}`);
        console.log(isSavedPre);
        const isSaved=await isSavedPre.json();
        const isSavedDetail=isSaved;
        // console.log(isSavedDetail);

        let saveBtnText='';
        if(isSavedDetail){
            saveBtnText="저장취소";
        } else{
            saveBtnText="저장하기"
        }
        contentDetail.innerHTML = `<div class="content-detail-inner active" id="experienceDetail-${experienceId}">
                            <div class="content-detail-header">
                                <button class="detail-arrow-btn">
                                    <svg fill="currentColor" height="20" role="img" width="20">
                                        <path clip-rule="evenodd" d="M11.566 5.435a.8.8 0 0 0-1.132 0l-6 6a.8.8 0 0 0 0 1.13l6 6a.8.8 0 1 0 1.132-1.13L6.93 12.8H19a.8.8 0 1 0 0-1.6H6.931l4.635-4.634a.8.8 0 0 0 0-1.131" fill-rule="evenodd"></path>
                                    </svg>
                                    <p>목록</p>
                                </button>
                            </div>
                            <div class="content-detail-body">
                                <button class="list-item-header">
                                    <div class="list-item-thumb">
                                        <img src="${fileUrl}" alt="">
                                    </div>
                                    <div class="list-item-content">
                                        <p class="list-item-title">${detailData.company.companyName}</p>
                                        <ul class="profile-stats">
                                            <li class="profile-stat-item">팔로워 <i class="num">${detailData.company.followerCount}</i></li>
                                            <li class="profile-stat-item">체험공고 <i class="num">${detailData.company.experienceCount}</i></li>
                                            <li class="profile-stat-item">인턴공고 <i class="num">${detailData.company.internCount}</i></li>
                                        </ul>
                                    </div>
                                </button>
                                
                                <div class="detail-content">
                                    <div class="detail-header">
                                        <strong class="detail-title">${detailData.notice.experienceNoticeTitle}</strong>
                                        <p class="detail-subtitle">${detailData.notice.experienceNoticeSubtitle}</p>
                                    </div>

                                    <div class="detail-actions">
                                        <!-- popup-trigger 클래스가 있으면 열림 -->
                                        <button class="detail-action-btn detail-apply-btn popup-trigger" data-experienceid=${experienceId} data-target="#quick-apply-popup">간편 지원하기</button>
                                        <button class="detail-action-btn detail-save-btn" data-experienceid=${experienceId}>${saveBtnText}</button>
                                        <button class="detail-action-btn detail-share-btn" data-companyid=${companyId} data-experienceid=${experienceId}>공유하기</button>
                                    </div>

                                    <ul class="detail-meta">
                                        <li class="detail-meta-item">
                                            <p class="meta-label">직군</p>
                                            <p class="meta-value">${detailData.notice.jobName}</p>
                                        </li>
                                        <li class="detail-meta-item">
                                            <p class="meta-label">회사 규모</p>
                                            <p class="meta-value">${detailData.company.scaleName||'-'}</p>
                                        </li>
                                        <li class="detail-meta-item detail-meta-item-import meta-item-full">
                                            <p class="meta-label">체험일</p>
                                            <p class="meta-value">${formattedStart}~${formattedEnd}</p>
                                        </li>
                                    </ul>

                                    <div class="deadline-info">
                                        <p class="deadline-remain">지원 마감까지 ${detailData.notice.remainingDays}일 남음</p>
                                        <p class="deadline-description">${expNoticeEndFormatted}까지 지원할 수 있습니다.</p>
                                    </div>

                                    <div class="detail-description">
                                        <div class="detail-item">
                                            <p class="detail-item-title">직무소개</p>
                                            <p class="detail-item-content">${detailData.notice.experienceNoticeIntroduceJob}</p>
                                        </div>
                                        <div class="detail-item">
                                            <p class="detail-item-title">참고사항</p>
                                            <p class="detail-item-content">${detailData.notice.experienceNoticeEtc}</p>
                                        </div>
                                        <div class="detail-item">
                                            <p class="detail-item-title">주요 업무</p>
                                            <p class="detail-item-content">${detailData.notice.experienceMainTasks}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>`;
        contentDetail.classList.add("active");
        contentMainList.classList.add("display-none-list");
        if (contentSide) contentSide.style.display = "none";
    }

    if(sharedCompanyId && sharedExperienceId) {
        showDetailByShare();
    }

    if (!container || !contentDetail) return;

    container.addEventListener("click", showDetailByShare);

    // transition 끝나면 inner 활성화
    const inner = contentDetail.querySelector(".content-detail-inner");
    if (inner) {
        contentDetail.addEventListener(
            "transitionend",
            () => {
                if (contentDetail.classList.contains("active")) {
                    inner.classList.add("active");
                }
            },
            {once: true}
        );
    }

    document.addEventListener("click", (e) => {
        const btn = e.target.closest(".detail-arrow-btn");
        if (!btn) return;

        const contentDetail = document.querySelector(".content-detail");
        const contentSide = document.querySelector(".content-side");

        const inner = contentDetail.querySelector(".content-detail-inner");
        if (inner) inner.classList.remove("active");
        contentDetail.classList.remove("active");
        contentMainList.classList.remove("display-none-list");

        if (contentSide) contentSide.style.display = "flex";
    });

    keywordInputValidate();

    // 채용 - 공고 저장/저장 취소, 공유하기  버튼 클릭 시 토스트 메시지 노출
    // function toastPopupFn() {
    //     const saveToast = document.querySelector("#toast-white");
    //     const saveBtn = document.querySelector(".detail-save-btn");
    //
    //     let saved = false; // 저장 상태
    //     let showingToast = false; // 연타방지
    //
    //     // 공고 저장/저장 취소
    //     if (saveBtn) {
    //         saveBtn.addEventListener("click", async () => {
    //             if (showingToast) return; // 토스트 떠 있으면 무시
    //
    //             const textBox = saveToast.querySelector("p");
    //             const idWrapDiv = document.querySelector(".content-detail-inner");
    //             const expId = idWrapDiv.id.split("-")[1];
    //
    //             // console.log(expId);
    //
    //             if (!saved) {
    //                 saved = true;
    //                 if (textBox) textBox.textContent = "공고를 저장했어요.";
    //                 await fetch(`/api/experiences/save?experienceId=${expId}`);
    //                 saveBtn.textContent = "저장취소";
    //             } else {
    //                 saved = false;
    //                 if (textBox) textBox.textContent = "공고 저장을 취소했어요.";
    //                 await fetch(`/api/experiences/unsave?experienceId=${expId}`);
    //                 saveBtn.textContent = "저장함";
    //             }
    //
    //             console.log("토스트 띄우기");
    //             // 토스트 띄우기
    //             saveToast.classList.add("show");
    //             showingToast = true;
    //
    //             setTimeout(() => {
    //                 saveToast.classList.remove("show");
    //                 showingToast = false;
    //             }, 2000);
    //         });
    //     }
    // }

    let nowExperienceId=``;
    // 간편지원, 저장, 공유 클릭될 때
    contentDetail.addEventListener("click", async (e) => {
        if (e.target.classList.contains("detail-share-btn")) {
            const saveToast = document.querySelector("#toast-white");
            const shareBtn = e.target;
            const textBox = saveToast.querySelector("p");
            // 공유하기
            let url = `http://13.209.155.6/experience/list?sharedCompanyId=${shareBtn.dataset.companyid}&sharedExperienceId=${shareBtn.dataset.experienceid}`;
            const textarea = document.createElement("textarea");
            document.body.appendChild(textarea);
            textarea.value = url;
            textarea.select();
            document.execCommand("copy");
            document.body.removeChild(textarea);
            if (textBox) textBox.textContent = "URL이 복사되었습니다.";
            saveToast.classList.add("show");
            showingToast = true;

            setTimeout(() => {
                saveToast.classList.remove("show");
                showingToast = false;
            }, 2000);
            ;
        }

        if (e.target.classList.contains("detail-save-btn")) {
            const saveToast = document.querySelector("#toast-white");
            const saveBtn = e.target;
            const textBox = saveToast.querySelector("p");
            // console.log(saveBtn.dataset.experienceid);

            const expId = Number(saveBtn.dataset.experienceid);

            // console.log(expId);

            let showingToast = false; // 연타방지

            if (saveBtn.textContent==="저장하기") {
                if (textBox) textBox.textContent = "공고를 저장했어요.";
                const response = await fetch(`/api/experiences/save?experienceId=${expId}`, {
                    method: "POST"
                });
                if(!response.ok){
                    window.location.href = "/member/login";
                }
                saveBtn.textContent = "저장취소";
            } else {
                if (textBox) textBox.textContent = "공고 저장을 취소했어요.";
                await fetch(`/api/experiences/unsave?experienceId=${expId}`, {
                    method: "POST"
                });
                saveBtn.textContent = "저장하기";
            }

            // console.log("토스트 띄우기");
            // 토스트 띄우기
            saveToast.classList.add("show");
            showingToast = true;

            setTimeout(() => {
                saveToast.classList.remove("show");
                showingToast = false;
            }, 2000);
        }

        if(e.target.classList.contains("popup-trigger")){
            await quickApplyPopupFn();
            const trigger=e.target;
            const requestToast = document.querySelector("#toast-white");
            const textBox = requestToast.querySelector("p");
            const dropdowns = document.querySelectorAll(".option-menu");

            // console.log("간편지원하기 클릭됨");
            const target = trigger.getAttribute("data-target");
            const popup = document.querySelector(target);
            const applyBtn=e.target;
            const expId = Number(applyBtn.dataset.experienceid);

            const isRequestedPre=await fetch(`/api/experiences/is-requested?experienceId=${expId}`);
            const isRequested=await isRequestedPre.json();
            const isRequestedDetail=isRequested;

            // console.log(isRequestedDetail);

            if(isRequestedDetail){
                textBox.textContent="이미 지원한 공고입니다."
                requestToast.classList.add("show");
                showingToast=true;
                setTimeout(() => {
                    requestToast.classList.remove("show");
                    showingToast = false;
                }, 2000);
                // showingToast=true;
                return;
            }

            nowExperienceId=expId;

            // console.log(nowExperienceId);

            // 드롭다운 전부 닫기
            dropdowns.forEach((menu) => menu.classList.remove("active"));

            // 다른 팝업 닫기 (data-sticky가 붙어있는 팝업 제외)
            popups.forEach((pop) => {
                if (!pop.hasAttribute("data-sticky")) {
                    pop.classList.remove("active");
                }
            });

            // 현재(부모) 팝업 닫기 - 스티키면 유지
            const parentPopup = trigger.closest(".popup-container");
            if (parentPopup && !parentPopup.hasAttribute("data-sticky")) {
                parentPopup.classList.remove("active");
            }

            // 해당 팝업 열기
            if (popup) {
                popup.classList.add("active");
            }
        }
    });

    async function selectFilePop() {
        const popup = document.getElementById("resume-check-popup");

        // 드롭다운도 전부 닫기
        dropdowns.forEach((menu) => menu.classList.remove("active"));

        // 다른 팝업 닫기 (data-sticky가 붙어있는 팝업 제외)
        popups.forEach((pop) => {
            if (!pop.hasAttribute("data-sticky")) {
                pop.classList.remove("active");
            }
        });

        popup.innerHTML = `<div class="popup-inner">
            <div class="popup-header">
                <button class="popup-prev">
                    <svg fill="currentColor" height="20" role="img" width="20">
                        <path clip-rule="evenodd" d="M11.566 5.435a.8.8 0 0 0-1.132 0l-6 6a.8.8 0 0 0 0 1.13l6 6a.8.8 0 1 0 1.132-1.13L6.93 12.8H19a.8.8 0 1 0 0-1.6H6.931l4.635-4.634a.8.8 0 0 0 0-1.131" fill-rule="evenodd"></path>
                    </svg>
                </button>
                <div class="popup-title-container">
                    <strong class="popup-title">이력서</strong>
                    <span class="popup-description">파일 또는 URL 선택</span>
                </div>
            </div>

            <div class="popup-body scroll">
            </div>

            <div class="popup-action line">
                <button id="file-add-btn" class="pop-btn btn-default popup-trigger" data-target="#resume-upload-popup">추가 등록</button>
                <button id="file-select-btn" class="pop-btn btn-primary" disabled>선택 완료</button>
            </div>
        </div>`;
        const bodyHtml = document.querySelector(".popup-body.scroll");
        // 이력서 있는지 여부 판별
        const storageFilesPre = await fetch(`/api/member/storage/load`);
        const storageFilesJson = await storageFilesPre.json();
        const storageFiles = storageFilesJson;
        let checkedFileId = ``;

        if (storageFiles.length > 0) {
            bodyHtml.innerHTML = `<ul class="form-list file-list form-row" id="storage-files-ul">
                </ul>`;
            const storageUl = document.getElementById("storage-files-ul");
            let liText = ``;
            storageFiles.forEach((file, i) => {
                const originName = file.fileOriginName;
                const extensionName = originName.split(".").pop();
                const number = i + 1;
                const fileId = file.id;
                // console.log(fileId);
                let extension = "";
                if (extensionName === "xlsx" || extensionName === "xlsm" || extensionName === "xls" || extensionName === "xlsb" || extensionName === "xltx" || extensionName === "xltm") {
                    extension = "excel";
                } else if (extensionName === "pdf") {
                    extension = "pdf";
                } else if (extensionName === "ppt" || extensionName === "pptx" || extensionName === "pptm" || extensionName === "potx") {
                    extension = "ppt";
                } else if (extensionName === "docx" || extensionName === "doc") {
                    extension = "docx";
                } else {
                    extension = "default";
                }
                // console.log(originName);
                // console.log(extensionName);
                if (extension) {
                    liText += `<li class="form-item">
                        <div class="file-container">
                            <div class="file-icon">
                                <img src="/images/experience/icon_file_${extension}.svg" alt="">
                            </div>
                            <div class="file-info">
                                <p class="file-label" data-fileid="${fileId}">${originName}</p>
                            </div>
                        </div>
                        <div class="btn-check-container">
                            <div class="radio-box">
                                <label for="check${number}" class="icon-radio">
                                    <input type="radio" name="radio" id="check${number}">
                                </label>
                            </div>
                        </div>
                    </li>`;
                }
                storageUl.innerHTML = liText;
                // const inputFile=document.getElementById(`check${number}`);
            })
        } else {
            bodyHtml.innerHTML = `<!-- 데이터 없을때 -->
                <div class="no-data">
                    <p class="title">아직 등록된 라이브러리가 없습니다.</p>
                    <p class="description">이력서 파일을 미리 등록하면 빠르게 공고에 지원할 수 있습니다.</p>

                    <button type="button" class="btn-primary popup-trigger" data-target="#resume-upload-popup">등록하기</button>
                </div>`;
        }

        // 해당 팝업 열기
        popup.classList.add("active");

        // 추가 등혹
        const plusStorageBtn=document.getElementById("file-add-btn");
        if(plusStorageBtn){
            plusStorageBtn.addEventListener("click", ()=>{
                saveStorageFilePop.classList.add("active");
            })
        }

        // 이력서 등록하기
        const putFileBtn = document.querySelector(".btn-primary.popup-trigger");
        if (putFileBtn) {
            putFileBtn.addEventListener("click", (e) => {
                saveStorageFilePop.classList.add("active");
            })
        }
        const resumeCheckPopupBox = document.getElementById("resume-check-popup");
        const quickApplyPopupBox = document.getElementById("quick-apply-popup");
        if (!resumeCheckPopupBox || !quickApplyPopupBox) return;

        const doneBtn = resumeCheckPopupBox.querySelector("#file-select-btn");
        const allRadiobox = resumeCheckPopupBox.querySelectorAll(
            'input[type="radio"]'
        );
        if (!doneBtn) return;

        // 체크 - 버튼 활성/비활성
        function updateDoneBtn() {
            const anyChecked = resumeCheckPopupBox.querySelector(
                'input[type="radio"]:checked'
            );
            doneBtn.disabled = !anyChecked;
        }

        for (var i = 0; i < allRadiobox.length; i++) {
            allRadiobox[i].addEventListener("change", updateDoneBtn);
        }
        updateDoneBtn();

        // 선택 완료 클릭하면 quickApplyPopup에 값 넣기
        doneBtn.addEventListener("click", function (e) {
            e.preventDefault();
            e.stopPropagation();

            const checkedBox = resumeCheckPopupBox.querySelector(
                'input[type="radio"]:checked'
            );
            if (!checkedBox || checkedBox.length === 0) return;

            // 파일명/id
            const itemBox = checkedBox.closest(".form-item");
            const fileName = itemBox.querySelector(".file-label");
            const checkedFileId = fileName.dataset.fileid;
            // console.log(checkedFileId);

            const fileNametext = fileName.innerText;

            console.log(fileNametext);

            // 숨김 input(#resume-value)에 값 저장
            const formPopup1 = quickApplyPopupBox.querySelector("form");
            let resumeInput = quickApplyPopupBox.querySelector("#resume-value");
            if (!resumeInput) {
                resumeInput = document.createElement("input");
                resumeInput.type = "hidden";
                resumeInput.id = "resume-value";
                resumeInput.name = "resume";
                if (formPopup1) formPopup1.appendChild(resumeInput);
            }
            resumeInput.value = checkedFileId;

            // 표시용 버튼 텍스트 변경
            const fileButton = quickApplyPopupBox.querySelector(".file-btn");
            fileButton.innerText = fileNametext;

            // 팝업 전환
            resumeCheckPopupBox.classList.remove("active");
            quickApplyPopupBox.classList.add("active");

            // 다음 열 때 초기화
            checkedBox.checked = false;

            updateDoneBtn();
        });

        // 이력서 팝업 이전 버튼 클릭시
        const prevBtn = document.querySelector("#resume-check-popup .popup-prev");
        const quickApplyPopup = document.getElementById("quick-apply-popup");
        const resumeCheckPopup = document.getElementById("resume-check-popup");
        if (!prevBtn || !quickApplyPopup || !resumeCheckPopup) return;

        // 혹시 모를 제출 방지
        prevBtn.type = "button";

        prevBtn.addEventListener("click", (e) => {
            e.preventDefault();
            e.stopPropagation();

            // 열려있던 드롭다운 있으면 닫기(선택)
            document
                .querySelectorAll(".option-menu.active")
                .forEach((m) => m.classList.remove("active"));

            // 팝업 전환
            resumeCheckPopup.classList.remove("active");
            quickApplyPopup.classList.add("active");
        });
    }

    // 이력서 선택 팝업
    document.querySelector(".file-btn").addEventListener("click", async (e) => {
        // console.log("이력서 버튼 클릭됨")
        selectFilePop();
    });


    // 인풋 파일 등록
    function fileInputFn() {
        console.log("fileInputFn() 실행");
        const fileInput = document.getElementById("file-input");
        const formFileLabel = document.querySelector(".form-file-label");

        if (!fileInput) return;

        fileInput.addEventListener("change", () => {
            if (fileInput.files.length > 0&&fileInput.files.length<2) {
                console.log("화면에 파일 이름 띄우기");
                formFileLabel.textContent = fileInput.files[0].name;
            }else if(fileInput.files.length>1){
                formFileLabel.textContent=fileInput.files[0].name+" 외 "+(fileInput.files.length-1)+"개";
            } else {
                formFileLabel.textContent = "파일";
            }
        });
        //     등록하기 눌렀을 때
        const saveBtn=document.getElementById("pop-apply");

        saveBtn.addEventListener("click", async (e) => {
            // console.log("버튼 클릭됨");
            if (formFileLabel.textContent === "파일") {
                alert("파일이 선택되지 않았습니다.")
                return;
            }
            const formData=new FormData();
            for (let i = 0; i < fileInput.files.length; i++) {
                formData.append("files", fileInput.files[i]);
            }
            await fetch(`/api/member/storage/save`,{
                method: "POST",
                body: formData
            });
            saveStorageFilePop.classList.remove("active");
            selectFilePop();
        })
    }
    fileInputFn();

    // 팝업
    function popupFn() {

        if (!triggers) return;

        // 팝업 - from 안의 버튼 submit 막기
        document.addEventListener(
            "submit",
            (e) => {
                if (e.target.closest(".popup-container")) {
                    e.preventDefault();
                }
            },
            true
        );

        // 팝업 열기
        triggers.forEach((trigger) => {
            trigger.addEventListener("click", () => {
                const target = trigger.getAttribute("data-target");
                const popup = document.querySelector(target);

                // 드롭다운도 전부 닫기
                dropdowns.forEach((menu) => menu.classList.remove("active"));

                // 다른 팝업 닫기 (data-sticky가 붙어있는 팝업 제외)
                popups.forEach((pop) => {
                    if (!pop.hasAttribute("data-sticky")) {
                        pop.classList.remove("active");
                    }
                });

                // 현재(부모) 팝업 닫기 - 스티키면 유지
                const parentPopup = trigger.closest(".popup-container");
                if (parentPopup && !parentPopup.hasAttribute("data-sticky")) {
                    parentPopup.classList.remove("active");
                }

                // 해당 팝업 열기
                if (popup) {
                    popup.classList.add("active");
                }
            });
        });

        // 팝업 닫기 버튼
        const popupRemoves = document.querySelectorAll(".popup-remove");
        popupRemoves.forEach((closeBtn) => {
            closeBtn.addEventListener("click", () => {
                const popup = closeBtn.closest(".popup-container");
                if (popup) {
                    popup.classList.remove("active");
                }
            });
        });

        // 바깥 클릭 시 닫기
        // document.addEventListener("click", (e) => {
        //     document
        //         .querySelectorAll(".popup-container.active")
        //         .forEach((popup) => {
        //             if (
        //                 !e.target.closest(".popup-inner") &&
        //                 !e.target.closest(".popup-trigger")
        //             ) {
        //                 popup.classList.remove("active");
        //             }
        //         });
        // });
    }
    popupFn();

    // 드롭다운
    function dropdownFn() {
        const triggers = document.querySelectorAll(".dropdown-trigger");
        const menus = document.querySelectorAll(".option-menu");

        if (!triggers) return;

        // 드롭다운 열기
        triggers.forEach((trigger) => {
            trigger.addEventListener("click", (e) => {
                e.stopPropagation();

                // 다른 드롭다운 닫기
                menus.forEach((menu) => menu.classList.remove("active"));

                const target = trigger.getAttribute("data-target");
                const menu = document.querySelector(target);

                if (menu) {
                    menu.classList.add("active");
                }
            });
        });

        // 옵션 클릭 시 input에 값 반영 + 닫기
        document.querySelectorAll(".option-item").forEach((item) => {
            item.addEventListener("click", () => {
                const dropdown = item.closest(".form-field-dropdown");
                const input = dropdown.querySelector(".dropdown-trigger");
                const target = input.getAttribute("data-target");
                const menu = document.querySelector(target);

                // 직접 입력
                if (item.classList.contains("option-input")) {
                    dropdown.classList.add("is-manual");
                    if (input) {
                        input.readOnly = false;
                        input.value = "";
                        input.focus();
                    }
                    menu.classList.remove("active");
                    return;
                }

                // 일반 옵션
                const text = item.innerText.trim();
                if (input && input.tagName === "INPUT") {
                    input.value = text;
                    input.readOnly = true;
                    dropdown.classList.remove("is-manual");
                }
                menu.classList.remove("active");
            });
        });

        // 바깥 클릭 시 닫기
        // document.addEventListener("click", () => {
        //     menus.forEach((menu) => menu.classList.remove("active"));
        // });
    }
    dropdownFn();

    // 보관함 유효성 검사 - url 추가
    function checkPopupLibraryUrl() {
        const applyBtn = document.querySelector("#url-upload-popup #pop-apply");
        const form = document.querySelector("#url-upload-popup form");
        const libraryToast = document.querySelector("#toast-library");
        const toastSubText = libraryToast?.querySelector(".toast-subText");
        const typeInput = document.querySelector("#url-upload-popup #type-input");
        const urlInput = document.querySelector("#url-upload-popup #url-input");

        if (!applyBtn || !form || !libraryToast || !toastSubText) return;

        // 유효성 검사
        const validate = () => {
            if (typeInput.value.trim() === "") {
                libraryToast.classList.add("show");
                setTimeout(() => {
                    libraryToast.classList.remove("show");
                }, 2000);

                typeInput.focus();
                return false;
            }
            // if (urlInput.value.trim() === "") {
            //     toastSubText.textContent = "URL을 입력해주세요.";
            //
            //     libraryToast.classList.add("show");
            //     setTimeout(() => {
            //         libraryToast.classList.remove("show");
            //     }, 2000);
            //
            //     urlInput.focus();
            //     return false;
            // }
            return true;
        };

        // 버튼 클릭일 때만 검사
        applyBtn.addEventListener("click", (e) => {
            e.preventDefault();
            if (!validate()) return;

            // 팝업 닫기
            document.getElementById("url-upload-popup").classList.remove("active");

            // 초기화
            if (typeInput) typeInput.value = "";
            if (urlInput) urlInput.value = "";
        });
    }
    checkPopupLibraryUrl();

    // 보관함 닫기 공통: 닫기 클릭시 확인 팝업 띄우기
    function popupLibraryClose(popupId, inputSelectors) {
        const popup = document.getElementById(popupId);
        const closeBtn = popup?.querySelector(".popup-library-close");
        const messagePopup = document.getElementById("message-popup");

        if (!popup || !closeBtn) return;

        closeBtn.addEventListener("click", (e) => {
            e.preventDefault();

            // 입력값 체크
            let hasValue = false;
            inputSelectors.forEach((sel) => {
                const input = popup.querySelector(sel);
                if (!input) return;

                if (input.type === "file") {
                    if (input.files && input.files.length > 0) hasValue = true;
                } else {
                    if (input.value && input.value.trim() !== "") hasValue = true;
                }
            });

            if (hasValue) {
                // 값이 하나라도 있으면 확인 팝업(#messagePopup) 열기
                messagePopup.classList.add("active");
            } else {
                // 값이 없으면 그냥 닫기
                popup.classList.remove("active");
            }
        });
    }
    popupLibraryClose("resume-upload-popup", ["#type-input", "#file-input"]);
    popupLibraryClose("url-upload-popup", ["#type-input", "#url-input"]);

    // 전화번호 포맷팅
    function formatPhoneNumber(input) {
        input.value = input.value
            .replace(/[^0-9]/g, "")
            .replace(/(^02|^01[0-9]|[0-9]{3})([0-9]+)?([0-9]{4})$/, "$1 $2 $3");
    }

    // 간편지원하기 팝업
    async function quickApplyPopupFn() {
        const popup = document.getElementById("quick-apply-popup");
        if (!popup) return;

        const submitBtn = popup.querySelector(".popup-action .btn-primary");
        const toast = document.getElementById("toast-red");
        const toastText = toast.querySelector(".toast-text");

        const userDetailsPre = await fetch('/api/experiences/user');
        if(!userDetailsPre.ok){
            window.location.href = "/member/login";
        }
        const userDetail = await userDetailsPre.json();
        const user = userDetail;

        console.log(user);

        const isEmail = (v) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(v.trim());
        const isPhone = (v) => {
            const d = v.replace(/[^\d]/g, "");
            return d.length >= 9 && d.length <= 11; // 국내 9~11자리 간단 체크
        };

        const name = popup.querySelector("#name-input");
        const email = popup.querySelector("#email-input");
        const phone = popup.querySelector("#phone-input");
        const resume = popup.querySelector("#resume-value");
        const url=popup.querySelector("#url-input");

        name.value=user.userName;

        email.value=user.userEmail||user.snsEmail;

        phone.value=user.userPhone;

        submitBtn.addEventListener("click", async () => {

            // 이름을 입력안했을때
            if (!name.value.trim()) {
                if (toast) {
                    toastText.textContent = "이름을 입력해주세요.";
                    toast.classList.add("show");

                    setTimeout(() => {
                        toast.classList.remove("show");
                    }, 2000);
                }
                name.focus();
                return;
            }

            // 이메일을 입력안했을때
            if (!email.value.trim()) {
                if (toast) {
                    toastText.textContent = "이메일을 입력해주세요.";
                    toast.classList.add("show");

                    setTimeout(() => {
                        toast.classList.remove("show");
                    }, 2000);
                }
                name.focus();
                return;
            }

            // 이메일 형식이 맞지 않을때
            if (!isEmail(email.value)) {
                if (toast) {
                    toastText.textContent = "올바른 이메일 형식이 아닙니다";
                    toast.classList.add("show");

                    setTimeout(() => {
                        toast.classList.remove("show");
                    }, 2000);
                }
                email.focus();
                return;
            }

            // 전화번호를 입력안했을때
            if (!phone.value.trim()) {
                if (toast) {
                    toastText.textContent = "전화번호를 입력해주세요.";
                    toast.classList.add("show");

                    setTimeout(() => {
                        toast.classList.remove("show");
                    }, 2000);
                }
                name.focus();
                return;
            }

            // 전화번호 숫자만 추출
            const phoneNumber = phone.value.replace(/[^0-9]/g, "");

            // 전화번호 자리수 체크
            if (phoneNumber.length > 13) {
                if (toast) {
                    toastText.textContent =
                        "전화번호는 최대 13자리까지 입력 가능합니다.";
                    toast.classList.add("show");

                    setTimeout(() => {
                        toast.classList.remove("show");
                    }, 2000);
                }
                phone.value = phoneNumber.slice(0, 13); // 13자리까지만 자르기
                phone.focus();
                return;
            }

            // 전화번호 형식이 맞지 않을때
            if (!isPhone(phone.value)) {
                if (toast) {
                    toastText.textContent = "전화번호 형식을 확인해 주세요.";
                    toast.classList.add("show");

                    setTimeout(() => {
                        toast.classList.remove("show");
                    }, 2000);
                }
                phone.value = phone.value.slice(0, 13);
                phone.focus();
                return;
            }

            // 이력서를 선택하지 않았을때
            if (!resume || !resume.value) {
                if (toast) {
                    toastText.textContent = "이력서를 선택해주세요.";
                    toast.classList.add("show");

                    setTimeout(() => {
                        toast.classList.remove("show");
                    }, 2000);
                }
                popup.querySelector(".file-btn").focus();
                return;
            }

            const requestExperienceDTO = {};
            requestExperienceDTO.requestExperienceMemberName = name.value;
            requestExperienceDTO.requestExperienceMemberEmail = email.value;
            requestExperienceDTO.requestExperienceMemberPhone = phone.value;
            requestExperienceDTO.fileId=Number(resume.value);
            requestExperienceDTO.experienceNoticeId=nowExperienceId;

            console.log(nowExperienceId);
            console.log(requestExperienceDTO);
            if(url.value){
                requestExperienceDTO.requestExperienceMemberUrl = url.value;
            }

            await experienceService.pay(1000, requestExperienceDTO);

            // const memberName=name.value;
            // const memberEmail=email.value;
            // const memberPhone=phone.value;
            // const fileId=Number(resume.value);

            popup.classList.remove("active");
        });
    }

    // 이력서선택 팝업
    function resumeCheckPopupFn() {
    }
    resumeCheckPopupFn();

    // 팝업 전체 닫기
    function popAllClose() {
        const popupAllCloses = document.querySelectorAll(".popup-all-close");

        if (!popupAllCloses) return;

        function closeAllPopups() {
            document
                .querySelectorAll(".popup-container")
                .forEach((popup) => popup.classList.remove("active"));
        }

        popupAllCloses.forEach((pop) => {
            pop.addEventListener("click", closeAllPopups);
        });
    }
    popAllClose();

    // 토스트 팝업 - 팔로우
    function followToastFn() {
        const followBtns = document.querySelectorAll(".btn-follow");
        const followToast = document.querySelector("#toast-follow");

        if (!followBtns.length) return;

        let saved = false; // 저장 상태
        let showingToast = false; // 연타방지

        followBtns.forEach((btn) => {
            btn.addEventListener("click", () => {
                if (showingToast) return; // 토스트 떠 있으면 무시

                const textBox = followToast.querySelector(".toast-text");
                const subTextBox = followToast.querySelector(".toast-subText");

                if (!saved) {
                    saved = true;
                    if (textBox && subTextBox) {
                        textBox.textContent = "000님을 팔로우했습니다.";
                        subTextBox.textContent = "관련 소식을 받아볼 수 있습니다.";
                    }
                    // 👉 모든 버튼 상태를 동시에 변경
                    followBtns.forEach((b) => {
                        b.textContent = "팔로우중";
                        b.classList.add("btn-default");
                        b.classList.remove("btn-primary");
                    });
                } else {
                    saved = false;
                    if (textBox && subTextBox) {
                        textBox.textContent = "000님을 팔로우 취소했습니다.";
                        subTextBox.textContent =
                            "소식 알림 및 게시물 추천 빈도가 줄어듭니다.";
                    }
                    followBtns.forEach((b) => {
                        b.textContent = "팔로우";
                        b.classList.remove("btn-default");
                        b.classList.add("btn-primary");
                    });
                }

                // 토스트 띄우기
                followToast.classList.add("show");
                showingToast = true;

                setTimeout(() => {
                    followToast.classList.remove("show");
                    showingToast = false;
                }, 2000);
            });
        });
    }
    followToastFn();

    // 페이지네이션
    // function pagenation() {
    //     const pageItems = document.querySelectorAll(".page-list .page-item");
    //
    //     pageItems.forEach((btn) => {
    //         btn.addEventListener("click", () => {
    //             pageItems.forEach((item) => item.classList.remove("active"));
    //
    //             btn.classList.add("active");
    //         });
    //     });
    // }
    // pagenation();

    // 파일 이미지 업로드
    function fileUploadFn() {
        const addPhotoBtn = document.querySelector("#btn-add-photo");
        const previewInner = document.querySelector(".popup-preview-inner");
        const toast = document.querySelector("#toast-red");
        const toastText = document.querySelector("#toast-red p");

        if (!addPhotoBtn || !previewInner) return;

        addPhotoBtn.addEventListener("change", (e) => {
            const files = Array.from(e.target.files);

            // 현재 썸네일 수 확인
            const existingThumbs =
                previewInner.querySelectorAll(".preview-item").length;

            if (existingThumbs + files.length > 8) {
                // alert("사진은 최대 8장까지 첨부할 수 있습니다.");
                toastText.textContent = "사진은 최대 8장까지 첨부할 수 있습니다.";
                toast.classList.add("show");
                setTimeout(() => toast.classList.remove("show"), 2000);
                return;
            }

            files.forEach((file) => {
                if (!file.type.startsWith("image/")) return;

                const reader = new FileReader();
                reader.readAsDataURL(file);

                reader.onload = (event) => {
                    const imageSrc = event.target.result;

                    // 썸네일 요소 한 번에 생성
                    const item = document.createElement("div");
                    item.className = "preview-item";
                    item.innerHTML = `
                        <span>
                            <img src="${imageSrc}" alt="${file.name}">
                        </span>
                        <button class="preview-remove-btn" type="button">
                            <svg viewBox="0 0 24 24" aria-label="icon" fill="currentColor" height="16" role="img" width="16">
                                <path clip-rule="evenodd"
                                    d="M6.434 6.435a.8.8 0 0 1 1.132 0L12 10.869l4.434-4.434a.8.8 0 1 1 1.132 1.13L13.13 12l4.435 4.435a.8.8 0 1 1-1.132 1.13L12 13.133l-4.434 4.434a.8.8 0 0 1-1.132-1.131L10.87 12 6.434 7.566a.8.8 0 0 1 0-1.131"
                                    fill-rule="evenodd"></path>
                            </svg>
                        </button>
                    `;

                    previewInner.appendChild(item);

                    // 삭제 버튼 이벤트
                    const cancelBtn = item.querySelector(".preview-remove-btn");
                    cancelBtn.addEventListener("click", () => {
                        item.remove();
                    });
                };
            });

            // 같은 파일 다시 선택 가능하게 초기화
            e.target.value = "";
        });
    }
    fileUploadFn();

    // 게시글 글자수 카운트
    function numCountFn() {
        const textarea = document.querySelector(".popup-input textarea");
        if (!textarea) return;

        const currentNum = document.querySelector(".current-num");
        const maxLength = Number(document.querySelector(".max-num").innerText);

        textarea.addEventListener("keyup", (e) => {
            const result = textarea.value.length;
            if (textarea.value.length > maxLength) {
                textarea.value = textarea.value.slice(0, maxLength);
            }
            currentNum.innerText = `${result}`;
        });
    }
    numCountFn();

    // 게시글 글쓰기 버튼 활성화
    function writeBtnActiveFn() {
        const textarea = document.querySelector(".popup-textarea");
        const writeBtn = document.querySelector(".pop-btn-write");
        const previewInner = document.querySelector(".popup-preview-inner");
        const toast = document.querySelector("#toast-white");
        const popupWriteCloseBtn = document.querySelector(".popup-write-close");
        const messagePopup = document.getElementById("message-popup2");

        if (!textarea || !writeBtn) return;

        // 버튼 활성/비활성 토글
        function toggleBtn() {
            const textLength = textarea.value.trim().length;
            const imageCount = previewInner
                ? previewInner.querySelectorAll(".preview-item").length
                : 0;

            if (textLength > 0 || imageCount > 0) {
                writeBtn.removeAttribute("disabled");
            } else {
                writeBtn.setAttribute("disabled", "true");
            }
        }

        // 글 입력 시 검사
        textarea.addEventListener("input", toggleBtn);

        // DOM 변경 감지 (이미지 추가/삭제 시 검사)
        if (previewInner) {
            const observer = new MutationObserver(toggleBtn);
            observer.observe(previewInner, { childList: true });
        }

        // 버튼 클릭 시 최종 조건 확인
        writeBtn.addEventListener("click", (e) => {
            const textLength = textarea.value.trim().length;
            const imageCount = previewInner
                ? previewInner.querySelectorAll(".preview-item").length
                : 0;

            if (textLength < 10 && imageCount === 0) {
                e.preventDefault();
                toast.classList.add("show");
                setTimeout(() => toast.classList.remove("show"), 2000);
            } else {
                // 조건 충족 → 팝업 닫기
                const popup = writeBtn.closest(".popup-container");
                if (popup) {
                    popup.classList.remove("active");
                }
            }
        });

        popupWriteCloseBtn.addEventListener("click", (e) => {
            const textLength = textarea.value.trim().length;
            const imageCount = previewInner
                ? previewInner.querySelectorAll(".preview-item").length
                : 0;

            if (textLength === 0 && imageCount === 0) {
                const popup = popupWriteCloseBtn.closest(".popup-container");
                if (popup) popup.classList.remove("active");
            } else {
                messagePopup.classList.add("active");
            }
        });

        // 초기 상태
        toggleBtn();
    }
    writeBtnActiveFn();
});

// JS
function bannerActiveFn() {
    const banners = document.querySelectorAll(".banner-list .ad-banner");
    if(banners.length>0){
        let timer = null;
        let currentIndex = -1;

        if (!banners) return;

        // 모두 숨기기
        function hideAll() {
            banners.forEach((banner) => banner.classList.remove("active"));
        }

        // 랜덤 배너 보이기
        function showRandomBanner() {
            hideAll();
            let randomIndex;
            do {
                randomIndex = Math.floor(Math.random() * banners.length);
            } while (randomIndex === currentIndex && banners.length > 1);
            // 직전 배너와 겹치지 않게 처리
            banners[randomIndex].classList.add("active");
            currentIndex = randomIndex;
        }

        // 최초 실행
        showRandomBanner();

        // 3초마다 랜덤 배너 변경
        timer = setInterval(showRandomBanner, 5000);
    }

}
bannerActiveFn();

let experiences = [];
const PAGE = 1;

// ------------------------------
// 1. 렌더링 + 정렬
// ------------------------------
function showList(list) {
    if (!Array.isArray(list)) list = [];

    const populBtn = document.getElementById("populBtn");
    const scaleBtn = document.getElementById("scaleBtn");

    const sortedList = [...list];

    if (populBtn && populBtn.classList.contains("active")) {
        sortedList.sort((a, b) => (b.saveCount || 0) - (a.saveCount || 0));
    } else if (scaleBtn && scaleBtn.classList.contains("active")) {
        sortedList.sort((a, b) => (b.scaleId || b.companyScaleId || 0) - (a.scaleId || a.companyScaleId || 0));
    } else {
        sortedList.sort((a, b) => (b.id || 0) - (a.id || 0));
    }

    experienceLayout.showList(sortedList);
}

// ------------------------------
// 2. 검색 fetch
// ------------------------------
async function fetchExperiences() {
    try {
        const keywordInput = document.getElementById("keyword-input");
        const keyword = keywordInput ? keywordInput.value.trim() : "";

        const data = await experienceService.getExperienceNotice(PAGE, keyword);

        experiences = Array.isArray(data) ? data : (data.experiences || []);
        showList(experiences);
    } catch (e) {
        console.error("데이터 로드 오류:", e);
        experiences = [];
        showList(experiences);
    }
}

// ------------------------------
// 3. 검색 버튼 + Enter 처리
// ------------------------------
function initSearch() {
    const searchBtn = document.getElementById("searchBtn");
    const keywordInput = document.getElementById("keyword-input");

    function handleSearch(e) {
        if (!checkKeywordLength(e)) return;
        fetchExperiences();
    }

    if (searchBtn) {
        searchBtn.addEventListener("click", handleSearch);
    }

    if (keywordInput) {
        keywordInput.addEventListener("keydown", (e) => {
            if (e.key === "Enter") {
                e.preventDefault();
                handleSearch(e);
            }
        });
    }
}


// ------------------------------
// 4. 정렬 버튼
// ------------------------------
function initSortButtons() {
    const sortButtons = document.querySelectorAll(".sort-btn");
    if (!sortButtons || sortButtons.length === 0) return;

    sortButtons.forEach(btn => {
        btn.addEventListener("click", () => {
            sortButtons.forEach(b => b.classList.remove("active"));
            btn.classList.add("active");
            showList(experiences);
        });
    });
}

// ------------------------------
// 5. 초기화
// ------------------------------
function initExperienceModule() {
    initSearch();
    initSortButtons();
    fetchExperiences();
}

initExperienceModule();


// 필터 적용

const sectorComplBtn = document.getElementById("sector-compl-btn");
const scaleComplBtn = document.getElementById("scale-compl-btn");

// 이벤트 등록
sectorComplBtn.addEventListener("click", applyFilters);
scaleComplBtn.addEventListener("click", applyFilters);
document.getElementById("search-form")?.addEventListener("submit", e => {
    e.preventDefault();
    showList();
});



