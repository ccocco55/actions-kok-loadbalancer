const companyService = (() => {
    const getList = async (page = 1, callback, searchCompanies = {}) => {
        let searchContext = "";

        const contexts = [];
        // push : 조건이 있는 검색 파라미터만 골라서 배열에 모으는 역할
        // encodeURIComponent : 검색 파라미터를 URL에 안전하게 넣기 위해 사용하는 함수
        if (searchCompanies.keyword && searchCompanies.keyword.trim() !== "") {
            contexts.push(`keyword=${encodeURIComponent(searchCompanies.keyword)}`);
        }
        if (searchCompanies.job && searchCompanies.job.trim() !== "") {
            contexts.push(`job=${encodeURIComponent(searchCompanies.job)}`);
        }
        if (searchCompanies.scale && searchCompanies.scale.trim() !== "") {
            contexts.push(`scale=${encodeURIComponent(searchCompanies.scale)}`);
        }

        if (contexts.length > 0) {
            searchContext = "?" + contexts.join("&");
        }

        // console.log(`검색 url: /api/company/${page}${searchContext}`);

        const response = await fetch(`/api/company/${page}${searchContext}`);
        const companiesCriteria = await response.json();
        if (callback) callback(companiesCriteria);
        return companiesCriteria;
    };

    return { getList : getList };
})();