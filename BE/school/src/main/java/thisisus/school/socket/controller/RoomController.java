package thisisus.school.socket.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import thisisus.school.member.security.service.CustomUserDetails;
import thisisus.school.socket.dto.RoomsInfoRequestDto;
import thisisus.school.socket.model.ChatRoom;
import thisisus.school.socket.service.ChatService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(tags = "채팅방")
@RequestMapping("/v1/chat")
public class RoomController {

    private final ChatService chatService;

    @PostMapping("/{postMemberId}")
    public ChatRoom createRoom(@PathVariable("postMemberId") Long postMemberId,
                               @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return chatService.createChatRoom(postMemberId, customUserDetails);
    }


    @GetMapping("/rooms")
    public List<RoomsInfoRequestDto> findAllRoom(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return chatService.findAllRoom(customUserDetails);
    }
}