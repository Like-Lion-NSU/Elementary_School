package thisisus.school.socket.dto.request;

import lombok.*;
import thisisus.school.socket.model.MessageType;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequestDto {

    private MessageType messageType;
    private String chatRoomId;
    private String senderId;
    private String message;
}
