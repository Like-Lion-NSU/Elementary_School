package thisisus.school.chatting.dto;

import java.util.List;
import java.util.stream.Collectors;

import thisisus.school.chatting.domain.ChatRoom;
import thisisus.school.chatting.domain.ChatStatus;

public record ChatRoomsResponse(
	Long roomId,
	ChatStatus chatStatus,
	String chatRoomName,
	ChatStatus requesetStatus,
	Long otherId
) {
	public static List<ChatRoomsResponse> convertChatRoomsForTeacher(List<ChatRoom> chatRooms) {
		return chatRooms.stream()
			.map(chatRoom -> new ChatRoomsResponse(
				chatRoom.getId(),
				chatRoom.getChatStatus(),
				chatRoom.getChatRequest().getStudent().getName(),
				chatRoom.getChatRequest().getChatStatus(),
				chatRoom.getChatRequest().getStudent().getId()
			))
			.collect(Collectors.toList());
	}

	public static List<ChatRoomsResponse> convertChatRoomsForStudent(List<ChatRoom> chatRooms) {
		return chatRooms.stream()
			.map(chatRoom -> new ChatRoomsResponse(
			chatRoom.getId(),
			chatRoom.getChatStatus(),
			chatRoom.getChatRequest().getTeacher().getName(),
			chatRoom.getChatRequest().getChatStatus(),
			chatRoom.getChatRequest().getTeacher().getId()
			))
			.collect(Collectors.toList());
	}
}