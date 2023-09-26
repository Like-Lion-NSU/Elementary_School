package thisisus.school.socket.dto;

import lombok.*;
import thisisus.school.socket.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreMessageRequestDto {
    private List<ChatMessage> chatMessages = new ArrayList<>();
    private Long clientId;
    private Long otherMemberId;
    private String clientEmail;
    private String otherMemberEmail;
}
