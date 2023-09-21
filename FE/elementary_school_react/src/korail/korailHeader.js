import React from "react";
import { Link } from "react-router-dom";
import "../css/korailHeader.css";

function KorailHeader({ lastScore, setProblemopen }) {
  const seeProblem = () => {
    setProblemopen(true);
  };
  return (
    <div className='korailHeader'>
      <Link to='/practiceType' className='korailHome'>
        <div>처음으로</div>
      </Link>
      <div className='korailBrand'>승차권 예매</div>
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

export default KorailHeader;
