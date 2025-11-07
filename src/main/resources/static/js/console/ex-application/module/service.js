const applicationExperienceService = (() => {
    const updateStatus = async (memberId, experienceNoticeId ,statusValue) => {
        const response = await fetch(`/api/enterprise-console/experience/applicant/${memberId}/status`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ requestExperienceStatus: statusValue, experienceNoticeId: experienceNoticeId })
        });

        if(response.ok) {
            console.log("상태변경 성공")
            window.location.href = `/enterprise-console/experience/application/${experienceNoticeId}/${memberId}`;
        }else if(response.status === 404){
            console.log("상태변경 실패")
        }else {
            const error = await response.text()
            console.log(error);
        }
    };

    const downloadResume = async (experienceNoticeId, memberId) => {
        const response = await fetch(`/api/enterprise-console/experience/${experienceNoticeId}/applications/files`, {
            method: 'POST',
            body: JSON.stringify([memberId]),
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            }
        });

        if (!response.ok) {
            alert("이력서 파일을 찾을 수 없습니다.");
            return;
        }
        return response.json();
    };

    return { updateStatus:updateStatus, downloadResume:downloadResume };
})();