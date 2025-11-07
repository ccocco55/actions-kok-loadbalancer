const service = (() => {
    const getBannerList = async (callback, page = 1) => {
        const response = await fetch(`/api/banner/${page}`);
        const bannerFileCriteriaDTO = await response.json();
        if(callback){
            callback(bannerFileCriteriaDTO);
        }

        if(response.ok) {
            console.log("현수막 이미지 존재");
        }else if(response.status === 404){
            console.log("현수막 이미지 없음");
        }else {
            const error = await response.text()
            console.log(error);
        }

        return bannerFileCriteriaDTO;
    }

    return {getBannerList: getBannerList}
})();