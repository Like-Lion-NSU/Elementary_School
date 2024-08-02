package thisisus.school.member.domain;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String nickname;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Enumerated(EnumType.STRING)
	private MemberStatus memberStatus;
	private LocalDate deletedAt;

	@Builder
	public Member(String name, String email, String nickname, Role role) {
		this.name = name;
		this.email = email;
		this.nickname = nickname;
		this.role = role;
		this.memberStatus = MemberStatus.ACTIVE;
	}

	public void update(String nickname, Role role) {
		this.nickname = nickname;
		this.role = role;
	}

	public void reRegistration() {
		this.memberStatus = MemberStatus.ACTIVE;
		this.role = Role.STUDENT;
		this.deletedAt = null;
	}

	public void delete() {
		this.memberStatus = MemberStatus.DELETED;
		this.role = Role.GUEST;
		this.deletedAt = LocalDate.now().plus(Period.ofMonths(3));
	}
}