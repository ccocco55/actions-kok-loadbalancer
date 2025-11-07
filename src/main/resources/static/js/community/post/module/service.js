const postService = (() => {
    // 게시글 전체 조회
    const getList = async (page = 1, callback) => {
        const response = await fetch(`/api/community/${page}`);
        const postsCriteria = await response.json();
        if (callback) callback(postsCriteria);
        return postsCriteria;
    };

    // 게시글 조회
    const getOne = async (id) => {
        const response = await fetch(`/api/community/post/${id}`);

        if (response.status === 403) {
            alert("일반 회원만 이용할 수 있습니다.");
            return null;
        }

        if (!response.ok) {
            console.error("게시글 조회 실패", response.status);
            return null;
        }

        return await response.json();
    };

    // 글쓰기
    const write = async (postContent, files = []) => {
        const formData = new FormData();
        formData.append("postContent", postContent);
        for (let i = 0; i < files.length; i++) {
            formData.append("files", files[i]);
        }

        const response = await fetch("/api/community", {
            method: "POST",
            body: formData
        });

        return response.json();
    };

    // 수정
    const update = async (postId, postContent, deleteFiles = [], files = []) => {
        const formData = new FormData();
        formData.append("postContent", postContent);
        deleteFiles.forEach(id => formData.append("deleteFiles", id));
        for (let i = 0; i < files.length; i++) {
            formData.append("files", files[i]);
        }

        const response = await fetch(`/api/community/${postId}`, {
            method: "POST",
            body: formData
        });

        if (!response.ok) {
            console.error("게시글 수정 실패", response.status);
            return null;
        }

        return await response.json();
    };

    // 삭제
    const remove = async (postId) => {
        const response = await fetch(`/api/community/${postId}`, { method: "DELETE" });
        return response.ok;
    };

    // 게시글 좋아요
    const postLike = async (postId) => {
        const postLikeDTO = { postId };
        const response = await fetch("/api/likes", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(postLikeDTO)
        });

        if (response.status === 403) {
            alert("일반 회원만 이용할 수 있습니다.");
            return null;
        }

        if (!response.ok) {
            const errorMessage = await response.text();
            console.error("좋아요 실패:", errorMessage);
            alert("이미 좋아요를 누른 게시글입니다.");
            return false;
        }

        return await response.json();
    };

    // 게시글 좋아요 취소
    const removeLike = async (postId) => {
        const response = await fetch(`/api/likes/${postId}`, {
            method: "DELETE"
        });
        return response.ok;
    };

    // 게시글 신고
    const reportPost = async (postId) => {
        const response = await fetch(`/api/report/${postId}`, { method: "POST" });

        const reportModal = document.querySelector(".report-7");

        if (response.status === 403) {
            alert("일반 회원만 이용할 수 있습니다.");
            reportModal.style.display = "none";
            return null;
        }

        const message = await response.text();

        if (!response.ok) {
            alert(message);
            return false;
        }

        alert(message);
        return true;
    };

    // 취업률 api 요청
    const employDataService = async () => {
        try {
            const response = await fetch(
                "https://openapi.gg.go.kr/Grduemplymtgenrlgdhl?KEY=079b6d57592844d18ca18ac7cfbba648&Type=json&pIndex=1&pSize=1000"
            );
            const data = await response.json();
            // return data.Grduemplymtgenrlgdhl[1].row;
            return data.Grduemplymtgenrlgdhl[1].row.filter(
                r => r.EMPLYMT_RT && r.EMPLYMT_RT > 0
            );
        } catch (err) {
            console.error("취업률 데이터 불러오기 실패:", err);
            throw err;
        }
    };

    return { getList : getList, getOne : getOne, write : write, update : update, remove : remove,
        postLike : postLike, removeLike : removeLike, reportPost : reportPost, employDataService : employDataService };

})();

