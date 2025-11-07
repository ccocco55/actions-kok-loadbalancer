const headerLayout = (() => {
    const showExperienceList = (experienceListDTO) => {
        console.log(experienceListDTO)
        const requestExperienceWarp = document.getElementById("requestExperienceWarp");
        let text = '';
        experienceListDTO.forEach((experienceListDTO) => {
            if (experienceListDTO.requestExperienceStatus === 'await') {
                text += `<div class="history-modal-main-section" data-id="${experienceListDTO.id}">
                            <div class="history-modal-main-company-wrap">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32"  src="${experienceListDTO.companyProfileUrl}"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div class="history-modal-main-section-info">
                                    <span class="history-modal-main-company-name">${experienceListDTO.companyName}</span>
                                    <span class="history-modal-main-announce-title">${experienceListDTO.experienceNoticeTitle}</span>
                                    <p class="history-modal-main-apply-date">지원일: </p><p class="history-modal-main-apply-date">${experienceListDTO.createdDateTime.split(" ")[0]}</p>
                                </div>
                                <div class="history-modal-main-apply-status-wrap">
                                    <div class="history-modal-main-apply-status">
                                        <span class="history-modal-main-apply-status-text">서류 접수</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            } else if (experienceListDTO.requestExperienceStatus === 'accept') {
                text += `<div class="history-modal-main-section" data-id="${experienceListDTO.id}">
                            <div class="history-modal-main-company-wrap">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32"  src="${experienceListDTO.companyProfileUrl}"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div class="history-modal-main-section-info">
                                    <span class="history-modal-main-company-name">${experienceListDTO.companyName}</span>
                                    <span class="history-modal-main-announce-title">${experienceListDTO.experienceNoticeTitle}</span>
                                    <p class="history-modal-main-apply-date">지원일: </p><p class="history-modal-main-apply-date">${experienceListDTO.createdDateTime.split(" ")[0]}</p>
                                </div>
                                <div class="history-modal-main-apply-status-wrap">
                                    <div class="history-modal-main-apply-status">
                                        <span class="history-modal-main-apply-status-text">합격</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            } else {
                text += `<div class="history-modal-main-section fail">
                            <div class="history-modal-main-company-wrap-fail">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32"  src="${experienceListDTO.companyProfileUrl}"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div class="history-modal-main-section-info">
                                    <span class="history-modal-main-company-name">${experienceListDTO.companyName}</span>
                                    <span class="history-modal-main-announce-title">${experienceListDTO.experienceNoticeTitle}</span>
                                    <p class="history-modal-main-apply-date">지원일: </p><p class="history-modal-main-apply-date">${experienceListDTO.createdDateTime.split(" ")[0]}</p>
                                </div>
                                <div class="history-modal-main-apply-status-wrap">
                                    <div class="history-modal-main-apply-status">
                                        <span class="history-modal-main-apply-status-text">불합격</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            }
        })
        requestExperienceWarp.innerHTML = text;
    }
    const showInternList = (internListDTO) => {
        console.log(internListDTO);
        const requestInternWarp = document.getElementById("requestInternWarp");
        let text = '';
        internListDTO.forEach((internListDTO) => {
            if (internListDTO.requestInternStatus === 'await') {
                text += `<div class="history-modal-main-section" data-id="${internListDTO.id}">
                            <div class="history-modal-main-company-wrap">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32" src="${internListDTO.companyProfileUrl}"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div class="history-modal-main-section-info">
                                    <span class="history-modal-main-company-name">${internListDTO.companyName}</span>
                                    <span class="history-modal-main-announce-title">${internListDTO.internNoticeTitle}</span>
                                    <p class="history-modal-main-apply-date">지원일: </p><p class="history-modal-main-apply-date">${internListDTO.createdDateTime.split(" ")[0]}</p>
                                </div>
                                <div class="history-modal-main-apply-status-wrap">
                                    <div class="history-modal-main-apply-status">
                                        <span class="history-modal-main-apply-status-text">서류 접수</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            } else if (internListDTO.requestInternStatus === 'accept') {
                text += `<div class="history-modal-main-section" data-id="${internListDTO.id}">
                            <div class="history-modal-main-company-wrap">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32" src="${internListDTO.companyProfileUrl}"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div class="history-modal-main-section-info">
                                    <span class="history-modal-main-company-name">${internListDTO.companyName}</span>
                                    <span class="history-modal-main-announce-title">${internListDTO.internNoticeTitle}</span>
                                    <p class="history-modal-main-apply-date">지원일: </p><p class="history-modal-main-apply-date">${internListDTO.createdDateTime.split(" ")[0]}</p>
                                </div>
                                <div class="history-modal-main-apply-status-wrap">
                                    <div class="history-modal-main-apply-status">
                                        <span class="history-modal-main-apply-status-text">합격</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            } else {
                text += `<div class="history-modal-main-section fail">
                            <div class="history-modal-main-company-wrap-fail">
                                <!-- 기업 프로필 이미지 -->
                                <img alt="image" width="32" height="32"  src="${internListDTO.companyProfileUrl}"
                                     style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;"/>
                                <div class="history-modal-main-section-info">
                                    <span class="history-modal-main-company-name">${internListDTO.companyName}</span>
                                    <span class="history-modal-main-announce-title">${internListDTO.internNoticeTitle}</span>
                                    <p class="history-modal-main-apply-date">지원일: </p><p class="history-modal-main-apply-date">${internListDTO.createdDateTime.split(" ")[0]}</p>
                                </div>
                                <div class="history-modal-main-apply-status-wrap">
                                    <div class="history-modal-main-apply-status">
                                        <span class="history-modal-main-apply-status-text">불합격</span>
                                    </div>
                                </div>
                            </div>
                        </div>`
            }
        })
        requestInternWarp.innerHTML = text;
    }
    const showExperienceDetail = (experienceDetailDTO) =>{
        const warp = document.querySelector(".history-detail-modal-wrap");
        console.log("디테일 레이아웃" + experienceDetailDTO)

        let text =
            `<div class="history-detail-modal">
                <div class="history-detail-modal-top">
                    <div class="history-detail-modal-top-section">
                        <div class="history-detail-modal-top-left">
                            <button class="history-detail-modal-top-left-button">
                                <div class="history-detail-modal-top-left-icon">
                                    <svg color="foregrounds.neutral.primary" fill="currentColor" height="20" role="img" width="20" viewBox="0 0 24 24">
                                        <path clip-rule="evenodd" d="M11.566 5.435a.8.8 0 0 0-1.132 0l-6 6a.8.8 0 0 0 0 1.13l6 6a.8.8 0 1 0 1.132-1.13L6.93 12.8H19a.8.8 0 1 0 0-1.6H6.931l4.635-4.634a.8.8 0 0 0 0-1.131" fill-rule="evenodd"></path>
                                    </svg>
                                </div>
                                <p class="history-detail-modal-top-left-text">목록으로</p>
                            </button>
                        </div>
                        <div class="history-detail-modal-top-middle">
                            <div class="history-detail-modal-main-text">지원서</div>
                            <div class="history-detail-modal-sub-text" >지원일: ${experienceDetailDTO[0].createdDateTime.split(" ")[0]}</div>
                        </div>
                        <div class="history-detail-modal-top-right">
                            <button class="history-detail-modal-top-right-button">
                                <p class="history-detail-modal-top-right-text">닫기</p>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="history-detail-modal-body-wrap">
                    <div class="history-detail-modal-body">
                        <!-- 신청한 공고의 상세 페이지로 이동 -->
                        <a href="" class="history-detail-modal-body-content">
                            <div class="history-detail-modal-info-wrap">
                                <img alt="image" width="32" height="32"  src="${experienceDetailDTO[0].companyProfileUrl}" style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;">
                                    <div class="history-detail-modal-info">
                                        <span class="info-company-name">${experienceDetailDTO[0].companyName}</span>
                                        <span class="info-experience-title" >${experienceDetailDTO[0].experienceNoticeTitle}</span>
                                        <p class="info-apply-date" >지원일: ${experienceDetailDTO[0].createdDateTime.split(" ")[0]}</p>
                                    </div>
                                    <div class="history-detail-modal-status-wrap">
                                        <div class="history-detail-modal-status">`
        if(experienceDetailDTO[0].requestExperienceStatus === 'accept') {
            text += `<span class="history-detail-modal-status-text">합격</span>`
        }else {
            text += `<span class="history-detail-modal-status-text">서류 접수</span>`
        }

        text += `
                                        </div>
                                    </div>
                                    </div>
                        </a>
                        <div class="history-detail-modal-body-section-wrap">
                            <div class="history-detail-modal-body-section">
                                <div class="body-section-info-wrap">
                                    <p class="body-section-info-text">이름</p>
                                </div>
                                <div class="body-section-user-info-wrap">
                                    <div class="body-section-user-info-text-wrap">
                                        <p class="body-section-user-info-text">${experienceDetailDTO[0].requestExperienceMemberName}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="history-detail-modal-body-section">
                                <div class="body-section-info-wrap">
                                    <p class="body-section-info-text">이메일</p>
                                </div>
                                <div class="body-section-user-info-wrap">
                                    <div class="body-section-user-info-text-wrap">
                                        <p class="body-section-user-info-text">${experienceDetailDTO[0].requestExperienceMemberEmail}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="history-detail-modal-body-section">
                                <div class="body-section-info-wrap">
                                    <p class="body-section-info-text">전화번호</p>
                                </div>
                                <div class="body-section-user-info-wrap">
                                    <div class="body-section-user-info-text-wrap">
                                        <p class="body-section-user-info-text">${experienceDetailDTO[0].requestExperienceMemberPhone}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="history-detail-modal-body-section-wrap">
                            <div class="history-detail-modal-body-section">
                                <div class="body-section-info-wrap">
                                    <p class="body-section-info-text">이력서</p>
                                </div>
                                <div class="body-section-user-info-wrap">
                                    <div class="body-section-user-info-text-wrap">`
        if(experienceDetailDTO[0].requestExperienceMemberUrl === null){
            text+= `<a  class="body-section-user-url-text" >입력하신 url이 없습니다.</a>`
        }else{

            text+= `<a href="${experienceDetailDTO[0].requestExperienceMemberUrl}" class="body-section-user-url-text" >${experienceDetailDTO[0].requestExperienceMemberUrl}</a>`
        }
        text+=`
                                    </div>
                                    <div class="body-section-user-url">
                                        <svg color="foregrounds.neutral.tertiary" fill="currentColor" height="20" role="img" width="20" viewBox="0 0 24 24">
                                            <path clip-rule="evenodd" d="M9.434 6.435a.8.8 0 0 1 1.132 0l5 5a.8.8 0 0 1 0 1.13l-5 5a.8.8 0 1 1-1.132-1.13L13.87 12 9.434 7.566a.8.8 0 0 1 0-1.131" fill-rule="evenodd"></path>
                                        </svg>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="history-detail-modal-body-section-wrap"></div>
                    </div>
                </div>
            </div>
            `
        warp.innerHTML=text;
    }
    const showInternDetail = (internDetailDTO) =>{
        const warp = document.querySelector(".history-detail-modal-wrap");
        let text =
            `<div class="history-detail-modal">
                <div class="history-detail-modal-top">
                    <div class="history-detail-modal-top-section">
                        <div class="history-detail-modal-top-left">
                            <button class="history-detail-modal-top-left-button">
                                <div class="history-detail-modal-top-left-icon">
                                    <svg color="foregrounds.neutral.primary" fill="currentColor" height="20" role="img" width="20" viewBox="0 0 24 24">
                                        <path clip-rule="evenodd" d="M11.566 5.435a.8.8 0 0 0-1.132 0l-6 6a.8.8 0 0 0 0 1.13l6 6a.8.8 0 1 0 1.132-1.13L6.93 12.8H19a.8.8 0 1 0 0-1.6H6.931l4.635-4.634a.8.8 0 0 0 0-1.131" fill-rule="evenodd"></path>
                                    </svg>
                                </div>
                                <p class="history-detail-modal-top-left-text">목록으로</p>
                            </button>
                        </div>
                        <div class="history-detail-modal-top-middle">
                            <div class="history-detail-modal-main-text">지원서</div>
                            <div class="history-detail-modal-sub-text" >지원일: ${internDetailDTO[0].createdDateTime.split(" ")[0]}</div>
                        </div>
                        <div class="history-detail-modal-top-right">
                            <button class="history-detail-modal-top-right-button">
                                <p class="history-detail-modal-top-right-text">닫기</p>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="history-detail-modal-body-wrap">
                    <div class="history-detail-modal-body">
                        <!-- 신청한 공고의 상세 페이지로 이동 -->
                        <a onclick="" class="history-detail-modal-body-content">
                            <div class="history-detail-modal-info-wrap">
                                <img alt="image" width="32" height="32"  src="${internDetailDTO[0].companyProfileUrl}" style="color: transparent; border-radius: 4.8px; cursor: default; max-height: 32px; max-width: 32px; min-height: 32px; min-width: 32px; object-fit: contain;">
                                    <div class="history-detail-modal-info">
                                        <span class="info-company-name">${internDetailDTO[0].companyName}</span>
                                        <span class="info-experience-title" >${internDetailDTO[0].internNoticeTitle}</span>
                                        <p class="info-apply-date" >지원일: ${internDetailDTO[0].createdDateTime.split(" ")[0]}</p>
                                    </div>
                                    <div class="history-detail-modal-status-wrap">
                                        <div class="history-detail-modal-status">`
        if(internDetailDTO[0].requestInternStatus === 'accept') {
            text += `<span class="history-detail-modal-status-text">합격</span>`
        }else {
            text += `<span class="history-detail-modal-status-text">서류 접수</span>`
        }

        text += `
                                        </div>
                                    </div>
                            </div>
                        </a>
                        <div class="history-detail-modal-body-section-wrap">
                            <div class="history-detail-modal-body-section">
                                <div class="body-section-info-wrap">
                                    <p class="body-section-info-text">이름</p>
                                </div>
                                <div class="body-section-user-info-wrap">
                                    <div class="body-section-user-info-text-wrap">
                                        <p class="body-section-user-info-text">${internDetailDTO[0].requestInternMemberName}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="history-detail-modal-body-section">
                                <div class="body-section-info-wrap">
                                    <p class="body-section-info-text">이메일</p>
                                </div>
                                <div class="body-section-user-info-wrap">
                                    <div class="body-section-user-info-text-wrap">
                                        <p class="body-section-user-info-text">${internDetailDTO[0].requestInternMemberEmail}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="history-detail-modal-body-section">
                                <div class="body-section-info-wrap">
                                    <p class="body-section-info-text">전화번호</p>
                                </div>
                                <div class="body-section-user-info-wrap">
                                    <div class="body-section-user-info-text-wrap">
                                        <p class="body-section-user-info-text">${internDetailDTO[0].requestInternMemberPhone}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="history-detail-modal-body-section-wrap">
                            <div class="history-detail-modal-body-section">
                                <div class="body-section-info-wrap">
                                    <p class="body-section-info-text">이력서</p>
                                </div>
                                <div class="body-section-user-info-wrap">
                                    <div class="body-section-user-info-text-wrap">`
        if(internDetailDTO[0].requestInternMemberUrl === null){
            text+= `<a  class="body-section-user-url-text" >입력하신 url이 없습니다.</a>`
        }else{

            text+= `<a href="${internDetailDTO[0].requestInternMemberUrl}" class="body-section-user-url-text" >${internDetailDTO[0].requestInternMemberUrl}</a>`
        }
        text+=`
                                    </div>
                                    <div class="body-section-user-url">
                                        <svg color="foregrounds.neutral.tertiary" fill="currentColor" height="20" role="img" width="20" viewBox="0 0 24 24">
                                            <path clip-rule="evenodd" d="M9.434 6.435a.8.8 0 0 1 1.132 0l5 5a.8.8 0 0 1 0 1.13l-5 5a.8.8 0 1 1-1.132-1.13L13.87 12 9.434 7.566a.8.8 0 0 1 0-1.131" fill-rule="evenodd"></path>
                                        </svg>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="history-detail-modal-body-section-wrap"></div>
                    </div>
                </div>
            </div>
            `
        warp.innerHTML=text;
    }

    return {showExperienceList: showExperienceList,showInternList:showInternList,showExperienceDetail:showExperienceDetail,showInternDetail:showInternDetail}
})
();