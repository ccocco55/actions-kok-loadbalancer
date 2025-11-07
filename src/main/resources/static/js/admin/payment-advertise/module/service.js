const service = (() => {
    const getPaymentAdvertiseList = async (callback, page = 1, keyword = '', category = '') => {
        const response = await fetch(`/api/payment/advertise/${page}?keyword=${keyword}&category=${category}`);
        const adminPaymentAdvertiseCriteriaDTO = await response.json();
        if(callback){
            callback(adminPaymentAdvertiseCriteriaDTO);
        }

        if(response.ok) {
            console.log("광고 결제 존재");
        }else if(response.status === 404){
            console.log("광고 결제 없음");
        }else {
            const error = await response.text()
            console.log(error);
        }

        return adminPaymentAdvertiseCriteriaDTO;
    }

    return {getPaymentAdvertiseList: getPaymentAdvertiseList}
})();