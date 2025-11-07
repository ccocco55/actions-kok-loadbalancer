const adNoticeService = (() => {
    // 목록
    const getList = async (companyId, page, keyword, callback) => {
        const response = await fetch(`/api/enterprise-console/ad/list/${companyId}/${page}?keyword=${keyword ?? ""}`);
        const data = await response.json();

        if (response.ok) {
            console.log("광고 존재")
            console.log(data)
        } else if (response.status === 404) {
            console.log("광고 없음")
        } else {
            const error = await response.text()
            console.log(error);
        }

        if (callback) {
            callback(data);
        }

        return data;
    }

    const updateAdStatus = async (noticeId, statusValue) => {
        const data = { id: noticeId, advertisementStatus: statusValue };

        const response = await fetch(`/api/enterprise-console/ad/${noticeId}/status`, {
            method:"PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        if(response.ok) {
            console.log("상태변경 성공")
        }else if(response.status === 404){
            console.log("상태변경 실패")
        }else {
            const error = await response.text()
            console.log(error);
        }

        return data;
    };

    const deleteAd = async (id) => {
        try {
            const response = await fetch(`/api/enterprise-console/ad/${id}`, {
                method: "DELETE",
            });

            if (!response.ok) {
                throw new Error("삭제 요청 실패");
            }

            const result = await response.text(); // "success"
            return result;
        } catch (err) {
            console.error("광고 삭제 중 오류:", err);
            return null;
        }
    };

    return {getList:getList, updateAdStatus:updateAdStatus, deleteAd:deleteAd}
})();