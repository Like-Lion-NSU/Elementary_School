package thisisus.school.post.domain;

import lombok.*;
import thisisus.school.common.BaseTimeEntity;
import thisisus.school.post.dto.request.PostUpdateRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
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
    private int likeCount;

    @Column
    private int viewCount;

    @Column
    private boolean isDelete;

//    @OneToMany(mappedBy = "post")
//    private List<PostLiked> likes = new ArrayList<>();
//
//    @OneToMany(mappedBy = "post")
//    private List<Comment> comments = new ArrayList<>();

    //삭제 수행 메소드
    public void delete() {
        this.isDelete = true;
    }

    public void update(PostUpdateRequest postRequest) {
        this.title = postRequest.getTitle();
        this.content = postRequest.getContent();
        this.category = postRequest.getCategory();
    }


//    public List<Comment> getComments(){
//        int size = this.comments.size();
//        if(size<1) return null;
//        List<Comment> comments = new ArrayList<>(this.comments);
//        comments.removeIf(comment -> comment.isDeleted()==true);
//        Collections.reverse(comments);
//        return comments;
//    }

//    @Builder
//    public Post(String title, String content, PostCategory category, Member member){
//        this.title=title;
//        this.content=content;
//        this.category=category;
//        this.likeCount=0;
//        this.viewCount=0;
//        this.member=member;
//        this.setDeleted(false);
//    }

//    public void savedLikeCount(boolean like){
//        if(like==false) {
//            this.likeCount++;
//        }else if(like==true){
//            this.likeCount--;
//        }else if(this.likeCount<=0){
//            this.likeCount=0;
//
//        }
//    }
}