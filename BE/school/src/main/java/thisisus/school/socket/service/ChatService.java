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
import thisisus.school.socket.dto.ChatMessageRequestDto;
import thisisus.school.socket.dto.PreMessageRequestDto;
import thisisus.school.socket.dto.RoomsInfoRequestDto;
import thisisus.school.socket.model.ChatMessage;
import thisisus.school.socket.model.ChatRoom;
import thisisus.school.socket.model.MemberChatRoom;
import thisisus.school.socket.repository.ChatRepository;
import thisisus.school.socket.repository.MemberChatRoomRepository;
import thisisus.school.socket.repository.RoomRepository;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms = new LinkedHashMap<>();
    private final ChatRepository chatRepository;
    private final RoomRepository roomRepository;
    private final MemberChatRoomRepository memberChatRoomRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public List<RoomsInfoRequestDto> findAllRoom(CustomUserDetails customUserDetails) {

        //DTO 변경 예정
        Member member = memberService.findMember(customUserDetails);
        List<MemberChatRoom> memberChatRooms = memberChatRoomRepository.findByMember(member);
        List<RoomsInfoRequestDto> roomsInfoRequestDtos = new ArrayList<>();
/*        Map<String, String[]> chatRooms = new HashMap<>();

        for (MemberChatRoom memberChatRoom : memberChatRooms) {
            String[] chatRoomInfo = new String[2];
            if(member.getName().equals(memberChatRoom.getChatRoom().getOtherMemberName())) {
                chatRoomInfo[0] = memberChatRoom.getChatRoom().getRegisterMember();
                chatRoomInfo[1] = member.getEmail();
                chatRooms.put(memberChatRoom.getChatRoom().getRoomId(), chatRoomInfo);
            }else{
                chatRoomInfo[0] = memberChatRoom.getChatRoom().getOtherMemberName();
                chatRoomInfo[1] = member.getEmail();
                chatRooms.put(memberChatRoom.getChatRoom().getRoomId(), chatRoomInfo);            }
        }*/
        for (MemberChatRoom memberChatRoom : memberChatRooms) {
            RoomsInfoRequestDto roomsInfoRequestDto;
            if(member.getName().equals(memberChatRoom.getChatRoom().getOtherMemberName())) {
                roomsInfoRequestDto = RoomsInfoRequestDto.builder()
                        .roomId(memberChatRoom.getChatRoom().getRoomId())
                        .memberEmail(member.getEmail())
                        .roomTitle(memberChatRoom.getChatRoom().getRegisterMember())
                        .build();
            }else {
                roomsInfoRequestDto = RoomsInfoRequestDto.builder()
                        .roomId(memberChatRoom.getChatRoom().getRoomId())
                        .memberEmail(member.getEmail())
                        .roomTitle(memberChatRoom.getChatRoom().getOtherMemberName())
                        .build();
            }
            roomsInfoRequestDtos.add(roomsInfoRequestDto);

        }
        return roomsInfoRequestDtos;
//        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public void saveMemberChatRoom(Member member, ChatRoom chatRoom){
        MemberChatRoom memberChatRoom = MemberChatRoom.builder()
                .member(member)
                .chatRoom(chatRoom)
                .build();

        memberChatRoomRepository.save(memberChatRoom);

    }

    public ChatRoom createChatRoom(Long postMemberId, CustomUserDetails customUserDetails){

        String randomId = UUID.randomUUID().toString();

        Member otherMember = memberRepository.findById(postMemberId).get();
        Member registerMember = memberService.findMember(customUserDetails);

        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomId)
                .otherMemberName(otherMember.getName())
                .registerMember(registerMember.getName())
                .build();

        roomRepository.save(chatRoom);

        saveMemberChatRoom(otherMember, chatRoom);
        saveMemberChatRoom(registerMember, chatRoom);

        return chatRoom;
    }

    /*public void sendMessage(ChatMessageRequestDto message, CustomUserDetails customUserDetails) {
            Member member = memberService.findMember(customUserDetails);
            ChatRoom chatRoom = roomRepository.findByRoomId(message.getRoomId());
            ChatMessage chatMessage = ChatMessage.builder()
                    .chatRoom(chatRoom)
                    .senderId(member.getId())
                    .message(message.getMessage())
                    .build();
            chatRepository.save(chatMessage);


    }*/


/*    public void sendMessage(ChatMessageRequestDto message, Map<String, String> memberInfo) {
        log.info("memberInfo : {}", memberInfo);
        Member member = memberRepository.findByEmail(memberInfo.get("Sender")).get();
        log.info("member : {}", member.getEmail());
        ChatRoom chatRoom = roomRepository.findByRoomId(message.getRoomId());
        log.info("message : {}", message);
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .senderId(member.getId())
                .message(memberInfo.get("content"))
                .build();
        log.info("chatMessage : {}", chatMessage.getMessage());
        chatRepository.save(chatMessage);
        log.info("저장 성공~");

    }*/

    public void sendMessage(ChatMessageRequestDto chatMessageRequestDto) {


        Member member = memberRepository.findByEmail(chatMessageRequestDto.getSender()).get();
        log.info("member : {}", member.getEmail());
        log.info("chatMessageRequestDto.getRoomId() : {}", chatMessageRequestDto.getRoomId());
        ChatRoom chatRoom = roomRepository.findByRoomId(chatMessageRequestDto.getRoomId());
        log.info("message : {}", chatMessageRequestDto);
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .senderId(member.getId())
                .timestamp(chatMessageRequestDto.getTimestamp())
                .message(chatMessageRequestDto.getContent())
                .build();
        log.info("chatMessage : {}", chatMessage.getMessage());
        chatRepository.save(chatMessage);
        log.info("저장 성공~");

    }

    public PreMessageRequestDto getMessageInfo(String roomId, CustomUserDetails customUserDetails) {
//        log.info("message 확인 : {}", chatRepository.findByChatRoomRoomIdOrderByTimestampDesc(roomId));
//        return chatRepository.findByChatRoomRoomIdOrderByTimestampDesc(roomId);
        ChatRoom chatRoom = roomRepository.findByRoomId(roomId);
        Member client = memberService.findMember(customUserDetails);
        String clientEmail = client.getEmail();
        String otherMemberEmail ="";
        List<ChatMessage> chatMessagesList = chatRepository.findByChatRoom(chatRoom);

        for (ChatMessage chatMessage : chatMessagesList) {
            Long sender = chatMessage.getSenderId();
            Member findMember = memberRepository.findById(sender).get();

            if (!findMember.getEmail().equals(clientEmail)) {
                otherMemberEmail = findMember.getEmail();
                break;
            }
        }
        log.info("otherMemberEmail : {}", otherMemberEmail);

        Long otherMemberId = null;

        Optional<Member> otherMemberOptional = memberRepository.findByEmail(otherMemberEmail);
        log.info("otherMemberOptional : {}", otherMemberOptional);

        if (otherMemberOptional.isPresent()) {
            otherMemberId = otherMemberOptional.get().getId();
        }
        log.info("otherMemberId : {}", otherMemberId);

        PreMessageRequestDto preMessageRequestDto = PreMessageRequestDto.builder()
                .chatMessages(chatMessagesList)
                .clientId(client.getId())
                .clientEmail(clientEmail)
                .otherMemberId(otherMemberId)
                .otherMemberEmail(otherMemberEmail)
                .build();

        log.info("message 확인 : {}", chatRepository.findByChatRoom(chatRoom));
        return preMessageRequestDto;

    }
/*    public ChatMessage saveMessage(Long sender, String message, Long chatroom) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSenderId(sender);
        chatMessage.setMessage(message);
        chatMessage.setRoomId(chatroom);
        return chatRepository.save(chatMessage);
    }*/
}