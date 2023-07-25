package thisisus.school.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//클라이언트로 보낼 jwtToken, accessToken등이 담긴 객체
@Getter
@Setter
@AllArgsConstructor
public class GetSocialOAuthRes {

    private String jwtToken;
    private int user_num;
    private String accessToken;
    private String tokenType;
}