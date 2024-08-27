package thisisus.school.chatting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import thisisus.school.chatting.domain.ChatRequest;

@Repository
public interface ChatRequestRepository extends JpaRepository<ChatRequest, Long> {

	@Query("SELECT cr FROM ChatRequest cr WHERE cr.student.id = :studentId AND cr.teacher.id = :teacherId")
	Optional<ChatRequest> findByTeacherIdAndStudentIdWithOptional(@Param("teacherId") Long teacherId,
		@Param("studentId") Long studentId);

	@Query("SELECT cr FROM ChatRequest cr WHERE cr.student.id = :studentId AND cr.teacher.id = :teacherId")
	ChatRequest findByTeacherIdAndStudentId(Long teacherId, Long studentId);
}