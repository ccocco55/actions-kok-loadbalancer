const memberService = (() => {
    const memberList = async (page, callback, keyword = "") => {

        const url = keyword
            ? `/api/member/list/${page}?keyword=${encodeURIComponent(keyword)}`
            : `/api/member/list/${page}`;

        const response = await fetch(url, {
            method:'GET'
        })

        const result = await response.json();


        if(callback){
            callback(result);
        }


        return result;
    }

    const memberDetail = async (id, callback) => {
        const response = await fetch(`/api/member/detail/${id}`, {
            method:'GET'
        })

        const result = await response.json();


        if(callback){
            callback(result);
        }

        return result;
    }
    return {memberList:memberList, memberDetail:memberDetail}
})();