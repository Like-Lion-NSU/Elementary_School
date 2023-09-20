package thisisus.school.socket.dto;

import lombok.*;
import thisisus.school.socket.model.MessageType;

import javax.persistence.Entity;
import java.sql.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequestDto {
    private String sender;
    private String content;
    private String roomId;
    private Date timeStamp;
}