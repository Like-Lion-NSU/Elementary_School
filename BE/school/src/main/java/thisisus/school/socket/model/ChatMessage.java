package thisisus.school.socket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    private Long senderId;
    private Long receiverId;
    private Long roomId;
    private String message;
}
