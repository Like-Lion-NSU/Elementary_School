package thisisus.school.member.domain;

import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Getter
@RequiredArgsConstructor
public enum Role {

    TEACHER("ROLE_TEACHER", "선생님"),
    STUDENT("ROLE_STUDENT", "학생");

    private final String key;
    private final String title;

    @ManyToOne(fetch = LAZY)     // 여러 상품을 한명이 주문 -> 다대일
    @JoinColumn(name = "member_id") // Fk=member_id, 연관관계 주인
    private Member member;  // 멤버

//    public static class MemberResponseDto {
//
//        @Builder
//        @Getter
//        @AllArgsConstructor
//        public static class TokenInfo {
//            private String grantType;
//            private String accessToken;
//            private Long accessTokenExpirationTime;
//            private String refreshToken;
//            private Long refreshTokenExpirationTime;
//        }
//    }
}
