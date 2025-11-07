const service = (() => {
    const getAdvertisementList = async (callback, page = 1, keyword = '', category = '') => {
        const response = await fetch(`/api/advertise/${page}?keyword=${keyword}&category=${category}`);
        const AdminAdvertisementCriteriaDTO = await response.json();
        if(callback){
            callback(AdminAdvertisementCriteriaDTO);
        }

        if(response.ok) {
            console.log("광고 게시글 존재");
        }else if(response.status === 404){
            console.log("광고 게시글 없음");
        }else {
            const error = await response.text()
            console.log(error);
        }

        return AdminAdvertisementCriteriaDTO;
    }

    const getAdvertisementDetail = async (callback, id = 1) => {
        const response = await fetch(`/api/advertise/detail/${id}`);
        const adminAdvertisementDTO = await response.json();
        if(callback){
            callback(adminAdvertisementDTO);
        }

        if(response.ok) {
            console.log("상세 게시글 존재");
        }else if(response.status === 404){
            console.log("상세 게시글 없음");
        }else {
            const error = await response.text()
            console.log(error);
        }

        return adminAdvertisementDTO;
    }

    return {getAdvertisementList: getAdvertisementList, getAdvertisementDetail: getAdvertisementDetail}
})();