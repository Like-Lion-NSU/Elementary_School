import React from 'react';
import { Link } from 'react-router-dom';
import '../css/megaHeader.css';

function MegaHeader() {
  return (
    <div className="E-header">
      <Link to="/practiceType" div className="E-logo">
        <span role="img" aria-label="홈 아이콘">🏠</span> 처음으로
      </Link>
      <div className="E-brand">메가커피</div>
      <Link to="#" div className="E-language">
        <span role="img" aria-label="언어 아이콘">🌐</span> 언어선택
      </Link>
    </div>
  );
}

export default MegaHeader;
