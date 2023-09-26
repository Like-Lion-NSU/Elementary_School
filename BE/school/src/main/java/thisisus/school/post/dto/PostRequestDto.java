package thisisus.school.post.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostRequestDto {
    private String title;
    private String content;
    private String category;
    private List<MultipartFile> files = new ArrayList<>();
}
