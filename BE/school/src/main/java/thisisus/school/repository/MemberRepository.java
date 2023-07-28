package thisisus.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
    Optional<Member> findByEmail(String email);
}
