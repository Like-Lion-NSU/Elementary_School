package thisisus.school.post.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailPostDto {

    private Long postId;
    private String email;
    private String title;
    private String content;
    private String category;

}
