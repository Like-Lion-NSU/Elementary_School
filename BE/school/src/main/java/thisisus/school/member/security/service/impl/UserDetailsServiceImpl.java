package thisisus.school.member.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import thisisus.school.member.domain.Member;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.member.security.service.CustomUserDetails;
import thisisus.school.member.security.service.UserDetailService;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailService.class);

    private final MemberRepository memberRepository;

    @Override
    public CustomUserDetails loadMemberbyEmail(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email).get();
        LOGGER.info("[loadMemberByEmail] loadMemberByEmail 수행, email : {}", email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }
        return CustomUserDetails.create(member);
    }
//    @Override
//    public CustomMemberDetails loadMemberbyEmail(String email) throws UsernameNotFoundException {
//
//        Member member = memberRepository.findByEmail(email).get();
//        LOGGER.info("[loadMemberByEmail] loadMemberByEmail 수행, email : {}", email);
//
//        if (member == null) {
//            throw new UsernameNotFoundException(email);
//        }
//        return CustomMemberDetails.create(member);
//    }

    @Override
    public UserDetails loadUserByEmail(String email) {
        LOGGER.info("[loadUserByEmail] loadUserByEmail 수행, email : {}", email);
        return memberRepository.getByEmail(email);
    }
}
