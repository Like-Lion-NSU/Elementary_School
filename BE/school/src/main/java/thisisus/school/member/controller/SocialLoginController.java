package thisisus.school.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import thisisus.school.member.security.service.CustomUserDetails;
import thisisus.school.member.service.MemberService;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/social")
public class SocialLoginController {

    private final MemberService memberService;

    @GetMapping("/social")
    public String social() {
        return "social";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/social/google")
    public String redirectGoogle() {
        return "redirect:/oauth2/authorization/google";
    }

    @GetMapping("/social/kakao")
    public String redirectKakao() {
        return "redirect:/oauth2/authorization/kakao";
    }

    @PostMapping("role/decide")
    public String setRole(@RequestParam String value, @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        if (value.equals("TEACHER")) {
            memberService.SetRole(value, customUserDetails);
        }

        return "redirect:/http://localhost:8081/mypage";
    }
}
