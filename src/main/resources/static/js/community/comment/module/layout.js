const commentLayout = (() => {
    const showCommentsList = (comments) => {
        const commentContainer = document.querySelector(".reply-10");
        let text = ``;

        comments.forEach((comment) => {
            text += `
            <div class="post-8" data-comment-id="${comment.id}" style="padding:8px; border-bottom:none; gap:5px;">

                <!-- 댓글 헤더 -->
                <div class="post-9">
                    <div>
                        <img alt="image" width="25" height="25"
                             src="${comment.memberProfileUrl || '/images/member/profile.png'}"
                             style="border-radius:999px; max-height:25px; max-width:40px; min-height:40px; min-width:40px; object-fit:contain; background-color: #ccc;">
                    </div>
                    <div class="post-10">
                        <div class="post-11">
                            <div class="post-12">
                                <p class="post-13" style="font-size:14px;">${comment.userName}</p>
                            </div>
                        </div>
                        <div class="post-14">
                            <p class="post-15" style="font-size:14px;">${comment.jobName || '-'}</p>
                            <p class="post-15" style="font-size:14px;">${comment.relativeDate}</p>
                        </div>
                    </div>
                    <!-- 댓글 삭제 -->
                    ${comment.owner ? `
                    <div class="delete">
                        <button class="post-28" style="width:70px; color:#e0e0e0; font-size:15px; font-weight:500;">
                            <svg class="delbtn" fill="currentColor" height="20" width="20">
                                <path d="M12 10.5c-.83 0-1.5.67-1.5 
                                         1.5s.67 1.5 1.5 1.5 
                                         1.5-.67 1.5-1.5-.67-1.5-1.5-1.5
                                         m0-4c.83 0 1.5-.67 1.5-1.5S12.83 3 12 3
                                         s-1.5.67-1.5 1.5S11.17 6.5 12 6.5
                                         m0 11c-.83 0-1.5.67-1.5 1.5s.67 1.5 
                                         1.5 1.5 1.5-.67 1.5-1.5
                                         -.67-1.5-1.5-1.5"></path>
                            </svg>
                            <div class="delbtn-1">
                                <div class="report-2">
                                    <div class="report-3">
                                        <div class="report-4">
                                            <div class="report-5">
                                                <p class="delete-comment-btn">댓글 삭제</p>
                                                <br>
                                                <p class="update-comment-btn">댓글 수정</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </button>
                    </div>`:``}
                </div>

                <!-- 댓글 본문 -->
                <div class="post-16" style="gap:8px;">
                    <div class="post-17">
                        <div class="post-18">
                            <div class="post-19" style="font-size:14px; white-space:normal;">
                                <span>${comment.commentContent}</span>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 답글 달기 버튼 -->
                <div class="detail-post-20">
                    <div class="post-21">
                        <div class="post-22">
                            <button class="post-23">
                                <p class="post-25 comment">답글 달기</p>
                            </button>
                        </div>
                    </div>
                </div>

                <!-- 답글 작성 -->
                <div class="comments">
                    <div class="reply-32">
                        <svg class="enter icons" fill="currentColor" height="20" width="20">
                            <path d="M5 3.2a.8.8 0 0 1 .8.8v7A3.2 3.2 0 0 0 9 
                                     14.2h8.069l-3.635-3.634a.8.8 0 1 1 
                                     1.132-1.131l5 5a.8.8 0 0 1 0 
                                     1.13l-5 5a.8.8 0 1 1-1.132-1.13L17.07 
                                     15.8H9A4.8 4.8 0 0 1 4.2 
                                     11V4a.8.8 0 0 1 .8-.8"></path>
                        </svg>
                    </div>
                    <div class="comment-text">
                        <div class="reply-15">
                            <div class="reply-16">
                                <div class="reply-17 reply-wrap" data-comment-id="${comment.id}">
                                    <textarea maxlength="2000" placeholder="답글을 남겨보세요." rows="1" class="reply-18 replytext"></textarea>
                                    <svg class="enter reply-submit" data-comment-id="${comment.id}" fill="currentColor" height="24" width="24">
                                        <path d="M9.434 6.435a.8.8 0 0 1 
                                                 1.132 0l5 5a.8.8 0 0 1 0 
                                                 1.13l-5 5a.8.8 0 1 1-1.132-1.13L13.87 
                                                 12 9.434 7.566a.8.8 0 0 1 0-1.131"></path>
                                    </svg>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            `;

            if (comment.replies && comment.replies.length > 0) {
                text += `
                <div class="comment-contain">
                    <div class="non-show-replies-container" style="display:flex; gap:5px;">
                        <svg color="foregrounds.neutral.tertiary" fill="currentColor" height="24" role="img" width="24" viewBox="0 0 24 24">
                            <path clip-rule="evenodd" d="M6.434 9.435a.8.8 0 0 1 1.132 0L12 13.869l4.434-4.434a.8.8 0 1 1 1.132 1.13l-5 5a.8.8 0 0 1-1.132 0l-5-5a.8.8 0 0 1 0-1.13" fill-rule="evenodd"></path>
                        </svg>
                        <button class="show-replies-content">답글 ${comment.totalReplyCount}개</button>
                    </div>
                    <div class="show-replies-container" style="display:none; gap:5px;">
                        <svg color="foregrounds.neutral.tertiary" fill="currentColor" height="24" role="img" width="24" viewBox="0 0 24 24">
                            <path clip-rule="evenodd" d="M17.566 14.566a.8.8 0 0 0 0-1.132l-5-5a.8.8 0 0 0-1.132 0l-5 5a.8.8 0 0 0 1.132 1.132L12 10.13l4.434 4.435a.8.8 0 0 0 1.132 0" fill-rule="evenodd"></path>
                        </svg>
                        <button class="show-replies-content">답글 닫기</button>
                    </div>
                    <div class="reply-list" style="display:none">
                `;

                comment.replies.forEach(reply => {
                    text += `
                        <div class="post-13 reply-item" style="padding:8px; border-bottom:none; gap:5px;" data-reply-id="${reply.id}">
                            <div class="post-9">
                                <div>
                                    <img alt="image" width="25" height="25"
                                         src="${reply.memberProfileUrl || '/images/member/profile.png'}"
                                         style="border-radius:999px; max-height:25px; max-width:40px; background-color: #ccc;">
                                </div>
                                <div class="post-10">
                                    <div class="post-11">
                                        <div class="post-12">
                                            <p class="post-13" style="font-size:14px;">${reply.userName}</p>
                                        </div>
                                    </div>
                                    <div class="post-14">
                                        <p class="post-15" style="font-size:14px;">${reply.jobName || '-'}</p>
                                        <p class="post-15" style="font-size:14px;">${reply.relativeDate}</p>
                                    </div>
                                </div>
                                ${reply.owner ? `
                                <div class="delete">
                                    <button class="delete-2" style="color:#e0e0e0; font-size:15px; font-weight:500;">
                                        <svg class="delbtn-0" fill="currentColor" height="20" width="20">
                                            <path d="M12 10.5c-.83 0-1.5.67-1.5 
                                                     1.5s.67 1.5 1.5 1.5 1.5-.67 
                                                     1.5-1.5-.67-1.5-1.5-1.5
                                                     m0-4c.83 0 1.5-.67 1.5-1.5S12.83 3 12 3
                                                     s-1.5.67-1.5 1.5S11.17 6.5 12 6.5
                                                     m0 11c-.83 0-1.5.67-1.5 1.5s.67 1.5 
                                                     1.5 1.5 1.5-.67 1.5-1.5
                                                     -.67-1.5-1.5-1.5"></path>
                                        </svg>
                                        <div class="delbtn-2">
                                            <div class="report-2">
                                                <div class="report-3">
                                                    <div class="report-4">
                                                        <div class="report-5">
                                                            <p class="delete-reply-btn" data-reply-id="${reply.id}">답글 삭제</p>
                                                            <br>
                                                            <p class="update-reply-btn" data-reply-id="${reply.id}">답글 수정</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </button>
                                </div>`:``}
                            </div>
                            <div class="detail-post-16" style="gap:8px;">
                                <div class="post-17">
                                    <div class="post-18">
                                        <div class="post-19" style="font-size:14px; white-space:normal;">
                                            <span>${reply.replyContent}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    `;
                });

                text += `
                    </div> 
                </div> 
                `;
            }

            text += `
            </div> 
            `;
        });

        commentContainer.innerHTML += text;
    };

    return { showCommentsList : showCommentsList };
})();
