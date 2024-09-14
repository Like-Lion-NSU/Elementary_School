package thisisus.school.infrastructure.batch.member;

import static thisisus.school.member.domain.QMember.*;

import java.time.LocalDate;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.item.querydsl.reader.QuerydslZeroPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ReaderConfig {
	public static final String DELETION_READER = "memberItemReaderForDeletion";

	private final EntityManagerFactory entityManagerFactory;
	private final int chunkSize = 1000;

	@Bean(DELETION_READER)
	public QuerydslZeroPagingItemReader<Long> deleteMemberReader() {
		return new QuerydslZeroPagingItemReader<>(
			entityManagerFactory,
			chunkSize,
			queryFactory -> queryFactory
				.select(member.id)
				.from(member)
				.where(member.deletedAt.eq(LocalDate.now())));
	}
}