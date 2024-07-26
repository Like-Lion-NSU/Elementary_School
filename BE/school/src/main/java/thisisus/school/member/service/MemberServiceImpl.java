package thisisus.school.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import thisisus.school.member.domain.Member;
import thisisus.school.member.dto.MemberInfoResponse;
import thisisus.school.member.dto.UpdateMemberRequest;
import thisisus.school.member.exception.NotFoundMemberException;
import thisisus.school.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Override
	@Transactional
	public void update(final Long memberId, final UpdateMemberRequest updateMemberRequest) {
		final Member member = memberRepository.findById(memberId)
			.orElseThrow(NotFoundMemberException::new);
		member.update(updateMemberRequest.nickname(), updateMemberRequest.role());
	}

	@Override
	@Transactional
	public void delete(final Long memberId) {
		final Member member = memberRepository.findById(memberId)
			.orElseThrow(NotFoundMemberException::new);
		member.delete();
	}

	@Override
	public MemberInfoResponse findMember(final Long memberId) {
		final Member member = memberRepository.findById(memberId)
			.orElseThrow(NotFoundMemberException::new);
		return new MemberInfoResponse(member.getEmail(), member.getRole());
	}
}