package thisisus.school.comment.repository;

import static thisisus.school.comment.domain.QComment.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import thisisus.school.comment.domain.Comment;

@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Comment> findAllByPostId(Long postId) {
		List<Comment> responses = queryFactory
			.select(comment)
			.from(comment)
			.where(comment.post.id.eq(postId))
			.fetch();
		return responses;
	}

	@Override
	public List<Comment> findAllByMemberId(Long memberId) {
		List<Comment> responses = queryFactory
			.select(comment)
			.from(comment)
			.where(comment.member.id.eq(memberId))
			.fetch();
		return responses;
	}
}
