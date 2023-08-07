import React from "react";
import { Link } from "react-router-dom";
import "../css/sidebar.css";

const Sidebar = () => {
  return (
    <div className="sidebar">
      <ul className="sidebarE-list">
        <li>
          <Link to="#">공지사항</Link>
        </li>
        <li>
          <Link to="#">소통해요</Link>
        </li>
        <li>
          <Link to="/practiceType">연습하기</Link>
        </li>
        <li>
          <Link to="/policy">정책정보</Link>
        </li>
        <li>
          <Link to="/question">질문게시판</Link>
        </li>
        <li>
          <Link to="/mypage">마이페이지</Link>
        </li>
        <li>
          <Link to="#">로그아웃</Link>
        </li>
      </ul>
    </div>
  );
};

export default Sidebar;
