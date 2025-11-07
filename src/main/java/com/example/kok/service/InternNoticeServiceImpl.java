package com.example.kok.service;

import com.example.kok.dto.CompanyInternNoticeCriteriaDTO;
import com.example.kok.dto.CompanyProfileFileDTO;
import com.example.kok.dto.InternNoticeCriteriaDTO;
import com.example.kok.dto.InternNoticeDTO;
import com.example.kok.dto.SaveInternNoticeDTO;
import com.example.kok.repository.CompanyProfileFileDAO;
import com.example.kok.repository.InternNoticeDAO;
import com.example.kok.util.CompanyNoticeCriteria;
import com.example.kok.repository.SaveInternNoticeDAO;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InternNoticeServiceImpl implements InternNoticeService {

    private final InternNoticeDAO internNoticeDAO;
    private final FileService fileService;
    private final CompanyProfileFileDAO companyProfileFileDAO;
    private final S3Service s3Service;
    private final SaveInternNoticeDAO saveInternNoticeDAO;

    @Override
    public InternNoticeCriteriaDTO selectAllInternNotice(int page, Search search) {
//        System.out.println("서비스임플 실행해용");
        InternNoticeCriteriaDTO internNoticeCriteriaDTO = new InternNoticeCriteriaDTO();
        Criteria criteria = new Criteria(page, internNoticeDAO.findCountAll());
        List<InternNoticeDTO> interns=internNoticeDAO.findAll(criteria, search);
        interns.forEach(intern -> {
            LocalDate endDate = LocalDate.parse(intern.getInternNoticeEndDate());
            LocalDate today = LocalDate.now();
            if (endDate.isBefore(today)) {
                long days = ChronoUnit.DAYS.between(today, endDate);
                intern.setRemainingDays(days);
            } else {
                intern.setRemainingDays(0L); // endDate보다 today가 이전일 경우 0
            }
            fileService.findFileByCompanyId(intern.getCompanyId())
                    .ifPresentOrElse(fileDTO -> {
                        intern.setFileName(fileDTO.getFileName());
                        intern.setFilePath(fileDTO.getFilePath());
                    }, ()->{
                        intern.setFileName("image.png");
                        intern.setFilePath("");
                    });
        });

        if(criteria.isHasMore()){
            interns.remove(interns.size() - 1);
        }

        internNoticeCriteriaDTO.setInterns(interns);
        internNoticeCriteriaDTO.setCriteria(criteria);
        return internNoticeCriteriaDTO;
    }

    @Override
    public void setPreSignedUrl(CompanyProfileFileDTO companyProfileFileDTO) {
        // 회사 ID로 파일 조회
        CompanyProfileFileDTO profile = companyProfileFileDAO.findFileByCompanyId(companyProfileFileDTO.getCompanyId());

        if (profile != null && profile.getFilePath() != null && !profile.getFilePath().isEmpty()) {
            String presignedUrl = s3Service.getPreSignedUrl(profile.getFilePath(), Duration.ofMinutes(5));
            companyProfileFileDTO.setFilePath(presignedUrl);
        } else {
            companyProfileFileDTO.setFilePath("/images/main-page/image.png");
        }
    }


    @Override
    @Cacheable(value = "internNoticeDTO", key = "#id")
    public InternNoticeDTO findNoticeById(Long id) {
        InternNoticeDTO result= internNoticeDAO.findById(id);
        String jobName= internNoticeDAO.findJobNameByID(id);
        result.setJobName(jobName);
        LocalDate endDate = LocalDate.parse(result.getInternNoticeEndDate());
        LocalDate today = LocalDate.now();
        if (!endDate.isBefore(today)) {
            long days = ChronoUnit.DAYS.between(today, endDate);
            result.setRemainingDays(days);
        } else {
            result.setRemainingDays(0L); // endDate보다 today가 이전일 경우 0
        }
        fileService.findFileByCompanyId(result.getCompanyId())
                .ifPresentOrElse(fileDTO -> {
                    result.setFileName(fileDTO.getFileName());
                    result.setFilePath(fileDTO.getFilePath());
                }, ()->{
                    result.setFileName("image.png");
                    result.setFilePath("");
                });
        return result;
    }

    @Override
    public void saveInt(SaveInternNoticeDTO saveInternNoticeDTO) {
        saveInternNoticeDAO.saveInt(saveInternNoticeDTO);
    }

    @Override
    public void deleteInt(SaveInternNoticeDTO saveInternNoticeDTO) {
        saveInternNoticeDAO.deleteInt(saveInternNoticeDTO);
    }

    @Override
    public List<InternNoticeDTO> findLatestFour() {
        List<InternNoticeDTO> interns = internNoticeDAO.findLatestFour();

        interns.forEach(intern -> {
            LocalDate endDate = LocalDate.parse(intern.getInternNoticeEndDate());
            LocalDate today = LocalDate.now();

            if (endDate != null) {
                if (endDate.isBefore(today)) {
                    long days = ChronoUnit.DAYS.between(today, endDate);
                    intern.setRemainingDays(days);
                } else {
                    intern.setRemainingDays(0L);
                }
            } else {
                intern.setRemainingDays(0L);
            }

            fileService.findFileByCompanyId(intern.getCompanyId())
                    .ifPresentOrElse(fileDTO -> {
                        intern.setFileName(fileDTO.getFileName());
                        intern.setFilePath(fileDTO.getFilePath());
                    }, () -> {
                        intern.setFileName("image.png");
                        intern.setFilePath("/images/mypage/logo_1757380047672.webp"); // 임시 이미지 설정
                    });
        });
        return interns;
    }

    @Override
    public boolean isSavedInt(SaveInternNoticeDTO saveInternNoticeDTO) {
        boolean result=saveInternNoticeDAO.idSavedInt(saveInternNoticeDTO);
        return result;
    }

    //    기업별 인턴 공고 목록
    @Override
    public CompanyInternNoticeCriteriaDTO getInternNoticesByCompanyId(int page, Long companyId, Search search) {
        int total = internNoticeDAO.findCountByCompanyId(companyId, search);
        CompanyNoticeCriteria criteria = new CompanyNoticeCriteria(page, total);

        List<InternNoticeDTO> notices = internNoticeDAO.findAllByCompanyId(companyId, criteria, search);

        criteria.setHasMore(criteria.getPage() < criteria.getRealEnd());

        CompanyInternNoticeCriteriaDTO companyInternNoticeCriteriaDTO = new CompanyInternNoticeCriteriaDTO();
        companyInternNoticeCriteriaDTO.setCriteria(criteria);
        companyInternNoticeCriteriaDTO.setInterns(notices);
        return companyInternNoticeCriteriaDTO;
    }
}