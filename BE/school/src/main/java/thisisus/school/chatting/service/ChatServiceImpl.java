package thisisus.school.chatting.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import thisisus.school.chatting.domain.ChatMessage;
import thisisus.school.chatting.domain.ChatRequest;
import thisisus.school.chatting.domain.ChatRoom;
import thisisus.school.chatting.dto.ChatMessageRequest;
import thisisus.school.chatting.dto.ChatReqeustRequest;
import thisisus.school.chatting.repository.ChatMessageRepository;
import thisisus.school.chatting.repository.ChatRequestRepository;
import thisisus.school.chatting.repository.ChatRoomRepository;
import thisisus.school.member.domain.Member;
import thisisus.school.member.exception.NotFoundMemberException;
import thisisus.school.member.repository.MemberRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatServiceImpl implements ChatService {

	private final ChatRequestRepository chatRequestRepository;
	private final ChatRoomRepository chatRoomRepository;
	private final MemberRepository memberRepository;
	private final ChatMessageRepository chatMessageRepository;

	@Transactional
	@Override
	public void chatRequest(final Long studentId, final ChatReqeustRequest chatReqeustRequest) {
		final Member teacher = memberRepository.findById(chatReqeustRequest.teacherId())
			.orElseThrow(NotFoundMemberException::new);
		teacher.validateTeacherRole();
		final Optional<ChatRequest> chatRequest = chatRequestRepository.findByTeacherIdAndStudentIdWithOptional
			(chatReqeustRequest.teacherId(), studentId);
		if (chatRequest.isEmpty()) {
			final Member student = memberRepository.findById(studentId)
				.orElseThrow(NotFoundMemberException::new);
			ChatRequest createChatRequest = chatReqeustRequest.toEntity(teacher, student);
			chatRequestRepository.save(createChatRequest);
		} else {
			chatRequest.get().changeStatusToPending();
		}
	}

	@Transactional
	@Override
	public void acceptChatRoomRequest(final Long teacherId, final Long studentId) {
		final ChatRequest chatRequest = chatRequestRepository.findByTeacherIdAndStudentId(teacherId, studentId);
		final Optional<ChatRoom> chatRoom = chatRoomRepository.findByChatRequestIdWithOptional(chatRequest.getId());
		chatRequest.changeStatusToAccepted();
		ChatRoom activeChatRoom;
		if (chatRoom.isEmpty()) {
			activeChatRoom = new ChatRoom(chatRequest);
			chatRoomRepository.save(activeChatRoom);
		} else {
			activeChatRoom = chatRoom.get();
			activeChatRoom.changeStatusToActive();
		}
	}

	@Transactional
	@Override
	public void saveChatMessage(final ChatMessageRequest chatMessageRequest) {
		ChatMessage chatMessage = chatMessageRequest.toEntity();
		chatMessageRepository.save(chatMessage);
	}

	@Transactional
	@Override
	public void declineChatRequest(final Long teacherId, final Long studentId) {
		final ChatRequest chatRequest = chatRequestRepository.findByTeacherIdAndStudentId(teacherId, studentId);
		chatRequest.changeStatusToDecliend();
	}
}