package thisisus.school.auth.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import thisisus.school.auth.dto.response.MemberInfoFromIdToken;
import thisisus.school.auth.exception.InvalidTokenException;
import thisisus.school.auth.infrastructure.JwtTokenProvider;
import thisisus.school.auth.infrastructure.kakao.KakaoProperties;
import thisisus.school.auth.infrastructure.oidc.PublicKeyDto;
import thisisus.school.auth.infrastructure.oidc.PublicKeysDto;

@Component
@RequiredArgsConstructor
public class LoginByIdTokenProcessor {

    private final PublicKeyProcessor publicKeyProcessor;
    private final JwtTokenProvider jwtTokenProvider;

    private final KakaoProperties kakaoProperties;

    public MemberInfoFromIdToken getMemberInfoFromIdToken(String idToken) {
        String kid = jwtTokenProvider.getKid(idToken);
        PublicKeysDto publicKeys = new PublicKeysDto();
        String iss = new String();
        String aud = new String();
        PublicKeysDto kakaoKeys = publicKeyProcessor.getKakaoPublicKeys();
        publicKeys = kakaoKeys;
        iss = kakaoProperties.getIss();
        aud = kakaoProperties.getClientId();

        PublicKeyDto key = publicKeys.getKeys().stream()
            .filter(k -> k.getKid().equals(kid))
            .findFirst()
            .orElseThrow(() -> new InvalidTokenException());
        return jwtTokenProvider.getMemberInfoFromIdToken(idToken, publicKeyProcessor.getRSAPublicKey(key), iss, aud);
    }
}