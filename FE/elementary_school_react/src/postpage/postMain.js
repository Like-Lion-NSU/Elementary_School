import React from "react";
import "../css/post.css";
import axios from "axios";

const PostMain = ({ content, likes, views, category, postId }) => {
  const UpdatePost = () => {
    axios({
      method: "PUT",
      url: `/post/${category}/${postId}`,
      data: {
        id: postId,
      },
    });
  };
  return (
    <div className="post-main">
      <div className="post-content">{content}</div>
      <div className="post-meta">
        <p>추천수: {likes}</p>
        <p>조회수: {views}</p>
        <p>수정</p>
      </div>
    </div>
  );
};

export default PostMain;
