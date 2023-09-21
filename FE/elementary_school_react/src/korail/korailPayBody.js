import React, { useState } from "react";
import { Link } from "react-router-dom";
import "../css/korailBody.css";
import KorailResult from "../korail/korailResult";

function KorailPayBody({ lastScore }) {
  const [resultOpen, setResultOpen] = useState(false);
  const handlePaymentClick = () => {
    setResultOpen(true);
  };

  const closeResultModal = () => {
    setResultOpen(false);
  };
  return (
    <div className='korail-pay-container'>
      <div className='ticket-details'>
        <div className='ticket-price'>
          <div>
            <p>승차권</p>
            <p>114,400원</p>
          </div>
          <div>
            <p>할인 적용 전</p>
            <p>114,400원 (총 0원 할인)</p>
          </div>
        </div>
        <div className='payment-method'>
          <p>신용(체크) 카드 결제</p>
        </div>
      </div>
      <div className='payment-button'>
        <p>총 1개 114,400원</p>
        <button onClick={handlePaymentClick}>결제/발권</button>
        {resultOpen && (
          <KorailResult
            resultopen={resultOpen}
            setResultopen={closeResultModal}
            lastScore={lastScore}
          />
        )}
      </div>
    </div>
  );
}

export default KorailPayBody;
