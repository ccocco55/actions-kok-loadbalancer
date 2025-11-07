//package com.example.kok.mapper;
//
//import com.example.kok.dto.CompanyDTO;
//import com.example.kok.repository.CompanyDAO;
//import com.example.kok.service.CompanyService;
//import com.example.kok.util.CompanySearch;
//import com.example.kok.util.Criteria;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//@Slf4j
//public class CompanyTests {
//    @Autowired
//    private CompanyMapper companyMapper;
//    @Autowired
//    private CompanyService companyService;
//    @Autowired
//    private CompanyDAO companyDAO;
//
//    @Test
//    void testKeywordSearch() {
//        Criteria criteria = new Criteria(1, 10);
//        CompanySearch search = new CompanySearch();
//        search.setKeyword("그린");
//
////        List<CompanyDTO> result = companyMapper.selectCompanyAll(criteria, search);
////
////        log.info("검색 결과: {}", result);
//    }
//}
