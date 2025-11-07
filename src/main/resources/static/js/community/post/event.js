// 게시글 무한 스크롤
let page = 1;
let checkScroll = true;
let postsCriteria;
const showList = async (page = 1) => {
    const loading = document.getElementById("loading");
    if (loading) loading.style.display = "block";

    postsCriteria = await postService.getList(page, postLayout.showList);

    if (loading) setTimeout(() => loading.style.display = "none", 500);
    return postsCriteria;
};
showList(page);

document.addEventListener("DOMContentLoaded", async () => {
    const sharedPostId = window.sharedPostId;

    if (sharedPostId) {
        try {
            const post = await postService.getOne(sharedPostId);
            postLayout.showDetail(post);

            const modal = document.getElementById("post-detail-modal");
            modal.style.display = "flex";
            modal.dataset.postId = sharedPostId;

            const commentContainer = modal.querySelector(".reply-10");
            commentContainer.innerHTML = "";
            await showComments(sharedPostId);
        } catch (err) {
            console.error("공유 게시글 불러오기 실패:", err);
        }
    }
});


const mainContainer = document.querySelector(".main-0");

mainContainer.addEventListener("scroll", async () => {
    const scrollTop = mainContainer.scrollTop;
    const clientHeight = mainContainer.clientHeight;
    const scrollHeight = mainContainer.scrollHeight;

    if (scrollTop + clientHeight >= scrollHeight - 100) {
        if (checkScroll) {
            postsCriteria = await showList(++page);
            checkScroll = false;
        }
        setTimeout(() => {
            if (postsCriteria && postsCriteria.criteria.hasMore) checkScroll = true;
        }, 800);
    }
});

// 파일 썸네일 관련
const input = document.getElementById('btn-add-photo');
const previewContainer = document.querySelector('.popup-preview-inner');
const MAX_FILES = 8;
const MAX_SIZE = 20 * 1024 * 1024;
let fileBuffer = [];
let deleteFileIds = [];

const toKey = (f) => `${f.name}|${f.size}|${f.lastModified}`;

const syncInput = () => {
    const dt = new DataTransfer();
    fileBuffer.forEach(f => {
        if (!f.existing) dt.items.add(f);
    });
    input.files = dt.files;
};

const render = () => {
    previewContainer.innerHTML = '';
    fileBuffer.forEach((file, idx) => {
        const item = document.createElement('div');
        item.className = 'preview-item';

        if (file.existing) {
            const img = document.createElement('img');
            img.className = 'preview-thumb';
            img.src = file.url;
            item.appendChild(img);
        }
        else if (file.type && file.type.startsWith('image/')) {
            const img = document.createElement('img');
            img.className = 'preview-thumb';
            const reader = new FileReader();
            reader.onload = (e) => { img.src = e.target.result; };
            reader.readAsDataURL(file);
            item.appendChild(img);
        }
        else {
            const box = document.createElement('div');
            box.className = 'preview-generic';
            box.textContent = file.name;
            item.appendChild(box);
        }

        const rm = document.createElement('button');
        rm.type = 'button';
        rm.className = 'preview-remove';
        rm.innerHTML = `<svg width="14" height="14" viewBox="0 0 24 24" fill="none">
                          <path d="M18 6L6 18M6 6l12 12" stroke="currentColor" stroke-width="2" stroke-linecap="round"/>
                        </svg>`;
        rm.addEventListener('click', () => {
            if (file.existing && file.id) {
                deleteFileIds.push(file.id);
            }
            fileBuffer.splice(idx, 1);
            syncInput();
            render();
        });

        item.appendChild(rm);
        previewContainer.appendChild(item);
    });
};

const addFiles = (files) => {
    const existingKeys = new Set(fileBuffer.map(toKey));
    const arFile = Array.from(files);

    for (const f of arFile) {
        if (fileBuffer.length >= MAX_FILES) {
            alert(`최대 ${MAX_FILES}개까지 업로드할 수 있습니다.`);
            break;
        }
        if (f.size > MAX_SIZE) {
            alert(`"${f.name}" 파일이 용량 제한(20MB)을 초과했습니다.`);
            continue;
        }
        if (existingKeys.has(toKey(f))) {
            continue;
        }
        fileBuffer.push(f);
        existingKeys.add(toKey(f));
    }
    syncInput();
    render();
};

if (input) {
    input.addEventListener('change', () => addFiles(input.files));
}

// 글쓰기 / 수정 모달 관련
const popup = document.getElementById("post-write-popup");
const writeBtns = document.querySelectorAll(".popup-trigger");
const closeBtn = document.querySelector(".popup-write-close");
const writeTextarea = document.querySelector(".popup-textarea");
const writeFiles = document.querySelector("#btn-add-photo");

if (popup) {
    popup.dataset.mode = "write";
}

// 열기
writeBtns.forEach((btn) => {
    btn.addEventListener("click", () => {
        popup.classList.add("active");
    });
});

// 닫기
if (closeBtn) {
    closeBtn.addEventListener("click", () => {
        const hasContent =
            writeTextarea.value.trim().length > 0 ||
            writeFiles.files.length > 0;
        if (hasContent) {
            document.querySelector(".change").style.display = "flex";
        } else {
            popup.classList.remove("active");
        }
    });
}

// 이벤트 위임
document.body.addEventListener("click", async (e) => {
    const target = e.target;

    // 작성 / 수정 버튼
    if (target.closest(".pop-btn-write")) {
        const content = writeTextarea.value.trim();
        const isEdit = popup.dataset.mode === "edit";
        const hasFiles = fileBuffer.length > 0;
        const files = writeFiles.files;

        if (!isEdit) {
            if (content.length < 10 && !hasFiles) {
                alert("10자 이상 작성하거나 파일을 추가해주세요.");
                return;
            }
            try {
                const postId = await postService.write(content, files);
                // console.log("글쓰기 성공:", postId);
                popup.classList.remove("active");
            } catch (err) {
                alert("업로드를 실패했습니다.");
            }
        } else {
            if (content.length < 10 && !hasFiles) {
                alert("10자 이상 작성하거나 기존 파일을 유지/새 파일을 추가해주세요.");
                return;
            }
            try {
                const postId = popup.dataset.postId;
                // console.log("게시글 id", postId, "삭제파일 id:", deleteFileIds);
                await postService.update(postId, content, deleteFileIds, files);
                alert("게시글이 수정되었습니다.");
                deleteFileIds = [];
                popup.classList.remove("active");

                const detailModal = document.getElementById("post-detail-modal");
                if (detailModal) detailModal.style.display = "none";

                location.reload();
                return;

            } catch (err) {
                alert("수정 중 오류가 발생했습니다.");
            }
        }

        // 초기화
        writeTextarea.value = "";
        writeFiles.value = "";
        fileBuffer = [];
        deleteFileIds = [];
        previewContainer.innerHTML = "";
        popup.dataset.mode = "write";

        const postContainer = document.querySelector("#post-container");
        if (postContainer) postContainer.innerHTML = "";
        page = 1;
        checkScroll = true;
        await showList(page);

        return;
    }

    // 게시글 수정 클릭
    if (target.closest(".update-post-btn")) {
        const postCard = target.closest(".post-8");
        const postId = postCard ? postCard.dataset.postId : document.getElementById("post-detail-modal").dataset.postId;

        try {
            const post = await postService.getOne(postId);
            popup.dataset.mode = "edit";
            popup.dataset.postId = postId;
            deleteFileIds = [];
            writeTextarea.value = post.postContent || "";

            fileBuffer = [];
            previewContainer.innerHTML = "";

            if (post.postFiles && post.postFiles.length > 0) {
                post.postFiles.forEach(file => {
                    const existingFile = {
                        id: file.fileId,
                        name: file.fileName,
                        size: 0,
                        lastModified: Date.now(),
                        existing: true,
                        url: file.postFilePath
                    };
                    fileBuffer.push(existingFile);
                });
                render();
            }

            popup.classList.add("active");
        } catch (err) {
            // alert("게시글을 불러올 수 없습니다.");
        }
        return;
    }

    // 게시글 상세 모달 열기
    if (target.closest(".check-detail-post")) {
        const postCard = target.closest(".post-8");
        const postId = postCard.dataset.postId;

        try {
            const post = await postService.getOne(postId);
            // console.log("게시글 :", post);
            postLayout.showDetail(post);

            const modal = document.getElementById("post-detail-modal");
            modal.style.display = "flex";
            modal.dataset.postId = postId;

            const commentContainer = modal.querySelector(".reply-10");
            commentContainer.innerHTML = "";
            await showComments(postId);

        } catch (err) {
            // alert("게시글을 불러올 수 없습니다.");
        }
        return;
    }


    // 게시글 상세 모달 닫기
    if (target.closest(".leply-7")) {
        const replytext = document.querySelectorAll(".replytext");
        const change = document.querySelector(".change");
        let hasText = false;
        replytext.forEach((t) => {
            if (t.value.trim() !== "") hasText = true;
        });

        if (hasText) {
            change.style.display = "flex";
        } else {
            document.querySelector(".reply").style.display = "none";
        }
        return;
    }

    // 계속 작성하기
    if (target.closest(".change .del-12")) {
        document.querySelector(".change").style.display = "none";
        if (!popup.classList.contains("active")) {
            popup.classList.add("active");
        }
        return;
    }

    // 작성하지 않고 나가기
    if (target.closest(".change .del-10")) {
        document.querySelector(".change").style.display = "none";

        document.querySelectorAll(".replytext").forEach((t) => (t.value = ""));
        document.querySelector(".reply").style.display = "none";

        writeTextarea.value = "";
        writeFiles.value = "";
        const previewContainer = document.querySelector(".popup-preview-inner");
        if (previewContainer) previewContainer.innerHTML = "";

        popup.classList.remove("active");
        return;
    }

    // 좋아요 (게시글)
    const likeBtn = target.closest(".like-btn");
    if (likeBtn) {
        const postId = likeBtn.dataset.postId;
        const isDetail = !!document.querySelector("#post-detail-modal .like-btn[data-post-id='" + postId + "']");
        const detailLikeBtn = document.querySelector("#post-detail-modal .like-btn[data-post-id='" + postId + "']");
        const listLikeBtn = document.querySelector(`.post-8[data-post-id="${postId}"] .like-btn`);

        const currentCountEl = likeBtn.querySelector(".post-25");
        const currentHeartEl = likeBtn.querySelector(".heart");
        let liked = likeBtn.dataset.liked === "true";
        let current = parseInt(currentCountEl.textContent) || 0;

        const success = liked
            ? await postService.removeLike(postId)
            : await postService.postLike(postId);

        if (!success) return;

        const newLiked = !liked;
        const newCount = liked ? current - 1 : current + 1;
        const newFill = newLiked ? "red" : "white";

        if (detailLikeBtn) {
            detailLikeBtn.dataset.liked = newLiked;
            detailLikeBtn.querySelector(".post-25").textContent = newCount;
            detailLikeBtn.querySelector(".heart").style.fill = newFill;
        }

        if (listLikeBtn) {
            listLikeBtn.dataset.liked = newLiked;
            listLikeBtn.querySelector(".post-25").textContent = newCount;
            listLikeBtn.querySelector(".heart").style.fill = newFill;
        }
    }


    // 신고 버튼 토글
    if (target.closest(".btn")) {
        const btn = target.closest(".btn");
        const reportMenu = btn.parentElement.querySelector(".report-1");
        if (reportMenu) {
            reportMenu.style.display = reportMenu.style.display === "flex" ? "none" : "flex";
        }
        return;
    }

    if (target.closest(".report-19")) {
        document.querySelector(".report-7").style.display = "none";
        return;
    }

    document.querySelectorAll(".report-1").forEach((r) => {
        if (!target.closest(".report-1") && !target.closest(".btn")) {
            r.style.display = "none";
        }
    });

    // 게시글 삭제 버튼
    if (target.closest(".delete-post-btn")) {
        const postCard = target.closest(".post-8");
        const postId = postCard ? postCard.dataset.postId : document.getElementById("post-detail-modal").dataset.postId;

        if (confirm("정말로 게시글을 삭제하시겠습니까?")) {
            const success = await postService.remove(postId);
            if (success) {
                alert("삭제되었습니다.");
                location.reload();
            }
        }
        return;
    }

    // 게시글 신고
    if (target.closest(".report-6")) {
        const postCard = target.closest(".post-8");
        const postId = postCard ? postCard.dataset.postId : document.getElementById("post-detail-modal").dataset.postId;

        const reportModal = document.querySelector(".report-7");

        if (postId && reportModal) {
            reportModal.dataset.postId = postId;
        }

        document.querySelectorAll(".report-1").forEach((r) => (r.style.display = "none"));
        reportModal.style.display = "flex";
        return;
    }

    if (target.closest(".report-17")) {
        const reportModal = document.querySelector(".report-7");
        const postId = reportModal.dataset.postId;

        if (!postId) {
            alert("게시글을 찾을 수 없습니다.");
            return;
        }

        try {
            const result = await postService.reportPost(postId);
            if (result === null)
                return;

            reportModal.style.display = "none";
        } catch (err) {
            alert(err.message);
            reportModal.style.display = "none";
        }
    }
});

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

// 취업률 모달창
function initEmploymentModal() {
    const employmentBtn = document.getElementById("employment-btn");
    const modal = document.getElementById("employment-modal");
    const closeBtn = document.querySelector(".employment-close");
    const tbody = document.getElementById("employment-table-body");

    if (!employmentBtn || !modal) return;

    // 열기
    employmentBtn.addEventListener("click", async (e) => {
        e.preventDefault();
        modal.style.display = "flex";
        document.body.style.overflow = "hidden";
        tbody.innerHTML = `<tr><td colspan="2">불러오는 중...</td></tr>`;

        try {
            const rows = await postService.employDataService();
            employmentLayout.setEmployData(rows);
        } catch (err) {
            console.error("취업률 데이터 로딩 실패:", err);
            tbody.innerHTML = `<tr><td colspan="2">데이터를 불러오는 중 오류가 발생했습니다.</td></tr>`;
        }
    });

    // 닫기
    closeBtn.addEventListener("click", () => {
        modal.style.display = "none";
        document.body.style.overflow = "";
    });

    modal.addEventListener("click", (e) => {
        if (e.target === modal) {
            modal.style.display = "none";
            document.body.style.overflow = "";
        }
    });
}

document.addEventListener("DOMContentLoaded", initEmploymentModal);
