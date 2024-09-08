package thisisus.school.chatting.dto;

import thisisus.school.chatting.domain.ChatRequest;
import thisisus.school.member.domain.Member;

public record ChatReqeustRequest(
	Long teacherId
) {
	public ChatRequest toEntity(Member teacher, Member student) {
		return new ChatRequest(teacher, student);
	}
}