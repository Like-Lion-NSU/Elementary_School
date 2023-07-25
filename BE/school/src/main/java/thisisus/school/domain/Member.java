package thisisus.school.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.spi.CalendarDataProvider;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // PK 시스템 상의 id 값

    /**
     * 회원 이름, 이메일, 생년월일은 소셜 로그인(구글, 카카오톡)을 통해 가져온다.
     */
    private String name;        // 회원 이름

    private String email;       // 회원 이메일

    private String birth;

    /**
     * 회원 가입 후 선택에 따라 role 정해짐
     */
    private String role;

    /**
     * 기본 point 값 얼마?
     */
    private Long point;

    private Calendar lastLogin;
}