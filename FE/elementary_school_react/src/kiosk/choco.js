import React, { useState } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark, faRotate } from '@fortawesome/free-solid-svg-icons';
import opJChoco from './img/optionImg/optionChoco.png';
import cream from './img/optionImg/cream.png';
import "../css/option.css";

function OpChoco({ opJChocoShow, setopJChocoShow }) {
  const [opJselectedOptions, setopJSelectedOptions] = useState({ cream: null });

  const opJChocoClose = () => {
    setopJChocoShow(false);
  };
  const opJChocoOpen = () => {
    setopJChocoShow(true);
  };

  const opJhandleCreamSelect = (option) => {
    setopJSelectedOptions((prev) => ({
      ...prev,
      cream: prev.cream === option ? null : option
    }));
  };

  const resetOptions = () => {
    setopJSelectedOptions({ cream: null });
  };

  return (
    <div>
      {opJChocoShow && (
        <div className="opJOption">
          <div className="opJHead">
            <div>선택하신 상품의 옵션상품을 모두 선택해주세요. </div>
            <FontAwesomeIcon icon={faXmark} onClick={opJChocoClose} />
          </div>
          <div className="opJImgFlex">
            <img src={opJChoco} alt="초코프라페" className="opJCoffeeImg" />
            <div className="opJTPE">
                <div className="opJTitlePrice">
                    <div className="opJTitle">리얼초코프라페</div>
                    <div className="opJPrice">3,900원</div>
                </div>
                <div className="opJExpain">(24ox)진한 초코소스와 바닐라향이 더해져 씹는 재미가 있는 달콤한 프라페</div>
            </div>
          </div>
          <div className="opJSelectOption">
            <div className="opJInnerSelect">선택된 옵션ㅣ{opJselectedOptions.cream}</div>
            <div className="opJReset" onClick={resetOptions}><FontAwesomeIcon icon={faRotate} />초기화</div>
          </div>
          <div className="opJShotSelect">
            <span className="opJSelectTitle">휘핑 여부(선택,단일선택)</span>
            <div className="opJSelects">
              <div
                className={`opJSelectsProduct ${opJselectedOptions.cream === "휘핑O" ? "selected" : ""}`}
                onClick={() => opJhandleCreamSelect("휘핑O")}
              >
                <img src={cream} alt="휘핑" className="opJSelectImg" />
                <div className="opJSelectsName">휘핑O</div>
                <div className="opJSelectPrice">+0 원</div>
              </div>
            </div>
          </div>
          <div className="opJButton">
            <div className="opJBtnStyle opJCancel" onClick={opJChocoClose}>취소</div>
            <div className="opJBtnStyle opJPut">주문담기</div>
          </div>
        </div>
      )}
      <div onClick={opJChocoOpen}>리얼 초코 프라페</div>
    </div>
  );
}

export default OpChoco;
