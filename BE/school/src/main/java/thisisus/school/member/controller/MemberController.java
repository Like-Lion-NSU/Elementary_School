package thisisus.school.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thisisus.school.auth.config.AuthenticatedMemberId;
import thisisus.school.common.response.SuccessResonse;
import thisisus.school.member.dto.MemberInfoResponse;
import thisisus.school.member.service.MemberService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/me")
  public SuccessResonse findMember(@AuthenticatedMemberId final Long memberId) {
    MemberInfoResponse memberInfoResponse = memberService.findMember(memberId);
    return SuccessResonse.of(memberInfoResponse);
  }
}
