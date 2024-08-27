package thisisus.school.chatting.repository;

import java.util.List;
import java.util.Optional;

import thisisus.school.chatting.domain.ChatRequest;

public interface ChatRequestCustomRepository {
	Optional<ChatRequest> findByTeacherIdAndStudentIdWithOptional(Long teacherId, Long studentId);

	ChatRequest findByTeacherIdAndStudentId(Long teacherId, Long studentId);

	List<ChatRequest> findByTeacherId(Long teacherId);
	List<ChatRequest> findByStudentId(Long studentId);
}