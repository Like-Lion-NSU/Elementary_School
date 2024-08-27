package thisisus.school.chatting.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import thisisus.school.auth.config.AuthenticatedMemberId;
import thisisus.school.chatting.dto.ChatMessageRequest;
import thisisus.school.chatting.dto.ChatReqeustRequest;
import thisisus.school.chatting.dto.ChatRoomsResponse;
import thisisus.school.chatting.service.ChatService;
import thisisus.school.common.response.SuccessResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class ChatController {

	private final ChatService chatService;

	@PostMapping("/api/chat/requests")
	public SuccessResponse chatRequest(
		@AuthenticatedMemberId Long studentId,
		@RequestBody ChatReqeustRequest chatReqeustRequest
	) {
		chatService.chatRequest(studentId, chatReqeustRequest);
		return SuccessResponse.of();
	}

	@PostMapping("/api/chat/room/new")
	public SuccessResponse acceptChatRoomRequest(
		@AuthenticatedMemberId Long teacherId,
		Long studentId
	) {
		chatService.acceptChatRoomRequest(teacherId, studentId);
		return SuccessResponse.of();
	}

	@MessageMapping("/chat/room/{roomId}")
	@SendTo("/sub/chat/room/{roomId}")
	public void sendMessage(
		@DestinationVariable("roomId") Long roomId,
		@Payload ChatMessageRequest chatMessageRequest
	) {
		chatService.saveChatMessage(chatMessageRequest);
	}

	@PatchMapping("/api/chat/room")
	public SuccessResponse declineChatRequest(
		@AuthenticatedMemberId Long teacherId,
		Long studentId
	) {
		chatService.declineChatRequest(teacherId, studentId);
		return SuccessResponse.of();
	}

	@GetMapping("/api/chat/chats")
	public SuccessResponse<List<ChatRoomsResponse>> findChatList(
		@AuthenticatedMemberId Long memberId
	){
		List<ChatRoomsResponse> responses = chatService.findChatRooms(memberId);
		return SuccessResponse.of(responses);
	}
}