const commentService = (() => {

    // 댓글 목록
    const getComments = async (postId) => {
        const response = await fetch(`/api/comments/${postId}`);
        return response.json();
    };

    // 댓글 작성
    const writeComment = async (commentDTO) => {
        const response = await fetch(`/api/comments`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(commentDTO),
        });
        return response.json();
    };

    // 댓글 수정
    const updateComment = async (commentId, content) => {
        const response = await fetch(`/api/comments/${commentId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ commentContent: content }),
        });
        return response.ok;
    };

    // 댓글 삭제
    const deleteComment = async (commentId) => {
        const response = await fetch(`/api/comments/${commentId}`, {
            method: "DELETE",
        });
        return response.ok;
    };

    // 대댓글 목록
    const getReplies = async (commentId) => {
        const response = await fetch(`/api/replies/${commentId}`);
        return response.json();
    };

    // 대댓글 작성
    const writeReply = async (replyDTO) => {
        const response = await fetch(`/api/replies`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(replyDTO),
        });
        return response.json();
    };

    // 대댓글 수정
    const updateReply = async (replyId, content) => {
        const response = await fetch(`/api/replies/${replyId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ replyContent: content }),
        });
        return response.ok;
    };

    // 대댓글 삭제
    const deleteReply = async (replyId) => {
        const response = await fetch(`/api/replies/${replyId}`, {
            method: "DELETE",
        });
        return response.ok;
    };

    return { getComments : getComments, writeComment : writeComment, updateComment : updateComment, deleteComment : deleteComment,
        getReplies : getReplies, writeReply : writeReply, updateReply : updateReply, deleteReply : deleteReply};

})();