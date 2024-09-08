package thisisus.school.chatting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import thisisus.school.chatting.domain.ChatMessage;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}