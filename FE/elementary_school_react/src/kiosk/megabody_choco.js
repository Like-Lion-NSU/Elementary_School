import React from "react";
import "../css/megabody.css";
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

const Megabody_choco = () => {
  return (
    <>
      <div className="bodySection">
        <table>
          <tbody>
            <tr>
              <td>
                <img src={img1}></img>
                <div className="info">
                  <p className="bev_name">고흥 유자망고 스무디</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
              <td>
                <img src={img2}></img>
                <div className="info">
                  <p className="bev_name">나주 플럼코트 스무디</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
              <td>
                <img src={img3}></img>
                <div className="info">
                  <p className="bev_name">코코넛커피 스무디</p>
                  <p className="price">4,800원</p>
                </div>
              </td>
              <td>
                <img src={img4}></img>
                <div className="info">
                  <p className="bev_name">플레인퐁크러쉬</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
              <td>
                <img src={img5}></img>
                <div className="info">
                  <p className="bev_name">초코허니퐁크러쉬</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <img src={img6}></img>
                <div className="info">
                  <p className="bev_name">슈크림허니퐁크러쉬</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
              <td>
                <img src={img7}></img>
                <div className="info">
                  <p className="bev_name">딸기퐁크러쉬</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
              <td>
                <img src={img8}></img>
                <div className="info">
                  <p className="bev_name">바나나퐁크러쉬</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
              <td>
                <img src={img9}></img>
                <div className="info">
                  <p className="bev_name">쿠키프라페</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
              <td>
                <img src={img10}></img>
                <div className="info">
                  <p className="bev_name">딸기쿠키프라페</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <img src={img11}></img>
                <div className="info">
                  <p className="bev_name">커피프라페</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
              <td>
                <img src={img12}></img>
                <div className="info">
                  <p className="bev_name">녹차프라페</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
              <td>
                <img src={img13}></img>
                <div className="info">
                  <p className="bev_name">유니콘프라페</p>
                  <p className="price">4,800원</p>
                </div>
              </td>
              <td>
                <img src={img14}></img>
                <div className="info">
                  <p className="bev_name">리얼초코프라페</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
              <td>
                <img src={img15}></img>
                <div className="info">
                  <p className="bev_name">스트로베리치즈홀릭</p>
                  <p className="price">4,500원</p>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <img src={img16}></img>
                <div className="info">
                  <p className="bev_name">플레인요거트스무디</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
              <td>
                <img src={img17}></img>
                <div className="info">
                  <p className="bev_name">딸기요거트스무디</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
              <td>
                <img src={img18}></img>
                <div className="info">
                  <p className="bev_name">망고요거트스무디</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </>
  );
};

export default Megabody_choco;
