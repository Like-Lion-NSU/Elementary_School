import React from "react";
import Modal from "react-modal";
import "../css/kioskproblem.css";
import img1 from "./img/megacoffee.png";
const kioskproblem = () => {
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
      width: "60%",
      height: "40%",
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
      isOpen={true}
      shouldCloseOnOverlayClick={false}
      style={customModalStyles}
    >
      <div>
        <img src={img1} className="kioskProblem_img"></img>
      </div>
      <h2>시험 문제</h2>
      <div className="kioskProblem_list">
        <ul>
          <li>아메리카노 연하게 1잔</li>
          <li>아이스티 1샷추가 1잔</li>
          <li>리얼초코프라페 휘핑O 1잔</li>
          <li>포장</li>
          <li>카드결제, 다른 할인 X</li>
        </ul>
      </div>
      <button>문제 풀이 하기</button>
    </Modal>
  );
};
export default kioskproblem;
