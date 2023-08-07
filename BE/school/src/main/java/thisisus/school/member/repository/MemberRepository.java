package thisisus.school.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
