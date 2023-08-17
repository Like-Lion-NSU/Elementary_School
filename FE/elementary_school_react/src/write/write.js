import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
import { useParams } from "react-router-dom";
import "../css/write.css";

const Write = () => {
  const [category, setCategory] = useState("");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [selectedFile, setSelectedFile] = useState(null);
  const [error, setError] = useState(null);
  const location = useLocation();
  const userContent = location.state;
  useEffect(() => {
    if (userContent) {
      setCategory(userContent.category);
      setTitle(userContent.title);
      setContent(userContent.content);
      setSelectedFile(userContent.imageUrl);
    } else {
      console.log("아무것도 안들어왔쥬?");
    }
  }, []);

  const handleSubmit = async (event) => {
    event.preventDefault();

    setError(null);

    const formData = new FormData();
    formData.append("category", category);
    formData.append("title", title);
    formData.append("content", content);
    if (selectedFile) {
      formData.append("files", selectedFile);
    }

    try {
      const accessToken = getCookieValue("accessToken");
      axios({
        method: "POST",
        url: "/writePost",
        data: formData,
        headers: { Authorization: `Bearer ${accessToken}` },
      }).then((result) => {
        console.log(result);
      });
    } catch (error) {
      console.error("글 등록 중 에러가 발생했습니다.", error);
      setError("글 등록 중 에러가 발생했습니다.");
    }
  };

  function getCookieValue(cookieName) {
    const cookies = document.cookie.split(";");
    for (const cookie of cookies) {
      const [name, value] = cookie.trim().split("=");
      if (name === cookieName) {
        return value;
      }
    }
  }

  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  return (
      <div className="write-container">
        <form onSubmit={handleSubmit}>
          <div>
            <select
                id="write-category"
                name="write-category"
                className="write-input"
                value={category}
                onChange={(e) => setCategory(e.target.value)}
            >
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
                value={title}
                onChange={(e) => setTitle(e.target.value)}
            />
          </div>
          <div>
          <textarea
              id="write-E-content"
              name="write-E-content"
              className="write-content"
              placeholder="내용을 입력하세요"
              required
              value={content}
              onChange={(e) => setContent(e.target.value)}
          />
          </div>
          <div>
            <input
                type="file"
                id="attachments"
                name="attachments"
                className="write-input"
                accept="image/*,video/*"
                onChange={handleFileChange}
            />
          </div>
          <div>{error && <div className="error-message">{error}</div>}</div>
          <button type="submit" className="write-button">
            글쓰기 완료
          </button>
        </form>
      </div>
  );
};

export default Write;