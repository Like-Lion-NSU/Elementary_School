package thisisus.school.socket.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;
    private Long senderId;
  /*  @Enumerated(EnumType.STRING)
    private MessageType type;*/
    @ManyToOne
    @JoinColumn(name = "chatRoom_id")
    private ChatRoom chatRoom;
    private String message;
}