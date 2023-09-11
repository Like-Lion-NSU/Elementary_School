package thisisus.school.socket.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import thisisus.school.socket.dto.request.RoomNameRequestDto;
import thisisus.school.socket.model.ChatMessage;
import thisisus.school.socket.model.ChatRoom;
import thisisus.school.socket.service.ChatService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "채팅")
@RequestMapping("/api/v1/chat")
public class ChatController {


}
