//package thisisus.school.post.dto;
//
//import io.swagger.annotations.ApiModel;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import thisisus.school.post.domain.Comment;
//import thisisus.school.post.domain.PostLiked;
//
//@ApiModel(value = "Liked 기본 응답")
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//public class LikedDefaultResponseDto {
//
//    private Long likedId;
//    private Long postId;
//    private boolean isDeleted;
//
//    public LikedDefaultResponseDto(PostLiked postLiked){
//        this.likedId=postLiked.getId();
//        this.postId=postLiked.getPost().getId();
//        this.isDeleted=postLiked.isDeleted();
//    }
//
//}
