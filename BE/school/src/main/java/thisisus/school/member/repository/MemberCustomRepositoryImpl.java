package thisisus.school.member.repository;

import static thisisus.school.member.domain.QMember.*;

import java.util.List;

import javax.persistence.EntityManager;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
	private final JPAQueryFactory queryFactory;
	private final EntityManager entityManager;

	@Override
	public long deleteByIdIn(List<Long> ids) {
		long count = queryFactory
			.delete(member)
			.where(member.id.in(ids))
			.execute();

		entityManager.clear();
		return count;
	}
}