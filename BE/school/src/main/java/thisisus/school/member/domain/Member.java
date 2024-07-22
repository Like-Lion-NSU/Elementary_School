package thisisus.school.member.domain;

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

	@Builder
	public Member(String name, String email, String nickname, Role role) {
		this.name = name;
		this.email = email;
		this.nickname = nickname;
		this.role = role;
		this.memberStatus = MemberStatus.ACTIVE;
	}

	public void delete() {
		this.memberStatus = MemberStatus.DELETED;
		this.email = null;
	}

	public void update(String nickname, Role role) {
		this.nickname = nickname;
		this.role = role;
	}
}