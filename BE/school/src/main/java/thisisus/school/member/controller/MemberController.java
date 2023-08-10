package thisisus.school.member.controller;

import org.apache.tomcat.jni.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import thisisus.school.member.domain.Member;
import thisisus.school.member.security.dto.CustomMemberDetails;
import thisisus.school.member.security.dto.MemberResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MemberController {

    @GetMapping("/test2")
    public String index(Model model, @AuthenticationPrincipal CustomMemberDetails customMemberDetails) {

        String view = "test2";

        if (customMemberDetails != null) {

            String memberName = customMemberDetails.getName();

            model.addAttribute("user", memberName);
            model.addAttribute("provider", customMemberDetails.getMember().getProvider());
//            System.out.println(memberName + customMemberDetails.getMember().getProvider());
            return customMemberDetails.getName() + customMemberDetails.getMember().getProvider();
        }

        return "Fail";
    }

    @GetMapping("/api/user")
    public Authentication user(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2User) {
        System.out.println("authentication = " + authentication + ", oAuth2User = " + oAuth2User);
        return authentication;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
