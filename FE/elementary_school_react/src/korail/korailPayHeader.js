import React from "react";
import { Link } from "react-router-dom";
import "../css/korailHeader.css";

function KorailPayHeader({ lastScore, setProblemopen }) {
  const seeProblem = () => {
    setProblemopen(true);
  };

  return (
    <div className='korailHeader'>
      <Link to='/korailSeatNum' className='korailHome'>
        <div>이전으로</div>
      </Link>
      <div className='korailBrand'>결제</div>
      <div>
        <button className='problemButton' onClick={seeProblem}>
          문제보기
        </button>
        <span className='korailNowScore'>
          현재 점수 <span>{lastScore}</span>점
        </span>
      </div>
    </div>
  );
}

export default KorailPayHeader;
