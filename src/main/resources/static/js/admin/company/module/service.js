const companyService = (() => {
    const companyList = async (page, callback, keyword = "") => {

        const url = keyword
            ? `/api/company/list/${page}?keyword=${encodeURIComponent(keyword)}`
            : `/api/company/list/${page}`;

        const response = await fetch(url, {
            method:'GET'
        })

        const result = await response.json();


        if(callback){
            callback(result);
        }


        return result;
    }

    const companyDetail = async (id, callback) => {
        const response = await fetch(`/api/company/detail/${id}`, {
            method:'GET'
        })

        const result = await response.json();


        if(callback){
            callback(result);
        }

        return result;
    }
    return {companyList:companyList, companyDetail:companyDetail}
})();