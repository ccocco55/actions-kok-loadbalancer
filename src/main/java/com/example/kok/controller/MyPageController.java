package com.example.kok.controller;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.AdvertisementDTO;
import com.example.kok.dto.UserMemberDTO;
import com.example.kok.repository.FollowDAO;
import com.example.kok.service.AdvertisementService;
import com.example.kok.service.CommunityPostService;
import com.example.kok.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mypage/**")
@RequiredArgsConstructor
public class MyPageController {
    private final MemberService memberService;
    private final AdvertisementService advertisementService;
    private final CommunityPostService communityPostService;
    @GetMapping("page")
    public String goToMyPage(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        Optional<UserMemberDTO> member=memberService.findProfileByMemberId(customUserDetails.getId());
        String profileFile=null;
        if(member.get().getFilePath()!=null){
            profileFile=member.get().getFilePath();
        }
        model.addAttribute("profileFile", profileFile);
//        model.addAttribute("user", customUserDetails);
//        System.out.println(customUserDetails.getMemberProfileUrl());
        List<AdvertisementDTO> advertisements = advertisementService.getAllAdvertisements();
//        System.out.println("##############################################");
//        System.out.println(advertisements);
        model.addAttribute("advertisements", advertisements);
        int postCount=communityPostService.getPostsCountByMemberId(customUserDetails.getId());
        model.addAttribute("postCount", postCount);
        return "mypage/page";
    }
}
