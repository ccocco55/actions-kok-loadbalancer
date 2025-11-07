const applicationInternService = (() => {
    const updateStatus = async (memberId, internNoticeId ,statusValue) => {
        const response = await fetch(`/api/enterprise-console/intern/applicant/${memberId}/status`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ requestInternStatus: statusValue, internNoticeId: internNoticeId })
        });

        if(response.ok) {
            console.log("상태변경 성공")
            window.location.href = `/enterprise-console/intern/application/${internNoticeId}/${memberId}`;
        }else if(response.status === 404){
            console.log("상태변경 실패")
        }else {
            const error = await response.text()
            console.log(error);
        }
    };

    const downloadResume = async (internNoticeId, memberId) => {
        const response = await fetch(`/api/enterprise-console/intern/${internNoticeId}/applications/files`, {
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