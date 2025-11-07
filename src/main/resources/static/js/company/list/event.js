// 무한 스크롤
let page = 1;
let checkScroll = true;
let companiesCriteria;
let SearchCompanies  = {};

const showList = async (page = 1) => {
    companiesCriteria = await companyService.getList(page, companyLayout.showList, SearchCompanies);

    return companiesCriteria;
};
showList(page);

const companyScrollContainer = document.querySelector(".list-container.pd-0");

companyScrollContainer.addEventListener("scroll", async () => {
    if (companyScrollContainer.scrollTop + companyScrollContainer.clientHeight >= companyScrollContainer.scrollHeight - 100) {
        if (checkScroll) {
            companiesCriteria = await showList(++page);
            checkScroll = false;
        }
        setTimeout(() => {
            if (companiesCriteria && companiesCriteria.criteria.hasMore) checkScroll = true;
        }, 800);
    }
});

// 검색 조건에 맞춰 기업 목록을 다시 불러오는 함수
async function fetchCompanies(page = 1) {
    const keywordInput = document.querySelector("#keyword-input");
    const keyword = keywordInput.value.trim();

    // 산업 분야 (두 번째 search-item)
    const industryBtns = document.querySelectorAll(
        ".search-item:nth-child(2) .dropdown-btn.active"
    );
    const industries = Array.from(industryBtns).map(btn => btn.textContent.trim());

    // 기업 규모 (세 번째 search-item)
    const scaleBtns = document.querySelectorAll(
        ".search-item:nth-child(3) .dropdown-btn.active"
    );
    const scales = Array.from(scaleBtns).map(btn => btn.querySelector("p").textContent.trim());

    // 백엔드에 보낼 검색 파라미터 객체
    SearchCompanies = {
        keyword: keyword || "",
        job: industries.length > 0 ? industries.join(",") : "",
        scale: scales.length > 0 ? scales.join(",") : ""
    };

    // console.log("검색 내용", SearchCompanies);

    // 검색 시 페이지와 기존 목록 초기화
    page = 1;
    const listContainer = document.querySelector(".list-container");
    listContainer.innerHTML = "";

    // API 호출
    companiesCriteria = await companyService.getList(
        page,
        (data) => {
            companyLayout.showList(data, true)
            // console.log("layout 불러올 데이터", data);
        },
        SearchCompanies
    );

    checkScroll = true;

    // console.log("검색 결과", companiesCriteria);
}

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
                fetchCompanies();
            }
        });
    }

    // 엔터 입력 시
    keywordInput.addEventListener("keydown", (e) => {
        if (e.key === "Enter") {
            e.preventDefault();
            if (checkKeywordLength(e)) {
                fetchCompanies();
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

// JS
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
