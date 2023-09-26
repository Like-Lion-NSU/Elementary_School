import React, { useState } from "react";
import Modal from "react-modal";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faXmark, faRotate } from "@fortawesome/free-solid-svg-icons";
import opJAmericano from "./img/optionImg/optionAmericano.png";
import halfShot from "./img/optionImg/halfShot.png";
import oneShot from "./img/optionImg/oneShot.png";
import twoShot from "./img/optionImg/twoShot.png";
import stevia from "./img/optionImg/stevia.png";
import "../css/option.css";

const IceAmeOption = ({
  setIce,
  setSelectedIceMenu,
  iceModalIsOpen,
  setIceModalIsOpen,
  setScore,
  lastScore,
}) => {
  const [opJselectedOptions, setopJSelectedOptions] = useState({
    shot: null,
    stevia: null,
  });
  const opJhandleShotSelect = (option) => {
    setopJSelectedOptions((prev) => ({
      ...prev,
      shot: prev.shot === option ? null : option,
    }));
  };

  const opJhandleSteviaSelect = (option) => {
    setopJSelectedOptions((prev) => ({
      ...prev,
      stevia: prev.stevia === option ? null : option,
    }));
  };

  const resetOptions = () => {
    setopJSelectedOptions({
      shot: null,
      stevia: null,
    });
  };
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
      width: "52.7vw",
      height: "95vh",
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
      isOpen={iceModalIsOpen}
      shouldCloseOnOverlayClick={false}
      style={customModalStyles}
    >
      <div className="opJOption">
        <div className="opJHead">
          <div>선택하신 상품의 옵션상품을 모두 선택해주세요. </div>
          <FontAwesomeIcon
            icon={faXmark}
            onClick={() => {
              setIceModalIsOpen(false);
            }}
          />
        </div>
        <div className="opJImgFlex">
          <img src={opJAmericano} alt="아메리카노" className="opJCoffeeImg" />
          <div className="opJTPE">
            <div className="opJTitlePrice">
              <div className="opJTitle">(ICE)아메리카노</div>
              <div className="opJPrice">2,000원</div>
            </div>
            <div className="opJExpain">
              (24ox)메가MGC커피 블렌딩 원두로 추출한 에스프레소에 물을 더해,
              풍부한 바디감을 느낄 수 있는 스탠다드 커피
            </div>
          </div>
        </div>
        <div className="opJSelectOption">
          <div className="opJInnerSelect">
            선택된 옵션ㅣ{opJselectedOptions.shot} {opJselectedOptions.stevia}
          </div>
          <div className="opJReset" onClick={resetOptions}>
            <FontAwesomeIcon icon={faRotate} />
            초기화
          </div>
        </div>
        <div className="opJShotSelect">
          <span className="opJSelectTitle">농도(선택,단일선택)</span>
          <div className="opJSelects">
            <div
              className={`opJSelectsProduct ${
                opJselectedOptions.shot === "연하게" ? "selected" : ""
              }`}
              onClick={() => opJhandleShotSelect("연하게")}
            >
              <img src={halfShot} alt="연하게" className="opJSelectImg" />
              <div className="opJSelectsName">연하게</div>
              <div className="opJSelectPrice">+0 원</div>
            </div>
            <div
              className={`opJSelectsProduct ${
                opJselectedOptions.shot === "샷 추가" ? "selected" : ""
              }`}
              onClick={() => opJhandleShotSelect("샷 추가")}
            >
              <img src={oneShot} alt="원샷" className="opJSelectImg" />
              <div className="opJSelectsName">샷 추가</div>
              <div className="opJSelectPrice">+500 원</div>
            </div>
            <div
              className={`opJSelectsProduct ${
                opJselectedOptions.shot === "2샷 추가" ? "selected" : ""
              }`}
              onClick={() => opJhandleShotSelect("2샷 추가")}
            >
              <img src={twoShot} alt="2샷" className="opJSelectImg" />
              <div className="opJSelectsName">2샷 추가</div>
              <div className="opJSelectPrice">+1,000 원</div>
            </div>
          </div>
        </div>
        <div className="opJShotSelect">
          <span className="opJSelectTitle">
            저당 스테비아 슈가 추가(선택, 단일선택)
          </span>
          <div className="opJSelects">
            <div
              className={`opJSelectsProduct ${
                opJselectedOptions.stevia === "스테비아 추가" ? "selected" : ""
              }`}
              onClick={() => opJhandleSteviaSelect("스테비아 추가")}
            >
              <img src={stevia} alt="스테비아" className="opJSelectImg" />
              <div className="opJSelectsName">스테비아 추가</div>
              <div className="opJSelectPrice">+600 원</div>
            </div>
          </div>
        </div>

        <div className="opJButton">
          <div
            className="opJBtnStyle opJCancel"
            onClick={() => {
              setIceModalIsOpen(false);
            }}
          >
            취소
          </div>
          <div
            className="opJBtnStyle opJPut"
            onClick={() => {
              if (
                opJselectedOptions.shot === "연하게" &&
                opJselectedOptions.stevia === null
              ) {
                console.log("주문담기 성공");
                setIceModalIsOpen(false);
                setSelectedIceMenu(true);
                setIce(1);
              } else {
                alert("다시 한번 생각해 보세요");
                if (lastScore > 0) {
                  setScore(lastScore - 10);
                } else {
                  return;
                }
                console.log(lastScore);
              }
            }}
          >
            주문담기
          </div>
        </div>
      </div>
    </Modal>
  );
};

export default IceAmeOption;
