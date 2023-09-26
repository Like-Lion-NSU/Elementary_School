import React from "react";
import { Link } from "react-router-dom"; // 리액트 라우터의 Link 컴포넌트를 임포트
import "../css/boardHeader.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";

function BoardHeader({ boardTitle }) {
  return (
    <header>
      <div className="board-header">
        <h1 className="board-title">{boardTitle}</h1>
        <div className="search-container">
          <input
            type="text"
            className="search-input"
            placeholder="검색어를 입력하세요"
          />
          <FontAwesomeIcon icon={faSearch} className="search-input-icon" />
        </div>
      </div>
    </header>
  );
}

export default BoardHeader;
