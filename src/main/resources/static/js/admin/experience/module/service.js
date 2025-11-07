const service = (() => {
    const getExperience = async (callback, page = 1, keyword = '') => {
        const response = await fetch(`/api/experience/${page}?keyword=${keyword}`);
        const adminExperienceListDTO = await response.json();
        if(callback){
            callback(adminExperienceListDTO);
        }

        if(response.ok) {
            // console.log("체험 게시글 존재");
        }else if(response.status === 404){
            // console.log("체험 게시글 없음");
        }else {
            const error = await response.text()
            console.log(error);
        }

        return adminExperienceListDTO;
    }

    const getExperienceDetail = async (callback, id = 1) => {
        const response = await fetch(`/api/experience/detail/${id}`);
        const adminExperienceDTO = await response.json();
        if(callback){
            callback(adminExperienceDTO);
        }

        if(response.ok) {
            console.log("상세 게시글 존재");
        }else if(response.status === 404){
            console.log("상세 게시글 없음");
        }else {
            const error = await response.text()
            console.log(error);
        }

        return adminExperienceDTO;
    }

    const getExperienceDetailList = async (callback, page = 1, id = 1) => {
        const response = await fetch(`/api/experience/detail/list/${id}/${page}`);
        const adminExperienceDetailDTO = await response.json();
        if(callback){
            callback(adminExperienceDetailDTO);
        }

        if(response.ok) {
            console.log("상세 목록 존재");
        }else if(response.status === 404){
            console.log("상세 목록 없음");
        }else {
            const error = await response.text()
            console.log(error);
        }

        return adminExperienceDetailDTO;
    }

    return {getExperience: getExperience, getExperienceDetail: getExperienceDetail, getExperienceDetailList: getExperienceDetailList}
})();