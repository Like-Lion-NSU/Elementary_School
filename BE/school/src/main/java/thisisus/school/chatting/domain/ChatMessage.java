package thisisus.school.chatting.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Document(collection = "chatMessages")
@NoArgsConstructor
@Getter
public class ChatMessage {
	@Id
	private String id;
	private Long roomId;
	private Long senderId;
	private String message;
	private LocalDateTime sendTime;

	public ChatMessage(Long roomId, Long senderId, String message) {
		this.roomId = roomId;
		this.senderId = senderId;
		this.message = message;
		this.sendTime = LocalDateTime.now();
	}
}