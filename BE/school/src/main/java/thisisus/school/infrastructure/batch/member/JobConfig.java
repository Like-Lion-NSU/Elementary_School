package thisisus.school.infrastructure.batch.member;

import static thisisus.school.infrastructure.batch.member.ReaderConfig.*;
import static thisisus.school.infrastructure.batch.member.WriterConfig.*;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.querydsl.reader.QuerydslZeroPagingItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import thisisus.school.member.domain.Member;

@Configuration
@RequiredArgsConstructor
public class JobConfig {
	public static final String JOB_NAME = "회원 유예 기간 후 삭제";
	public static final String DELETION_JOB = "memberDeletionExecutionJob";
	public static final String DELETION_STEP = "memberDeletionExecutionStep";
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean(DELETION_JOB)
	public Job deleteMemberJob(@Qualifier(DELETION_STEP) Step deleteStep) {
		return jobBuilderFactory.get(JOB_NAME)
			.start(deleteStep)
			.build();
	}

	@Bean(DELETION_STEP)
	public Step deleteStep(
		@Qualifier(DELETION_READER) QuerydslZeroPagingItemReader<Member> deleteMemberReader,
		@Qualifier(DELETION_WRITER) ItemWriter<Member> deleteMemberWriter
	) {
		return stepBuilderFactory.get("deleteMemberStep")
			.<Member, Member>chunk(1000)
			.reader(deleteMemberReader)
			.writer(deleteMemberWriter)
			.build();
	}
}