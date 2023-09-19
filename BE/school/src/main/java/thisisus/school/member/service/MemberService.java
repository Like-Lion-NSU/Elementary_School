package thisisus.school.member.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thisisus.school.member.domain.Member;
import thisisus.school.member.dto.MemberInfoDto;
import thisisus.school.member.etc.MemberNotFoundException;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.member.security.service.CustomUserDetails;
import thisisus.school.post.domain.Post;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(MemberService.class);

    public Member findMember(CustomUserDetails customUserDetails) {
        Member findMember = memberRepository.findByEmail(customUserDetails.getEmail()).get();
        return findMember;
    }


    public void SetRole(String value, CustomUserDetails customUserDetails) {
        Member member = findMember(customUserDetails);

//        Optional<Member> findMember = memberRepository.findByEmail(customUserDetails.getEmail());

        if (member == null) {
            LOGGER.info("[오류] 해당 {}가 존재하지 않습니다.", customUserDetails.getName());
            LOGGER.info("[오류] {} 역할 수정 실패", customUserDetails.getName());
            return ;
        }

//        Member member = findMember
//                .map(entity -> entity.setRole("TEACHER"))
//                .get();
        member.setRole(value);
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


    @Transactional
    public ResponseEntity<String> deleteMember(CustomUserDetails customUserDetails) {
        try {
            Member member = findMember(customUserDetails);
//            List<Post> posts = member.getPosts();
//            for(Post post : posts){
//                post.delete();
//            }
//            memberRepository.delete(member);
            member.delete();
            return ResponseEntity.status(HttpStatus.OK).body("Member deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting member");
        }
    }
}
