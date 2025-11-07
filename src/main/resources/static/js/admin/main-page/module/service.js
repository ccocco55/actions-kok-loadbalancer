const service = (() => {
    const getMainPage = async (callback) => {
        const response = await fetch(`/api/mainPage`);
        const adminMainPageDTO = await response.json();
        if(callback){
            callback(adminMainPageDTO);
        }

        if(response.ok) {
            console.log("메인 페이지 존재");
        }else if(response.status === 404){
            console.log("메인 페이지 없음");
        }else {
            const error = await response.text()
            console.log(error);
        }

        return adminMainPageDTO;
    }

    const getChart = async (callback) => {
        const response = await fetch(`/api/mainPage/chart`);
        const chartDTO = await response.json();
        if(callback){
            callback(chartDTO);
        }

        if(response.ok){
            console.log("차트 존재");
        } else if(response.status === 404){
            console.log("차트 없음");
        } else {
            const error = await response.text()
            console.log(error);
        }

        return chartDTO;
    }


    return {getMainPage: getMainPage, getChart: getChart}
})();