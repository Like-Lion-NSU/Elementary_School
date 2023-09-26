package thisisus.school.socket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.socket.model.ChatRoom;

public interface RoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findByRoomId(String id);
}
