import React from "react";
import "../css/post.css";

const PostHeader = ({ title, authorEmail }) => {
  return (
    <div className="post-header">
      <h2>{title}</h2>
      <p>{authorEmail}</p>
    </div>
  );
};

export default PostHeader;
