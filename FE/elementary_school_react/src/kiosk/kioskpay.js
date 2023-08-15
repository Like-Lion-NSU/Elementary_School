import React from "react";
import Modal from "react-modal";
import "../css/kioskpayment_1.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faX } from "@fortawesome/free-solid-svg-icons";
import img1 from "./img/mega_pay/kt.png";
import img2 from "./img/mega_pay/T.png";
import img3 from "./img/mega_pay/pay1.png";
import img4 from "./img/mega_pay/pay2.png";
import img5 from "./img/mega_pay/pay.jfif";
import img6 from "./img/mega_pay/payco.png";
import img7 from "./img/mega_pay/naver.png";
import img8 from "./img/mega_pay/zero.png";
import img9 from "./img/mega_pay/paybook.png";
import img10 from "./img/mega_pay/hana.jfif";
import img11 from "./img/mega_pay/kbpay.png";
import img12 from "./img/mega_pay/coupon.png";
import img13 from "./img/mega_pay/megapay.png";
import axios from "axios";

const Pay_step = ({
  num,
  comment,
  img_link_1,
  name_1,
  img_link_2,
  name_2,
  clickEvent,
}) => {
  return (
    <div className="Pay_step">
      <span className="stepcInfo">STEP{num}</span>
      <span className="stepcComment">{comment}</span>
      <div className="btncSection">
        <button onClick={clickEvent}>
          <img src={img_link_1} />
          <p>{name_1}</p>
        </button>
        <button onClick={clickEvent}>
          <img src={img_link_2} />
          <p>{name_2}</p>
        </button>
      </div>
    </div>
  );
};
const Pay_how = ({ img_link, name, clickEvent }) => {
  return (
    <button className="Pay_how" onClick={clickEvent}>
      <img src={img_link} />
      <p>{name}</p>
    </button>
  );
};
const Pay_event = ({ img_link, name, clickEvent }) => {
  return (
    <button className="Pay_event" onClick={clickEvent}>
      <img src={img_link} />
      <p>{name}</p>
    </button>
  );
};
const MegaPay = ({
  payModalIsOpen,
  setPayModalIsOpen,
  lastScore,
  setScore,
}) => {
  const handleOtherClick = () => {
    alert("잘못 고르셨습니다. 감점 처리됩니다. 다른 버튼을 선택해주세요");
    if (lastScore > 0) {
      setScore(lastScore - 10);
    } else {
      return;
    }
    console.log(lastScore);
  };

  const Pay_header = () => {
    return (
      <div className="Pay_header">
        <h3>결제 수단 선택 (원)</h3>
        <button>
          <FontAwesomeIcon
            icon={faX}
            onClick={() => {
              setPayModalIsOpen(false);
            }}
          />
        </button>
      </div>
    );
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
  const connectBack = () => {
    const accessToken = getCookieValue("accessToken");
    axios({
      method: "POST",
      url: "/practice/point",
      headers: {
        Authorization: `Bearer ${accessToken}`,
        "Content-Type": "application/x-www-form-urlencoded",
      },
      data: {
        result: lastScore,
      },
    })
      .then((result) => {
        window.location.href = "/";
      })
      .catch((error) => {
        if (error.response && error.response.status === 401) {
          try {
            const refreshToken = getCookieValue("refreshToken");
            const refreshResponse = axios.post("/auth/refresh", null, {
              headers: {
                Authorization: `Bearer ${refreshToken}`,
              },
            });
            const newAccessToken = refreshResponse.data;
            axios
              .post("/practice/point", {
                headers: {
                  Authorization: `Bearer ${newAccessToken}`,
                  "Content-Type": "application/x-www-form-urlencoded",
                },
                data: {
                  result: lastScore,
                },
              })
              .then((result) => {
                window.location.href = "/";
              });
          } catch (err) {}
        }
      });
  };

  // 쿠키 값 추출 함수 예시
  function getCookieValue(cookieName) {
    const cookies = document.cookie.split(";");
    for (const cookie of cookies) {
      const [name, value] = cookie.trim().split("=");
      if (name === cookieName) {
        return value;
      }
    }
  }
  return (
    <Modal
      isOpen={payModalIsOpen}
      shouldCloseOnOverlayClick={false}
      style={customModalStyles}
    >
      <Pay_header />
      <Pay_step
        num={"1"}
        comment={"제휴할인을 선택해주세요"}
        img_link_1={img1}
        name_1={"KT VIP초이스(통합 월1회)"}
        img_link_2={img2}
        name_2={"SKT우주패스"}
        clickEvent={handleOtherClick}
      />
      <br></br>
      <div className="Pay_step">
        <span className="stepcInfo">STEP2</span>
        <span className="stepcComment">결제수단을 선택해주세요</span>
        <div className="btncSection">
          <button>
            <img src={img3} onClick={connectBack} />
            <p>카드결제(삼성페이/LG페이)</p>
          </button>
          <button onClick={handleOtherClick}>
            <img src={img4} />
            <p>앱카드(QR/바코드)</p>
          </button>
        </div>
      </div>
      <Pay_how
        img_link={img5}
        name={"카카오페이"}
        clickEvent={handleOtherClick}
      />
      <Pay_how img_link={img6} name={"페이코"} clickEvent={handleOtherClick} />
      <Pay_how
        img_link={img7}
        name={"네이버페이"}
        clickEvent={handleOtherClick}
      />
      <Pay_how
        img_link={img8}
        name={"제로페이"}
        clickEvent={handleOtherClick}
      />
      <Pay_how
        img_link={img9}
        name={"BC페이북"}
        clickEvent={handleOtherClick}
      />
      <Pay_how
        img_link={img10}
        name={"하나 Pay"}
        clickEvent={handleOtherClick}
      />
      <Pay_how img_link={img11} name={"KB pay"} clickEvent={handleOtherClick} />
      <br />
      <Pay_event
        img_link={img12}
        name={"쿠폰사용"}
        clickEvent={handleOtherClick}
      />
      <Pay_event
        img_link={img13}
        name={"메가선불페이"}
        clickEvent={handleOtherClick}
      />
      <div className="pricecresult">
        <span className="priceccal">주문금액 : 9400원 - 할인금액 : 0원</span>
        <span className="pricectotal">결제금액 : 9400원</span>
      </div>
    </Modal>
  );
};

export default MegaPay;
