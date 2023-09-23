import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "../css/main.css";
import Sidebar from "../sidebar/sidebar";
import axios from "axios";

const Main = () => {
  return (
    <div className="main-page" id="mainE-container">
      <div>
        <h1 id="mainE-title">환영합니다</h1>
        <div className="main-buttons-wrapper">
          <div className="main-buttons">
            <Link to="/소통해요/posts" className="mainE-button">
              <span role="img" aria-label="communication">
                📣
              </span>
              <div className="mainE-role">소통해요</div>
            </Link>
            <Link to="/질문해요/posts" className="mainE-button">
              <span role="img" aria-label="qna">
                📝
              </span>
              <div className="mainE-role">질문해요</div>
            </Link>
          </div>
          <div className="main-buttons">
            <Link to="/practiceType" className="mainE-button">
              <span role="img" aria-label="practice">
                📱
              </span>
              <div className="mainE-role">연습하기</div>
            </Link>
            <Link to="/policy" className="mainE-button">
              <span role="img" aria-label="information">
                🗞️
              </span>
              <div className="mainE-role">정책정보</div>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Main;
