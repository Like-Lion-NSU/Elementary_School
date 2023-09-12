package thisisus.school.socket.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import thisisus.school.socket.dto.request.RoomNameRequestDto;
import thisisus.school.socket.model.ChatRoom;
import thisisus.school.socket.service.ChatService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "채팅방")
@RequestMapping("/chat")
public class RoomController {

    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestBody RoomNameRequestDto roomNameRequestDto) {
        return chatService.createChatRoom(roomNameRequestDto.getRoomName());
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }
}
