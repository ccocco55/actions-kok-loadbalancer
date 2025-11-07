const experienceRegisterService = (() => {
    const register = async (data) => {
        const response = await fetch(`/api/enterprise-console/experience/create`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data),
        });

        if (!response.ok) {
            console.error("등록 실패: " + response.status);
            throw new Error("등록 실패");
        }

        await response.json();
    };

    // 체험 공고 수정
    const update = async (id, data) => {
        const response = await fetch(`/api/enterprise-console/experience/edit/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data),
        });

        if (!response.ok) {
            throw new Error("수정 실패: " + response.status);
        }
        return await response.json();
    };

    return {register:register, update:update};
})();
