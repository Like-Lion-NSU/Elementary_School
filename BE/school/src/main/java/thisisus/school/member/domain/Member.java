package thisisus.school.member.domain;

import static thisisus.school.member.domain.MemberStatus.*;
import static thisisus.school.member.domain.Role.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import thisisus.school.auth.exception.AlreadyRegisteredEmailException;
import thisisus.school.chatting.domain.ChatRequest;
import thisisus.school.chatting.exception.CannotRequestChatToStudentException;
import thisisus.school.comment.domain.Comment;
import thisisus.school.post.domain.Post;

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
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Post> posts = new ArrayList<>();
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ChatRequest> teacher = new ArrayList<>();
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ChatRequest> student = new ArrayList<>();
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

	private void reRegistration() {
		this.memberStatus = MemberStatus.ACTIVE;
		this.role = Role.STUDENT;
		this.deletedAt = null;
	}

	public void delete() {
		this.memberStatus = DELETED;
		this.role = Role.GUEST;
		this.deletedAt = LocalDate.now().plus(Period.ofMonths(3));
	}

	public void reRegisterIfDeleted() {
		if (this.getMemberStatus().equals(DELETED)) {
			this.reRegistration();
		} else {
			throw new AlreadyRegisteredEmailException();
		}
	}

	public void validateTeacherRole() {
		if (!this.role.equals(TEACHER)) {
			throw new CannotRequestChatToStudentException();
		}
	}
}