package thisisus.school.chatting.repository;

import static thisisus.school.chatting.domain.QChatRequest.*;

import java.util.List;
import java.util.Optional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import thisisus.school.chatting.domain.ChatRequest;

@RequiredArgsConstructor
public class ChatRequestCustomRepositoryImpl implements ChatRequestCustomRepository {
	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<ChatRequest> findByTeacherIdAndStudentIdWithOptional(Long teacherId, Long studentId) {
		return Optional.ofNullable(queryFactory
			.select(chatRequest)
			.from(chatRequest)
			.where(chatRequest.student.id.eq(studentId)
				.and(chatRequest.teacher.id.eq(teacherId)))
			.fetchOne());
	}

	@Override
	public ChatRequest findByTeacherIdAndStudentId(Long teacherId, Long studentId) {
		return queryFactory
			.select(chatRequest)
			.from(chatRequest)
			.where(chatRequest.student.id.eq(studentId)
				.and(chatRequest.teacher.id.eq(teacherId)))
			.fetchOne();
	}

	@Override
	public List<ChatRequest> findByTeacherId(Long teacherId){
		return queryFactory
			.select(chatRequest)
			.from(chatRequest)
			.where(chatRequest.teacher.id.eq(teacherId))
			.fetch();
	}

	@Override
	public List<ChatRequest> findByStudentId(Long studentId){
		return queryFactory
			.select(chatRequest)
			.from(chatRequest)
			.where(chatRequest.student.id.eq(studentId))
			.fetch();
	}
}