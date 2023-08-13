package thisisus.school.member.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import thisisus.school.member.security.service.CustomUserDetails;

@Controller
//@RequestMapping("/social")
public class SocialLoginController {

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
        return "redirect:/http://localhost:8081/login/oauth2/code/google";
    }

    @GetMapping("/social/kakao")
    public String redirectKakao() {
        return "redirect:/http://localhost:8081/login/oauth2/code/kakao";
    }

    @PostMapping("/")
    public String setRole(@RequestParam String value, @AuthenticationPrincipal CustomUserDetails user) {

        return "redirect:/http://localhost:8081/mypage";
    }
}
