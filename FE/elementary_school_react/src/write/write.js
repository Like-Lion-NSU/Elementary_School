import React from 'react';
import { Link } from 'react-router-dom';
import '../css/write.css';
import Sidebar from '../sidebar/sidebar';

const Write = () => {
    const handleSubmit = (event) => {
    event.preventDefault(); // 아직 버튼 클릭 시 동작을 안 합니다.
};

    return (
    <div className="write-container">
        <Sidebar />
        <form onSubmit={handleSubmit}>
        <div>
        <label htmlFor="category"></label>
        <select id="write-category" name="write-category" className="write-input">
            <option value="">카테고리를 입력해주세요</option>
            <option value="소통해요">소통해요</option>
            <option value="질문해요">질문해요</option>
        </select>
        </div>
        <div>
        <input
            type="text"
            id="write-title"
            name="write-title"
            className="write-input"
            placeholder="제목을 입력하세요"
            required
        />
        </div>
        <div>
        <textarea
            id="write-E-content"
            name="write-E-content"
            className="write-content"
            placeholder="내용을 입력하세요"
            required
        />
        </div>
        <button type="write-E-submit" className="write-button">글쓰기 완료</button>
    </form>
    </div>
);
};

export default Write;