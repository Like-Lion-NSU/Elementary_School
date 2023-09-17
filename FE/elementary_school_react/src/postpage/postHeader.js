import React from "react";
import "../css/post.css";
import { Link } from "react-router-dom";

const PostHeader = ({ title, authorEmail, currentEmail }) => {
  return (
    <div className="post-header">
      <h2>{title}</h2>
      <p>{authorEmail}</p>
      {authorEmail !== currentEmail && (
        <Link to={`/chat/${authorEmail}`}>
          <button>채팅</button>
        </Link>
      )}
    </div>
  );
};

export default PostHeader;
