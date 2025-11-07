const service = (() => {
    const getEmployList = async (callback, page = 1, keyword = '') => {
        const response = await fetch(`/api/employ/${page}?keyword=${keyword}`);
        const adminInternNoticeListCriteriaDTO = await response.json();
        if(callback){
            callback(adminInternNoticeListCriteriaDTO);
        }

        if(response.ok) {
            // console.log("체험 게시글 존재");
        }else if(response.status === 404){
            // console.log("체험 게시글 없음");
        }else {
            const error = await response.text()
            console.log(error);
        }

        return adminInternNoticeListCriteriaDTO;
    }

    const getEmployDetail = async (callback, id = 1) => {
        const response = await fetch(`/api/employ/detail/${id}`);
        const adminInternNoticeDetailDTO = await response.json();
        if(callback){
            callback(adminInternNoticeDetailDTO);
        }

        if(response.ok) {
            console.log("상세 게시글 존재");
        }else if(response.status === 404){
            console.log("상세 게시글 없음");
        }else {
            const error = await response.text()
            console.log(error);
        }

        return adminInternNoticeDetailDTO;
    }

    const getEmployDetailList = async (callback, page = 1, id = 1) => {
        const response = await fetch(`/api/employ/detail/list/${id}/${page}`);
        const adminInternNoticeDetailCriteriaDTO = await response.json();
        if(callback){
            callback(adminInternNoticeDetailCriteriaDTO);
        }

        if(response.ok) {
            console.log("상세 목록 존재");
        }else if(response.status === 404){
            console.log("상세 목록 없음");
        }else {
            const error = await response.text()
            console.log(error);
        }

        return adminInternNoticeDetailCriteriaDTO;
    }

    return {getEmployList: getEmployList, getEmployDetail: getEmployDetail, getEmployDetailList: getEmployDetailList}
})();