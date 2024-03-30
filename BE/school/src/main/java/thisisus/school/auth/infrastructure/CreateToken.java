package thisisus.school.auth.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import thisisus.school.auth.dto.response.AuthResponse;
import thisisus.school.member.domain.Member;

@Component
@RequiredArgsConstructor
public class CreateToken {

  private final JwtTokenProvider jwtTokenProvider;
  public AuthResponse createAccessToken(Member member){
    String accessToken = jwtTokenProvider.createAccessToken(member.getId(), member.getRole().name());
    String refreshToken = jwtTokenProvider.createRefreshToken(member.getId(), member.getRole().name());
    return new AuthResponse(accessToken,refreshToken);
  }

  public AuthResponse refreshToken(Member member, String refreshToken){
    String accessToken = jwtTokenProvider.createAccessToken(member.getId(), member.getRole().toString());
    return new AuthResponse(accessToken,refreshToken);
  }
}
