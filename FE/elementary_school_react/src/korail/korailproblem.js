import React, { useState } from "react";
import Modal from "react-modal";
import "../css/korailproblem.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPen } from "@fortawesome/free-solid-svg-icons";
const Korailproblem = ({ problemopen, setProblemopen }) => {
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
      <div className='korailPHeader'>
        <div className='korailFont'>국민학교 제2회 학력고사</div>
        <div className='korailFont' id='kioskQ'>
          시험 문제
        </div>
        <div>
          <div
            className='korailFont'
            id='goExam'
            onClick={() => setProblemopen(false)}
          >
            <FontAwesomeIcon icon={faPen} /> 시험장 가기
          </div>
        </div>
      </div>
      <div className='korailQFlex'>
        <div className='korailProblem_list'>
          <ol>
            <li>1번 문제 ) 서울에서 부산 가는 기차표</li>
            <li>2번 문제 ) 2023년 9월 23일 (토) 16시 08분</li>
            <li>3번 문제 ) 어른 4명</li>
            <li>4번 문제 ) 무궁화호 6호차 17, 18, 19, 20</li>
            <li>5번 문제 ) 카드 결제하기 / 다른 할인 X</li>
          </ol>
        </div>
      </div>
    </Modal>
  );
};
export default Korailproblem;
