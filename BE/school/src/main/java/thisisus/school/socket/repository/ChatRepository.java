package thisisus.school.socket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.socket.dto.PreMessageRequestDto;
import thisisus.school.socket.model.ChatMessage;
import thisisus.school.socket.model.ChatRoom;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomRoomIdOrderByTimestampDesc(String roomId);
    List<ChatMessage> findByChatRoom(ChatRoom chatRoom);
}
