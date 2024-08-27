package thisisus.school.chatting.service;

import thisisus.school.chatting.dto.ChatMessageRequest;
import thisisus.school.chatting.dto.ChatReqeustRequest;

public interface ChatService {
	void chatRequest(Long studentId, ChatReqeustRequest chatReqeustRequest);

	void acceptChatRoomRequest(Long teacherId, Long studentId);

	void saveChatMessage(ChatMessageRequest chatMessageRequest);

	void declineChatRequest(Long teacherId, Long studentId);
}