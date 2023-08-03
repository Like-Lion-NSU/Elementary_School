import React from 'react';
import { Link } from 'react-router-dom';
import '../css/main.css';
import Sidebar from '../sidebar/sidebar';

const MainPage = () => {
  return (
    <div className="main-page" id="mainE-container">
      <Sidebar />
      <h1 id="mainE-title">환영합니다.</h1>
      <div className="main-buttons-wrapper">
        <div className="main-buttons">
          <Link to="#" className="mainE-button">
            <span role="img" aria-label="communication">📣</span>
            <div className='mainE-role'>소통해요</div>
          </Link>
          <Link to="#" className="mainE-button">
            <span role="img" aria-label="qna">📝</span>
            <div className='mainE-role'>질문게시판</div>
          </Link>
        </div>
        <div className="main-buttons">
          <Link to="#" className="mainE-button">
            <span role="img" aria-label="practice">📱</span>
            <div className='mainE-role'>연습하기</div>
          </Link>
          <Link to="#" className="mainE-button">
            <span role="img" aria-label="information">🗞️</span>
            <div className='mainE-role'>정책정보</div>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default MainPage;