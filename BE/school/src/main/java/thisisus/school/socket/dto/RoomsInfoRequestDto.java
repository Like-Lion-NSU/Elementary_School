package thisisus.school.socket.dto;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomsInfoRequestDto {

    private String roomId;
    private String roomTitle;
    private String memberEmail;
    private String timestamp;
}
