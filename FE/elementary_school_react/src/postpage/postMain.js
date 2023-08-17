import React from "react";
import "../css/post.css";
import { useNavigate } from "react-router-dom";

const PostMain = ({ category, title, content, likes, views, imageUrl }) => {
    const navigate = useNavigate();
    const postUpdate = () => {
        navigate("/writePost", {
            state: {
                category: category,
                title: title,
                content: content,
                imageUrl: imageUrl,
                message: "수정 완료",
            },
        });
    };
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
                <p onClick={postUpdate}>수정</p>
            </div>
        </div>
    );
};

export default PostMain;