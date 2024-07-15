package thisisus.school.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import thisisus.school.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {

	boolean existsByEmail(String email);

	Member findByEmail(String email);
}