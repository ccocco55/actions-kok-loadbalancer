const adService = (() => {
    // 광고 등록
    const register = async (data) => {

        const formData = new FormData();

        // 일반 텍스트 데이터 추가
        formData.append("advertisementMainText", data.advertisementMainText);
        formData.append("advertisementSubText", data.advertisementSubText);
        formData.append("companyId", data.companyId);
        formData.append("advertiseStartDatetime", data.advertiseStartDatetime);
        formData.append("advertiseEndDatetime", data.advertiseEndDatetime);
        formData.append("paymentPrice", data.paymentPrice);

        if (document.querySelector("#add-background").files.length > 0) {
            formData.append("files", document.querySelector("#add-background").files[0]);
        }

        const response = await fetch(`/api/enterprise-console/ad/create`, {
            method: "POST",
            body: formData,
        });

        if (response.ok) {
            console.log("광고 등록 성공:", response.status);
            return await response.text();
        } else {
            console.error("광고 등록 실패:", response.status);
            throw new Error("광고 등록 실패");
        }
    };

    // 광고 수정
    const update = async (id, data) => {

        const formData = new FormData();

        formData.append("advertisementMainText", data.advertisementMainText);
        formData.append("advertisementSubText", data.advertisementSubText);
        formData.append("companyId", data.companyId);
        formData.append("advertiseStartDatetime", data.advertiseStartDatetime);
        formData.append("advertiseEndDatetime", data.advertiseEndDatetime);
        formData.append("paymentPrice", data.paymentPrice);

        const response = await fetch(`/api/enterprise-console/ad/edit/${id}`, {
            method: "PUT",
            body: formData,
        });

        if (!response.ok) {
            console.error("광고 수정 실패:", response.status);
            throw new Error("광고 수정 실패");
        }

        console.log("광고 수정 성공:", response.status);
        return await response.text();
    };

    return { register:register, update:update };
})();
