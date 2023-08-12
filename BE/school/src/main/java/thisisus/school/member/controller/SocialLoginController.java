package thisisus.school.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
