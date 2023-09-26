import React from "react";
import "../css/boardTable.css";
import { Link } from "react-router-dom";

function BoardTableC({ res }) {
  const movePage = (response) => {
    window.location.href = `/post/${response.category}/${response.postId}`;
  };
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
                <tr key={response.id} onClick={() => movePage(response)}>
                  <td>{index + 1}</td>
                  <td>{response.email}</td>
                  <td>{response.updateAt}</td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default BoardTableC;
