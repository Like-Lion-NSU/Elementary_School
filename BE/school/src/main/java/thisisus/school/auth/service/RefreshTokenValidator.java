package thisisus.school.auth.service;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import thisisus.school.auth.exception.AlreadyLoggedOutException;
import thisisus.school.auth.exception.InvalidTokenException;
import thisisus.school.auth.exception.MemberRefreshTokenMismatchException;
import thisisus.school.auth.infrastructure.AuthTokenGenerator;
import thisisus.school.member.domain.Member;

@RequiredArgsConstructor
@Component
public class RefreshTokenValidator {

	private final AuthTokenGenerator authTokenGenerator;

	public void validateToken(String refreshToken) {
		if (!authTokenGenerator.isValidToken(refreshToken)) {
			throw new InvalidTokenException();
		}
	}

	public void validateTokenOwner(String refreshToken, Member member) {
		if (!member.getRefreshToken().equals(refreshToken)) {
			throw new MemberRefreshTokenMismatchException();
		}
	}

	public void validateLogoutToken(Member member) {
		if (member.getRefreshToken().isEmpty()) {
			throw new AlreadyLoggedOutException();
		}
	}
}