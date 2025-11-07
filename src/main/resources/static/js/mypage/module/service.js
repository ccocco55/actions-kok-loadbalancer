const myPageService = (() => {
    // 프로필 편집 누르면 정보 가져오기
    const getProfile=async ()=>{
        const profile=await fetch(`/api/mypage/profile-load`);
        const response=await profile.json();

        return response;
    }
    // 게시글 목록
    const getPostsList=async (page=1, keyword="")=>{
        const list=await fetch(`/api/mypage/post-list`);
        const response=await list.json();
        // console.log(response);

        return response;
    }
    // 게시글 상세 불러오기
    const getPostDetail=async (id)=>{
        const postDetail=await fetch(`/api/community/post/${id}`);
        const response=await postDetail.json();
        
        return response;
    }
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

    // 저장 체험 목록 불러오기
    const getSavedExperienceList = async () => {
        const request = await fetch (`/api/mypage/saved-exp`);
        const response = await request.json();

        return response;
    };

    // 저장 인턴 목록 불러오기
    const getSavedInternList = async () => {
        const request = await fetch (`/api/mypage/saved-int`);
        const response = await request.json();

        return response;
    };
    
    // 체험 목록 불러오기
    const getExperienceList = async () => {
        const request = await fetch (`/api/mypage/request-list`);
        const response = await request.json();

        return response;
    };

    // 지원 취소-체험
    const deleteRequestExperience=async (requestId)=>{
        const response=await fetch(`/api/mypage/delete-request/${requestId}`,{
            method: "DELETE"
        });

        return response.ok;
    }

    // 지원 취소-인턴
    const deleteRequestIntern=async (requestId)=>{
        const response=await fetch(`/api/mypage/delete-intern/${requestId}`,{
            method: "DELETE"
        });

        return response.ok;
    }

    // 인턴 목록 불러오기
    const getInternList = async () => {
        const request = await fetch (`/api/mypage/intern-list`);
        const response = await request.json();

        return response;
    };
    // 인턴 지원 취소하기
    const deleteInternRequest=async ()=>{
        const request=await fetch(`/api/`)
    }
    // 결제 목록 불러오기
    const getPaymentList = async () => {
        const request = await fetch (`/api/mypage/payment-list`);
        const response = await request.json();

        return response;
    };

    const profileUpdate=async (form)=>{
        const response = await fetch(`/api/mypage/profile-update`,{
            method: "POST",
            body: form
        });
        console.log(response);
        return response.ok;
    }

    const loadStorage=async ()=>{
        const request=await fetch(`/api/member/storage/load`);
        const responsePre=await request.json();
        const response=responsePre;

        console.log("서비스 response: ", response);
        console.log(typeof response);

        return response;
    }

    const deleteStorage=async (fileId)=>{
        const response=await fetch(`/api/member/storage/delete?fileId=${fileId}`,{
            method: "DELETE"
        });

        return response.ok;
    }

    const saveStorage=async (files)=>{
        const response=await fetch(`/api/member/storage/save?files=${files}`, {
            method: "POST"
        });

        return response.ok;
    }

    const profileDelete=async ()=>{
        const response=await fetch(`/api/mypage/profile-delete`, {
            method: "POST"
        });
        return response.ok;
    }
    return { getProfile:getProfile, getPostsList:getPostsList, getPostDetail:getPostDetail, getExperienceList:getExperienceList, getComments:getComments, writeComment:writeComment, updateComment:updateComment, deleteComment:deleteComment,
    getReplies:getReplies, writeReply:writeReply, updateReply:updateReply, deleteReply:deleteReply, getInternList:getInternList, getPaymentList:getPaymentList,
    getSavedExperienceList:getSavedExperienceList, getSavedInternList:getSavedInternList, deleteRequestExperience:deleteRequestExperience, deleteRequestIntern:deleteRequestIntern,
    profileUpdate:profileUpdate, loadStorage:loadStorage, deleteStorage:deleteStorage, saveStorage:saveStorage, profileDelete:profileDelete};
})();
