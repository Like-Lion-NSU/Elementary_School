import React from "react";
import "../css/megabody.css";
import img1 from "./img/mega_body/americano_ice.jpg";
import img3 from "./img/mega_body/cafelatte_ice.jpg";
import img4 from "./img/mega_body/caramel_ice.jpg";
import img5 from "./img/mega_body/cubelatte_ice.jpg";
import img6 from "./img/mega_body/moca_ice.jpg";
import img7 from "./img/mega_body/cappu_ice.jpg";
import img8 from "./img/mega_body/tiramisu_ice.jpg";

const Megabody = () => {
  return (
    <>
      <div className="bodySection">
        <table>
          <tbody>
            <tr>
              <td>
                <img src={img1}></img>
                <div className="info">
                  <p className="bev_name">(ICE)아메리카노</p>
                  <p className="price">2,700원</p>
                </div>
              </td>
              <td>
                <img src={img1}></img>
                <div className="info">
                  <p className="bev_name">(ICE)꿀아메리카노</p>
                  <p className="price">2,700원</p>
                </div>
              </td>
              <td>
                <img src={img1}></img>
                <div className="info">
                  <p className="bev_name">(ICE)헤이즐넛아메리카노</p>
                  <p className="price">2,700원</p>
                </div>
              </td>
              <td>
                <img src={img1}></img>
                <div className="info">
                  <p className="bev_name">(ICE)바닐라아메리카노</p>
                  <p className="price">2,700원</p>
                </div>
              </td>
              <td>
                <img src={img1}></img>
                <div className="info">
                  <p className="bev_name">(ICE)메가리카노</p>
                  <p className="price">3,000원</p>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <img src={img3}></img>
                <div className="info">
                  <p className="bev_name">(ICE)카페라떼</p>
                  <p className="price">2,900원</p>
                </div>
              </td>
              <td>
                <img src={img3}></img>
                <div className="info">
                  <p className="bev_name">(ICE)바닐라라떼</p>
                  <p className="price">3,400원</p>
                </div>
              </td>
              <td>
                <img src={img4}></img>
                <div className="info">
                  <p className="bev_name">(ICE)카라멜마끼야또</p>
                  <p className="price">3,700원</p>
                </div>
              </td>
              <td>
                <img src={img5}></img>
                <div className="info">
                  <p className="bev_name">(ICE)큐브라떼</p>
                  <p className="price">4,200원</p>
                </div>
              </td>
              <td>
                <img src={img6}></img>
                <div className="info">
                  <p className="bev_name">(ICE)카페모카</p>
                  <p className="price">3,900원</p>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <img src={img7}></img>
                <div className="info">
                  <p className="bev_name">(ICE)카푸치노</p>
                  <p className="price">2,900원</p>
                </div>
              </td>
              <td>
                <img src={img3}></img>
                <div className="info">
                  <p className="bev_name">(ICE)헤이즐넛라떼</p>
                  <p className="price">3,400원</p>
                </div>
              </td>
              <td>
                <img src={img8}></img>
                <div className="info">
                  <p className="bev_name">(ICE)티라미수</p>
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

export default Megabody;
