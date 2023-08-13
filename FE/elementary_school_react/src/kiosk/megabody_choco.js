import React, { useState } from "react";
import "../css/megabody.css";
import IceChocoOption from "./modalChoco";
import img1 from "./img/mega_body_choco/uja.jpg";
import img2 from "./img/mega_body_choco/naju.jpg";
import img3 from "./img/mega_body_choco/kokonut.png";
import img4 from "./img/mega_body_choco/plainpong.jpg";
import img5 from "./img/mega_body_choco/chocohoney.jpg";
import img6 from "./img/mega_body_choco/kreamhoney.jpg";
import img7 from "./img/mega_body_choco/strawpong.jpg";
import img8 from "./img/mega_body_choco/bananapong.jpg";
import img9 from "./img/mega_body_choco/cookiepra.jpg";
import img10 from "./img/mega_body_choco/stracookie.jpg";
import img11 from "./img/mega_body_choco/coffee.jpg";
import img12 from "./img/mega_body_choco/malcha.jpg";
import img13 from "./img/mega_body_choco/unicon.jpg";
import img14 from "./img/mega_body_choco/real.jpg";
import img15 from "./img/mega_body_choco/cheese.jpg";
import img16 from "./img/mega_body_choco/yo.jpg";
import img17 from "./img/mega_body_choco/syo.jpg";
import img18 from "./img/mega_body_choco/myo.jpg";

const Megabody_choco = ({
  setSelectedChoMenu,
  setCho,
  setScore,
  lastScore,
}) => {
  const [chocoModalIsOpen, setChocoModalIsOpen] = useState(false);
  const handleOtherChocoClick = () => {
    alert("잘못 고르셨습니다. 감점 처리됩니다. 다른 버튼을 선택해주세요");
    if (lastScore > 0) {
      setScore(lastScore - 10);
    } else {
      return;
    }
    console.log(lastScore);
  };
  return (
    <>
      <div className="C-bodySection">
        <table className="mega-C-table">
          <tbody className="mega-C-tbody">
            <tr>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img1} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">고흥 유자망고 스무디</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img2} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">나주 플럼코트 스무디</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img3} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">코코넛커피 스무디</p>
                  <p className="mega-C-price">4,800원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img4} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">플레인퐁크러쉬</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img5} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">초코허니퐁크러쉬</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
            </tr>
            <tr>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img6} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">슈크림허니퐁크러쉬</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img7} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">딸기퐁크러쉬</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img8} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">바나나퐁크러쉬</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img9} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">쿠키프라페</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img10} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">딸기쿠키프라페</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
            </tr>
            <tr>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img11} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">커피프라페</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img12} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">녹차프라페</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img13} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">유니콘프라페</p>
                  <p className="mega-C-price">4,800원</p>
                </div>
              </td>
              <td
                className="mega-C-td"
                onClick={() => {
                  setChocoModalIsOpen(true);
                }}
              >
                <img src={img14} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">리얼초코프라페</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img15} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">스트로베리치즈홀릭</p>
                  <p className="mega-C-price">4,500원</p>
                </div>
              </td>
            </tr>
            <tr>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img16} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">플레인요거트스무디</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img17} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">딸기요거트스무디</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherChocoClick}>
                <img src={img18} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">망고요거트스무디</p>
                  <p className="mega-C-price">3,900원</p>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
        <IceChocoOption
          chocoModalIsOpen={chocoModalIsOpen}
          setChocoModalIsOpen={setChocoModalIsOpen}
          setSelectedChoMenu={setSelectedChoMenu}
          setCho={setCho}
          setScore={setScore}
          lastScore={lastScore}
        />
      </div>
    </>
  );
};

export default Megabody_choco;
