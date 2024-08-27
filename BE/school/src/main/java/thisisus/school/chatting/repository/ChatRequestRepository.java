package thisisus.school.chatting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thisisus.school.chatting.domain.ChatRequest;

@Repository
public interface ChatRequestRepository extends JpaRepository<ChatRequest, Long>, ChatRequestCustomRepository {
}