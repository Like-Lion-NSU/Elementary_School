import React from "react";
import { Link } from "react-router-dom";
import "../css/megaHeader.css";

function MegaHeader({ lastScore, setProblemopen }) {
  const seeProblem = () => {
    setProblemopen(true);
  };
  return (
    <div className="E-header">
      <Link to="/v1/practiceType" div className="E-logo">
        <span role="img" aria-label="홈 아이콘">
          🏠
        </span>
        처음으로
      </Link>
      <div className="E-brand">메가커피</div>
      <div>
        <button className="problemButton" onClick={seeProblem}>
          문제보기
        </button>
        <span className="nowScore">
          현재 점수 <span>{lastScore}</span>점
        </span>
      </div>
    </div>
  );
}

export default MegaHeader;
