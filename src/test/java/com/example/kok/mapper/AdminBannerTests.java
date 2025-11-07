//package com.example.kok.mapper;
//
//import com.example.kok.dto.BannerFileCriteriaDTO;
//import com.example.kok.dto.BannerFileDTO;
//import com.example.kok.util.Criteria;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Slf4j
//public class AdminBannerTests {
//    @Autowired
//    private AdminBannerMapper adminBannerMapper;
//
////    Mapper
//    @Test
//    public void testInsertBannerFile(){
//        BannerFileDTO bannerFileDTO = new BannerFileDTO();
//        bannerFileDTO.setBannerFileOriginName("원본 이름");
//        bannerFileDTO.setBannerFileName("파일 이름");
//        bannerFileDTO.setBannerFilePath("파일 저장 경로");
//        bannerFileDTO.setBannerFileSize("파일 크기");
//        bannerFileDTO.setBannerFileContentType("파일 형식");
//        adminBannerMapper.insertBannerFile(bannerFileDTO);
//        log.info("{}", bannerFileDTO);
//    }
//
//    @Test
//    public void testSelectBannerFile(){
//        int page = 1;
//        Criteria criteria = new Criteria(page, adminBannerMapper.countBannerFiles());
//        adminBannerMapper.selectBannerFiles(criteria).stream().map(BannerFileDTO::toString).forEach(log::info);
//        log.info("{}", criteria);
//    }
//
//    @Test
//    public void testCountBannerFiles(){
//        log.info("전체 개수: {}", adminBannerMapper.countBannerFiles());
//    }
//
//    @Test
//    public void testDeleteBannerFile(){
//        Long id = 1L;
//        log.info("삭제 전 전체 개수: {}", adminBannerMapper.countBannerFiles());
//        adminBannerMapper.deleteBannerFile(id);
//        log.info("삭제 후 전체 개수: {}", adminBannerMapper.countBannerFiles());
//    }
//}
