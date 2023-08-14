import React from 'react';
import '../css/post.css';

const PostMain = ({ content, likes, views }) => {
  return (
    <div className="post-main">
      <div className="post-content">
        {content}
      </div>
      <div className="post-meta">
        <p>추천수: {likes}</p>
        <p>조회수: {views}</p>
      </div>
    </div>
  );
};

export default PostMain;