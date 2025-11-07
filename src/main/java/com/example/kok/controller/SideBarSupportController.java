package com.example.kok.controller;

import com.example.kok.common.exception.PostNotFoundException;
import com.example.kok.dto.AdminNoticeCriteriaDTO;
import com.example.kok.dto.AdminNoticeDTO;
import com.example.kok.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/**")
@RequiredArgsConstructor
public class SideBarSupportController implements SideBarSupportControllerDocs{
    private final AdminService adminService;

    @GetMapping("side-bar/support/{page}")
    public ResponseEntity<AdminNoticeCriteriaDTO> goToSupportSideBar (@PathVariable int page){
        AdminNoticeCriteriaDTO adminNoticeCriteriaDTO = adminService.getList(page);
        return ResponseEntity.ok(adminNoticeCriteriaDTO);
    }

    @GetMapping("side-bar/support/detail/{id}")
    public ResponseEntity<AdminNoticeDTO> goToSupportSideBarDetail (@PathVariable Long id) {
        AdminNoticeDTO adminNoticeDTO = adminService.getNotice(id);
        return ResponseEntity.ok(adminNoticeDTO);
    }
}
