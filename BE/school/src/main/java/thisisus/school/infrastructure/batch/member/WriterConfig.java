package thisisus.school.infrastructure.batch.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import thisisus.school.member.domain.Member;
import thisisus.school.member.repository.MemberRepository;

@Configuration
@RequiredArgsConstructor
public class WriterConfig {

	public static final String DELETION_WRITER = "MemberItemWriterForDeletion";

	private final MemberRepository memberRepository;

	@Bean(DELETION_WRITER)
	public ItemWriter<Member> deleteMemberWriter() {
		return members -> {
			List<Long> ids = members.stream()
				.map(Member::getId)
				.collect(Collectors.toList());
			if (!ids.isEmpty()) {
				memberRepository.deleteByIdIn(ids);
			}
		};
	}
}