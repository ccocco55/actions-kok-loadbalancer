const experienceNoticeService = (() => {
    // 목록
    const getList = async (page, status, keyword, callback) => {
        const response = await fetch(`/api/enterprise-console/experience/list/${page}?status=${status ?? ""}&keyword=${keyword ?? ""}`);
        const data = await response.json();

        if (callback) {
            setTimeout(() => {
                callback(data);
            }, 1000)
        }

        if (response.ok) {
            console.log("공고 존재")
            console.log(data)
        } else if (response.status === 404) {
            console.log("공고 없음")
        } else {
            const error = await response.text()
            console.log(error);
        }

        return data;
    }

    const updateExperienceStatus = async (noticeId, statusValue) => {
        const data = { id: noticeId, experienceNoticeStatus: statusValue };

        const response = await fetch(`/api/enterprise-console/experience/${noticeId}/status`, {
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

    // 삭제
    const deleteExperience = async (id) => {
        const response = await fetch(`/api/enterprise-console/experience/${id}`, {
            method: "DELETE",
        });

        if (!response.ok) {
            console.log("삭제 실패")
        }

        const result = await response.text();
        console.log("삭제 성공:", result);
        return result; // "success"
    };


    return {getList:getList, updateExperienceStatus:updateExperienceStatus, deleteExperience:deleteExperience}
})();


