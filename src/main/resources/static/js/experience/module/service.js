const experienceService = (() => {
    const getExperienceNotice = async (page = 1, keyword = "") => {
        const url = `/api/experiences/${page}?keyword=${encodeURIComponent(keyword)}`;
        const response = await fetch(url);
        if (!response.ok) {
            if (response.status === 404) return { experiences: [], criteria: {} };
            throw new Error(`서버 오류: ${response.status}`);
        }
        return await response.json();
    };

    const pay = async (money, requestExperienceDTO) => {
        try {
            const response = await Bootpay.requestPayment({
                application_id: "68f1b34fb96306619f55bb95",
                price: money,
                order_name: "체험 신청 비용",
                order_id: "TEST_ORDER_ID",
                pg: "토스",
                method: "토스",
                tax_free: 0,
                user: {
                    id: "id",
                    username: "이름",
                    phone: "01000000000",
                    email: "test@test.com",
                },
                items: [
                    {
                        id: "item_id",
                        name: "체험 신청 비용",
                        qty: 1,
                        price: money,
                    },
                ],
                extra: {
                    open_type: "iframe",
                    card_quota: "0,2,3",
                    escrow: false,
                },
            });
            switch (response.event) {
                case "issued":
                    // 가상계좌 입금 완료 처리
                    break;
                case "done":
                    console.log(response);
                    // 결제 완료 처리

                    await fetch(`/api/experiences/request`, {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify(requestExperienceDTO)
                    });

                    const result = await response.text(); // 또는 response.json()
                    console.log("서버 응답:", result);

                    alert("결제 완료!");

                    break;
                case "confirm": //payload.extra.separately_confirmed = true; 일 경우 승인 전 해당 이벤트가 호출됨
                    console.log(response.receipt_id);
                    /**
                     * 1. 클라이언트 승인을 하고자 할때
                     * // validationQuantityFromServer(); //예시) 재고확인과 같은 내부 로직을 처리하기 한다.
                     */
                    const confirmedData = await Bootpay.confirm(); //결제를 승인한다
                    if (confirmedData.event === "done") {
                        //결제 성공
                    }

                    /**
                     * 2. 서버 승인을 하고자 할때
                     * // requestServerConfirm(); //예시) 서버 승인을 할 수 있도록  API를 호출한다. 서버에서는 재고확인과 로직 검증 후 서버승인을 요청한다.
                     * Bootpay.destroy(); //결제창을 닫는다.
                     */

                    alert("결제 confirm!");
                    break;
                default:
                    console.log("결제 상태:", response.event);
                    break;
            }
        } catch (e) {
            // 결제 진행중 오류 발생
            // e.error_code - 부트페이 오류 코드
            // e.pg_error_code - PG 오류 코드
            // e.message - 오류 내용
            console.log(e.message);
            switch (e.event) {
                case "cancel":
                    // 사용자가 결제창을 닫을때 호출
                    console.log(e.message);
                    break;
                case "error":
                    // 결제 승인 중 오류 발생시 호출
                    console.log(e.error_code);
                    break;
            }
        }
    };
    return { getExperienceNotice:getExperienceNotice, pay:pay };
})();