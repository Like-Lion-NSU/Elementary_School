package thisisus.school.socket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import thisisus.school.member.controller.MemberController;
import thisisus.school.member.domain.Member;
import thisisus.school.member.repository.MemberRepository;
import thisisus.school.member.security.service.CustomUserDetails;
import thisisus.school.member.service.MemberService;
import thisisus.school.socket.model.ChatMessage;
import thisisus.school.socket.model.ChatRoom;
import thisisus.school.socket.repository.ChatRepository;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms = new LinkedHashMap<>();
    private final ChatRepository chatRepository;

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoom createChatRoom(Long postMemberId, Long currentMemberId, CustomUserDetails customUserDetails){

        String randomId = UUID.randomUUID().toString();
        Member currentMember = memberService.findMember(customUserDetails);
        if(currentMemberId!=currentMember.getId()){
            throw new RuntimeException("현재 로그인한 유저와 정보가 맞지 않습니다.");
        }

        Member otherMember = memberRepository.findById(postMemberId).get();

        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomId)
                .name(otherMember.getName())
                .build();
        chatRooms.put(randomId, chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public ChatMessage saveMessage(Long sender, String message, Long chatroom) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSenderId(sender);
        chatMessage.setMessage(message);
        chatMessage.setRoomId(chatroom);
        return chatRepository.save(chatMessage);
    }
}