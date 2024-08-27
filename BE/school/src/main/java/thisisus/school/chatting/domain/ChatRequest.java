package thisisus.school.chatting.domain;

import static thisisus.school.chatting.domain.ChatStatus.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thisisus.school.common.BaseTimeEntity;
import thisisus.school.member.domain.Member;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class ChatRequest extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id", nullable = false)
	private Member teacher;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
	private Member student;

	@Enumerated(EnumType.STRING)
	private ChatStatus chatStatus;

	public ChatRequest(Member teacher, Member student) {
		this.teacher = teacher;
		this.student = student;
		this.chatStatus = PENDING;
	}

	public void changeStatusToPending() {
		this.chatStatus = PENDING;
	}

	public void changeStatusToDecliend() {
		this.chatStatus = DECLIEND;
	}

	public void changeStatusToAccepted() {
		this.chatStatus = ACCEPTED;
	}
}