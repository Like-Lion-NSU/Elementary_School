package thisisus.school.chatting.service;

import java.util.List;

import thisisus.school.chatting.dto.ChatMessageRequest;
import thisisus.school.chatting.dto.ChatReqeustRequest;
import thisisus.school.chatting.dto.ChatRoomsResponse;

public interface ChatService {
	void chatRequest(Long studentId, ChatReqeustRequest chatReqeustRequest);

	void acceptChatRoomRequest(Long teacherId, Long studentId);

	void saveChatMessage(ChatMessageRequest chatMessageRequest);

	void declineChatRequest(Long teacherId, Long studentId);
	List<ChatRoomsResponse> findChatRooms(Long memberId);
}