package thisisus.school.chatting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.lettuce.core.dynamic.annotation.Param;
import thisisus.school.chatting.domain.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	@Query("select cr from ChatRoom cr where cr.chatRequest.id = :chatRequestId")
	Optional<ChatRoom> findByChatRequestIdWithOptional(@Param("chatRequestId") Long chatRequestId);
}