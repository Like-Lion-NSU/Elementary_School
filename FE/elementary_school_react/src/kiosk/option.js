import { useState } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { faRotate } from '@fortawesome/free-solid-svg-icons';
import opJAmericano from './img/optionImg/optionAmericano.png'
import opJIceTea from './img/optionImg/optionIceTea.png'
import opJChoco from './img/optionImg/optionChoco.png'
import halfShot from './img/optionImg/halfShot.png'
import oneShot from './img/optionImg/oneShot.png'
import twoShot from './img/optionImg/twoShot.png'
import stevia from './img/optionImg/stevia.png'
import hoeny from './img/optionImg/honey.png'
import cream from './img/optionImg/cream.png'

import "../css/option.css";
// App.js에 쓴거 지우기 경로
function OpOption(){
  const [opJAmericanoShow, setopJAmericanoShow] = useState(false)
  const [opJIceTeaShow, setopJIceTeaShow] = useState(false)
  const [opJChocoShow, setopChocoShow] = useState(false)
  const [opJselectedOptions, setopJSelectedOptions] = useState({shot: null,stevia: null, honey : null, cream : null});
  const opJresetOptions = () => {
    setopJSelectedOptions({shot: null, stevia: null , honey : null, cream : null});
  }

  const opJAmericanoClose = () => {
    setopJAmericanoShow(false);
  };
  const opJAmericanoOpen = () => {
    setopJAmericanoShow(true);
  };
  const opJIceTeaClose = () => {
    setopJIceTeaShow(false);
  };
  const opJIceTeaOpen = () => {
    setopJIceTeaShow(true);
  };
  const opJChocoClose = () => {
    setopChocoShow(false);
  };
  const opJChocoOpen = () => {
    setopChocoShow(true);
  };
  const opJhandleShotSelect = (option) => {
    setopJSelectedOptions((prev) => ({
      ...prev,
      shot: prev.shot === option ? null : option //이미 골랐던거면 null 아니면 선택
    }));
  };

  const opJhandleSteviaSelect = (option) => {
    setopJSelectedOptions((prev) => ({
      ...prev,
      stevia: prev.stevia === option ? null : option,
    }));
  };
  const opJhandleHoneySelect = (option) => {
    setopJSelectedOptions((prev) => ({
      ...prev,
      honey: prev.honey === option ? null : option,
    }));
  };
  const opJhandleCreamSelect = (option) => {
    setopJSelectedOptions((prev) => ({
      ...prev,
      cream: prev.cream === option ? null : option,
    }));
  };
    return(
        <div>
            <div className="opJKiosk">
                <h1>기존 키오스크 화면</h1>
                <div onClick={opJAmericanoOpen}>아메리카노</div>
                <div onClick={opJIceTeaOpen}>아샷추</div>
                <div onClick={opJChocoOpen}>초코프라페</div>
            </div>
            {opJAmericanoShow && (
              <div className="opJOption">  
                <div className="opJHead">
                    <div>선택하신 상품의 옵션상품을 모두 선택해주세요. </div>
                    <FontAwesomeIcon icon={faXmark} onClick={opJAmericanoClose}/>
                </div>
                <div className="opJImgFlex">
                    <img src={opJAmericano} alter="아메리카노" className="opJCoffeeImg"/>
                    <div className="opJTPE">
                        <div className="opJTitlePrice">
                            <div className="opJTitle">(ICE)아메리카노</div>
                            <div className="opJPrice">2,000원</div>
                        </div>
                        <div className="opJExpain">(24ox)메가MGC커피 블렌딩 원두로 추출한 에스프레소에 물을 더해, 풍부한 바디감을 느낄 수 있는 스탠다드 커피</div>
                    </div>
                </div>
                <div className="opJSelectOption">
                    <div className="opJInnerSelect">선택된 옵션ㅣ{opJselectedOptions.shot} { opJselectedOptions.stevia}</div>
                    <div className="opJReset" onClick={opJresetOptions}><FontAwesomeIcon icon={faRotate}/>초기화</div>
                </div>
                <div className="opJShotSelect">
                    <span className="opJSelectTitle">농도(선택,단일선택)</span>
                    <div className="opJSelects">
                        <div className={`opJSelectsProduct ${opJselectedOptions.shot === "연하게" ? "selected" : ""}`} onClick={() => opJhandleShotSelect("연하게")}>
                            <img src={halfShot} alt="연하게" className="opJSelectImg"/>
                            <div className="opJSelectsName">연하게</div>
                            <div className="opJSelectPrice">+0 원</div>
                        </div>
                        <div className={`opJSelectsProduct ${opJselectedOptions.shot === "샷 추가" ? "selected" : ""}`} onClick={() => opJhandleShotSelect("샷 추가")}>
                            <img src={oneShot} alt="원샷" className="opJSelectImg"/>
                            <div className="opJSelectsName">샷 추가</div>
                            <div className="opJSelectPrice">+500 원</div>
                        </div>
                        <div className={`opJSelectsProduct ${opJselectedOptions.shot === "2샷 추가" ? "selected" : ""}`} onClick={() => opJhandleShotSelect("2샷 추가")}>
                            <img src={twoShot} alt="2샷" className="opJSelectImg"/>
                            <div className="opJSelectsName">2샷 추가</div>
                            <div className="opJSelectPrice">+1,000 원</div>
                        </div>
                    </div>
                </div>
                <div className="opJShotSelect">
                    <span className="opJSelectTitle">저당 스테비아 슈가 추가(선택, 단일선택)</span>
                    <div className="opJSelects">
                        <div className={`opJSelectsProduct ${opJselectedOptions.stevia === "스테비아 추가" ? "selected" : ""}`} onClick={() => opJhandleSteviaSelect("스테비아 추가")}>
                            <img src={stevia} alt="스테비아" className="opJSelectImg"/>
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
            {opJIceTeaShow && (
              <div className="opJOption">  
                <div className="opJHead">
                    <div>선택하신 상품의 옵션상품을 모두 선택해주세요. </div>
                    <FontAwesomeIcon icon={faXmark} onClick={opJIceTeaClose}/>
                </div>
                <div className="opJImgFlex">
                    <img src={opJIceTea} alter="아이스티" className="opJCoffeeImg"/>
                    <div className="opJTPE">
                        <div className="opJTitlePrice">
                            <div className="opJTitle">복숭아아이스티</div>
                            <div className="opJPrice">3,000원</div>
                        </div>
                        <div className="opJExpain">(24ox)홍차의 깊은 맛과 풍부한 복숭아 향이 어우러진 달콤한 여름철 인기 음료</div>
                    </div>
                </div>
                <div className="opJSelectOption">
                    <div className="opJInnerSelect">선택된 옵션ㅣ{opJselectedOptions.shot} { opJselectedOptions.honey}</div>
                    <div className="opJReset" onClick={opJresetOptions}><FontAwesomeIcon icon={faRotate}/>초기화</div>
                </div>
                <div className="opJShotSelect">
                    <span className="opJSelectTitle">농도(선택,단일선택)</span>
                    <div className="opJSelects">
                        <div className={`opJSelectsProduct ${opJselectedOptions.shot === "샷 추가" ? "selected" : ""}`} onClick={() => opJhandleShotSelect("샷 추가")}>
                            <img src={oneShot} alt="원샷" className="opJSelectImg"/>
                            <div className="opJSelectsName">샷 추가</div>
                            <div className="opJSelectPrice">+500 원</div>
                        </div>
                        <div className={`opJSelectsProduct ${opJselectedOptions.shot === "2샷 추가" ? "selected" : ""}`} onClick={() => opJhandleShotSelect("2샷 추가")}>
                            <img src={twoShot} alt="2샷" className="opJSelectImg"/>
                            <div className="opJSelectsName">2샷 추가</div>
                            <div className="opJSelectPrice">+1,000 원</div>
                        </div>
                    </div>
                </div>
                <div className="opJShotSelect">
                    <span className="opJSelectTitle">꿀 추가(선택, 단일선택)</span>
                    <div className="opJSelects">
                        <div className={`opJSelectsProduct ${opJselectedOptions.honey === "꿀 추가" ? "selected" : ""}`} onClick={() => opJhandleHoneySelect("꿀 추가")}>
                            <img src={hoeny} alt="꿀" className="opJSelectImg"/>
                            <div className="opJSelectsName">끌 추가</div>
                            <div className="opJSelectPrice">+700 원</div>
                        </div>
                    </div>
                </div>
                <div className="opJButton">
                    <div className="opJBtnStyle opJCancel" onClick={opJIceTeaClose}>취소</div>
                    <div className="opJBtnStyle opJPut">주문담기</div>
                </div>
              </div>
            )}
            {opJChocoShow && (
              <div className="opJOption">  
                <div className="opJHead">
                    <div>선택하신 상품의 옵션상품을 모두 선택해주세요. </div>
                    <FontAwesomeIcon icon={faXmark} onClick={opJChocoClose}/>
                </div>
                <div className="opJImgFlex">
                    <img src={opJChoco} alter="초코프라페" className="opJCoffeeImg"/>
                    <div className="opJTPE">
                        <div className="opJTitlePrice">
                            <div className="opJTitle">리얼초코프라페</div>
                            <div className="opJPrice">3,900원</div>
                        </div>
                        <div className="opJExpain">(24ox)진한 초코소스와 바닐라향이 더해져 씹는 재미가 있는 달콤한 프라페</div>
                    </div>
                </div>
                <div className="opJSelectOption">
                    <div className="opJInnerSelect">선택된 옵션ㅣ{opJselectedOptions.cream}</div>
                    <div className="opJReset" onClick={opJresetOptions}><FontAwesomeIcon icon={faRotate}/>초기화</div>
                </div>
                <div className="opJShotSelect">
                    <span className="opJSelectTitle">휘핑 여부(선택,단일선택)</span>
                    <div className="opJSelects">
                        <div className={`opJSelectsProduct ${opJselectedOptions.cream === "휘핑O" ? "selected" : ""}`} onClick={() => opJhandleCreamSelect("휘핑O")}>
                            <img src={cream} alt="휘핑" className="opJSelectImg"/>
                            <div className="opJSelectsName">휘핑O</div>
                            <div className="opJSelectPrice">+0 원</div>
                        </div>
                    </div>
                </div>
                <div className="opJButton">
                    <div className="opJBtnStyle opJCancel" onClick={opJChocoClose}>취소</div>
                    <div className="opJBtnStyle opJPut">주문담기</div>
                </div>
              </div>
            )}
        </div>
    )
}
export default OpOption