import React from "react";
import "../css/error.css";
import errorImg from "./error.png";
const ErrorPage = () => {
  return (
    <div className="ErrorSection">
      <img src={errorImg} alter="에러" />
      <div>
        <p>
          <span>404</span> 현장학습에 오신걸 환영합니다!
        </p>
        <div>
          지금 보이시는 화면은 오류 화면입니다! <br />
          나가시는 곳은 아래 버튼을 클릭하시면됩니다.
        </div>
        <a href="/home" className="errorBtn">
          현장학습 끝내기
        </a>
      </div>
    </div>
  );
};
export default ErrorPage;
