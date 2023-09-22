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
    private String roomId;
    private String Sender;
    private String content;
    private String timestamp;
}