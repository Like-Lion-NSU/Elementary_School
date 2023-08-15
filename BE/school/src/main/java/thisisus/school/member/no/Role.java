//package thisisus.school.member.domain;
//
//import lombok.*;
//
//import javax.annotation.Resources;
//import javax.persistence.*;
//
//import java.util.HashSet;
//import java.util.LinkedHashSet;
//import java.util.Set;
//
//import static javax.persistence.FetchType.LAZY;
//
//@Data
//@Entity
//@Table(name = "role")
//@RequiredArgsConstructor
//public class Role {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "role_id")
//    private Long id;
//
//    @Column(name = "role_name")
//    private String roleName;
//
//    @Column(name = "role_desc")
//    private String roleDesc;
//
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;
//
//    public Role(Long id, String roleName, String roleDesc) {
//        this.id = id;
//        this.roleName = roleName;
//        this.roleDesc = roleDesc;
//    }
//
///*    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleSet")
//    @OrderBy("ordernum desc")
//    private Set<Resources> resourcesSet = new LinkedHashSet<>();*/
//
//
////    TEACHER("ROLE_TEACHER", "선생님"),
////    STUDENT("ROLE_STUDENT", "학생");
////
////    private final String key;
////    private final String title;
////
////    @ManyToOne(fetch = LAZY)     // 여러 상품을 한명이 주문 -> 다대일
////    @JoinColumn(name = "member_id") // Fk=member_id, 연관관계 주인
////    private Member member;  // 멤버
//
////    public static class MemberResponseDto {
////
////        @Builder
////        @Getter
////        @AllArgsConstructor
////        public static class TokenInfo {
////            private String grantType;
////            private String accessToken;
////            private Long accessTokenExpirationTime;
////            private String refreshToken;
////            private Long refreshTokenExpirationTime;
////        }
////    }
//}
