import React, { useState } from "react";
import Modal from "react-modal";
import "../css/kioskproblem.css";
import img1 from "./img/megacoffee.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPen } from "@fortawesome/free-solid-svg-icons";
const Kioskproblem = ({ problemopen, setProblemopen }) => {
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
      width: "50vw",
      height: "35vh",
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
  return (
    <Modal
      isOpen={problemopen}
      shouldCloseOnOverlayClick={false}
      style={customModalStyles}
    >
      <div className="kioskHeader">
        <div className="kioskFont">국민학교 제1회 학력고사</div>
        <div className="kioskFont" id="kioskQ">
          시험 문제
        </div>
        <div>
          <div
            className="kioskFont"
            id="goExam"
            onClick={() => setProblemopen(false)}
          >
            <FontAwesomeIcon icon={faPen} /> 시험장 가기
          </div>
        </div>
      </div>
      <div className="kioskQFlex">
        <img src={img1} className="kioskProblem_img"></img>
        <div className="kioskProblem_list">
          <ol>
            <li>1번 문제 ) 아메리카노 연하게 1잔</li>
            <li>2번 문제 ) 아이스티 1샷추가 1잔</li>
            <li>3번 문제 ) 리얼초코프라페 휘핑O 1잔</li>
            <li>4번 문제 ) 포장</li>
            <li>5번 문제 ) 카드 결제하기 / 다른 할인 X</li>
          </ol>
        </div>
      </div>
    </Modal>
  );
};
export default Kioskproblem;
