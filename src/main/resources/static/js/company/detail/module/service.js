const followService = (() => {

    const follow = async (companyId) => {
        const response = await fetch(`/api/follow/${companyId}`, {
            method: "POST"
        });

        const checkExistLogin = await response.json();
        if (checkExistLogin === false) {
            alert("일반 회원만 이용할 수 있습니다.");
            return false;
        }
        return true;
    };

    const unfollow = async (companyId) => {
        const response = await fetch(`/api/follow/${companyId}`, {
            method: "DELETE",
        });
        return response.ok;
    };

    const isFollowing = async (companyId) => {
        const response = await fetch(`/api/follow/${companyId}`);
        if (!response.ok) return false;
        return await response.json();
    };

    const getFollowerCount = async (companyId) => {
        const response = await fetch(`/api/follow/count/${companyId}`);
        if (!response.ok) return 0;
        return await response.json();
    };

    return { follow : follow, unfollow : unfollow, isFollowing : isFollowing, getFollowerCount : getFollowerCount};
})();

const companyNoticeService = (() => {

    const getExperienceNotices = async (companyId, page = 1, search = {}, callback) => {
        let experienceSearchContext = "";
        const experienceContexts = [];

        // push : 조건이 있는 검색 파라미터만 골라서 배열에 모으는 역할
        // encodeURIComponent : 검색 파라미터를 URL에 안전하게 넣기 위해 사용하는 함수
        if (search.keyword && search.keyword.trim() !== "") {
            experienceContexts.push(`keyword=${encodeURIComponent(search.keyword)}`);
        }

        if (search.category && search.category.trim() !== "") {
            experienceContexts.push(`category=${encodeURIComponent(search.category)}`);
        }

        if (experienceContexts.length > 0) {
            experienceSearchContext = "?" + experienceContexts.join("&");
        }

        const experienceNotice = `/api/company/${companyId}/experiences/${page}${experienceSearchContext}`;
        // console.log("체험 카테고리:", experienceNotice);
        const response = await fetch(experienceNotice);

        const result = await response.json();
        if (callback) callback(result);
        return result;
    };


    const getInternNotices = async (companyId, page = 1, search = {}, callback) => {
        let internSearchContext = "";
        const internContexts = [];

        // push : 조건이 있는 검색 파라미터만 골라서 배열에 모으는 역할
        // encodeURIComponent : 검색 파라미터를 URL에 안전하게 넣기 위해 사용하는 함수
        if (search.keyword && search.keyword.trim() !== "") {
            internContexts.push(`keyword=${encodeURIComponent(search.keyword)}`);
        }

        if (search.category && search.category.trim() !== "") {
            internContexts.push(`category=${encodeURIComponent(search.category)}`);
        }

        if (internContexts.length > 0) {
            internSearchContext = "?" + internContexts.join("&");
        }

        const internNotice = `/api/company/${companyId}/interns/${page}${internSearchContext}`;

        // console.log("인턴 카테고리:", internNotice);
        const response = await fetch(internNotice);
        const result = await response.json();
        if (callback) callback(result);
        return result;
    };

    return { getExperienceNotices : getExperienceNotices, getInternNotices : getInternNotices };
})();
