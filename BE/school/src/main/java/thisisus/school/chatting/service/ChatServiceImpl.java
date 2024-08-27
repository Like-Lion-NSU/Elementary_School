package thisisus.school.chatting.service;

import static thisisus.school.chatting.dto.ChatRoomsResponse.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import thisisus.school.auth.exception.AccessDeniedResourceException;
import thisisus.school.chatting.domain.ChatMessage;
import thisisus.school.chatting.domain.ChatRequest;
import thisisus.school.chatting.domain.ChatRoom;
import thisisus.school.chatting.dto.ChatMessageRequest;
import thisisus.school.chatting.dto.ChatReqeustRequest;
import thisisus.school.chatting.dto.ChatRoomsResponse;
import thisisus.school.chatting.repository.ChatMessageRepository;
import thisisus.school.chatting.repository.ChatRequestRepository;
import thisisus.school.chatting.repository.ChatRoomRepository;
import thisisus.school.member.domain.Member;
import thisisus.school.member.domain.Role;
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

	@Override
	public List<ChatRoomsResponse> findChatRooms(final Long memberId) {
		final Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);
		List<ChatRequest> chatRequests = getChatRequestsForMember(member);
		List<Long> chatRequestIds = extractChatRequestIds(chatRequests);
		List<ChatRoom> chatRooms = chatRoomRepository.findByChatRequestId(chatRequestIds);

		return convertChatRoomsToResponse(member.getRole(), chatRooms);
	}

	private List<ChatRequest> getChatRequestsForMember(Member member) {
		if (Role.STUDENT.equals(member.getRole())) {
			return chatRequestRepository.findByStudentId(member.getId());
		} else if (Role.TEACHER.equals(member.getRole())) {
			return chatRequestRepository.findByTeacherId(member.getId());
		}
		throw new AccessDeniedResourceException();
	}

	private List<Long> extractChatRequestIds(List<ChatRequest> chatRequests) {
		return chatRequests.stream()
			.map(ChatRequest::getId)
			.collect(Collectors.toList());
	}

	private List<ChatRoomsResponse> convertChatRoomsToResponse(Role role, List<ChatRoom> chatRooms) {
		if (Role.STUDENT.equals(role)) {
			return convertChatRoomsForStudent(chatRooms);
		} else if (Role.TEACHER.equals(role)) {
			return convertChatRoomsForTeacher(chatRooms);
		}
		throw new AccessDeniedResourceException();
	}
}