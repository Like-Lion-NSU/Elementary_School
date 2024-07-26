package thisisus.school.member.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import thisisus.school.auth.config.AuthenticatedMemberId;
import thisisus.school.common.response.SuccessResponse;
import thisisus.school.member.dto.MemberInfoResponse;
import thisisus.school.member.dto.UpdateMemberRequest;
import thisisus.school.member.service.MemberService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {

	private final MemberService memberService;

	@PatchMapping("/update")
	public SuccessResponse updateMember(
		@AuthenticatedMemberId final Long memberId,
		@RequestBody @Valid UpdateMemberRequest updateMemberRequest
	) {
		memberService.update(memberId, updateMemberRequest);
		return SuccessResponse.of();
	}

	@DeleteMapping("/delete")
	public SuccessResponse deleteMember(
		@AuthenticatedMemberId final Long memberId
	) {
		memberService.delete(memberId);
		return SuccessResponse.of();
	}

	@GetMapping("/me")
	public SuccessResponse findMember(
		@AuthenticatedMemberId final Long memberId
	) {
		MemberInfoResponse memberInfoResponse = memberService.findMember(memberId);
		return SuccessResponse.of(memberInfoResponse);
	}
}