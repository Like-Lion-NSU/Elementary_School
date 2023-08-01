import React from 'react';
import { useNavigate } from 'react-router-dom';
import './role.css';

const RolePage = () => {
  const roleEGoToTeacherPage = () => {
    navigate('/teacher-page');
  };

  const roleEGoToStudentPage = () => {
    navigate('/student-page');
  };

  const navigate = useNavigate();

  return (
    <div className="role-page" id="roleE-container">
      <h1 id="roleE-title">역할을 선택해주세요</h1>
      <div className="role-buttons">
        <button className="roleE-button" onClick={roleEGoToTeacherPage}>
          <span role="img" aria-label="Teacher">👩‍🏫</span>
        </button>
        <button className="roleE-button" onClick={roleEGoToStudentPage}>
          <span role="img" aria-label="Student">👩‍🎓</span>
        </button>
      </div>
    </div>
  );
};

export default RolePage;