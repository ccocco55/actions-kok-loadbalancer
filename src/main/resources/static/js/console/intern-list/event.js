const jobMenu = document.querySelector(".job");
const jobItems = document.querySelectorAll(".job-3");
const checkIcon = document.querySelector(".setting-31");
const searchSpan = document.querySelector(".search-span");

// job-3 선택
jobItems.forEach((item) => {
    item.addEventListener("click", (e) => {
        e.stopPropagation();

        // 체크마크 이동
        if (checkIcon.parentNode) checkIcon.parentNode.removeChild(checkIcon);
        item.appendChild(checkIcon);

        // 선택 강조
        jobItems.forEach((j) => j.classList.remove("selected"));
        item.classList.add("selected");

        // span 업데이트
        const text = item.querySelector(".job-6").innerText;
        searchSpan.innerText = text;

        // 메뉴 숨김
        jobMenu.style.display = "none";
    });
});

const internTable = document.querySelector("#intern-list-table");
if (internTable) {
    internTable.addEventListener("click", async (e) => {
        const activeExp = e.target.closest("button.appli-active-btn");
        if (activeExp) {
            const btn = e.target.closest(".appli-active-btn");
            const tr = btn.closest(".body-tr");
            const span = tr.querySelector(".exp-status");
            const isActive = btn.classList.contains("active");

            if (isActive) {
                btn.classList.remove("active");
                btn.classList.add("inactive");
                span.classList.remove("active");
                span.classList.add("inactive");
                span.innerText = "모집 완료";
                statusValue = "inactive";
            } else {
                btn.classList.remove("inactive");
                btn.classList.add("active");
                span.classList.remove("inactive");
                span.classList.add("active");
                span.innerText = "모집중";
                statusValue = "active";
            }

            const noticeId = tr.dataset.id;
            try {
                const data = await internNoticeService.updateInternStatus(noticeId, statusValue);
                console.log("DB 반영 성공:", data);
            } catch (err) {
                console.error("DB 반영 실패:", err);
            }
        }

        // 햄버거 버튼 클릭
        const hambugerBtn = e.target.closest("button.hambuger");
        if (hambugerBtn) {
            e.stopPropagation();

            // 모든 팝업 닫기
            document.querySelectorAll(".hambuger-pop-wrap").forEach((pop) => {
                pop.style.display = "none";
                pop.classList.remove("active");
            });

            // 현재 버튼 다음 형제 팝업만 열기
            const hambugerPopWrap = hambugerBtn.nextElementSibling;
            if (hambugerPopWrap) {
                hambugerPopWrap.classList.add("active");
                hambugerPopWrap.style.display = "block";
            }
            return;
        }

        // 팝업 삭제하기 버튼 클릭
        const deleteBtn = e.target.closest(".red-ham-list");
        if (deleteBtn) {
            const tr = deleteBtn.closest(".body-tr");
            const noticeId = tr?.dataset.id;
            if (!noticeId) return;

            const confirmDelete = confirm("정말 이 공고를 삭제하시겠습니까?");
            if (!confirmDelete) return;

            const result = await internNoticeService.deleteIntern(noticeId);

            if (result === "success") {
                alert("공고가 삭제되었습니다!");
                internNoticeService.getList(page, status, keyword,(data) => {
                    internLayout.contentLayout();
                    internLayout.rowTemplate(data.internLists);
                    internLayout.totalCount(data);
                    internLayout.listTotalCount(data);
                    internLayout.renderPagination(data.criteria);
                    bindPaginationEvent(status, keyword);
                });
            } else {
                alert("삭제 실패! 다시 시도해주세요.");
            }
        }
    });

    // 문서 아무 곳 클릭 시 팝업 닫기
    document.addEventListener("click", (e) => {
        if (!e.target.closest(".hambuger-pop-wrap") && !e.target.closest(".hambuger")) {
            document.querySelectorAll(".hambuger-pop-wrap").forEach((pop) => {
                pop.style.display = "none";
            });
        }
    });
}

// ######################### 공고목록 ############################
const page = 1;
let status = null;
let keyword ="";

const bindPaginationEvent = (status) => {
    const paginationArea = document.querySelector("#intern-list-table .page-ul");
    if (!paginationArea) return;

    paginationArea.addEventListener("click",(e) => {
        const link = e.target.closest(".page-a");
        if (!link) return;

        e.preventDefault();
        paginationArea.querySelectorAll(".page-a").forEach(a => {
            a.classList.remove("active");
        });
        link.classList.add("active");

        const page = parseInt(link.dataset.page, 10);

        internNoticeService.getList(page, status, keyword,(data) => {
            internLayout.contentLayout();
            internLayout.rowTemplate(data.internLists);
            internLayout.totalCount(data);
            internLayout.listTotalCount(data);
            internLayout.renderPagination(data.criteria);

            bindPaginationEvent(status, keyword);
        });
    });
};

internNoticeService.getList(page, status, keyword,(data) => {
    internLayout.contentLayout();
    internLayout.rowTemplate(data.internLists);
    internLayout.totalCount(data);
    internLayout.listTotalCount(data);
    internLayout.renderPagination(data.criteria);
    bindPaginationEvent(status, keyword);
});


// ######################### 검색 ############################
const searchInput = document.querySelector(".search-input"); // 검색어
const statusButtons = document.querySelectorAll(".category-sub"); // 전체/모집중/종료

// 상태 버튼 이벤트 (전체/모집중/모집종료)
statusButtons.forEach(btn => {
    btn.addEventListener("click", () => {
        // active 스타일 토글
        statusButtons.forEach(b => b.classList.remove("active"));
        btn.classList.add("active");

        if (btn.classList.contains("ing")) {
            status = "active";
        } else if (btn.classList.contains("end")) {
            status = "inactive";
        } else {
            status = ""; // 전체
        }

        // 상태 탭 누르면 바로 검색 실행
        doSearch(1);
    });
});

// 검색 실행 함수
function doSearch(page = 1) {
    keyword = searchInput.value.trim();

    internNoticeService.getList(page, status, keyword, (data) => {
        internLayout.contentLayout();
        internLayout.rowTemplate(data.internLists);
        internLayout.totalCount(data);
        internLayout.listTotalCount(data);
        internLayout.renderPagination(data.criteria);
        bindPaginationEvent(page, status, keyword);
    });
}

// 엔터 입력 시 실행
searchInput.addEventListener("keyup", (e) => {
    if (e.key === "Enter") {
        doSearch();
    }
});
