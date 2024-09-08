package thisisus.school.chatting.service;

import static thisisus.school.chatting.domain.ChatStatus.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import thisisus.school.chatting.domain.ChatRoom;
import thisisus.school.chatting.dto.ChatMessageRequest;
import thisisus.school.chatting.exception.NotFoundChatRoomException;
import thisisus.school.chatting.repository.ChatRoomRepository;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {
	private final ObjectMapper objectMapper;
	private final ChatRoomRepository chatRoomRepository;
	private final Map<Long, Set<WebSocketSession>> chatRoomSessionsMap = new HashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("{} 연결됨", session.getId());

		TextMessage initialMessage = new TextMessage("채팅이 시작되었습니다.");
		handleTextMessage(session, initialMessage);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		ChatMessageRequest chatMessage = objectMapper.readValue(payload, ChatMessageRequest.class);

		Long roomId = chatMessage.roomId();
		if (roomId != null) {
			chatRoomSessionsMap.computeIfAbsent(roomId, key -> new HashSet<>()).add(session);
			session.getAttributes().put("roomId", roomId);
		}

		ChatRoom chatRoom = chatRoomRepository.findById(roomId)
			.orElseThrow(NotFoundChatRoomException::new);

		if (chatRoom.getChatStatus().equals(ACTIVE)) {
			sendMessageToChatRoom(chatMessage, roomId);
		} else if (chatRoom.getChatStatus().equals(ENDED)) {
			removeClosedSession(roomId);
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws IOException {
		log.info("{} 연결 끊김", session.getId());

		Long roomId = (Long)session.getAttributes().get("roomId");
		if (roomId != null) {
			chatRoomSessionsMap.get(roomId).remove(session);
			if (chatRoomSessionsMap.get(roomId).isEmpty()) {
				ChatMessageRequest endMessage = new ChatMessageRequest(roomId, null, "채팅이 종료되었습니다.");
				sendMessageToChatRoom(endMessage, roomId);
				removeClosedSession(roomId);
			}
		}
	}

	private void removeClosedSession(Long roomId) {
		chatRoomSessionsMap.remove(roomId);
	}

	private void sendMessageToChatRoom(ChatMessageRequest chatMessage, Long roomId) {
		chatRoomSessionsMap.getOrDefault(roomId, Collections.emptySet())
			.parallelStream().forEach(session -> sendMessage(session, chatMessage));
	}

	private <T> void sendMessage(WebSocketSession session, T message) {
		try {
			session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}