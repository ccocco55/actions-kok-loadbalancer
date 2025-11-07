const service = (() => {
    const getReportList = async (callback, page = 1) => {
        const response = await fetch(`/api/notify/post/${page}`);
        const adminReportCriteriaDTO = await response.json();
        if(callback){
            callback(adminReportCriteriaDTO);
        }

        if(response.ok) {
            console.log("신고 게시글 존재");
        }else if(response.status === 404){
            console.log("신고 게시글 없음");
        }else {
            const error = await response.text()
            console.log(error);
        }

        return adminReportCriteriaDTO;
    }

    const getReportDetail = async (callback, id = 1) => {
        const response = await fetch(`/api/notify/post/detail/${id}`);
        const adminReportDTO = await response.json();
        if(callback){
            callback(adminReportDTO);
        }

        if(response.ok) {
            console.log("신고 상세 게시글 존재");
        }else if(response.status === 404){
            console.log("신고 상세 게시글 없음");
        }else {
            const error = await response.text()
            console.log(error);
        }

        return adminReportDTO;
    }

    return {getReportList: getReportList, getReportDetail: getReportDetail}
})();