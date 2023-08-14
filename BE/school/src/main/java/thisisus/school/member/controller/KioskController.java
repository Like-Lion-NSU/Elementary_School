package thisisus.school.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import thisisus.school.member.security.service.CustomUserDetails;
import thisisus.school.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class KioskController {

    private final MemberService memberService;

    @PostMapping("/Kiosk/add-point")
    private ResponseEntity<String> updatesPoint(@RequestParam String value, @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        if (customUserDetails != null) {
            try {
                memberService.updatePoint(value, customUserDetails);
                return ResponseEntity.ok("포인트 업데이트 성공");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.badRequest().body("해당 유저가 없습니다.");
    }
}
