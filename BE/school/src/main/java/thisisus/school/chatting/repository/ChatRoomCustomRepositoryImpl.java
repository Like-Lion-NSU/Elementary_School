package thisisus.school.chatting.repository;

import static thisisus.school.chatting.domain.QChatRequest.*;
import static thisisus.school.chatting.domain.QChatRoom.*;

import java.util.List;
import java.util.Optional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import thisisus.school.chatting.domain.ChatRoom;

@RequiredArgsConstructor
public class ChatRoomCustomRepositoryImpl implements ChatRoomCustomRepository {
	private final JPAQueryFactory queryFactory;

	final
	@Override
	public Optional<ChatRoom> findByChatRequestIdWithOptional(Long chatRequestId) {
		return Optional.ofNullable(queryFactory
			.select(chatRoom)
			.from(chatRoom)
			.where(chatRoom.id.eq(chatRequestId))
			.fetchOne());
	}

	@Override
	public List<ChatRoom> findByChatRequestId(List<Long> chatRequesetIds) {
		return queryFactory
			.select(chatRoom)
			.from(chatRoom)
			.join(chatRoom.chatRequest, chatRequest)
			.fetchJoin()
			.where(chatRoom.chatRequest.id.in(chatRequesetIds))
			.fetch();
	}
}