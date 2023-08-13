import React, { useState } from "react";
import Modal from "react-modal";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import MegaPay from "./kioskpay";
import {
  faXmark,
  faCircleChevronRight,
  faArrowLeft,
  faStore,
  faGlassWater,
} from "@fortawesome/free-solid-svg-icons";
import "../css/basket.css";

const BasketModal = ({
  bascketModalIsOpen,
  setBascketModalIsOpen,
  payModalIsOpen,
  setPayModalIsOpen,
  lastScore,
  setScore,
}) => {
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
      isOpen={bascketModalIsOpen}
      shouldCloseOnOverlayClick={false}
      style={customModalStyles}
    >
      <div className="baJBasket">
        <div className="opJHead">
          <div>주문 세부내역을 다시 한번 확인하여 주세요. </div>
          <FontAwesomeIcon
            icon={faXmark}
            onClick={() => {
              setBascketModalIsOpen(false);
            }}
          />
        </div>
        <div className="baJMenu">
          <div>1 복숭아아이스티</div>
          <div className="baJRight">1개</div>
          <div className="baJRight">3,000원</div>
        </div>
        <div className="baJOption">
          <div className="baJLeftMargin">
            <FontAwesomeIcon icon={faCircleChevronRight} /> 샷 추가
          </div>
          <div className="baJRight">1개</div>
          <div className="baJRight">500원</div>
        </div>
        <div className="baJMenu">
          <div>2 아이스아메리카노</div>
          <div className="baJRight">1개</div>
          <div className="baJRight">2,000원</div>
        </div>
        <div className="baJOption">
          <div className="baJLeftMargin">
            <FontAwesomeIcon icon={faCircleChevronRight} /> 연하게
          </div>
          <div className="baJRight">1개</div>
          <div className="baJRight">0원</div>
        </div>
        <div className="baJMenu">
          <div>3 리얼초코프라페</div>
          <div className="baJRight">1개</div>
          <div className="baJRight">3,900원</div>
        </div>
        <div className="baJOption">
          <div className="baJLeftMargin">
            <FontAwesomeIcon icon={faCircleChevronRight} /> 휘핑O
          </div>
          <div className="baJRight">1개</div>
          <div className="baJRight">0원</div>
        </div>
        <div className="baJBottom">
          <div className="baJRef">※ 매장 이용시 일회용컵 사용 불가 ※</div>
          <div className="baJCountCost">
            <div className="baJSpaceBetween">
              <div>총 수량</div>
              <div className="baJFlex">
                <div className="baJFontRed">3</div>
                <div>&nbsp;개</div>
              </div>
            </div>
            <div className="baJSpaceBetween">
              <div>총 결제금액</div>
              <div className="baJFlex">
                <div className="baJFontRed">9,400</div>
                <div>&nbsp;원</div>
              </div>
            </div>
          </div>
          <div className="baJButton">
            <div className="baJBack">
              <div
                className="baJBackBtn"
                onClick={() => {
                  setBascketModalIsOpen(false);
                }}
              >
                <FontAwesomeIcon icon={faArrowLeft} />
                <div>돌아가기</div>
              </div>
            </div>
            <div className="baJShop">
              <div className="baJBtnFlex">
                <FontAwesomeIcon icon={faStore} className="baJShopIcon" />
                <div
                  className="baJBtnColumn"
                  onClick={() => {
                    alert("포장하려고 했을걸요?");
                    if (lastScore > 0) {
                      setScore(lastScore - 10);
                    } else {
                      return;
                    }
                    console.log(lastScore);
                  }}
                >
                  <div>먹고가기</div>
                  <div>(다회용컵)</div>
                </div>
              </div>
            </div>
            <div
              className="baJPack"
              onClick={() => {
                setBascketModalIsOpen(false);
                setPayModalIsOpen(true);
              }}
            >
              <div className="baJBtnFlex">
                <FontAwesomeIcon icon={faGlassWater} className="baJGlassIcon" />
                <div className="baJBtnColumn">
                  <div>포장하기</div>
                  <div>(일회용컵)</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </Modal>
  );
};

export default BasketModal;
