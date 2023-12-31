import React from "react";
import { Link } from "react-router-dom";
import "../css/practiceType.css";
import megacoffeeImage from "./img/megacoffee.png";
import korailImage from "./img/korail.png";
import Sidebar from "../sidebar/sidebar";

const PracticeTypePage = () => {
  return (
    <div className="type-page" id="typeE-container">
      <Sidebar />
      <h1 id="typeE-title">연습 종류를 선택해주세요.</h1>
      <div className="type-buttons">
        <Link to="/kiosk" className="typeE-button">
          <img src={megacoffeeImage} alt="Mega Coffee" />
          <div className="typeE-type">메가커피</div>
        </Link>
        <Link to="/korail" className="typeE-button">
          <img src={korailImage} alt="Korail" />
          <div className="typeE-type">코레일</div>
        </Link>
      </div>
    </div>
  );
};

export default PracticeTypePage;
