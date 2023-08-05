import React from "react";
import { Link } from "react-router-dom";
import "../css/question.css";
const Question = () => {
  return (
    <div className="question-page" id="questionJ-container">
      <div>
        <h2>소통해요</h2>
        <input />
      </div>
      <div>
        <table border={1}>
          <thead>
            <tr>
              <th>게시물 제목</th>
              <th>작성자</th>
              <th>조회수</th>
            </tr>
          </thead>
        </table>
      </div>
    </div>
  );
};
export default Question;
