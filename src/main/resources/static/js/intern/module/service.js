const internService = (() => {
    const getInternNotice = async (page = 1, keyword = "") => {
        const url = `/api/interns/${page}?keyword=${encodeURIComponent(keyword)}`;
        const response = await fetch(url);
        if (!response.ok) {
            if (response.status === 404) return { interns: [], criteria: {} };
            throw new Error(`서버 오류: ${response.status}`);
        }
        return await response.json();
    };
    return { getInternNotice };
})();