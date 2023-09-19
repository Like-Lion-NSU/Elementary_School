package thisisus.school.socket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.socket.model.ChatMessage;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

}
