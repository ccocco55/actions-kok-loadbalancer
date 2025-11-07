// 댓글 불러오기
const showComments = async (postId) => {
    try {
        const comments = await commentService.getComments(postId);
        const commentContainer = document.querySelector("#post-detail-modal .reply-10");
        commentContainer.innerHTML = "";
        commentLayout.showCommentsList(comments);
    } catch (err) {
        console.error("댓글 불러오기 실패:", err);
        alert("댓글을 불러올 수 없습니다.");
    }
};

document.body.addEventListener("click", async (e) => {
    const target = e.target;
    const modal = document.getElementById("post-detail-modal");
    const postId = modal.dataset.postId;

    // 댓글 작성
    if (target.closest(".reply-14 .enter") || target.closest(".reply-mobile .enter")) {
        const modal = document.getElementById("post-detail-modal");
        const postId = modal.dataset.postId;
        const parent = target.closest(".reply-17");
        const textarea = parent.querySelector(".replytext");
        const content = textarea.value.trim();

        if (!content) {
            alert("댓글을 입력해주세요.");
            return;
        }

        try {
            await commentService.writeComment({
                postId: postId,
                commentContent: content,
            });

            textarea.value = "";

            // 댓글 새로고침
            const commentContainer = modal.querySelector(".reply-10");
            commentContainer.innerHTML = "";
            await showComments(postId);

        } catch (err) {
            console.error("댓글 작성 실패:", err);
            alert("댓글 작성 중 오류가 발생했습니다.");
        }
    }

    // 답글 작성
    if (target.closest(".reply-wrap .enter")) {
        const modal = document.getElementById("post-detail-modal");
        const postId = modal.dataset.postId;
        const replyBox = target.closest(".reply-wrap");
        const textarea = replyBox.querySelector(".replytext");
        const content = textarea.value.trim();
        const commentId = replyBox.dataset.commentId;

        if (!content) {
            alert("답글을 입력해주세요.");
            return;
        }

        try {
            await commentService.writeReply({
                commentId: commentId,
                replyContent: content,
                postId: postId
            });

            textarea.value = "";

            await showComments(postId);

        } catch (err) {
            console.error("답글 작성 실패:", err);
            alert("답글 작성 중 오류가 발생했습니다.");
        }
    }

    // 답글 보기 / 접기 버튼 토글
    if (target.closest(".show-replies-content")) {
        const commentContain = target.closest(".comment-contain");
        if (!commentContain) return;

        const nonShow = commentContain.querySelector(".non-show-replies-container");
        const show = commentContain.querySelector(".show-replies-container");
        const replyList = commentContain.querySelector(".reply-list");

        if (replyList.style.display === "none") {
            replyList.style.display = "block";
            nonShow.style.display = "none";
            show.style.display = "flex";
        } else {
            replyList.style.display = "none";
            nonShow.style.display = "flex";
            show.style.display = "none";
        }
    }

    // 답글 작성란 토글
    if (target.closest(".post-25.comment")) {
        const postCard = target.closest(".post-23");
        if (postCard) {
            const comments = postCard.closest(".post-8").querySelector(".comments");
            if (comments) {
                comments.style.display = (comments.style.display === "flex") ? "none" : "flex";
            }
        }
    }

    // 수정/삭제 토글
    if (target.closest(".delbtn")) {
        const menu = target.closest("button").querySelector(".delbtn-1");
        if (menu) {
            menu.style.display = menu.style.display === "block" ? "none" : "block";
        }
    }

    if (target.closest(".delbtn-0")) {
        const menu = target.closest("button").querySelector(".delbtn-2");
        if (menu) {
            menu.style.display = menu.style.display === "block" ? "none" : "block";
        }
    }

    // 댓글 삭제
    if (target.closest(".delete-comment-btn")) {
        const commentCard = target.closest(".post-8");
        const commentId = commentCard.dataset.commentId;

        if (confirm("정말로 댓글을 삭제하시겠습니까?")) {
            await commentService.deleteComment(commentId);
            alert("삭제되었습니다.");
            location.reload();
        }
    }

    // 대댓글 삭제
    if (target.closest(".delete-reply-btn")) {
        const btn = target.closest(".delete-reply-btn");
        const replyId = btn.dataset.replyId;

        if (confirm("정말로 답글을 삭제하시겠습니까?")) {
            const success = await commentService.deleteReply(replyId);
            if (success) {
                alert("삭제되었습니다.");
                location.reload();
            }
        }
    }

    // 댓글 수정
    if (target.closest(".update-comment-btn")) {
        document.querySelectorAll(".delbtn-1").forEach(el => el.style.display = "none");

        const commentCard = target.closest(".post-8");
        const commentId = commentCard.dataset.commentId;
        const contentEl = commentCard.querySelector(".post-16 .post-19 span");
        const originalContent = (contentEl.textContent || "").trim();

        commentCard.querySelector(".post-16").innerHTML = `
            <div class="reply-14">
                <div class="reply-15">
                    <div class="reply-16">
                        <div class="reply-17">
                            <textarea maxlength="2000" rows="1" class="reply-18 reply-edit-text">${originalContent}</textarea>
                            <p class="comment-update-submit" data-comment-id="${commentId}" style="cursor:pointer; color:#0066ff; font-weight:500;">수정</p>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }

    // 댓글 수정 완료
    if (target.closest(".comment-update-submit")) {
        const btn = target.closest(".comment-update-submit");
        const container = btn.closest(".reply-17");
        const textarea = container.querySelector(".reply-edit-text");
        const commentId = btn.dataset.commentId;
        const newContent = textarea.value.trim();

        if (!newContent) {
            alert("내용을 입력해주세요.");
            return;
        }

        const success = await commentService.updateComment(commentId, newContent);
        if (success) {
            alert("댓글이 수정되었습니다.");
            await showComments(postId);
        } else {
            alert("수정 중 오류가 발생했습니다.");
        }
    }

    // 대댓글 수정
    if (target.closest(".update-reply-btn")) {
        document.querySelectorAll(".delbtn-2").forEach(el => el.style.display = "none");

        const replyCard = target.closest(".reply-item");
        const replyId = replyCard.dataset.replyId;
        const contentEl = replyCard.querySelector(".detail-post-16 .post-19 span");
        const originalContent = (contentEl.textContent || "").trim();

        replyCard.querySelector(".detail-post-16").innerHTML = `
            <div class="reply-14">
                <div class="reply-15">
                    <div class="reply-16">
                        <div class="reply-17">
                            <textarea maxlength="2000" rows="1" class="reply-18 reply-edit-text">${originalContent}</textarea>
                            <p class="reply-update-submit" data-reply-id="${replyId}" style="cursor:pointer; color:#0066ff; font-weight:500;">수정</p>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }

    // 대댓글 수정 완료
    if (target.closest(".reply-update-submit")) {
        const btn = target.closest(".reply-update-submit");
        const container = btn.closest(".reply-17");
        const textarea = container.querySelector(".reply-edit-text");
        const replyId = btn.dataset.replyId;
        const newContent = textarea.value.trim();

        if (!newContent) {
            alert("내용을 입력해주세요.");
            return;
        }

        const success = await commentService.updateReply(replyId, newContent);
        if (success) {
            alert("답글이 수정되었습니다.");
            await showComments(postId);
        } else {
            alert("수정 중 오류가 발생했습니다.");
        }
    }
});