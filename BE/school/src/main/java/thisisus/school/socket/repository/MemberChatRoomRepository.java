package thisisus.school.socket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thisisus.school.member.domain.Member;
import thisisus.school.post.domain.Post;
import thisisus.school.post.domain.PostCategory;
import thisisus.school.socket.model.MemberChatRoom;

import java.util.List;

public interface MemberChatRoomRepository extends JpaRepository<MemberChatRoom, Long> {

    List<MemberChatRoom> findByMember(Member member);
}
