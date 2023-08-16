import React from "react";
import { Link } from "react-router-dom";
import "../css/sidebar.css";
import Lo_J_Logo from "./img/logo.png";
import axios from "axios";

const Sidebar = () => {
  const logoutClick = async () => {
    try {
      const response = await axios.post("/logout");
      if (response.status === 200) {
        window.location.href = "/";
      }
    } catch (error) {
      console.error(error);
    }
  };
  return (
    <div className="sidebar">
      <ul className="sidebarE-list">
        <li>
          <Link to="/home">
            <img src={Lo_J_Logo} className="logologo-E" alt="로고" />
          </Link>
        </li>
        <li>
          <Link to="/community">소통해요</Link>
        </li>
        <li>
          <Link to="/question">질문해요</Link>
        </li>
        <li>
          <Link to="/practiceType">연습하기</Link>
        </li>
        <li>
          <Link to="/policy">정책정보</Link>
        </li>
        <li>
          <Link to="/mypage">나의정보</Link>
        </li>
        <li>
          <Link to="/shop">매점가기</Link>
        </li>
        <li>
          <div className="sideCursorPointer" onClick={logoutClick}>
            로그아웃
          </div>
        </li>
      </ul>
    </div>
  );
};

export default Sidebar;
