import React from 'react';
import { Link } from 'react-router-dom';
import '../css/role.css';

const RolePage = () => {
  return (
    <div className="role-page" id="roleE-container">
      <h1 id="roleE-title">역할을 선택해주세요.</h1>
      <div className="role-buttons">
        <Link to="/practiceType" className="roleE-button">
          <span role="img" aria-label="Teacher">👩‍🏫</span>
          <div className='roleE-role'>선생님</div>
        </Link>
        <Link to="/" className="roleE-button">
          <span role="img" aria-label="Student">👩‍🎓</span>
          <div className='roleE-role'>학생</div>
        </Link>
      </div>
    </div>
  );
};

export default RolePage;