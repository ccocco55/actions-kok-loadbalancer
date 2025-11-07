package com.example.kok.controller;

import com.example.kok.aop.aspect.annotation.LogReturnStatus;
import com.example.kok.aop.aspect.annotation.LogStatus;
import com.example.kok.dto.*;
import com.example.kok.enumeration.Provider;
import com.example.kok.repository.UserDAO;
import com.example.kok.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/**")
@RequiredArgsConstructor
public class AdminRestController implements AdminRestControllerDocs {
    private final AdminService adminService;
    private final AdminReportService adminReportService;
    private final AdminAdvertisementService adminAdvertisementService;
    private final AdminBannerService adminBannerService;
    private final AdminEmployService adminEmployService;
    private final AdminPaymentAdvertiseService adminPaymentAdvertiseService;
    private final AdminPaymentExperienceService adminPaymentExperienceService;
    private final AdminMainPageService adminMainPageService;
    private final AdminExperienceService adminExperienceService;
    private final AdminEmployDetailService adminEmployDetailService;

//    메인 페이지
    @GetMapping("mainPage")
    public ResponseEntity<AdminMainPageDTO> mainPage(){
        AdminMainPageDTO adminMainPageDTO = adminMainPageService.mainPage();
        return  ResponseEntity.ok(adminMainPageDTO);
    }

    @GetMapping("mainPage/chart")
    public ResponseEntity<List<ChartDTO>> mainPageChart(){
        List<ChartDTO> chartDTO = adminMainPageService.mainPageChart();
        if (chartDTO == null || chartDTO.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(chartDTO);
        }
        return ResponseEntity.ok(chartDTO);
    }

//    공지 목록
    @GetMapping("support/{page}")
    public ResponseEntity<AdminNoticeCriteriaDTO> list(@PathVariable("page") int page){
        AdminNoticeCriteriaDTO adminNoticeCriteriaDTO = adminService.getList(page);
        return ResponseEntity.ok(adminNoticeCriteriaDTO);
    }

//    체험 목록
    @GetMapping("experience/{page}")
    @LogReturnStatus
    public ResponseEntity<AdminExperienceListDTO> getExperience(@PathVariable("page") int page,
                                                @RequestParam(required = false) String keyword){
        AdminExperienceListDTO adminExperienceListDTO = adminService.getExperience(page, keyword);
        return ResponseEntity.ok(adminExperienceListDTO);
    }

//    체험 상세
    @GetMapping("experience/detail/{id}")
    public ResponseEntity<AdminExperienceDTO> getExperienceDetail(@PathVariable("id")Long id){
        AdminExperienceDTO adminExperienceDTO = adminService.getExperienceDetail(id);
        log.info("체험 상세: {}", adminExperienceDTO);
        return ResponseEntity.ok(adminExperienceDTO);
    }

    @GetMapping("experience/detail/list/{id}/{page}")
    public ResponseEntity<AdminExperienceDetailDTO> getExperienceDetailList(@PathVariable("page")int page, @PathVariable("id")Long id){
        AdminExperienceDetailDTO adminExperienceDetailDTO = adminExperienceService.getDetailLists(page, id);
        log.info("체험 상세 목록: {}", adminExperienceDetailDTO);
        return ResponseEntity.ok(adminExperienceDetailDTO);
    }

//    인턴 목록
    @GetMapping("employ/{page}")
    public ResponseEntity<AdminInternNoticeListCriteriaDTO> getInternNoticeList(@PathVariable("page") int page,
                                                                @RequestParam(required = false) String keyword){
        AdminInternNoticeListCriteriaDTO adminInternNoticeListCriteriaDTO = adminEmployService.getList(page, keyword);
        return ResponseEntity.ok(adminInternNoticeListCriteriaDTO);
    }

//    인턴 상세
    @GetMapping("employ/detail/{id}")
    public ResponseEntity<AdminInternNoticeDetailDTO> getInternNoticeDetail(@PathVariable("id")Long id){
        AdminInternNoticeDetailDTO adminInternNoticeDetailDTO = adminEmployService.getDetail(id);
        return ResponseEntity.ok(adminInternNoticeDetailDTO);
    }

    @GetMapping("employ/detail/list/{id}/{page}")
    public ResponseEntity<AdminInternNoticeDetailCriteriaDTO> getInternNoticeDetailList(@PathVariable("page")int page, @PathVariable("id")Long id){
        AdminInternNoticeDetailCriteriaDTO adminInternNoticeDetailCriteriaDTO = adminEmployDetailService.getDetailList(page, id);
        return ResponseEntity.ok(adminInternNoticeDetailCriteriaDTO);
    }

//    신고 게시글 목록
    @GetMapping("notify/post/{page}")
    public ResponseEntity<AdminReportCriteriaDTO> getNotifyList(@PathVariable("page") int page){
        AdminReportCriteriaDTO adminReportCriteriaDTO = adminReportService.getReportList(page);
        return ResponseEntity.ok(adminReportCriteriaDTO);
    }

//    신고 게시글 상세
    @GetMapping("notify/post/detail/{id}")
    @LogStatus
    public ResponseEntity<AdminReportDTO> getNotifyDetail(@PathVariable("id")Long id){
        AdminReportDTO adminReportDTO = adminReportService.getReportDetail(id);
        return ResponseEntity.ok(adminReportDTO);
    }

//    배너 - 광고 목록
    @GetMapping("advertise/{page}")
    @LogReturnStatus
    public ResponseEntity<AdminAdvertisementCriteriaDTO> getAdvertisementList(@PathVariable("page") int page,
                                                              @RequestParam(required = false) String keyword,
                                                              @RequestParam(required = false) String category){
        AdminAdvertisementCriteriaDTO adminAdvertisementCriteriaDTO = adminAdvertisementService.advertisementList(page, keyword, category);
        return ResponseEntity.ok(adminAdvertisementCriteriaDTO);
    }

//    배너 - 광고 상세
    @GetMapping("advertise/detail/{id}")
    public ResponseEntity<AdminAdvertisementDTO> getAdvertisementDetail(@PathVariable("id")Long id){
        AdminAdvertisementDTO adminAdvertisementDTO = adminAdvertisementService.advertisementDetail(id);
        return ResponseEntity.ok(adminAdvertisementDTO);
    }

//    배너 - 현수막
    @GetMapping("banner/{page}")
    public ResponseEntity<BannerFileCriteriaDTO> getBanner(@PathVariable("page") int page){
        BannerFileCriteriaDTO bannerFileCriteriaDTO = adminBannerService.bannerList(page);
        return ResponseEntity.ok(bannerFileCriteriaDTO);
    }

//    결제 - 광고
    @GetMapping("payment/advertise/{page}")
    public ResponseEntity<AdminPaymentAdvertiseCriteriaDTO> getPaymentAdvertise(@PathVariable("page") int page,
                                                                @RequestParam(required = false) String keyword,
                                                                @RequestParam(required = false) String category){
        AdminPaymentAdvertiseCriteriaDTO adminPaymentAdvertiseCriteriaDTO = adminPaymentAdvertiseService.paymentAdvertiseList(page, keyword, category);
        return ResponseEntity.ok(adminPaymentAdvertiseCriteriaDTO);
    }

//    결제 - 체험
    @GetMapping("payment/experience/{page}")
    @LogReturnStatus
    public ResponseEntity<AdminPaymentExperienceCriteriaDTO> getPaymentExperience(@PathVariable("page") int page,
                                                                  @RequestParam(required = false) String keyword,
                                                                  @RequestParam(required = false) String category){
        AdminPaymentExperienceCriteriaDTO adminPaymentExperienceCriteriaDTO = adminPaymentExperienceService.getPaymentExperienceList(page, keyword, category);
        return ResponseEntity.ok(adminPaymentExperienceCriteriaDTO);
    }
}
