import React from "react";
import "../css/post.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const PostMain = ({
  postId,
  category,
  title,
  content,
  likes,
  views,
  imageUrl,
  email,
  currentMemberEmail,
}) => {
  const navigate = useNavigate();
  const postUpdate = () => {
    navigate("/writePost", {
      state: {
        postId: postId,
        category: category,
        title: title,
        content: content,
        imageUrl: imageUrl,
        message: "수정 완료",
      },
    });
  };
  const postdelete = () => {
    const accessToken = getCookieValue("accessToken");
    axios({
      method: "DELETE",
      url: `/v1/post/${category}/${postId}`,
      headers: { Authorization: `Bearer ${accessToken}` },
    }).then((result) => {
      window.location.href = `/${category}/posts`;
    });
  };
  function getCookieValue(cookieName) {
    const cookies = document.cookie.split(";");
    for (const cookie of cookies) {
      const [name, value] = cookie.trim().split("=");
      if (name === cookieName) {
        return value;
      }
    }
  }
  return (
    <div className="post-main">
      <div className="post-content">{content}</div>
      {/* 이미지가 있을 경우에만 이미지 표시 */}
      {imageUrl && (
        <img src={imageUrl} alt="게시물 이미지" className="post-image" />
      )}
      <div className="post-meta">
        <p>추천수: {likes}</p>
        <p>조회수: {views}</p>
        {currentMemberEmail == email ? (
          <>
            <p onClick={postUpdate} className="updatePost">
              수정
            </p>
            <p onClick={postdelete} className="deletePost">
              삭제
            </p>
          </>
        ) : (
          <p></p>
        )}
      </div>
    </div>
  );
};

export default PostMain;
