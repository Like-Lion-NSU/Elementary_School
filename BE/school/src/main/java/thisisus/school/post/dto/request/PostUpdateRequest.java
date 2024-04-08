package thisisus.school.post.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thisisus.school.post.domain.PostCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostUpdateRequest {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private PostCategory category;
}
