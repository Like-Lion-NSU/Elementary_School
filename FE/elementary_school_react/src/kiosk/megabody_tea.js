import React, { useState } from "react";
import "../css/megabody.css";
import IceTeaOption from "./modalIceTea";
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
  const [teaModalIsOpen, setTeaModalIsOpen] = useState(false);
  const handleOtherTeaClick = () => {
    alert("잘못 고르셨습니다. 감점 처리됩니다. 다른 버튼을 선택해주세요");
  };
  return (
    <>
      <div className="C-bodySection">
        <table className="mega-C-table">
          <tbody className="mega-C-tbody">
            <tr>
              <td
                className="mega-C-td"
                onClick={() => {
                  setTeaModalIsOpen(true);
                }}
              >
                <img src={img1} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">복숭아 아이스티</p>
                  <p className="mega-C-price">3,000원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img2} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(ICE)녹차</p>
                  <p className="mega-C-price">2,500원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img3} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(ICE)페퍼민트</p>
                  <p className="mega-C-price">2,500원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img4} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(ICE)캐모마일</p>
                  <p className="mega-C-price">2,500원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img5} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(ICE)얼그레이</p>
                  <p className="mega-C-price">2,500원</p>
                </div>
              </td>
            </tr>
            <tr>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img6} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(ICE)허니자몽블랙티</p>
                  <p className="mega-C-price">3,700원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img7} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(ICE)사과유자차</p>
                  <p className="mega-C-price">3,500원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img8} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(ICE)유자차</p>
                  <p className="mega-C-price">3,300원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img9} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(ICE)레몬차</p>
                  <p className="mega-C-price">3,300원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img10} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(ICE)자몽차</p>
                  <p className="mega-C-price">3,300원</p>
                </div>
              </td>
            </tr>
            <tr>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img11} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(HOT)허니자몽블랙티</p>
                  <p className="mega-C-price">3,300원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img12} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(HOT)사과유자차</p>
                  <p className="mega-C-price">3,300원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img13} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(HOT)유자차</p>
                  <p className="mega-C-price">3,300원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img14} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(HOT)레몬차</p>
                  <p className="mega-C-price">3,300원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img15} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(HOT)자몽차</p>
                  <p className="mega-C-price">3,300원</p>
                </div>
              </td>
            </tr>
            <tr>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img16} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(HOT)녹차</p>
                  <p className="mega-C-price">2,500원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img17} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(HOT)패퍼민트</p>
                  <p className="mega-C-price">2,500원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img18} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(HOT)캐모마일</p>
                  <p className="mega-C-price">2,500원</p>
                </div>
              </td>
              <td className="mega-C-td" onClick={handleOtherTeaClick}>
                <img src={img19} className="mega-C-img"></img>
                <div className="mega-C-info">
                  <p className="C-bev_name">(HOT)얼그레이</p>
                  <p className="mega-C-price">2,500원</p>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
        <IceTeaOption
          teaModalIsOpen={teaModalIsOpen}
          setTeaModalIsOpen={setTeaModalIsOpen}
        />
      </div>
    </>
  );
};

export default Megabody_tea;
