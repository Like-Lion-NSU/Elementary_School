package thisisus.school.chatting.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChatStatus {
	PENDING("대기중"),
	ACCEPTED("수락됨"),
	DECLIEND("거절됨"),

	ACTIVE("활성"),
	ENDED("종료");

	String name;
}