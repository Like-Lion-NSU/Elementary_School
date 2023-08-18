import React from "react";
import { Link } from "react-router-dom";
import "../css/sidebar.css";
import Lo_J_Logo from "./img/logo.png";

const Sidebar = () => {
  return (
    <div className="sidebar">
      <ul className="sidebarE-list">
        <li>
          <Link to="/v1/home">
            <img src={Lo_J_Logo} className="logologo-E" alt="로고" />
          </Link>
        </li>
        <li>
          <Link to="/v1/community">소통해요</Link>
        </li>
        <li>
          <Link to="/v1/question">질문해요</Link>
        </li>
        <li>
          <Link to="/v1/practiceType">연습하기</Link>
        </li>
        <li>
          <Link to="/v1/policy">정책정보</Link>
        </li>
        <li>
          <Link to="/v1/mypage">나의정보</Link>
        </li>
        <li>
          <Link to="/v1/shop">매점가기</Link>
        </li>
        <li>
          <Link to="/v1/">로그아웃</Link>
        </li>
      </ul>
    </div>
  );
};

export default Sidebar;
