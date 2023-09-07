package thisisus.school.member.controller;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import thisisus.school.member.security.service.CustomUserDetails;
import thisisus.school.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class SocialLoginController {

    private final MemberService memberService;
    private final Logger LOGGER = LoggerFactory.getLogger(SocialLoginController.class);

    @PostMapping("/role/decide")
    public ResponseEntity<String> setRole(@RequestParam String role, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        LOGGER.info("[value] value : {}", role);
        LOGGER.info("[customUserDetails] customUserDetails : {}", customUserDetails);

        memberService.SetRole(role, customUserDetails);


        return ResponseEntity.status(HttpStatus.OK).body("Role set successfully");
    }

}