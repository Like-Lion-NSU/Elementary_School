import React from "react";
import { Link } from "react-router-dom";
import "../css/sidebar.css";
import Lo_J_Logo from "./img/logo.png";

const Sidebar = () => {
  return (
    <div className="sidebar">
      <ul className="sidebarE-list">
        <li>
          <Link to="/home">
            <img src={Lo_J_Logo} className="logologo-E" alt="로고" />
          </Link>
        </li>
        <li>
          <Link to="/소통해요/posts">소통해요</Link>
        </li>
        <li>
          <Link to="/질문해요/posts">질문해요</Link>
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
          <Link to="/">로그아웃</Link>
        </li>
      </ul>
    </div>
  );
};

export default Sidebar;
