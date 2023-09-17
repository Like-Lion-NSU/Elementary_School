package thisisus.school.socket.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue()
    private Long senderId;
    private Long receiverId;
    private Long roomId;
    private String message;
}