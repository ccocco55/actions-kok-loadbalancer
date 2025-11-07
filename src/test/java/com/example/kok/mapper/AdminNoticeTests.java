//package com.example.kok.mapper;
//
//import com.example.kok.dto.AdminNoticeCriteriaDTO;
//import com.example.kok.dto.AdminNoticeDTO;
//import com.example.kok.repository.AdminNoticeDAO;
//import com.example.kok.service.AdminService;
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
//public class AdminNoticeTests {
//    @Autowired
//    private AdminNoticeMapper adminNoticeMapper;
//    @Autowired
//    private AdminNoticeDAO adminNoticeDAO;
//    @Autowired
//    private AdminService adminService;
//
//    //    Mapper
//    @Test
//    public void testInsertNotice () {
//        for(int i = 0; i < 134; i++) {
//            AdminNoticeDTO adminNoticeDTO = new AdminNoticeDTO();
//            adminNoticeDTO.setAdminNoticeTitle("공지 제목" + (i + 1));
//            adminNoticeDTO.setAdminNoticeContent("본문의 첫줄은 부제목으로 사용됩니다. 그 아래로는 본문 내용이 들어갑니다. 공지 게시글 내용이 들어가는 공간입니다." + (i + 1));
//
//            adminNoticeMapper.insertNotice(adminService.toVO(adminNoticeDTO));
//            log.info("{}", adminNoticeDTO);
//        }
//    }
//
//    @Test
//    public void testSelectNoticeAll() {
//        Criteria criteria = new Criteria(1, adminNoticeMapper.countNoticeAll());
//        List<AdminNoticeDTO> adminNoticeDTO = adminNoticeMapper.selectNoticeAll(criteria);
//        adminNoticeMapper.selectNoticeAll(criteria).stream().map(AdminNoticeDTO::toString).forEach(log::info);
//    }
//
//    @Test
//    public void testCountNoticeAll() {
//        log.info("전체 개수: {}", adminNoticeMapper.countNoticeAll());
//    }
//
//    @Test
//    public void testUpdateNoticeFromId() {
//        AdminNoticeDTO adminNoticeDTO = new AdminNoticeDTO();
//        adminNoticeDTO.setAdminNoticeTitle("수정된 공지 제목3");
//        adminNoticeDTO.setAdminNoticeContent("수정된 공지 게시글 내용3");
//        adminNoticeDTO.setId(3L);
//
//        adminNoticeMapper.updateNoticeFromId(adminService.toVO(adminNoticeDTO));
//        log.info("{}", adminNoticeDTO);
//    }
//
//    @Test
//    public void testDeleteNoticeFromId() {
//        log.info("삭제 전 전체 개수: {}", adminNoticeMapper.countNoticeAll());
//        adminNoticeMapper.deleteNoticeFromId(30L);
//        log.info("삭제 후 전체 개수: {}", adminNoticeMapper.countNoticeAll());
//    }
//
////    DAO
//    @Test
//    public void testInsert() {
//        for(int i = 0; i < 9; i++) {
//            AdminNoticeDTO adminNoticeDTO = new AdminNoticeDTO();
//            adminNoticeDTO.setAdminNoticeTitle("공지 제목0" + (i + 1));
//            adminNoticeDTO.setAdminNoticeContent("공지 게시글 내용0" + (i + 1));
//
//            adminNoticeDAO.insert(adminService.toVO(adminNoticeDTO));
//            log.info("{}\n", adminNoticeDTO);
//        }
//    }
//
//    @Test
//    public void testSelectAll() {
//        Criteria criteria = new Criteria(1, 59);
//        log.info(criteria.toString());
//        adminNoticeDAO.selectAll(criteria).stream().map(AdminNoticeDTO::toString).forEach(log::info);
//    }
//
//    @Test
//    public void testCountAll(){
//        log.info("전체 개수: {}", adminNoticeDAO.countAll());
//    }
//
//    @Test
//    public void testUpdateNotice() {
//        AdminNoticeDTO adminNoticeDTO = new AdminNoticeDTO();
//        adminNoticeDTO.setAdminNoticeTitle("수정된 공지 제목4");
//        adminNoticeDTO.setAdminNoticeContent("수정된 공지 게시글 내용4");
//        adminNoticeDTO.setId(4L);
//
//        adminNoticeDAO.updateNotice(adminService.toVO(adminNoticeDTO));
//        log.info("수정된 게시글: {}", adminNoticeDTO);
//    }
//
//    @Test
//    public void testDeleteNotice() {
//        adminNoticeDAO.deleteNotice(60L);
//    }
//
////    Service
//    @Test
//    public void testWrite() {
//        for(int i = 0; i < 80; i++) {
//            AdminNoticeDTO adminNoticeDTO = new AdminNoticeDTO();
//            adminNoticeDTO.setAdminNoticeTitle("공지 제목" + (i + 1));
//            adminNoticeDTO.setAdminNoticeContent("공지 게시글 내용" + (i + 1));
//
//            adminService.write(adminNoticeDTO);
//            log.info("{}\n", adminNoticeDTO);
//        }
//    }
//
//    @Test
//    public void testGetList(){
//        int page = 5;
//        Criteria criteria = new Criteria(page, 80);
//        log.info(criteria.toString());
//        AdminNoticeCriteriaDTO adminNoticeCriteriaDTO = adminService.getList(page);
//        log.info(adminNoticeCriteriaDTO.toString());
//    }
//
//    @Test
//    public void testUpdate(){
//        AdminNoticeDTO adminNoticeDTO = new AdminNoticeDTO();
//        adminNoticeDTO.setAdminNoticeTitle("수정된 공지 제목5");
//        adminNoticeDTO.setAdminNoticeContent("수정된 공지 게시글 내용5");
//        adminNoticeDTO.setId(5L);
//
//        adminService.update(adminNoticeDTO);
//        log.info("수정된 게시글: {}", adminNoticeDTO);
//    }
//
//    @Test
//    public void testDelete(){
//        log.info("삭제 전 전체 개수: {}", adminNoticeDAO.countAll());
//        adminService.delete(80L);
//        log.info("삭제 후 전체 개수: {}", adminNoticeDAO.countAll());
//    }
//}
