package thisisus.school.socket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;
import thisisus.school.socket.model.ChatMessage;

@RestController
@RequiredArgsConstructor
public class ChattController {

    @MessageMapping("/chatting")
    public void chat(ChatMessage chatMessage){
    }
}
