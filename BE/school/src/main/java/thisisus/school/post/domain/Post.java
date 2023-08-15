package thisisus.school.post.domain;

import lombok.*;
import thisisus.school.common.BaseEntity;
import thisisus.school.post.dto.PostRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private PostCategory category;
    private int likeCount;
    private int viewCount;
    private Long memberId;
    @OneToMany(mappedBy = "post")
    private List<PostLiked> likes = new ArrayList<>();
    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "post")
    private List<PostPhoto> postPhotos = new ArrayList<>();

    @Builder
    public Post(String title, String content, PostCategory category){
        this.title=title;
        this.content=content;
        this.category=category;
        this.likeCount=0;
        this.viewCount=0;
        this.setDeleted(false);
    }

    public void updatePost(PostRequestDto postRequestDto){
        this.title=postRequestDto.getTitle();
        this.content=postRequestDto.getContent();
        this.category= PostCategory.valueOf(postRequestDto.getCategory());

    }

    public void savedLikeCount(boolean like){
        if(like==false) {
            this.likeCount++;
        }else if(like==true){
            this.likeCount--;
        }else if(this.likeCount<=0){
            this.likeCount=0;

        }
    }


    public void delete(){
        this.title=null;
        this.content=null;
        this.category=null;
        this.setDeleted(true);
    }


}
