package thisisus.school.member.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import thisisus.school.member.domain.Member;
import thisisus.school.member.domain.Role;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.member.security.etc.OAuthProcessingException;
import thisisus.school.member.security.util.AuthProvider;
import thisisus.school.member.security.util.OAuth2UserInfo;
import thisisus.school.member.security.util.OAuth2UserInfoFactory;

import java.util.Optional;


@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserServiceTest extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    // OAuth2UserRequest에 있는 Access Token으로 유저정보 get
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        return process(oAuth2UserRequest, oAuth2User);
    }

    // 획득한 유저정보를 Java Model과 맵핑하고 프로세스 진행
    private OAuth2User process(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        AuthProvider authProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId());
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());

        if (userInfo.getEmail().isEmpty()) {
            throw new OAuthProcessingException("Email not found from OAuth2 provider");
        }
        Optional<Member> userOptional = memberRepository.findByEmail(userInfo.getEmail());
        Member member;

        if (userOptional.isPresent()) {		// 이미 가입된 경우
            member = userOptional.get();
            if (authProvider != member.getAuthProvider()) {
                throw new OAuthProcessingException("Wrong Match Auth Provider");
            }

        } else {			// 가입되지 않은 경우
            member = createUser(userInfo, authProvider);
        }
        return CustomUserDetails.create(member, oAuth2User.getAttributes());
    }

    private Member createUser(OAuth2UserInfo userInfo, AuthProvider authProvider) {
        Member member = Member.builder()
                .email(userInfo.getEmail())
                .role(Role.STUDENT)
                .point(100L)
                .authProvider(authProvider)
                .build();
        return memberRepository.save(member);
    }
}