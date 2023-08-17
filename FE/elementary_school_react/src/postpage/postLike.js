import React, { useState } from "react";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";
import { faHeart as faHeartRegular } from "@fortawesome/free-regular-svg-icons";
import "../css/post.css";

const PostLike = ({ category, postId, initialLikes, likeId }) => {
  const [liked, setLiked] = useState(false);
  const [likesCount, setLikesCount] = useState(initialLikes);

  const handleLike = () => {
    if (liked) {
      //취소
      axios
        .post(`/post/${category}/${postId}/like/${likeId}`)
        .then((response) => {
          if (response.data.success) {
            setLiked(false);
            setLikesCount(likesCount - 1);
          }
        })
        .catch((error) => {
          console.error("좋아요 취소 에러:", error);
        });
    } else {
      axios
        .post(`/post/${category}/${postId}/like`)
        .then((response) => {
          if (response.data.success) {
            setLiked(true);
            setLikesCount(likesCount + 1);
          }
        })
        .catch((error) => {
          console.error("좋아요 에러:", error);
        });
    }
  };

  return (
    <div className="post-like">
      <button className="like-button" onClick={handleLike}>
        <FontAwesomeIcon
          icon={liked ? faHeart : faHeartRegular}
          className="heart"
        />
      </button>
    </div>
  );
};

export default PostLike;
