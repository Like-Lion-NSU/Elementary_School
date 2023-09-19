package thisisus.school.socket.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import thisisus.school.socket.dto.ChatMessageRequestDto;
import thisisus.school.socket.model.ChatMessage;
import thisisus.school.socket.service.ChatService;

@RestController
@RequiredArgsConstructor
@Api(tags = "채팅")
@Slf4j
//@RequestMapping("/api/v1/chat")
public class ChatController {
    //"/pub/chat/enter"

    private final SimpMessagingTemplate template;
    private final ChatService chatService;
    @MessageMapping(value = "/api/chat/enter")
    public String enter(ChatMessageRequestDto message){
        message.setMessage(message.getSenderId() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/enter/" + message.getChatRoomId(), message);
        log.info("");
        return "hello," + message.getSenderId();
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessage message){
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping("/chatroom/{id}") // 실제론 메세지 매핑으로 pub/chatroom/{id} 임
    public void sendMessage(@DestinationVariable("id") Long id, ChatMessageRequestDto chatDTO) {
        log.info("chat {} send by {} to room number{}", chatDTO.getMessage(), chatDTO.getSenderId());
//        chatService.saveMessage(chatDTO);
        // /sub/chatroom/{id}로 메세지 보냄
        template.convertAndSend("/sub/chatroom/" + chatDTO.getChatRoomId(), chatDTO);
        chatService.saveMessage(chatDTO.getSenderId(),chatDTO.getMessage(),chatDTO.getChatRoomId());
        log.info("Message [{}] send by member: {} to chatting room: {}",chatDTO.getMessage(), chatDTO.getSenderId(), chatDTO.getChatRoomId());
    }
}