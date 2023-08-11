import React, { useState } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark, faRotate } from '@fortawesome/free-solid-svg-icons';
import opJAmericano from './img/optionImg/optionAmericano.png';
import halfShot from './img/optionImg/halfShot.png';
import oneShot from './img/optionImg/oneShot.png';
import twoShot from './img/optionImg/twoShot.png';
import stevia from './img/optionImg/stevia.png';
import "../css/option.css";

function OpAmericano() {
  const [opJAmericanoShow, setopJAmericanoShow] = useState(false);
  const [opJselectedOptions, setopJSelectedOptions] = useState({ shot: null, stevia: null, honey: null, cream: null });

  const opJAmericanoClose = () => {
    setopJAmericanoShow(false);
  };

  const opJAmericanoOpen = () => {
    setopJAmericanoShow(true);
  };

  const opJhandleShotSelect = (option) => {
    setopJSelectedOptions((prev) => ({
      ...prev,
      shot: prev.shot === option ? null : option
    }));
  };

  const opJhandleSteviaSelect = (option) => {
    setopJSelectedOptions((prev) => ({
      ...prev,
      stevia: prev.stevia === option ? null : option
    }));
  };

  const resetOptions = () => {
    setopJSelectedOptions({ shot: null, stevia: null, honey: null, cream: null });
  };

  return (
    <div>
      {opJAmericanoShow && (
        <div className="opJOption">
          <div className="opJHead">
            <div>선택하신 상품의 옵션상품을 모두 선택해주세요. </div>
            <FontAwesomeIcon icon={faXmark} onClick={opJAmericanoClose} />
          </div>
          <div className="opJImgFlex">
            <img src={opJAmericano} alt="아메리카노" className="opJCoffeeImg" />
            <div className="opJTPE">
                <div className="opJTitlePrice">
                    <div className="opJTitle">(ICE)아메리카노</div>
                    <div className="opJPrice">2,000원</div>
                </div>
                <div className="opJExpain">(24ox)메가MGC커피 블렌딩 원두로 추출한 에스프레소에 물을 더해, 풍부한 바디감을 느낄 수 있는 스탠다드 커피</div>
            </div>
          </div>
          <div className="opJSelectOption">
            <div className="opJInnerSelect">선택된 옵션ㅣ{opJselectedOptions.shot} {opJselectedOptions.stevia}</div>
            <div className="opJReset" onClick={resetOptions}><FontAwesomeIcon icon={faRotate} />초기화</div>
          </div>
          <div className="opJShotSelect">
            <span className="opJSelectTitle">농도(선택,단일선택)</span>
            <div className="opJSelects">
              <div className={`opJSelectsProduct ${opJselectedOptions.shot === "연하게" ? "selected" : ""}`}
                onClick={() => opJhandleShotSelect("연하게")}>
                <img src={halfShot} alt="연하게" className="opJSelectImg" />
                <div className="opJSelectsName">연하게</div>
                <div className="opJSelectPrice">+0 원</div>
              </div>
              <div className={`opJSelectsProduct ${opJselectedOptions.shot === "샷 추가" ? "selected" : ""}`} 
                onClick={() => opJhandleShotSelect("샷 추가")}>
                <img src={oneShot} alt="원샷" className="opJSelectImg"/>
                <div className="opJSelectsName">샷 추가</div>
                <div className="opJSelectPrice">+500 원</div>
              </div>
              <div className={`opJSelectsProduct ${opJselectedOptions.shot === "2샷 추가" ? "selected" : ""}`} 
                onClick={() => opJhandleShotSelect("2샷 추가")}>
                <img src={twoShot} alt="2샷" className="opJSelectImg"/>
                <div className="opJSelectsName">2샷 추가</div>
                <div className="opJSelectPrice">+1,000 원</div>
              </div>
            </div>
          </div>
          <div className="opJShotSelect">
            <span className="opJSelectTitle">저당 스테비아 슈가 추가(선택, 단일선택)</span>
            <div className="opJSelects">
              <div
                className={`opJSelectsProduct ${opJselectedOptions.stevia === "스테비아 추가" ? "selected" : ""}`}
                onClick={() => opJhandleSteviaSelect("스테비아 추가")}
              >
                <img src={stevia} alt="스테비아" className="opJSelectImg" />
                <div className="opJSelectsName">스테비아 추가</div>
                <div className="opJSelectPrice">+600 원</div>
              </div>
            </div>
          </div>
          
          <div className="opJButton">
            <div className="opJBtnStyle opJCancel" onClick={opJAmericanoClose}>취소</div>
            <div className="opJBtnStyle opJPut">주문담기</div>
          </div>
        </div>
      )}
      <div onClick={opJAmericanoOpen}>아메리카노</div>
    </div>
  );
}

export default OpAmericano;
