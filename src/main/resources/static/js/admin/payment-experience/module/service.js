const service = (() => {
    const getPaymentExperienceList = async (callback, page = 1, keyword = '', category = '') => {
        const response = await fetch(`/api/payment/experience/${page}?keyword=${keyword}&category=${category}`);
        const adminPaymentExperienceCriteriaDTO = await response.json();
        if(callback){
            callback(adminPaymentExperienceCriteriaDTO);
        }

        if(response.ok) {
            console.log("체험 결제 존재");
        }else if(response.status === 404){
            console.log("체험 결제 없음");
        }else {
            const error = await response.text()
            console.log(error);
        }

        return adminPaymentExperienceCriteriaDTO;
    }

    return {getPaymentExperienceList: getPaymentExperienceList}
})();