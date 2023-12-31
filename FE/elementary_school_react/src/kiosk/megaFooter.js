import React, { useState } from "react";
import BasketModal from "./modalbasket";
import MegaPay from "./kioskpay";
import KioskResult from "./kioskResult";
import "../css/megaFooter.css";
import { Link } from "react-router-dom";

function MegaFooter({
  selectedIceMenu,
  selectedBokMenu,
  selectedChoMenu,
  setSelectedIceMenu,
  setSelectedBokMenu,
  setSelectedChoMenu,
  Icecount,
  setIce,
  Chocount,
  setCho,
  Bokcount,
  setBok,
  lastScore,
  setScore,
}) {
  const [payModalIsOpen, setPayModalIsOpen] = useState(false);
  const [bascketModalIsOpen, setBascketModalIsOpen] = useState(false);
  const [resultopen, setResultopen] = useState(false);

  return (
    <div className="E-footer">
      <div className="E-left-box">
        {selectedIceMenu === true ? (
          <div className="C-Ame-selected">
            <div className="C-First">
              <button
                className="selectedCDbtn"
                onClick={() => {
                  setSelectedIceMenu(false);
                  setIce(0);
                }}
              >
                X
              </button>
              <span>(ICE)아메리카노</span>
            </div>
            <div className="C-Second">
              <button
                className="minusCbtn"
                onClick={() => {
                  if (Icecount > 1) {
                    setIce(Icecount - 1);
                  } else {
                    return;
                  }
                }}
              >
                -
              </button>
              <span>{Icecount}개</span>
              <button
                className="plusCbtn"
                onClick={() => {
                  setIce(Icecount + 1);
                }}
              >
                +
              </button>
            </div>
            <span>{Icecount * 2000}원</span>
          </div>
        ) : null}
        {selectedBokMenu === true ? (
          <div className="C-Bok-selected">
            <div className="C-First">
              <button
                className="selectedCDbtn"
                onClick={() => {
                  setSelectedBokMenu(false);
                  setBok(0);
                }}
              >
                X
              </button>
              <span>복숭아이스티</span>
            </div>
            <div className="C-Second">
              <button
                className="minusCbtn"
                onClick={() => {
                  if (Bokcount > 1) {
                    setBok(Bokcount - 1);
                  } else {
                    return;
                  }
                }}
              >
                -
              </button>
              <span>{Bokcount}개</span>
              <button
                className="plusCbtn"
                onClick={() => {
                  setBok(Bokcount + 1);
                }}
              >
                +
              </button>
            </div>
            <span>{Bokcount * 3500}원</span>
          </div>
        ) : null}
        {selectedChoMenu === true ? (
          <div className="C-Cho-selected">
            <div className="C-First">
              <button
                className="selectedCDbtn"
                onClick={() => {
                  setSelectedChoMenu(false);
                  setCho(0);
                }}
              >
                X
              </button>
              <span>리얼초코프라페</span>
            </div>
            <div className="C-Second">
              <button
                className="minusCbtn"
                onClick={() => {
                  if (Chocount > 1) {
                    setCho(Chocount - 1);
                  } else {
                    return;
                  }
                }}
              >
                -
              </button>
              <span>{Chocount}개</span>
              <button
                className="plusCbtn"
                onClick={() => {
                  setCho(Chocount + 1);
                }}
              >
                +
              </button>
            </div>
            <span>{Chocount * 3900}원</span>
          </div>
        ) : null}
      </div>
      <div className="E-right-box">
        <div
          className="E-delete-button"
          onClick={() => {
            setSelectedIceMenu(false);
            setSelectedBokMenu(false);
            setSelectedChoMenu(false);
            setBok(0);
            setIce(0);
            setCho(0);
          }}
        >
          전체삭제
        </div>
        <div className="E-cart-info">
          선택한 상품 {Icecount + Chocount + Bokcount}개
        </div>
        <Link to="#" className="E-payment-box-btn">
          <div
            className="E-payment-box"
            onClick={() => {
              if (
                Icecount * 2000 + Chocount * 3900 + Bokcount * 3500 ===
                9400
              ) {
                setBascketModalIsOpen(true);
              } else if (Icecount === 0 || Chocount === 0 || Bokcount === 0) {
                alert(
                  "아직 메뉴를 모두 선택하지 않으셨습니다. 메뉴를 다시 한 번 확인해 주세요."
                );
              } else {
                alert(
                  "잘못된 메뉴의 개수를 선택하셨습니다. 다시 확인해 주세요."
                );
              }
            }}
          >
            <div className="E-amount">
              {Icecount * 2000 + Chocount * 3900 + Bokcount * 3500}원
            </div>
            <div className="E-payment-button">결제하기</div>
          </div>
        </Link>
      </div>
      <BasketModal
        bascketModalIsOpen={bascketModalIsOpen}
        setBascketModalIsOpen={setBascketModalIsOpen}
        payModalIsOpen={payModalIsOpen}
        setPayModalIsOpen={setPayModalIsOpen}
        lastScore={lastScore}
        setScore={setScore}
      />
      <MegaPay
        payModalIsOpen={payModalIsOpen}
        setPayModalIsOpen={setPayModalIsOpen}
        resultopen={resultopen}
        setResultopen={setResultopen}
        lastScore={lastScore}
        setScore={setScore}
      />
      <KioskResult
        resultopen={resultopen}
        setResultopen={setResultopen}
        lastScore={lastScore}
        setScore={setScore}
      />
    </div>
  );
}

export default MegaFooter;
