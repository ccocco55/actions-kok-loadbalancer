let status = null;

// 초기 로딩
document.addEventListener("DOMContentLoaded", () => {
    filter(1);
});

const statusButtons = document.querySelectorAll(".category-sub");
statusButtons.forEach(btn => {
    btn.addEventListener("click", () => {
        // active 스타일 토글
        statusButtons.forEach(b => b.classList.remove("active"));
        btn.classList.add("active");

        if (btn.classList.contains("await")) {
            status = "await";
        } else if (btn.classList.contains("accept")) {
            status = "accept";
        } else if (btn.classList.contains("reject")) {
            status = "reject";
        } else {
            status = "";
        }

        filter(1);
    });
});

function filter(page = 1) {
    internDatailLayout.contentLayout();
    internDatailService.getList(internNoticeId, page, status, (data) => {
        if (!data) return;

        internDatailLayout.rowTemplate(data.applicantLists);
        internDatailLayout.renderPagination(data.criteria);
        internDatailLayout.listTotalCount(data);
    });
};

document.addEventListener("click", (e) => {
    const row = e.target.closest(".body-tr");

    if (row) {
        const userId = row.dataset.userId;

        const url = `/enterprise-console/intern/application/${internNoticeId}/${userId}`;
        window.location.href = url;
    }
});


