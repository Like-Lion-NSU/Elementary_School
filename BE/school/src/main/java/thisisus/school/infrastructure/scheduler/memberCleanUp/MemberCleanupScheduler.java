package thisisus.school.infrastructure.scheduler.memberCleanUp;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import thisisus.school.member.repository.MemberRepository;

@RequiredArgsConstructor
@Slf4j
@Component
public class MemberCleanupScheduler {
	private final MemberRepository memberRepository;

	@Transactional
	@Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
	public void deleteExpiredMembers() {
		long count = memberRepository.deleteByDeletedAt();
		log.info("멤버 총 {} 명 삭제", count);
	}
}