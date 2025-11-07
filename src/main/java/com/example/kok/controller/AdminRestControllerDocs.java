package com.example.kok.controller;

import com.example.kok.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// http://localhost:10000/swagger-ui/index.html
@Tag(name = "Admin", description = "Admin API")
public interface AdminRestControllerDocs {

    @Operation(summary = "메인 페이지 평균 및 통계",
            description = "일반 회원 및 기업이 이용한 서비스 관련 통계"
    )
    public ResponseEntity<AdminMainPageDTO> mainPage();

    @Operation(summary = "메인 페이지 차트",
            description = "일반 회원 및 기업이 이용한 서비스 관련 차트"
    )
    public ResponseEntity<List<ChartDTO>> mainPageChart();

    @Operation(summary = "공지 목록",
            description = "관리자가 올린 공지를 목록으로 보여준다."
    )
    public ResponseEntity<AdminNoticeCriteriaDTO> list(@PathVariable("page") int page);

    @Operation(summary = "체험 목록",
            description = "체험 공고 전체를 목록으로 보여준다.",
            parameters = {
                @Parameter(name = "page", description = "목록을 출력할 페이지 번호"),
                @Parameter(name = "keyword", description = "목록을 출력할 키워드(필수X)")
            }
    )
    public ResponseEntity<AdminExperienceListDTO> getExperience(@PathVariable("page") int page,
                                                @RequestParam(required = false) String keyword);

    @Operation(summary = "체험 상세",
            description = "체험 목록의 각 상세 정보를 보여준다.",
            parameters = {
                @Parameter(name = "id", description = "공고의 상세 정보를 출력할 아이디")
            }
    )
    public ResponseEntity<AdminExperienceDTO> getExperienceDetail(@PathVariable("id")Long id);

    @Operation(summary = "체험 상세 목록",
            description = "체험 상세 정보 아래로 신청과 평점 목록을 보여준다.",
            parameters = {
                @Parameter(name = "page", description = "상세 정보 안에서 목록을 출력할 페이지 번호"),
                @Parameter(name = "id", description = "공고의 상세 정보를 출력할 아이디")
            }
    )
    public ResponseEntity<AdminExperienceDetailDTO> getExperienceDetailList(@PathVariable("page")int page, @PathVariable("id")Long id);

    @Operation(summary = "인턴 목록",
            description = "인턴 공고 전체를 목록으로 보여준다.",
            parameters = {
                @Parameter(name = "page", description = "목록을 출력할 페이지 번호"),
                @Parameter(name = "keyword", description = "목록을 출력할 키워드(필수X)")
            }
    )
    public ResponseEntity<AdminInternNoticeListCriteriaDTO> getInternNoticeList(@PathVariable("page") int page,
                                                                @RequestParam(required = false) String keyword);

    @Operation(summary = "인턴 상세",
            description = "인턴 목록의 각 상세 정보를 보여준다.",
            parameters = {
                @Parameter(name = "id", description = "공고의 상세 정보를 출력할 아이디")
            }
    )
    public ResponseEntity<AdminInternNoticeDetailDTO> getInternNoticeDetail(@PathVariable("id")Long id);

    @Operation(summary = "인턴 상세 목록",
            description = "인턴 상세 정보 아래로 신청 목록을 보여준다.",
            parameters = {
                @Parameter(name = "id", description = "공고의 상세 정보를 출력할 아이디"),
                @Parameter(name = "page", description = "상세 정보 안에서 목록을 출력할 페이지 번호")
            }
    )
    public ResponseEntity<AdminInternNoticeDetailCriteriaDTO> getInternNoticeDetailList(@PathVariable("page")int page, @PathVariable("id")Long id);

    @Operation(summary = "신고 게시글 목록",
            description = "전체 신고 게시글을 목록으로 보여준다.",
            parameters = {
                @Parameter(name = "page", description = "목록을 출력할 페이지 번호")
            }
    )
    public ResponseEntity<AdminReportCriteriaDTO> getNotifyList(@PathVariable("page") int page);

    @Operation(summary = "신고 게시글 상세",
            description = "신고 목록의 각 상세 정보를 보여준다.",
            parameters = {
                    @Parameter(name = "id", description = "신고 게시글의 상세 정보를 출력할 아이디")
            }
    )
    public ResponseEntity<AdminReportDTO> getNotifyDetail(@PathVariable("id")Long id);

    @Operation(summary = "배너 - 광고 목록",
            description = "기업에서 신청한 전체 광고를 목록으로 보여준다.",
            parameters = {
                @Parameter(name = "page", description = "목록을 출력할 페이지 번호"),
                @Parameter(name = "keyword", description = "목록을 출력할 키워드(필수X)"),
                @Parameter(name = "category", description = "목록을 출력할 카테고리(필수X)")
            }
    )
    public ResponseEntity<AdminAdvertisementCriteriaDTO> getAdvertisementList(@PathVariable("page") int page,
                                                              @RequestParam(required = false) String keyword,
                                                              @RequestParam(required = false) String category);

    @Operation(summary = "배너 - 광고 상세",
            description = "신청한 광고 목록의 각 상세 정보를 보여준다.",
            parameters = {
                @Parameter(name = "id", description = "광고 신청의 상세 정보를 출력할 아이디")
            }
    )
    public ResponseEntity<AdminAdvertisementDTO> getAdvertisementDetail(@PathVariable("id")Long id);

    @Operation(summary = "배너 - 현수막",
            description = "사이트에서 사용할 배너의 기본 이미지들을 확인할 수 있다.",
            parameters = {
                @Parameter(name = "page", description = "목록을 출력할 페이지 번호"),
            }
    )
    public ResponseEntity<BannerFileCriteriaDTO> getBanner(@PathVariable("page") int page);

    @Operation(summary = "결제 - 광고",
            description = "기업에서 광고를 신청하면서 결제한 내역을 확인할 수 있다.",
            parameters = {
                @Parameter(name = "page", description = "목록을 출력할 페이지 번호"),
                @Parameter(name = "keyword", description = "목록을 출력할 키워드(필수X)"),
                @Parameter(name = "category", description = "목록을 출력할 카테고리(필수X)")
            }
    )
    public ResponseEntity<AdminPaymentAdvertiseCriteriaDTO> getPaymentAdvertise(@PathVariable("page") int page,
                                                                @RequestParam(required = false) String keyword,
                                                                @RequestParam(required = false) String category);

    @Operation(summary = "결제 - 체험",
            description = "회원이 체험을 신청하면서 결제한 내역을 확인할 수 있다.",
            parameters = {
                @Parameter(name = "page", description = "목록을 출력할 페이지 번호"),
                @Parameter(name = "keyword", description = "목록을 출력할 키워드(필수X)"),
                @Parameter(name = "category", description = "목록을 출력할 카테고리(필수X)")
            }
    )
    public ResponseEntity<AdminPaymentExperienceCriteriaDTO> getPaymentExperience(@PathVariable("page") int page,
                                                                  @RequestParam(required = false) String keyword,
                                                                  @RequestParam(required = false) String category);
}
