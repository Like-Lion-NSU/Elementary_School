import React, { useState, useEffect } from "react";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";
import { faHeart as faHeartRegular } from "@fortawesome/free-regular-svg-icons";
import "../css/post.css";

const PostLike = ({ category, postId, checkedLike }) => {
  //liked 값을 가져올수 있음
  const [liked, setLiked] = useState(false);
  const [currentLikeId, setCurrentLikeId] = useState(null);

  useEffect(() => {
    if (checkedLike === true || checkedLike === null) {
      setLiked(false);
    } else {
      setLiked(true);
    }
  }, []);

  const handleLiked = async () => {
    const accessToken = getCookieValue("accessToken");
    if (!liked) {
      await axios({
        method: "POST",
        url: `/v1/post/${category}/${postId}/like`,
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
        .then((response) => {
          console.log(response);
          setCurrentLikeId(response.data.likeId);
          setLiked(true);
          console.log("좋아요 처리 완료");
        })
        .catch((error) => {
          console.log("좋아요 처리 중 에러가 발생했습니다.", error);
        });
    } else {
      await axios({
        method: "DELETE",
        url: `/v1/post/${category}/${postId}/like/${currentLikeId}`,
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
        .then((response) => {
          setCurrentLikeId(null);
          setLiked(false);
          console.log("좋아요 취소 처리 완료");
        })
        .catch((error) => {
          console.log("좋아요 취소에 필요한 정보가 부족합니다.", error);
        });
    }

    function getCookieValue(cookieName) {
      const cookies = document.cookie.split(";");
      for (const cookie of cookies) {
        const [name, value] = cookie.trim().split("=");
        if (name === cookieName) {
          return value;
        }
      }
      return "";
    }
  };
  return (
    <div className="post-like">
      <button className="like-button" onClick={handleLiked}>
        {liked ? (
          <FontAwesomeIcon icon={faHeart} />
        ) : (
          <FontAwesomeIcon icon={faHeartRegular} />
        )}
      </button>
    </div>
  );
};
export default PostLike;
