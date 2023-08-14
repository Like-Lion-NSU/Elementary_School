import React, { useState } from 'react';
import axios from 'axios';
import '../css/post.css';

function PostFooter({ comments, userIsAuthor }) {
  const [editCommentId, setEditCommentId] = useState(null);
  const [editedCommentText, setEditedCommentText] = useState('');
  const [newCommentText, setNewCommentText] = useState('');
  const [showCommentButton, setShowCommentButton] = useState(true);

  const handleEdit = (comment) => {
    setEditCommentId(comment.id);
    setEditedCommentText(comment.text);
  };

  const handleCancelEdit = () => {
    setEditCommentId(null);
    setEditedCommentText('');
  };

  const handleSaveEdit = async (comment) => {
    try {
      await axios.put(`/api/comments/${comment.id}`, {
        text: editedCommentText,
      });
      setEditCommentId(null);
      setEditedCommentText('');
    } catch (error) {
      console.error('댓글 수정 오류:', error);
    }
  };

  const handleDelete = async (comment) => {
    try {
      await axios.delete(`/api/comments/${comment.id}`);
    } catch (error) {
      console.error('댓글 삭제 오류:', error);
    }
  };

  const handleAccept = async (comment) => {
    try {
      await axios.post(`/api/comments/${comment.id}/accept`);
    } catch (error) {
      console.error('댓글 채택 오류:', error);
    }
  };

  const handleAddComment = async () => {
    try {
      await axios.post('/api/comments', {
        text: newCommentText,
      });
      setNewCommentText('');
      setShowCommentButton(false);
    } catch (error) {
      console.error('댓글 추가 오류:', error);
    }
  };

  const dummyComments = [
    {
      id: 1,
      authorEmail: 'user1@example.com',
      date: '2023-08-15',
      text: '첫 번째 댓글입니다.',
      isAccepted: false,
    },
    {
      id: 2,
      authorEmail: 'user2@example.com',
      date: '2023-08-16',
      text: '두 번째 댓글입니다.',
      isAccepted: true,
    },
  ];

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
            <button className="comment-submit-button" onClick={handleAddComment}>
              댓글 등록
            </button>
          )}
        </div>
      </div>
      {(comments && comments.length > 0) || dummyComments.length > 0 ? (
        <ul className="comment-list">
          {comments.concat(dummyComments).map((comment, index) => (
            <li
              key={index}
              className={`comment-item ${userIsAuthor ? 'my-comment' : ''}`}
            >
              <div className="comment-meta">
                <p>{comment.authorEmail}</p>
                <p>{comment.date}</p>
              </div>
              {editCommentId === comment.id ? (
                <div>
                  <textarea
                    value={editedCommentText}
                    onChange={(e) => setEditedCommentText(e.target.value)}
                  />
                  <div className="comment-actions">
                    <button onClick={() => handleSaveEdit(comment)}>저장</button>
                    <button onClick={handleCancelEdit}>취소</button>
                  </div>
                </div>
              ) : (
                <div>
                  <p className="comment-content">{comment.text}</p>
                  {userIsAuthor && (
                    <div className="comment-actions">
                      <button onClick={() => handleDelete(comment)}>삭제</button>
                      {!comment.isAccepted && (
                        <button onClick={() => handleAccept(comment)}>채택</button>
                      )}
                      <button onClick={() => handleEdit(comment)}>수정</button>
                    </div>
                  )}
                </div>
              )}
            </li>
          ))}
        </ul>
      ) : (
        <p>No comments yet.</p>
      )}
    </div>
  );
}

export default PostFooter;