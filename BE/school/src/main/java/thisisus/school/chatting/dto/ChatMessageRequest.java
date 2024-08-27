package thisisus.school.chatting.dto;

import thisisus.school.chatting.domain.ChatMessage;

public record ChatMessageRequest(
	Long roomId,
	Long senderId,
	String message
) {
	public ChatMessage toEntity(){
		return new ChatMessage(roomId, senderId, message);
	}
}