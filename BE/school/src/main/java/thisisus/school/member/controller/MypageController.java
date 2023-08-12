package thisisus.school.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import thisisus.school.member.security.service.CustomUserDetails;

@Controller
public class MypageController {

    @GetMapping("/question")
    public ResponseEntity<CustomUserDetails> getMyPage(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        if (customUserDetails != null) {
            return ResponseEntity.ok(customUserDetails);        // front에서 까서 확인
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
