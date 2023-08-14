package thisisus.school.member.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import thisisus.school.member.domain.Member;
import thisisus.school.member.dto.MemberInfoDto;
import thisisus.school.member.etc.MemberNotFoundException;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.member.security.service.CustomUserDetails;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(MemberService.class);

    private Member findMember(CustomUserDetails customUserDetails) {
        Member findMember = memberRepository.findByEmail(customUserDetails.getEmail()).get();
        return findMember;
    }


    public void SetRole(String value, CustomUserDetails customUserDetails) {
        Member member = findMember(customUserDetails);

//        Optional<Member> findMember = memberRepository.findByEmail(customUserDetails.getEmail());

        if (member != null) {
            LOGGER.info("[오류] 해당 {}가 존재하지 않습니다.", customUserDetails.getName());
            LOGGER.info("[오류] {} 역할 수정 실패", customUserDetails.getName());
            return ;
        }

//        Member member = findMember
//                .map(entity -> entity.setRole("TEACHER"))
//                .get();
        member.setRole("TEACHER");
        memberRepository.save(member);
        return ;
    }

    public MemberInfoDto getMemberInfo(CustomUserDetails customUserDetails) {

        Member memberInfo = findMember(customUserDetails);

        if (memberInfo == null) {
            new MemberNotFoundException("Member not found");
        }
        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .email(memberInfo.getEmail())
                .role(memberInfo.getRole())
                .point(memberInfo.getPoint())
                .build();
        return memberInfoDto;

//        Member memberInfo = memberRepository.findByEmail(customUserDetails.getEmail())
//                .orElseThrow(() -> new MemberNotFoundException("Member not found"));
//        if (memberInfo != null) {
//            MemberInfoDto memberInfoDto = MemberInfoDto.builder()
//                    .email(memberInfo.getEmail())
//                    .role(memberInfo.getRole())
//                    .point(memberInfo.getPoint())
//                    .build();
//            return memberInfoDto;
//        }

    }

    public void updatePoint(String point, CustomUserDetails customUserDetails) {

        Long updatePoint = Long.parseLong(point);

        Member updateMember = findMember(customUserDetails);

        if (updateMember == null) {
            throw new MemberNotFoundException("Member not found");
        } else {
            updateMember.setPoint(updateMember.getPoint() + updatePoint);
            memberRepository.save(updateMember);
        }
    }


    public void deleteMember(CustomUserDetails customUserDetails) {
        Member deleteMember = findMember(customUserDetails);
        memberRepository.deleteById(deleteMember.getId());
    }
}
