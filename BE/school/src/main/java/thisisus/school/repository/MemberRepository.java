package thisisus.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
}
