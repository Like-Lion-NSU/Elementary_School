package thisisus.school.chatting.repository;

import java.util.List;
import java.util.Optional;

import thisisus.school.chatting.domain.ChatRoom;

public interface ChatRoomCustomRepository {
	Optional<ChatRoom> findByChatRequestIdWithOptional(Long chatRequestId);

	List<ChatRoom> findByChatRequestId(List<Long> chatRequesetIds);
}