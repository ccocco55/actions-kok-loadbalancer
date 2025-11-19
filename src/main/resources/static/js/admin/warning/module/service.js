const warningService = (() => {
    const warningList = async (features) => {

        const response = await fetch("https://distances-stream-tea-carb.trycloudflare.com/api/slang-check", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ feature: features })
        });

        const data = await response.json();

        return data;
    }

    const allPosts = async () => {
        const response = await fetch("/api/warning/all-posts", {
            method:'GET'

        })

        const result = await response.json();


        return result;
    }

    const postDetail = async (id, callback) => {
        const response = await fetch(`/api/warning/detail/${id}`, {
            method:'GET'

        })

        const result = await response.json();

        if(callback){
            callback(result);
        }

        return result;
    }


    const warningPostList = async (page, callback, ids = [], keyword) => {
        // ids 배열을 쿼리 문자열로 변환
        const idsQuery = ids.length > 0 ? ids.map(id => `ids=${id}`).join('&') : '';

        // keyword 쿼리
        const keywordQuery = keyword ? `keyword=${encodeURIComponent(keyword)}` : '';

        // ids 먼저, keyword 마지막으로 합치기
        let query = [idsQuery, keywordQuery].filter(q => q !== '').join('&');
        if(query) query = '?' + query;

        const url = `/api/warning/list/${page}${query}`;

        const response = await fetch(url, { method: 'GET' });
        const result = await response.json();

        if(callback) callback(result);
        return result;
    }


    const deletePost = async (id) => {
        const response = await fetch(`/api/warning/delete/${id}`, {
            method: "POST"
        });

        if (response.ok) {
            console.log("삭제 완료");
        } else {
            console.error("삭제 실패", response.status);
        }
    };

    const changeStatusPost = async (id) => {
        const response = await fetch(`/api/warning/change-status/${id}`, {
            method: "POST"
        });

        if (response.ok) {
            console.log("삭제 완료");
        } else {
            console.error("삭제 실패", response.status);
        }
    };





    return { warningList:warningList, allPosts: allPosts, postDetail:postDetail, warningPostList:warningPostList, deletePost:deletePost, changeStatusPost:changeStatusPost }
})();