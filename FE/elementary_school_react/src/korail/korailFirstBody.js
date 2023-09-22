import React, { useState } from "react";
import "../css/korailBody.css";

function KorailFirstBody({ setScore, lastScore }) {
  const [departure, setDeparture] = useState("영등포");
  const [arrival, setArrival] = useState("영등포");

  const [selectingDeparture, setSelectingDeparture] = useState(false);
  const [selectingArrival, setSelectingArrival] = useState(false);

  const regions = [
    "서울",
    "용산",
    "광명",
    "영등포",
    "수원",
    "평택",
    "천안아산",
    "천안",
    "대전",
    "대구",
    "포항",
    "부산",
    "강릉",
    "전주",
    "목포",
    "순천",
    "청량리",
    "여수EXPO",
  ];

  const handleDepartureClick = () => {
    setSelectingDeparture(true);
    setSelectingArrival(false);
  };

  const handleArrivalClick = () => {
    setSelectingDeparture(false);
    setSelectingArrival(true);
  };

  const handleRegionClick = (region) => {
    if (selectingDeparture) {
      if (region === "서울") {
        setDeparture(region);
      } else {
        alert("출발지를 다시 선택해주세요.");
        if (lastScore > 0) {
          setScore(lastScore - 10);
        }
      }
      setSelectingDeparture(false);
    } else if (selectingArrival) {
      if (region === "부산") {
        setArrival(region);
      } else {
        alert("도착지를 다시 선택해주세요");
        if (lastScore > 0) {
          setScore(lastScore - 10);
        }
      }
      setSelectingArrival(false);
    }
  };

  return (
    <div className='korail-body-station'>
      <div className='stations'>
        <div className='station'>
          <div className='station-label'>출발</div>
          <div className='station-select-box' onClick={handleDepartureClick}>
            <div className='station-select'>{departure}</div>
          </div>
        </div>
        <div className='exchange-icon'>→</div>

        <div className='station'>
          <div className='station-label'>도착</div>
          <div className='station-select-box' onClick={handleArrivalClick}>
            <div className='station-select'>{arrival}</div>
          </div>
        </div>
      </div>
      {(selectingDeparture || selectingArrival) && (
        <div className='region-buttons'>
          {regions.map((region) => (
            <button
              key={region}
              className='region-button'
              onClick={() => handleRegionClick(region)}
            >
              {region}
            </button>
          ))}
        </div>
      )}
    </div>
  );
}

export default KorailFirstBody;
