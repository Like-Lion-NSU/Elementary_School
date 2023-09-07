import React, { useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

import "../css/post.css";

function PostFooter({ comments, userIsAuthor }) {
  const [editCommentId, setEditCommentId] = useState(null);
  const [editedCommentText, setEditedCommentText] = useState("");
  const [newCommentText, setNewCommentText] = useState("");
  const [showCommentButton, setShowCommentButton] = useState(true);

  const { category, postId } = useParams();
  const formData = new FormData();
  formData.append("content", newCommentText);

  const handleEdit = (comment) => {
    setEditCommentId(comment.commentId);
    setEditedCommentText(comment.body);
  };

  const handleCancelEdit = () => {
    setEditCommentId(null);
    setEditedCommentText("");
  };

  const handleSaveEdit = async () => {
    try {
      const data = editedCommentText;
      const accessToken = getCookieValue("accessToken");
      await axios({
        method: "PUT",
        url: `/post/${category}/${postId}/comment/${editCommentId}`,
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
        //프론트랑 백이랑 data안의 값 맞추기
        data: { content: data },
      }).then((response) => {
        console.log("댓글 수정 결과:", response);
        setEditCommentId(null);
        setEditedCommentText("");
        window.location.reload();
      });
    } catch (error) {
      console.error("댓글 수정 오류:", error);
    }
  };

  const handleDelete = async (comments) => {
    try {
      const accessToken = getCookieValue("accessToken");
      await axios({
        method: "delete",
        url: `/v1/post/${category}/${postId}/comment/${comments.commentId}`,
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      }).then((response) => {
        console.log("댓글 삭제 결과:", response);
        console.log("comments", comments);
        window.location.reload();
      });
    } catch (error) {
      console.error("댓글 삭제 오류:", error);
    }
  };

  const handleAddComment = async () => {
    try {
      console.log("newCommentText:", newCommentText);
      const accessToken = getCookieValue("accessToken");
      await axios({
        method: "POST",
        url: `/v1/post/${category}/${postId}/comment`,
        data: formData,
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      }).then((response) => {
        console.log("댓글 추가 결과:", response);
        setNewCommentText("");
        setShowCommentButton(false);
        window.location.reload();
      });
    } catch (error) {
      console.error("댓글 추가 오류:", error);
    }
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

  console.log("댓글 데이터:", comments);
  return (
    <div className="post-footer">
      <div className="comment-input">
        <div className="comment-E-input-container">
          <textarea
            className="comment-E-input"
            placeholder="댓글을 입력하세요..."
            value={newCommentText}
            onChange={(e) => setNewCommentText(e.target.value)}
            onClick={() => setShowCommentButton(true)}
          />
          {showCommentButton && (
            <button
              className="comment-submit-button"
              onClick={handleAddComment}
            >
              댓글 등록
            </button>
          )}
        </div>
      </div>
      <ul className="comment-list">
        {comments.map((comment, index) => (
          <li
            key={index}
            className={`comment-item ${userIsAuthor ? "my-comment" : ""}`}
          >
            <div className="comment-meta">
              <p>{comment.email}</p>
              <p>{comment.body}</p>
              <p>{comment.updateAt}</p>
            </div>
            {editCommentId === comment.commentId ? (
              <div>
                <p className="comment-content">{comment.body}</p>
                <textarea
                  value={editedCommentText}
                  onChange={(e) => setEditedCommentText(e.target.value)}
                />
                <div className="comment-actions">
                  <button onClick={handleSaveEdit}>저장</button>
                  <button onClick={handleCancelEdit}>취소</button>
                </div>
              </div>
            ) : (
              <div>
                <p className="comment-content">{comment.text}</p>
                <div className="comment-actions">
                  <button onClick={() => handleDelete(comment)}>삭제</button>
                  <button onClick={() => handleEdit(comment)}>수정</button>
                </div>
              </div>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default PostFooter;
