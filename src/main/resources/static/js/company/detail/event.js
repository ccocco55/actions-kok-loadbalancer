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
    const resetBtn = document.querySelector(".btn-reset");

    if (!keywordInput) return;

    function checkKeywordLength() {
        if (keywordInput && toast) {
            if (keywordInput.value.length > 50) {
                toast.classList.add("show");

                setTimeout(() => {
                    toast.classList.remove("show");
                }, 2000);
            } else {
                toast.classList.remove("show");
            }
        }
    }

    // 검색 버튼 클릭 시 토스트 메시지 체크
    const searchButton = document.querySelector(".search-button");
    if (searchButton) {
        searchButton.addEventListener("click", () => {
            checkKeywordLength();
        });
    }

    // 엔터 입력 시 체크
    keywordInput.addEventListener("keydown", (e) => {
        if (e.key === "Enter") {
            e.preventDefault();
            checkKeywordLength();
        }
    });

    // 모바일 인풋 삭제
    deleteBtn.style.display = "none";

    keywordInput.addEventListener("input", () => {
        if (keywordInput.value.trim() !== "") {
            deleteBtn.style.display = "inline-block"; // 보이게
        } else {
            deleteBtn.style.display = "none"; // 숨기기
        }
    });

    // 삭제 버튼 클릭 시 인풋 비우고 버튼 숨기기
    deleteBtn.addEventListener("click", () => {
        keywordInput.value = "";
        deleteBtn.style.display = "none";
        keywordInput.focus(); // 다시 입력할 수 있게 포커스 주기
    });

    resetBtn.addEventListener("click", () => {
        keywordInput.value = "";
        deleteBtn.style.display = "none";
        keywordInput.focus(); // 다시 입력할 수 있게 포커스 주기
    });
}
keywordInputValidate();

// 토스트 팝업 - 팔로우
function followEventFn() {
    const followBtns = document.querySelectorAll(".btn-follow");
    const followToast = document.querySelector("#toast-follow");

    if (!followBtns || followBtns.length === 0) return;

    followBtns.forEach(async (btn) => {
        const companyId = btn.dataset.companyId;
        const followerCountEl = document.querySelector(".stat-item .stat-value");

        if (!companyId) return;

        // 현재 팔로우 여부
        const isFollowed = await followService.isFollowing(companyId);
        updateFollowButton(btn, isFollowed);

        // 팔로워 수
        const count = await followService.getFollowerCount(companyId);
        updateFollowerCount(followerCountEl, count);

        // 버튼 클릭
        btn.addEventListener("click", async () => {
            const currentlyFollowing = btn.classList.contains("btn-default");

            if (currentlyFollowing) {
                // 언팔로우
                const ok = await followService.unfollow(companyId);
                if (ok) {
                    updateFollowButton(btn, false);
                    showFollowToast(
                        followToast,
                        "팔로우를 취소했습니다.",
                        "소식 알림 및 게시물 추천 빈도가 줄어듭니다."
                    );
                    const newCount = await followService.getFollowerCount(companyId);
                    updateFollowerCount(followerCountEl, newCount);
                }
            } else {
                // 팔로우
                const ok = await followService.follow(companyId);
                if (ok) {
                    updateFollowButton(btn, true);
                    showFollowToast(
                        followToast,
                        "기업을 팔로우했습니다.",
                        "관련 소식을 받아볼 수 있습니다."
                    );
                    const newCount = await followService.getFollowerCount(companyId);
                    updateFollowerCount(followerCountEl, newCount);
                }
            }
        });
    });
}

// 버튼 상태 업데이트
function updateFollowButton(button, isFollowing) {
    if (isFollowing) {
        button.textContent = "팔로우중";
        button.classList.add("btn-default");
        button.classList.remove("btn-primary");
    } else {
        button.textContent = "팔로우";
        button.classList.add("btn-primary");
        button.classList.remove("btn-default");
    }
}

// 팔로워 수 업데이트
function updateFollowerCount(el, count) {
    if (el) {
        el.textContent = count;
    }
}

// 토스트 메시지 표시
function showFollowToast(toastEl, mainText, subText) {
    if (!toastEl) return;

    const textBox = toastEl.querySelector(".toast-text");
    const subTextBox = toastEl.querySelector(".toast-subText");

    textBox.textContent = mainText;
    subTextBox.textContent = subText;

    toastEl.classList.add("show");
    setTimeout(() => {
        toastEl.classList.remove("show");
    }, 2000);
}

followEventFn();

// 체험, 인턴 - 필터 버튼 클릭 시 active 클래스 토글
function sortBtnFn() {
    const sortBtns = document.querySelectorAll(".sort-options .sort-btn");

    if (!sortBtns) return;

    sortBtns.forEach((btn) => {
        btn.addEventListener("click", () => {
            sortBtns.forEach((b) => b.classList.remove("active"));
            btn.classList.add("active");
        });
    });
}
sortBtnFn();

const companyId = document.querySelector(".btn-follow").dataset.companyId;
if (companyId) {
    let currentPage = 1;
    let search = { keyword: "", category: "" };

    const keywordInput = document.getElementById("keyword-input");
    const sortBtns = document.querySelectorAll(".sort-options .sort-btn");
    const paginationContainer = document.querySelector(".page-list");

    showExperienceNoticeList();

    // 초기화
    document.addEventListener("click", (e) => {
        const resetBtn = e.target.closest(".btn-reset");
        if (resetBtn) {
            if (keywordInput) keywordInput.value = "";
            search.keyword = "";
            currentPage = 1;
            search.category = "experience";

            sortBtns.forEach((b) => b.classList.remove("active"));
            sortBtns[0].classList.add("active");

            showExperienceNoticeList();
        }
    });


    // 검색
    if (keywordInput) {
        keywordInput.addEventListener("keydown", (e) => {
            if (e.key === "Enter") {
                search.keyword = keywordInput.value.trim();
                currentPage = 1;

                if (search.category === "intern") {
                    showInternNoticeList();
                } else {
                    showExperienceNoticeList();
                }
            }
        });
    }

    // 체험/인턴 카테고리
    sortBtns.forEach((btn) => {
        btn.addEventListener("click", () => {
            sortBtns.forEach((b) => b.classList.remove("active"));
            btn.classList.add("active");

            search.keyword = keywordInput.value.trim()

            const text = btn.textContent.trim();
            if (text === "체험") {
                search.category = "experience";
                currentPage = 1;
                showExperienceNoticeList();
            } else if (text === "인턴") {
                search.category = "intern";
                currentPage = 1;
                showInternNoticeList();
            }
        });
    });

    // 페이징 처리
    const pagination = document.querySelector(".pagenation");
    if (pagination) {
        pagination.addEventListener("click", (e) => {
            const target = e.target.closest(".page-item");
            if (!target || target.disabled) return;

            const targetPage = Number(target.dataset.page);
            if (!isNaN(targetPage)) {
                currentPage = targetPage;

                if (search.category === "intern") {
                    showInternNoticeList();
                } else {
                    showExperienceNoticeList();
                }
            }
        });
    }

    // 체험 공고
    async function showExperienceNoticeList() {
        await companyNoticeService.getExperienceNotices(
            companyId,
            currentPage,
            search,
            (data) => companyNoticeLayout.showExperienceNotices(data)
        );
    }

    // 인턴 공고
    async function showInternNoticeList() {
        await companyNoticeService.getInternNotices(
            companyId,
            currentPage,
            search,
            (data) => companyNoticeLayout.showInternNotices(data)
        );
    }
}

// 광고 배너
function bannerActiveFn() {
    const banners = document.querySelectorAll(".banner-list .ad-banner");
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
bannerActiveFn();


