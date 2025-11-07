package com.example.kok.controller;

import com.example.kok.dto.AdminCompanyCriteriaDTO;
import com.example.kok.dto.AdminCompanyDTO;
import com.example.kok.dto.AdminMemberCriteriaDTO;
import com.example.kok.dto.UserMemberDTO;
import com.example.kok.service.CompanyService;
import com.example.kok.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/company/")
@RequiredArgsConstructor
public class AdminCompaniesApiController implements AdminCompaniesApiControllerDocs{
    @Autowired
    private CompanyService companyService;

    @GetMapping("list/{page}")
    public ResponseEntity<?> findUserMembers(@PathVariable("page") int page, @RequestParam(required = false) String keyword) {

        AdminCompanyCriteriaDTO adminCompanyCriteriaDTO = companyService.findAllCompanies(page, keyword);
        if (adminCompanyCriteriaDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(adminCompanyCriteriaDTO);
        }

        return ResponseEntity.ok(adminCompanyCriteriaDTO);

    }

    @GetMapping("detail/{userId}")
    public ResponseEntity<?> findUserMemberById(@PathVariable("userId") Long userId) {
        AdminCompanyDTO adminCompanyDTO = companyService.findCompany(userId);
        return ResponseEntity.ok(adminCompanyDTO);
    }

}
