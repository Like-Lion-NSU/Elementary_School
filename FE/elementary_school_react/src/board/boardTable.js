import React from 'react';
import '../css/boardTable.css';
import { Link } from 'react-router-dom';

function BoardTable({ posts }) {
  return (
    <div>
      <div className="board-table-container">
        <table className="board-table">
          <thead>
            <tr>
              <th>제목</th>
              <th>작성자</th>
              <th>날짜</th>
              <th>조회수</th>
            </tr>
          </thead>
          <tbody className="board-table-body">
            {posts.map(post => (
              <tr key={post.id} onClick={() => window.location.href = `/post/${post.id}`} style={{ cursor: 'pointer' }}>
                <td>{post.title}</td>
                <td>{post.author}</td>
                <td>{post.date}</td>
                <td>{post.views}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <Link to="/write" className="board-write-button">글쓰기</Link>
    </div>
  );
}

export default BoardTable;