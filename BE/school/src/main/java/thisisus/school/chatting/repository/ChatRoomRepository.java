package thisisus.school.chatting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import thisisus.school.chatting.domain.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomCustomRepository{
}