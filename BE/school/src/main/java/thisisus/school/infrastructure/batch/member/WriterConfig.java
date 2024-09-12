package thisisus.school.infrastructure.batch.member;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import thisisus.school.member.repository.MemberRepository;

@Configuration
@RequiredArgsConstructor
public class WriterConfig {

	public static final String DELETION_WRITER = "memberItemWriterForDeletion";

	private final MemberRepository memberRepository;

	@Bean(DELETION_WRITER)
	public ItemWriter<Long> deleteMemberWriter() {
		return ids -> {
			if (!ids.isEmpty()) {
				memberRepository.deleteByIdIn((List<Long>)ids);
			}
		};
	}
}