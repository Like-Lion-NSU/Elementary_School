import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "../css/main.css";
import Sidebar from "../sidebar/sidebar";
import axios from "axios";

const Main = () => {
  const [userInfo, setUserInfo] = useState(null);
  useEffect(() => {
    const tokenMain = async () => {
      try {
        const accessToken = getCookieValue("accessToken"); // 예시 함수로 쿠키 값 추출
        console.log("accessToken:", accessToken); // 추가된 부분
        if (accessToken == undefined || accessToken == null) {
          window.location.href = "/";
        }

        const response = await axios.get("/v1/home", {
          headers: {
            Authorization: `Bearer ${accessToken}`,
            Accept: "application/json", // JSON 응답을 요청한다고 설정
          },
        });
        console.log("날라오는 데이터", response.data);
        console.log("날아오는 상태", response.status);

        if (response.status === 200) {
          setUserInfo(response.data);
        } else {
          window.location.href = "/";
        }
      } catch (error) {
        if (error.response && error.response.status === 401) {
          try {
            const refreshToken = getCookieValue("refreshToken"); // 예시 함수로 쿠키 값 추출

            const refreshResponse = await axios.post("/v1/auth/refresh", null, {
              headers: {
                Authorization: `Bearer ${refreshToken}`,
              },
            });
            console.log("날라오는 데이터", refreshResponse.data);
            console.log("날아오는 상태", refreshResponse.status);

            if (refreshResponse.status !== 200) {
              window.location.href = "/";
            }

            const newAccessToken = refreshResponse.data;
            const refreshedResponse = await axios.get("/v1/home", {
              headers: {
                Authorization: `Bearer ${newAccessToken}`,
              },
            });

            setUserInfo(refreshedResponse.data);
          } catch (refreshError) {
            // RefreshToken으로 새로운 AccessToken 발급 실패
            // 로그아웃 처리 등을 수행
          }
        }
        // Handle other errors
      }
    };

    tokenMain();
  }, []);

  // 쿠키 값 추출 함수 예시
  function getCookieValue(cookieName) {
    const cookies = document.cookie.split(";");
    for (const cookie of cookies) {
      const [name, value] = cookie.trim().split("=");
      if (name === cookieName) {
        return value;
      }
    }
  }
  return (
    <div className="main-page" id="mainE-container">
      <div>
        <Sidebar />
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
              <div className="mainE-role">채팅해요</div>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Main;
