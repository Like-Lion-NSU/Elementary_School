package thisisus.school.comment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thisisus.school.comment.domain.Comment;
import thisisus.school.member.domain.Member;
import thisisus.school.post.domain.Post;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentRequest {
	@NotBlank
	@NotNull
	private String content;
	@NotBlank
	@NotNull
	private Long postId;

	public Comment toEntity(Member member, Post post){
		return Comment.builder()
			.content(content)
			.post(post)
			.member(member)
			.build();
	}
}
