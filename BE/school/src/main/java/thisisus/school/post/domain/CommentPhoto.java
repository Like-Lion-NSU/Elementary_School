//package thisisus.school.post.domain;
//
//import lombok.*;
//import thisisus.school.common.BaseEntity;
//
//import javax.persistence.*;
//
//@Data
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//public class CommentPhoto extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="comment_photo_id")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name="comment_id")
//    private Comment comment;
//
//    //파일 경로 저장
//    private String photoUrl;
//
//    @Builder
//    public CommentPhoto(Comment comment, String photoUrl){
//        this.comment=comment;
//        this.photoUrl=photoUrl;
//        this.setDeleted(false);
//
//        comment.getCommentPhoto().add(this);
//    }
//
//    public void delete(){
//        this.setDeleted(true);
//    }
//}
//
