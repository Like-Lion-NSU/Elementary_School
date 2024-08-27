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
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class ChatRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_request_id", nullable = false)
	private ChatRequest chatRequest;

	@Enumerated(EnumType.STRING)
	private ChatStatus chatStatus;

	public ChatRoom(ChatRequest chatRequest) {
		this.chatRequest = chatRequest;
		this.chatStatus = ACTIVE;
	}

	public void changeStatusToActive() {
		this.chatStatus = ACTIVE;
	}
}