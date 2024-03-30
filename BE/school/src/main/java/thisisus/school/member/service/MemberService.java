package thisisus.school.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thisisus.school.member.domain.Member;
import thisisus.school.member.dto.MemberInfoResponse;
import thisisus.school.member.exception.NotFoundMember;
import thisisus.school.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberInfoResponse findMember(Long memberId){
      Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMember::new);
      return new MemberInfoResponse(member.getEmail(), member.getRole());
    }
}
