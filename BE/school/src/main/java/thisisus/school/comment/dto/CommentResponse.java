package thisisus.school.comment.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import thisisus.school.comment.domain.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentResponse {
	private Long commentId;
	private String content;
	private String writer;

	public CommentResponse(Comment comment){
		this.commentId = comment.getId();
		this.content = comment.getContent();
		this.writer = comment.getMember().getNickname();
	}
}
