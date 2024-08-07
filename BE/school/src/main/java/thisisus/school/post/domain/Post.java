package thisisus.school.post.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import thisisus.school.common.BaseTimeEntity;
import thisisus.school.member.domain.Member;
import thisisus.school.post.exception.NotCorrectUserException;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private PostCategory category;

    @Column
    @ColumnDefault("0")
    private Integer likeCount;

    @Column
    @ColumnDefault("0")
    private Integer viewCount;

    @Column
    private boolean isDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void delete() {
        this.isDelete = true;
    }

    public void update(String title, String content, PostCategory category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void checkWriter(Post post, Long memberId) {
        if (post.getMember().getId() != memberId) {
            throw new NotCorrectUserException();
        }
    }

    public void increaseLikeCount() {
        this.likeCount = getLikeCount() + 1;
    }

    public void decreaseLikeCount() {
        this.likeCount = getLikeCount() - 1;
    }
}
