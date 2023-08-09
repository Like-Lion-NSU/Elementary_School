//package thisisus.school.member.security.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import thisisus.school.member.repository.MemberRepository;
//import thisisus.school.member.security.service.UserDetailService;
//
//@Slf4j
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailService {
//
//    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailService.class);
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadMemberbyEmail(String email) throws UsernameNotFoundException {
//        LOGGER.info("[loadMemberByEmail] loadMemberByEmail 수행, email : {}", email);
//        return memberRepository.getByEmail(email);
//    }
//}
