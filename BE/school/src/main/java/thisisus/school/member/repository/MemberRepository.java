package thisisus.school.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import thisisus.school.member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Member getByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT m.refreshToken FROM Member m WHERE m.id=:id")
    String getRefreshTokenById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.refreshToken=:token WHERE m.id=:id")
    void updateRefreshToken(@Param("id") Long id, @Param("token") String token);
}
