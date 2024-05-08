package thisisus.school.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.member.domain.Member;
import thisisus.school.member.exception.NotFoundMemberException;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    Member findByEmail(String email);

    default Member findByMemberId(Long memberId) {
        return findById(memberId).orElseThrow(NotFoundMemberException::new);
    }
}