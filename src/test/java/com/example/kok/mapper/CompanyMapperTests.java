//package com.example.kok.mapper;
//
//import com.example.kok.repository.CompanyDAO;
//import com.example.kok.service.CompanyService;
//import com.example.kok.util.Criteria;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Slf4j
//public class CompanyMapperTests {
//    @Autowired
//    CompanyMapper companyMapper;
//    @Autowired
//    CompanyDAO companyDAO;
//    @Autowired
//    CompanyService companyService;
//
//    //    mapperTests
//    @Test
//    public void selectCompanyById() {
//        log.info("selectCompanyById: {}", companyMapper.selectCompany(6L));
//    }
//
//    @Test
//    public void selectCompanyList() {
//        Criteria criteria = new Criteria(1, 0);
//        log.info("selectCompanyList: {}", companyMapper.selectCompanyList(criteria, ""));
//    }
//
//    @Test
//    public void countCompany() {
//        log.info("countCompany: {}", companyMapper.selectCompanyCount(""));
//    }
//
//    //    DaoTests
//    @Test
//    public void selectCompanyByIdDAOTests() {
//        log.info("selectCompanyById: {}", companyDAO.selectCompany(6L));
//    }
//
//    @Test
//    public void selectCompanyListDAOTests() {
//        Criteria criteria = new Criteria(1, 0);
//        log.info("selectCompanyList: {}", companyDAO.selectCompanyList(criteria, ""));
//    }
//
//    @Test
//    public void countCompanyDAOTests() {
//        log.info("countCompany: {}", companyDAO.selectCompanyCount(""));
//    }
//
////    serviceTests
//
//    @Test
//    public void findListCompany() {
//
//        log.info("listCompany: {}", companyService.findAllCompanies(1, ""));
//    }
//
//    @Test
//    public void findCompanyServiceTests() {
//        log.info("findCompanyService: {}", companyService.findCompany(6L));
//    }
//}
