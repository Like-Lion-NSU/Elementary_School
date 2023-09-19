package thisisus.school.socket.dto;

import lombok.*;
import thisisus.school.socket.model.MessageType;

import javax.persistence.Entity;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequestDto {

//    private String name;
    private MessageType messageType;
    private Long chatRoomId;
    private Long senderId;
    private String message;
}