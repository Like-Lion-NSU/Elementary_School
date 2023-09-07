import React from "react";
import "../css/boardTable.css";
import { Link } from "react-router-dom";

function BoardTableC({ res }) {
  return (
    <div>
      <div className="board-table-container">
        <table className="board-table">
          <thead>
            <tr>
              <th>번호</th>
              <th>작성자</th>
              <th>날짜</th>
            </tr>
          </thead>
          <tbody className="board-table-body">
            {res &&
              res.map((response, index) => (
                <tr key={response.id}>
                  <td>
                    <Link to={`/post/${response.category}/${response.postId}`}>
                      {index + 1}
                    </Link>
                  </td>
                  <td>{response.email}</td>
                  <td>{response.updateAt}</td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
      <Link to="/writePost" className="board-write-button">
        글쓰기
      </Link>
    </div>
  );
}

export default BoardTableC;
