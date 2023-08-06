package thisisus.school.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
}
