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
<<<<<<< HEAD
    private String Sender;
    private String content;
    private String timestamp;
=======
    private Date timeStamp;
>>>>>>> 4878d235431250cff5b6bc129b101707a08d5097
}