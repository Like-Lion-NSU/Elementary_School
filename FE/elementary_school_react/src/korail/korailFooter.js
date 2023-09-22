import React from "react";
import { Link } from "react-router-dom";
import "../css/korailFooter.css";

function KorailFooter() {
  return (
    <div className='korailFooter'>
      <Link to='/korailSeat'>열차 조회하기</Link>
    </div>
  );
}

export default KorailFooter;
