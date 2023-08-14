package thisisus.school.member.controller;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import thisisus.school.member.dto.MemberInfoDto;
import thisisus.school.member.security.service.CustomUserDetails;
import thisisus.school.member.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MypageController {

    private final MemberService memberService;


    @GetMapping("/mypage")
    public ResponseEntity<MemberInfoDto> getMyPage( @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        try {
            MemberInfoDto memberInfo = memberService.getMemberInfo(customUserDetails);
            return ResponseEntity.ok(memberInfo);       // Json 형태로 전달됨
        } catch (ExpiredJwtException expiredJwtException) {
            // AccessToken 만료되었을 경우
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // 401 Unauthorized 응답
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 다른 예외 처리
        }
    }

    @GetMapping("/drop")
    public String deleteMember(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        memberService.deleteMember(customUserDetails);

        return "redirect:/http://localhost:8081/login";
    }
}
