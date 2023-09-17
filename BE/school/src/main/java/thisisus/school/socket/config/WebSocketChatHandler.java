//package thisisus.school.socket.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//import thisisus.school.socket.dto.ChatMessageRequestDto;
//import thisisus.school.socket.model.ChatRoom;
//import thisisus.school.socket.model.MessageType;
//import thisisus.school.socket.service.ChatService;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//@Component
//@RequiredArgsConstructor
//public class WebSocketChatHandler extends TextWebSocketHandler {
//    private final ObjectMapper mapper;
//
//    //현재 연결된 세션들
//    private final Set<WebSocketSession> sessions = new HashSet<>();
//
//    //chatRoomId: {sessions1, session2}
//    private final Map<Long,Set<WebSocketSession>> chatRoomSessionMap = new HashMap<>();
//    private final ChatService chatService;
//
//    //소켓 연결 확인
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        sessions.add(session);
//    }
//
//    //소켓 통신 시 메시지의 전송을 다루는 부분
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        ChatMessageRequestDto chatMessageRequestDto = mapper.readValue(payload, ChatMessageRequestDto.class);
//
//        ChatRoom chatRoom = chatService.findRoomById(chatMessageRequestDto.getChatRoomId());
//        handlerActions(session, chatMessageRequestDto);
//    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        sessions.remove(session);
//    }
//
//    //채팅 관련 메소드
//    private void removeClosedSession(Set<WebSocketSession> chatRoomSession){
//        chatRoomSession.removeIf(session -> !sessions.contains(session));
//    }
//    public void handlerActions(WebSocketSession session, ChatMessageRequestDto chatMessageRequestDto) {
//        if (chatMessageRequestDto.getMessageType().equals(MessageType.Enter)) {
//            sessions.add(session);
//            chatMessageRequestDto.setMessage(chatMessageRequestDto.getSenderId() + "님이 입장했습니다.");
//        }
//        sendMessage(chatMessageRequestDto);
//
//    }
//
//    private <T> void sendMessage(T message) {
//        sessions.parallelStream()
//                .forEach(session -> chatService.sendMessage(session, message));
//    }
//
//}