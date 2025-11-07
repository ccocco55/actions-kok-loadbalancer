//package com.example.kok.mapper;
//
//import com.example.kok.dto.ConsoleInternNoticeDTO;
//import com.example.kok.enumeration.Status;
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
//public class ConsoleInternTests {
//    @Autowired
//    private ConsoleInternNoticeMapper mapper;
//
//    @Test
//    void testSelectInternByCompany() {
//        // given
//        Long companyId = 1L;
//        Criteria criteria = new Criteria(1, 10);
//        Status status = Status.ACTIVE;
//        String keyword = "백엔드";
//
//        // when
//        List<ConsoleInternNoticeDTO> result = mapper.selectInternByCompany(companyId, criteria, status, keyword);
//
//        // then
////        assertNotNull(result);
////        assertFalse(result.isEmpty());
////        result.forEach(System.out::println);
//    }
//}
