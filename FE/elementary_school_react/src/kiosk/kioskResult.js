import "../css/problemResult.css";
import React from "react";
import Modal from "react-modal";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
import axios from "axios";

const KioskResult = ({ resultopen, setResultopen, lastScore, setScore }) => {
  const customModalStyles = {
    overlay: {
      backgroundColor: " rgba(0, 0, 0, 0.4)",
      width: "100%",
      height: "100vh",
      zIndex: "10",
      position: "fixed",
      top: "0",
      left: "0",
    },
    content: {
      width: "45vw",
      height: "30vh",
      zIndex: "150",
      position: "absolute",
      top: "50%",
      left: "50%",
      transform: "translate(-50%, -50%)",
      borderRadius: "10px",
      boxShadow: "2px 2px 2px rgba(0, 0, 0, 0.25)",
      backgroundColor: "white",
      justifyContent: "center",
      overflow: "auto",
      padding: "0",
    },
  };
  const ddengQuestion = () => {
    const scoreQuestion = (100 - lastScore) / 10;
    return scoreQuestion;
  };
  const kioskBack = () => {
    const accessToken = getCookieValue("accessToken");
    axios({
      method: "POST",
      url: "/v1/practice/point",
      headers: {
        Authorization: `Bearer ${accessToken}`,
        "Content-Type": "application/x-www-form-urlencoded",
      },
      params: {
        lastScore: lastScore,
      },
    })
      .then((result) => {
        window.location.href = "/home";
      })
      .catch((error) => {
        if (error.response && error.response.status === 401) {
          try {
            const refreshToken = getCookieValue("refreshToken");
            const refreshResponse = axios.post("/auth/refresh", null, {
              headers: {
                Authorization: `Bearer ${refreshToken}`,
              },
            });
            const newAccessToken = refreshResponse.data;
            axios
              .post("/v1/practice/point", {
                headers: {
                  Authorization: `Bearer ${newAccessToken}`,
                  "Content-Type": "application/x-www-form-urlencoded",
                },
                params: {
                  lastScore: lastScore,
                },
              })
              .then((result) => {
                window.location.href = "/v1/home";
              });
          } catch (err) {}
        }
      });
  };

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
    <Modal
      isOpen={resultopen}
      shouldCloseOnOverlayClick={false}
      style={customModalStyles}
    >
      <div className="ResultHeader">
        <div className="resultFont">국민학교 제1회 학력고사</div>
        <div className="resultFont" id="ResultQ">
          시험 결과 공지
        </div>
        <div onClick={kioskBack}>
          <div
            id="goHome"
            className="resultFont"
            onClick={() => setResultopen(false)}
          >
            <FontAwesomeIcon icon={faXmark} /> 닫기
          </div>
        </div>
      </div>
      <div>
        <div className="resultFlex">
          <div className="resultLeft">틀린 문제 : </div>
          <div className="resultRight"> {ddengQuestion()} 개</div>
        </div>
        <div className="resultFlex">
          <div className="resultLeft">학력고사 결과 : </div>
          <div className="resultRight"> {lastScore} 점</div>
        </div>
        <div className="resultFlex">
          <div className="resultLeft">적립 포인트 : </div>
          <div className="resultRight"> {lastScore} point</div>
        </div>
        <div className="resultCenter">수고하셨습니다. *^-^*~~</div>
      </div>
    </Modal>
  );
};
export default KioskResult;
