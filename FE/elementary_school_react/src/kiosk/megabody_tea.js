import React from "react";
import "../css/megabody.css";
import img1 from "./img/mega_body_tea/bok.jpg";
import img2 from "./img/mega_body_tea/nok_i.jpg";
import img3 from "./img/mega_body_tea/pe_i.jpg";
import img4 from "./img/mega_body_tea/chamo_i.jpg";
import img5 from "./img/mega_body_tea/ul_i.jpg";
import img6 from "./img/mega_body_tea/hoja_i.jpg";
import img7 from "./img/mega_body_tea/au_i.jpg";
import img8 from "./img/mega_body_tea/uja_i.jpg";
import img9 from "./img/mega_body_tea/lae_i.jpg";
import img10 from "./img/mega_body_tea/ja_i.jpg";
import img11 from "./img/mega_body_tea/hoja.jpg";
import img12 from "./img/mega_body_tea/au.jpg";
import img13 from "./img/mega_body_tea/uja.jpg";
import img14 from "./img/mega_body_tea/lae.jpg";
import img15 from "./img/mega_body_tea/ja.jpg";
import img16 from "./img/mega_body_tea/nok.jpg";
import img17 from "./img/mega_body_tea/pe.jpg";
import img18 from "./img/mega_body_tea/chamo.jpg";
import img19 from "./img/mega_body_tea/ul.jpg";

const Megabody_tea = () => {
  return (
    <>
      <div className="bodySection">
        <table>
          <tbody>
            <tr>
              <td>
                <img src={img1}></img>
                <div className="info">
                  <p className="bev_name">복숭아 아이스티</p>
                  <p className="price">3,000원</p>
                </div>
              </td>
              <td>
                <img src={img2}></img>
                <div className="info">
                  <p className="bev_name">(ICE)녹차</p>
                  <p className="price">2,500원</p>
                </div>
              </td>
              <td>
                <img src={img3}></img>
                <div className="info">
                  <p className="bev_name">(ICE)페퍼민트</p>
                  <p className="price">2,500원</p>
                </div>
              </td>
              <td>
                <img src={img4}></img>
                <div className="info">
                  <p className="bev_name">(ICE)캐모마일</p>
                  <p className="price">2,500원</p>
                </div>
              </td>
              <td>
                <img src={img5}></img>
                <div className="info">
                  <p className="bev_name">(ICE)얼그레이</p>
                  <p className="price">2,500원</p>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <img src={img6}></img>
                <div className="info">
                  <p className="bev_name">(ICE)허니자몽블랙티</p>
                  <p className="price">3,700원</p>
                </div>
              </td>
              <td>
                <img src={img7}></img>
                <div className="info">
                  <p className="bev_name">(ICE)사과유자차</p>
                  <p className="price">3,500원</p>
                </div>
              </td>
              <td>
                <img src={img8}></img>
                <div className="info">
                  <p className="bev_name">(ICE)유자차</p>
                  <p className="price">3,300원</p>
                </div>
              </td>
              <td>
                <img src={img9}></img>
                <div className="info">
                  <p className="bev_name">(ICE)레몬차</p>
                  <p className="price">3,300원</p>
                </div>
              </td>
              <td>
                <img src={img10}></img>
                <div className="info">
                  <p className="bev_name">(ICE)자몽차</p>
                  <p className="price">3,300원</p>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <img src={img11}></img>
                <div className="info">
                  <p className="bev_name">(HOT)허니자몽블랙티</p>
                  <p className="price">3,300원</p>
                </div>
              </td>
              <td>
                <img src={img12}></img>
                <div className="info">
                  <p className="bev_name">(HOT)사과유자차</p>
                  <p className="price">3,300원</p>
                </div>
              </td>
              <td>
                <img src={img13}></img>
                <div className="info">
                  <p className="bev_name">(HOT)유자차</p>
                  <p className="price">3,300원</p>
                </div>
              </td>
              <td>
                <img src={img14}></img>
                <div className="info">
                  <p className="bev_name">(HOT)레몬차</p>
                  <p className="price">3,300원</p>
                </div>
              </td>
              <td>
                <img src={img15}></img>
                <div className="info">
                  <p className="bev_name">(HOT)자몽차</p>
                  <p className="price">3,300원</p>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <img src={img16}></img>
                <div className="info">
                  <p className="bev_name">(HOT)녹차</p>
                  <p className="price">2,500원</p>
                </div>
              </td>
              <td>
                <img src={img17}></img>
                <div className="info">
                  <p className="bev_name">(HOT)패퍼민트</p>
                  <p className="price">2,500원</p>
                </div>
              </td>
              <td>
                <img src={img18}></img>
                <div className="info">
                  <p className="bev_name">(HOT)캐모마일</p>
                  <p className="price">2,500원</p>
                </div>
              </td>
              <td>
                <img src={img19}></img>
                <div className="info">
                  <p className="bev_name">(HOT)얼그레이</p>
                  <p className="price">2,500원</p>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </>
  );
};

export default Megabody_tea;
