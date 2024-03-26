package thisisus.school.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

  boolean existsByKakaoId(Long kakaoId);

  Member findByKakaoId(Long kakaoId);

}

