const layout = (() => {
    const showAverage = (adminMainPageDTO) => {
        const averageContainer = document.getElementById("row");
        let text = ``;

        text += `
            <div class="col-12 col-md-6 col-lg-3">
                <div class="access-data data-member-page">
                    <div class="title">[회원] 평균 체험 지원 수</div>
                    <div class="value">
                        <span class="icon"><i class="mdi mdi-file-send-outline"></i></span>${adminMainPageDTO.memberExperienceRequestAvg}<span class="unit">회</span>                                                          
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-6 col-lg-3">
                <div class="access-data data-member-page">
                    <div class="title">[회원] 평균 인턴 지원 수</div>
                    <div class="value">
                        <span class="icon"><i class="mdi mdi-file-send-outline"></i></span>${adminMainPageDTO.memberInternRequestAvg}<span class="unit">회</span>                                                          
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-6 col-lg-3">
                <div class="access-data data-non-member-page">
                    <div class="title">[기업회원] 평균 체험 모집 수</div>
                    <div class="value">
                        <span class="icon"><i class="mdi mdi-file-send-outline"></i></span>${adminMainPageDTO.companyExperienceNoticeAvg}<span class="unit">회</span>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-6 col-lg-3">
                <div class="access-data data-non-member-page">
                    <div class="title">[기업회원] 평균 인턴 모집 수</div>
                    <div class="value">
                        <span class="icon"><i class="mdi mdi-file-send-outline"></i></span>${adminMainPageDTO.companyInternNoticeAvg}<span class="unit">회</span>
                    </div>
                </div>
            </div>
        `;
        averageContainer.innerHTML = text;
    }


    const showTotal = (adminMainPageDTO) => {
        const totalContainer = document.getElementById("box-body");
        let boxBodyText = ``;

        boxBodyText += `
            <table class="user-price-table justify-table">
                <thead>
                    <tr>
                        <th></th>
                        <th>일반회원</th>
                        <th></th>
                        <th>기업회원</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="td-class" rowspan="3">
                            <p>지원</p>
                            <p>·</p>
                            <p>모집</p>
                        </td>
                        <td class="td-value value-new">${adminMainPageDTO.memberExperienceRequestTotal}</td>
                        <td class="td-field">체험</td>
                        <td class="td-value value-new">${adminMainPageDTO.companyExperienceNoticeTotal}</td>
                    </tr>
                    <tr>
                        <td class="td-value value-revisit">${adminMainPageDTO.memberInternRequestTotal}</td>
                        <td class="td-field">인턴</td>
                        <td class="td-value value-revisit">${adminMainPageDTO.companyInternNoticeTotal}</td>
                    </tr>
                    <tr>
                        <td class="td-value value-total">${adminMainPageDTO.memberRequestTotal}</td>
                        <td class="td-field">전체</td>
                        <td class="td-value value-total">${adminMainPageDTO.companyNoticeTotal}</td>
                    </tr>
                    <tr>
                        <td class="td-divider" colspan="4"></td>
                    </tr>
                </tbody>
                <tbody>
                    <tr>
                        <td class="td-class" rowspan="2">결제</td>
                        <td class="td-value value-new">${adminMainPageDTO.memberPaymentTotalText}</td>
                        <td class="td-field">체험비</td>
                        <td class="td-value value-new">-</td>
                    </tr>
                    <tr>
                        <td class="td-value value-revisit">-</td>
                        <td class="td-field">광고비</td>
                        <td class="td-value value-revisit">${adminMainPageDTO.companyPaymentTotalText}</td>
                    </tr>
                    <tr>
                        <td class="td-divider" colspan="3"></td>
                    </tr>
                </tbody>
            </table>
        `;
        totalContainer.innerHTML = boxBodyText;
    }

    return {showAverage: showAverage, showTotal: showTotal};
})();


// 차트
const date = new Date();
const year = date.getFullYear();
const month = date.getMonth() + 1;
const preMonth = month - 1 > 0 ? month - 1 : month + 11;
const preMonth1 = preMonth - 1 > 0 ? preMonth - 1 : preMonth + 11;
const preMonth2 = preMonth1 - 1 > 0 ? preMonth1 - 1 : preMonth1 + 11;

google.charts.load('current', {'packages':['corechart']});

function drawChart(chartDTO) {

    // 첫 번째 차트
    var firstData = google.visualization.arrayToDataTable([
        ['month', '(회원)체험 신청', '(기업)체험 공고'],
        [preMonth2 + "월",  chartDTO[3].monthCount,     chartDTO[3].experienceMonthCount],
        [preMonth1 + "월",  chartDTO[2].monthCount,     chartDTO[2].experienceMonthCount],
        [preMonth + "월",  chartDTO[1].monthCount,     chartDTO[1].experienceMonthCount],
        [month + "월",      chartDTO[0].monthCount,     chartDTO[0].experienceMonthCount]
    ]);

    var firstOptions = {
        title: '월간 체험 수',
        curveType: 'function',
        legend: { position: 'bottom' }
    };

    var firstChart = new google.visualization.LineChart(document.getElementById('first-chart'));
    firstChart.draw(firstData, firstOptions);

    // 두 번째 차트
    var secondData = google.visualization.arrayToDataTable([
        ['month', '(회원)인턴 신청', '(기업)인턴 공고'],
        [preMonth2 + "월",  chartDTO[3].internRequestMonthCount,     chartDTO[3].internNoticeMonthCount],
        [preMonth1 + "월",  chartDTO[2].internRequestMonthCount,     chartDTO[2].internNoticeMonthCount],
        [preMonth + "월",  chartDTO[1].internRequestMonthCount,     chartDTO[1].internNoticeMonthCount],
        [month + "월",      chartDTO[0].internRequestMonthCount,     chartDTO[0].internNoticeMonthCount]
    ]);

    var secondOptions = {
        title: '월간 인턴 수',
        curveType: 'function',
        legend: { position: 'bottom' }
    };

    var secondChart = new google.visualization.LineChart(document.getElementById('second-chart'));
    secondChart.draw(secondData, secondOptions);

    // 세 번째 차트
    var thirdData = google.visualization.arrayToDataTable([
        ['month', '(회원)체험 결제'],
        [preMonth2 + "월",  chartDTO[3].experiencePayment],
        [preMonth1 + "월",  chartDTO[2].experiencePayment],
        [preMonth + "월",  chartDTO[1].experiencePayment],
        [month + "월",      chartDTO[0].experiencePayment]
    ]);

    var thirdOptions = {
        title: '월간 체험 결재액',
        curveType: 'function',
        legend: { position: 'bottom' }
    };

    var thirdChart = new google.visualization.LineChart(document.getElementById('third-chart'));
    thirdChart.draw(thirdData, thirdOptions);

    // 네 번째 차트
    var fourthData = google.visualization.arrayToDataTable([
        ['month', '(기업)광고 결제'],
        [preMonth2 + "월",  chartDTO[3].advertisementPayment],
        [preMonth1 + "월",  chartDTO[2].advertisementPayment],
        [preMonth + "월",  chartDTO[1].advertisementPayment],
        [month + "월",      chartDTO[0].advertisementPayment]
    ]);

    var fourthOptions = {
        title: '월간 광고 결재액',
        curveType: 'function',
        legend: { position: 'bottom' }
    };

    var fourthChart = new google.visualization.LineChart(document.getElementById('fourth-chart'));
    fourthChart.draw(fourthData, fourthOptions);

}

google.charts.setOnLoadCallback(service.getChart(drawChart));
