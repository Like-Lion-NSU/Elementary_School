package thisisus.school.member.repository;

import java.util.List;

public interface MemberCustomRepository {
	long deleteByIdIn(List<Long> ids);
}