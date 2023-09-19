package thisisus.school.member.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import thisisus.school.member.security.service.CustomUserDetails;
import thisisus.school.member.service.MemberService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class KioskController {

    private final MemberService memberService;
    private final Logger LOGGER = LoggerFactory.getLogger(KioskController.class);

    @PostMapping("/practice/point")
    private ResponseEntity<String> updatesPoint(@RequestParam String lastScore, @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        if (customUserDetails != null) {
            try {
//                String lastScore = requestData.get("data");
                LOGGER.info(lastScore);
                memberService.updatePoint(lastScore, customUserDetails);
                return ResponseEntity.ok("포인트 업데이트 성공");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.badRequest().body("해당 유저가 없습니다.");
    }
}
