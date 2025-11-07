package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.ConsolePaymentCriteriaDTO;
import com.example.kok.service.ConsolePaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.Collections;

@Slf4j
@Controller
@RequestMapping("/enterprise-console/payment")
@RequiredArgsConstructor
public class ConsolePaymentController {
    private final ConsolePaymentService consolepaymentService;

//    기업 콘솔 결제내역
    @GetMapping
    public String goToList(@RequestParam(defaultValue = "1") int page,
                           @AuthenticationPrincipal CustomUserDetails customUserDetails,
                           Model model) {

        Long companyId = customUserDetails.getId();
        String companyName = customUserDetails.getCompanyName();
        String memberName = customUserDetails.getUsername();

        ConsolePaymentCriteriaDTO payment = consolepaymentService.getList(companyId, page);

        if (payment == null) {
            payment = new ConsolePaymentCriteriaDTO();
            payment.setPayments(Collections.emptyList());
        }

        model.addAttribute("payment", payment);
        model.addAttribute("companyName", companyName);
        model.addAttribute("memberName", memberName);

        return "enterprise-console/payment/console-payment";
    }

}
