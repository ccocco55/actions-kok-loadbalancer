const sideLayout = (()=>{
    const showPopularCompany = (CompanyDTO) => {
        const popularWarp = document.getElementById("company-list-wrap");
        let text = '';
        CompanyDTO.forEach((CompanyDTO)=>{
            if(CompanyDTO.companyProfileFile===null){
                text+=`<a style="cursor: pointer" onclick="window.location.href='/company/${CompanyDTO.userId}'">
                        <div class="search-modal-company-list">
                            <img alt="image" width="36" height="36" srcset="" src="/images/main-page/image.png" style="color: transparent; border-radius: 5.4px; cursor: default; max-height: 36px; max-width: 36px; min-height: 36px; min-width: 36px; object-fit: contain;">
                                <div class="search-modal-company-section">
                                    <span class="search-modal-company-name">${CompanyDTO.companyName}</span>
                                    <p class="search-modal-company-experience">${CompanyDTO.followerCount}</p>
                                </div>
                        </div>
                    </a>`
            }else{text+=`<a style="cursor: pointer" onclick="window.location.href='/company/${CompanyDTO.userId}'">
                        <div class="search-modal-company-list">
                            <img alt="image" width="36" height="36" srcset="" src="${CompanyDTO.companyProfileFile}" style="color: transparent; border-radius: 5.4px; cursor: default; max-height: 36px; max-width: 36px; min-height: 36px; min-width: 36px; object-fit: contain;">
                                <div class="search-modal-company-section">
                                    <span class="search-modal-company-name">${CompanyDTO.companyName}</span>
                                    <p class="search-modal-company-experience">${CompanyDTO.followerCount}</p>
                                </div>
                        </div>
                    </a>`}

        })
        popularWarp.innerHTML=text;
    }
    const showExperience = (ExperienceNoticeDTO)=>{
        const experienceWarp = document.getElementById("experienceWarp");
        let text ='';
        ExperienceNoticeDTO.forEach((ExperienceNoticeDTO,i)=>{
            if(ExperienceNoticeDTO.filePath===null){
                text+=
                    `
                    <a style="cursor: pointer" onclick="window.location.href='/experience/list?sharedCompanyId=${ExperienceNoticeDTO.companyId}&sharedInternId=${ExperienceNoticeDTO.id}'">
                        <div class="search-modal-list-section" data-id="${ExperienceNoticeDTO.id}">
                            <span class="search-modal-list-number">${i+1}</span>
                            <img alt="image" width="40" height="40"  src="/images/main-page/image.png" style="color: transparent; border-radius: 999px; cursor: default; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                            <div class="search-modal-list-member">
                                <span class="search-modal-list-name">${ExperienceNoticeDTO.companyName}</span>
                                <p class="search-modal-list-job">${ExperienceNoticeDTO.experienceNoticeTitle}</p>
                            </div>
                        </div>
                    </a>`
            }else{
                text+=
                    `
                    <a style="cursor: pointer" onclick="window.location.href='/experience/list?sharedCompanyId=${ExperienceNoticeDTO.companyId}&sharedExperienceId=${ExperienceNoticeDTO.id}'">
                        <div class="search-modal-list-section" data-id="${ExperienceNoticeDTO.id}">
                            <span class="search-modal-list-number">${i+1}</span>
                            <img alt="image" width="40" height="40"  src="${ExperienceNoticeDTO.filePath}" style="color: transparent; border-radius: 999px; cursor: default; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                            <div class="search-modal-list-member">
                                <span class="search-modal-list-name">${ExperienceNoticeDTO.companyName}</span>
                                <p class="search-modal-list-job">${ExperienceNoticeDTO.experienceNoticeTitle}</p>
                            </div>
                        </div>
                    </a>
                    `
            }

        });
        experienceWarp.innerHTML=text;

    }
    const showIntern = (InternNoticeDTO)=>{
        const internWarp = document.getElementById("internWarp");
        let text ='';
        InternNoticeDTO.forEach((InternNoticeDTO,i)=>{
            if(InternNoticeDTO.filePath===null){
                text+=
                    `
    <a style="cursor: pointer" onclick="window.location.href='/intern/list?sharedCompanyId=${InternNoticeDTO.companyId}&sharedInternId=${InternNoticeDTO.id}'">
        <div class="search-modal-list-section" data-id="${InternNoticeDTO.id}">
            <span class="search-modal-list-number">${i+1}</span>
            <img alt="image" width="40" height="40" srcset="" src="/images/main-page/image.png" style="color: transparent; border-radius: 999px; cursor: default; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                <div class="search-modal-list-member">
                    <span class="search-modal-list-name">${InternNoticeDTO.companyName}</span>
                    <p class="search-modal-list-job">${InternNoticeDTO.internNoticeTitle}</p>
                </div>
        </div>
    </a>`
            }else{
                text+=
                    `
                <a style="cursor: pointer" onclick="window.location.href='/intern/list?sharedCompanyId=${InternNoticeDTO.companyId}&sharedInternId=${InternNoticeDTO.id}'">
                    <div class="search-modal-list-section" data-id="${InternNoticeDTO.id}">
                        <span class="search-modal-list-number">${i+1}</span>
                        <img alt="image" width="40" height="40" srcset="" src="${InternNoticeDTO.filePath}" style="color: transparent; border-radius: 999px; cursor: default; max-height: 40px; max-width: 40px; min-height: 40px; min-width: 40px; object-fit: contain;">
                        <div class="search-modal-list-member">
                            <span class="search-modal-list-name">${InternNoticeDTO.companyName}</span>
                            <p class="search-modal-list-job">${InternNoticeDTO.internNoticeTitle}</p>
                        </div>
                    </div>
                </a>`
            }

        });
        internWarp.innerHTML=text;

    }
    const showLink = (memberDTO)=>{
        const link = document.getElementById("link");
        let text ='';
        memberDTO.forEach((memberDTO)=>{
            if(memberDTO.memberProvider ==='kakao'){
                text+=`<div class="setting-modal-account-email-wrap">
                    <div class="setting-modal-account-email">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 40 40" height="24" width="24">
                            <path fill="#1E1E1E" fill-rule="evenodd" d="M5 18.027c-.019-6.544 6.682-11.87 14.966-11.893S34.98 11.396 35 17.94s-6.682 11.87-14.966 11.893a19 19 0 0 1-2.697-.183l-6.07 4.122a.43.43 0 0 1-.675-.474l1.399-5.286C7.8 25.923 5.012 22.235 5 18.027m6.721 3.87c.443-.002.717-.29.716-.754l-.015-5.211 1.508-.004c.475-.002.642-.353.641-.654a.636.636 0 0 0-.645-.652l-4.452.013a.633.633 0 0 0-.64.656c.001.3.17.652.644.65l1.509-.004.015 5.21c.001.463.276.75.72.75m7.22-.143c.127.091.284.134.44.12a.63.63 0 0 0 .654-.621 1.2 1.2 0 0 0-.083-.369l-2.034-5.502c-.189-.53-.58-.833-1.071-.832-.56.002-.917.435-1.065.84l-2.067 5.523a.8.8 0 0 0-.065.315.68.68 0 0 0 .708.661.655.655 0 0 0 .689-.512l.318-.97 2.998-.01.317.981c.04.152.132.285.26.376m5.89-.353a.7.7 0 0 0 .06-.25.64.64 0 0 0-.17-.479.69.69 0 0 0-.517-.198l-1.99.006-.014-5.196c-.002-.462-.277-.748-.72-.747-.442 0-.717.289-.715.75l.016 5.709c.002.497.284.793.753.792l2.674-.008a.65.65 0 0 0 .624-.38m5.321.442c.154 0 .66-.043.714-.565.038-.293-.137-.52-.349-.794v-.001l-2.052-2.67 1.723-1.747q.091-.105.17-.191l.005-.006c.234-.26.351-.39.351-.588.008-.422-.341-.651-.688-.66a.8.8 0 0 0-.57.264l-2.52 2.642-.006-2.3a.675.675 0 0 0-.72-.704.69.69 0 0 0-.716.709l.017 5.939c.001.41.29.685.72.683a.687.687 0 0 0 .716-.687l-.006-1.932.514-.527 1.893 2.564c.277.373.427.54.692.566q.056.005.112.005m-14.388-2.71 1.071-3.268.003-.005.003-.002a.015.015 0 0 1 .022.007l1.092 3.262z" clip-rule="evenodd" style="fill: color(display-p3 0.1176 0.1176 0.1176); fill-opacity: 1;"></path><path fill="#FEE300" d="M12.437 21.143c.001.463-.273.752-.716.753s-.718-.286-.719-.749l-.015-5.21-1.51.004c-.473.002-.642-.35-.642-.65a.634.634 0 0 1 .64-.656l4.451-.013a.634.634 0 0 1 .645.652c0 .3-.166.652-.641.654l-1.508.004z" style="fill: color(display-p3 0.9961 0.8902 0); fill-opacity: 1;"></path><path fill="#FEE300" fill-rule="evenodd" d="M19.381 21.875a.67.67 0 0 1-.701-.497l-.317-.98-2.998.009-.318.97a.655.655 0 0 1-.689.512.68.68 0 0 1-.708-.661.8.8 0 0 1 .065-.316l2.067-5.522c.148-.405.505-.838 1.065-.84.491-.001.882.302 1.07.832l2.035 5.502q.07.178.083.369a.63.63 0 0 1-.654.622m-3.617-2.741 1.071-3.269.003-.005.003-.002a.015.015 0 0 1 .022.007l1.092 3.262z" clip-rule="evenodd" style="fill: color(display-p3 0.9961 0.8902 0); fill-opacity: 1;"></path><path fill="#FEE300" d="M24.892 21.152a.654.654 0 0 1-.684.628l-2.674.008c-.47.001-.751-.295-.753-.792l-.016-5.708c-.002-.462.273-.75.715-.751.443-.002.718.285.72.747l.015 5.196 1.99-.006a.7.7 0 0 1 .517.198.64.64 0 0 1 .17.48M30.866 21.278c-.054.522-.56.564-.714.565q-.056 0-.112-.005c-.265-.027-.415-.193-.692-.566l-1.893-2.564-.514.527.006 1.932a.687.687 0 0 1-.716.687c-.43.002-.719-.273-.72-.683l-.017-5.94a.69.69 0 0 1 .717-.708.676.676 0 0 1 .719.705l.006 2.3 2.52-2.643a.8.8 0 0 1 .57-.263c.347.008.696.237.688.66 0 .197-.117.327-.351.587l-.005.006q-.079.085-.17.19l-1.723 1.748 2.051 2.67h.001c.212.275.387.502.35.795" style="fill: color(display-p3 0.9961 0.8902 0); fill-opacity: 1;"></path>
                        </svg>
                        <div class="setting-modal-account-email-text-wrap">
                            <span class="setting-modal-account-email-text">${memberDTO.snsEmail}</span>
                        </div>
                    </div>
                </div>`
            }else if(memberDTO.memberProvider==='naver'){
                text+=`
                        <div class="setting-modal-account-email-wrap">
                            <div class="setting-modal-account-email">
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 40 40" height="24" width="24">
                                    <path fill="#03CF5D" d="m24.136 31.9-8.394-12.313v12.314H7V8.099h8.864l8.394 12.322V8.1H33v23.802z"></path>
                                </svg>
                                <div class="setting-modal-account-email-text-wrap">
                                    <span class="setting-modal-account-email-text">${memberDTO.snsEmail}</span>
                                </div>
                            </div>
                        </div>`
            }else{
                <!-- 구글 로그인 -->
                text+=`<div class="setting-modal-account-email-wrap">
                            <div class="setting-modal-account-email">
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 40 40" height="24" width="24">
                                    <path fill="#FC4C53" d="M33.813 17.334H20v5.68h7.893c-.346 1.826-1.386 3.373-2.946 4.413-1.307.88-2.974 1.413-4.947 1.413-3.813 0-7.053-2.573-8.213-6.04h-.018l.018-.013A8.8 8.8 0 0 1 11.32 20c0-.973.173-1.907.467-2.786 1.16-3.467 4.4-6.04 8.213-6.04 2.16 0 4.08.746 5.613 2.186l4.2-4.2C27.267 6.787 23.96 5.333 20 5.333c-5.733 0-10.68 3.294-13.093 8.094-1 1.973-1.574 4.2-1.574 6.573s.574 4.6 1.574 6.573v.014c2.413 4.787 7.36 8.08 13.093 8.08 3.96 0 7.28-1.307 9.707-3.547 2.773-2.56 4.373-6.32 4.373-10.786 0-1.04-.093-2.04-.267-3" style="fill: color(display-p3 0.9882 0.298 0.3255); fill-opacity: 1;"></path>
                                    <mask id="google_svg__a" width="30" height="30" x="5" y="5" maskUnits="userSpaceOnUse" style="mask-type: luminance;">
                                        <path fill="#fff" d="M33.814 17.334H20v5.68h7.894c-.347 1.826-1.387 3.373-2.947 4.413-1.307.88-2.973 1.413-4.947 1.413-3.813 0-7.053-2.573-8.213-6.04h-.017l.017-.013A8.8 8.8 0 0 1 11.32 20c0-.973.174-1.907.467-2.786 1.16-3.467 4.4-6.04 8.213-6.04 2.16 0 4.08.746 5.614 2.186l4.2-4.2C27.267 6.787 23.96 5.333 20 5.333c-5.733 0-10.68 3.294-13.093 8.094-1 1.973-1.573 4.2-1.573 6.573s.573 4.6 1.573 6.573v.014c2.413 4.787 7.36 8.08 13.093 8.08 3.96 0 7.28-1.307 9.707-3.547 2.773-2.56 4.373-6.32 4.373-10.786 0-1.04-.093-2.04-.266-3" style="fill: rgb(255, 255, 255); fill-opacity: 1;"></path>
                                    </mask>
                                    <g mask="url(#google_svg__a)">
                                        <path fill="url(#google_svg__b)" d="M8.862 36.055c5.71 0 10.34-6.25 10.34-13.958 0-7.71-4.63-13.959-10.34-13.959s-10.34 6.25-10.34 13.959 4.63 13.958 10.34 13.958"></path>
                                        <path fill="url(#google_svg__c)" d="M26.695 50.215c11.52-1.44 19.972-9.693 18.88-18.436S34.259 17.116 22.74 18.555 2.768 28.247 3.86 36.99s11.316 14.664 22.835 13.225"></path>
                                        <path fill="url(#google_svg__d)" d="m18.807 15.04.654 7.461.832 4.996 9.816 9.131 11.476-21.196z"></path>
                                    </g>
                                    <defs>
                                        <radialGradient id="google_svg__b" cx="0" cy="0" r="1" gradientTransform="matrix(10.3404 0 0 13.958 8.862 22.096)" gradientUnits="userSpaceOnUse">
                                            <stop offset="0.368" stop-color="#FFCF09" style="stop-color: color(display-p3 1 0.8118 0.0353); stop-opacity: 1;"></stop>
                                            <stop offset="0.718" stop-color="#FFCF09" stop-opacity="0.7" style="stop-color: color(display-p3 1 0.8118 0.0353); stop-opacity: 0.7;"></stop>
                                            <stop offset="1" stop-color="#FFCF09" stop-opacity="0" style="stop-opacity: 0;"></stop>
                                        </radialGradient>
                                        <radialGradient id="google_svg__c" cx="0" cy="0" r="1" gradientTransform="matrix(20.8579 -2.60538 1.9774 15.83048 24.718 34.385)" gradientUnits="userSpaceOnUse">
                                            <stop offset="0.383" stop-color="#34A853" style="stop-color: color(display-p3 0.2039 0.6588 0.3255); stop-opacity: 1;"></stop>
                                            <stop offset="0.706" stop-color="#34A853" stop-opacity="0.7" style="stop-color: color(display-p3 0.2039 0.6588 0.3255); stop-opacity: 0.7;"></stop>
                                            <stop offset="1" stop-color="#34A853" stop-opacity="0" style="stop-opacity: 0;"></stop>
                                        </radialGradient>
                                        <linearGradient id="google_svg__d" x1="35.412" x2="20.198" y1="12.382" y2="31.066" gradientUnits="userSpaceOnUse">
                                            <stop offset="0.671" stop-color="#4285F4" style="stop-color: color(display-p3 0.2588 0.5216 0.9569); stop-opacity: 1;"></stop>
                                            <stop offset="0.885" stop-color="#4285F4" stop-opacity="0" style="stop-opacity: 0;"></stop>
                                        </linearGradient>
                                    </defs>
                                </svg>
                                <div class="setting-modal-account-email-text-wrap">
                                    <span class="setting-modal-account-email-text">${memberDTO.snsEmail}</span>
                                </div>
                            </div>
                        </div>`}
        });
        link.innerHTML = text;
    }

    const showSupport = (adminNoticeCriteriaDTO) => {
        const supportWrap = document.getElementById("support-wrap");
        let text = ``;

        adminNoticeCriteriaDTO.noticeList.forEach((notice) => {
            text += `
                <li class="customer-support-list-section" data-id="${notice.id}">
                    <div class="customer-support-list-content">
                        <span class="customer-support-list-title-wrap">
                            <span class="customer-support-list-title-icon"></span>${notice.adminNoticeTitle}
                        </span>
                        <span class="customer-support-list-text">
                            ${notice.adminNoticeContent}
                        </span>
                    </div>
                </li>
            `;
        });
        supportWrap.innerHTML += text;
    }

    const showSupportDetail = (adminNoticeDTO) => {
        const supportDetailWrap = document.getElementById("member-support-detail");
        let text = ``;

        text += `
            <div class="detail-sub-top">
                <span class="detail-back-wrap" tabindex="0" role="button">
                    <span class="detail-back-icon"></span>
                </span>
                <span class="detail-title-wrap">
                    <span class="detail-title">
<!--                        <span class="detail-title-section">계정 및 프로필</span>-->
<!--                        <span class="detail-title-section-icon">/</span>-->
                        <span class="detail-title-text">콕(KOK) 고객지원</span>
                    </span>
                </span>
            </div>
            <div class="detail-main-body-wrap">
                <div class="detail-main-body" id="detail-main-body">
                    <div class="detail-main-content-wrap1">
                        <div class="detail-main-content-wrap2">
                            <div class="detail-main-content-wrap3" role="main">
                                <article class="detail-main-article-wrap">
                                    <div class="detail-main-article" role="article">
                                        <h3 onclick="" id="detail-main-title" class="detail-main-title">
                                            <span class="detail-main-title-text">${adminNoticeDTO.adminNoticeTitle}</span>
                                        </h3>
                                        <span class="detail-main-content-text">
                                            <br>
                                            ${adminNoticeDTO.adminNoticeContent}
                                        </span>
                                        <span class="detail-main-content-space"></span>
                                        <p class="detail-main-update-date">업데이트 날짜: ${adminNoticeDTO.updatedDateTime}</p>
                                    </div>
                                </article>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `;
        supportDetailWrap.innerHTML = text;
    }
    return{showPopularCompany:showPopularCompany,showExperience:showExperience,showIntern:showIntern,showLink:showLink,showSupport:showSupport,showSupportDetail:showSupportDetail}
})();