package thisisus.school.member.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import thisisus.school.member.domain.Member;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.member.security.jwt.JwtTokenProvider;
import thisisus.school.member.security.service.CustomUserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

//    @GetMapping("/")
//    public String index() {
//        return "/index";
//    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('STUDENT')")
    public Member getCurrentUser(@AuthenticationPrincipal CustomUserDetails user) {
        return memberRepository.findByEmail(user.getEmail()).orElseThrow(() -> new IllegalStateException("not found user"));
    }

    @GetMapping("/test2")
    public String index(Model model, @AuthenticationPrincipal CustomUserDetails customMemberDetails) {

        String view = "test2";

        if (customMemberDetails != null) {

            String memberName = customMemberDetails.getName();

            model.addAttribute("user", memberName);
//            System.out.println(memberName + customMemberDetails.getMember().getProvider());
            return customMemberDetails.getName();
        }

        return "Fail";
    }

    @GetMapping("/api/user")
    public Authentication user(Authentication authentication, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        System.out.println("authentication = " + authentication + ", CustomMemberDetails = " + customUserDetails);
        return authentication;
    }

}
