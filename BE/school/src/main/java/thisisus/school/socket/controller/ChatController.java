package thisisus.school.socket.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import thisisus.school.member.domain.Member;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.member.security.service.CustomUserDetails;
import thisisus.school.socket.dto.ChatMessageRequestDto;
import thisisus.school.socket.model.ChatMessage;
import thisisus.school.socket.model.MessageType;
import thisisus.school.socket.service.ChatService;

@RestController
@RequiredArgsConstructor
@Api(tags = "채팅")
@Slf4j
//@RequestMapping("/api/v1/chat")
public class ChatController {
    private final MemberRepository memberRepository;
    private final ChatService chatService;
    //"/pub/chat/enter"

    private final SimpMessagingTemplate template;

    @MessageMapping("/api/chat/onMessage")
    public void onMessage(@RequestBody ChatMessageRequestDto message){
        template.convertAndSend("/sub/api/chat/message/" + message.getRoomId() +  message);
    }

    @MessageMapping("/api/chat/message")
    public String message(@RequestBody ChatMessageRequestDto message,
                        @AuthenticationPrincipal CustomUserDetails customUserDetails){

        return chatService.sendMessage(message, customUserDetails);
    }


/*    @MessageMapping("/chat/{id}") // 실제론 메세지 매핑으로 pub/chatroom/{id} 임
    public void sendMessage(@DestinationVariable("id") Long id, ChatMessageRequestDto chatDTO) {
        log.info("chat {} send by {} to room number{}", chatDTO.getMessage(), chatDTO.getSenderId());
//        chatService.saveMessage(chatDTO);
        // /sub/chatroom/{id}로 메세지 보냄
        template.convertAndSend("/sub/chatroom/" + chatDTO.getChatRoomId(), chatDTO);
        chatService.saveMessage(chatDTO.getSenderId(),chatDTO.getMessage(),chatDTO.getChatRoomId());
        log.info("Message [{}] send by member: {} to chatting room: {}",chatDTO.getMessage(), chatDTO.getSenderId(), chatDTO.getChatRoomId());
    }*/
}