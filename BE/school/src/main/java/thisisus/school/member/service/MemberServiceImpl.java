package thisisus.school.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thisisus.school.member.domain.Member;
import thisisus.school.member.dto.MemberInfoResponse;
import thisisus.school.member.dto.UpdateMemberRequest;
import thisisus.school.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void update(final Long memberId, final UpdateMemberRequest updateMemberRequest) {
        Member member = memberRepository.findByMemberId(memberId);
        member.update(updateMemberRequest.nickname(), updateMemberRequest.role());
    }

    @Override
    public void delete(final Long memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        member.delete();
    }

    @Override
    public MemberInfoResponse findMember(final Long memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        return new MemberInfoResponse(member.getEmail(), member.getRole());
    }
}