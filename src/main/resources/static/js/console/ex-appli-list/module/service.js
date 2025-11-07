const experienceDatailService = (() => {
    // 목록
    const getList = async (experienceNoticeId, page, status) => {
        const response = await fetch(`/api/enterprise-console/experience/applicate-list/${experienceNoticeId}/${page}?status=${status}`);
        const data = await response.json();

        if (response.ok) {
            console.log("지원자 목록 존재")
            console.log(data)
        } else if (response.status === 404) {
            console.log("지원자 목록 없음")
        } else {
            const error = await response.text()
            console.log(error);
        }

        return data;
    }

    const updateStatus = async (requestId, statusValue) => {
        try {
            const response = await fetch(`/api/enterprise-console/experience/status/${requestId}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    id: requestId,
                    requestExperienceStatus: statusValue
                })
            });

            if (response.ok) {
                console.log("상태 변경 성공");
                return true;
            } else {
                const err = await response.text();
                console.log("상태 변경 실패", err);
                return false;
            }
        } catch (error) {
            console.error("서버 통신 오류:", error);
            return false;
        }
    };

    const downLoad = async (experienceNoticeId, memberIdList) => {
        const response = await fetch(`/api/enterprise-console/experience/${experienceNoticeId}/applications/files`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(memberIdList)
        });

        if (!response.ok) {
            console.error("다운로드 실패", response.status);
        }

        const urls = await response.json();
        return urls;
    }

    return {getList:getList, updateStatus:updateStatus, downLoad:downLoad}
})();


