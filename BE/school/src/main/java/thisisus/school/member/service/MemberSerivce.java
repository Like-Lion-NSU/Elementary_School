package thisisus.school.member.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import thisisus.school.member.domain.Member;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.member.security.service.CustomUserDetails;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberSerivce {

    private final MemberRepository memberRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(MemberSerivce.class);

    public void SetRole(String value, CustomUserDetails customUserDetails) {
        Optional<Member> findMember = memberRepository.findByEmail(customUserDetails.getEmail());

        if (findMember.isEmpty()) {
            LOGGER.info("[오류] 해당 {}가 존재하지 않습니다.", customUserDetails.getName());
            LOGGER.info("[오류] {} 역할 수정 실패", customUserDetails.getName());
            return ;
        }

        if (value == "teacher") {
            Member member = findMember
                    .map(entity -> entity.update(findMember.get()))
                    .get();        
        }

        
    }

}
