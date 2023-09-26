import React from "react";
import likelion from "./whitelogo.png";
import "../css/footer.css";

const Footer = () => {
  return (
    <div className="Footer">
      <div className="JTable">
        <img src={likelion} className="Jlogo" />
        <div className="JFooterContent">
          <p>
            <b>소속</b> 남서울대학교&nbsp;&nbsp;<b>팀명</b>
            이게우리야&nbsp;&nbsp;
          </p>

          <p>
            <b>Back-End</b> 권영태 서송현 박민서&nbsp;&nbsp;
            <b>Front-End</b> 장창현 황은혜 정재은
          </p>
        </div>
      </div>
    </div>
  );
};
export default Footer;
