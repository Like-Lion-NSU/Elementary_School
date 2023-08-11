import React, { useState } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark, faRotate } from '@fortawesome/free-solid-svg-icons';
import opJIceTea from './img/optionImg/optionIceTea.png';
import oneShot from './img/optionImg/oneShot.png';
import twoShot from './img/optionImg/twoShot.png';
import hoeny from './img/optionImg/honey.png';
import "../css/option.css";

function OpIceTea({ opJIceTeaShow, setopJIceTeaShow }) {
  const [opJselectedOptions, setopJSelectedOptions] = useState({ shot: null, honey: null });

  const opJIceTeaClose = () => {
    setopJIceTeaShow(false);
  };
  const opJIceTeaOpen = () => {
    setopJIceTeaShow(true);
  };
  const opJhandleShotSelect = (option) => {
    setopJSelectedOptions((prev) => ({
      ...prev,
      shot: prev.shot === option ? null : option
    }));
  };

  const opJhandleHoneySelect = (option) => {
    setopJSelectedOptions((prev) => ({
      ...prev,
      honey: prev.honey === option ? null : option
    }));
  };

  const resetOptions = () => {
    setopJSelectedOptions({ shot: null, honey: null });
  };

  return (
    <div>
      {opJIceTeaShow && (
        <div className="opJOption">
          <div className="opJHead">
            <div>선택하신 상품의 옵션상품을 모두 선택해주세요. </div>
            <FontAwesomeIcon icon={faXmark} onClick={opJIceTeaClose} />
          </div>
          <div className="opJImgFlex">
            <img src={opJIceTea} alt="아이스티" className="opJCoffeeImg" />
            <div className="opJTPE">
                <div className="opJTitlePrice">
                    <div className="opJTitle">복숭아아이스티</div>
                    <div className="opJPrice">3,000원</div>
                </div>
                <div className="opJExpain">(24ox)홍차의 깊은 맛과 풍부한 복숭아 향이 어우러진 달콤한 여름철 인기 음료</div>
            </div>
          </div>
          <div className="opJSelectOption">
            <div className="opJInnerSelect">선택된 옵션ㅣ{opJselectedOptions.shot} {opJselectedOptions.honey}</div>
            <div className="opJReset" onClick={resetOptions}><FontAwesomeIcon icon={faRotate} />초기화</div>
          </div>
          <div className="opJShotSelect">
            <span className="opJSelectTitle">농도(선택,단일선택)</span>
            <div className="opJSelects">
              <div
                className={`opJSelectsProduct ${opJselectedOptions.shot === "샷 추가" ? "selected" : ""}`}
                onClick={() => opJhandleShotSelect("샷 추가")}
              >
                <img src={oneShot} alt="원샷" className="opJSelectImg" />
                <div className="opJSelectsName">샷 추가</div>
                <div className="opJSelectPrice">+500 원</div>
              </div>
              <div className={`opJSelectsProduct ${opJselectedOptions.shot === "2샷 추가" ? "selected" : ""}`} onClick={() => opJhandleShotSelect("2샷 추가")}>
                <img src={twoShot} alt="2샷" className="opJSelectImg"/>
                <div className="opJSelectsName">2샷 추가</div>
                <div className="opJSelectPrice">+1,000 원</div>
              </div>
            </div>
          </div>
          <div className="opJShotSelect">
            <span className="opJSelectTitle">꿀 추가(선택, 단일선택)</span>
            <div className="opJSelects">
              <div
                className={`opJSelectsProduct ${opJselectedOptions.honey === "꿀 추가" ? "selected" : ""}`}
                onClick={() => opJhandleHoneySelect("꿀 추가")}
              >
                <img src={hoeny} alt="꿀" className="opJSelectImg" />
                <div className="opJSelectsName">꿀 추가</div>
                <div className="opJSelectPrice">+700 원</div>
              </div>
            </div>
          </div>
          
          <div className="opJButton">
            <div className="opJBtnStyle opJCancel" onClick={opJIceTeaClose}>취소</div>
            <div className="opJBtnStyle opJPut">주문담기</div>
          </div>
        </div>
      )}
      <div onClick={opJIceTeaOpen}>복숭아 아이스티</div>
    </div>
  );
}

export default OpIceTea;
