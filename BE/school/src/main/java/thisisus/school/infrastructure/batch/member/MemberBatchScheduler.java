package thisisus.school.infrastructure.batch.member;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class MemberBatchScheduler {

	private final JobLauncher jobLauncher;
	private final Job deleteMemberJob;

	@Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
	public void deleteExpiredMembers() throws Exception {
		log.info("스케줄러 시작");
		JobParameters jobParameters = new JobParametersBuilder()
			.addLong("time", System.currentTimeMillis())
			.toJobParameters();
		jobLauncher.run(deleteMemberJob, jobParameters);
	}
}