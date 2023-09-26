import React, { useState } from "react";

function KorailLastBody({
  lastScore,
  setScore,
  passengerCount,
  setPassengerCount,
}) {
  const [isPassengerTabOpen, setIsPassengerTabOpen] = useState(false);

  const openPassengerTab = () => {
    setIsPassengerTabOpen(true);
  };

  const closePassengerTab = () => {
    setIsPassengerTabOpen(false);
  };

  const handleIncrement = (type) => {
    if (type === "adult" && passengerCount.adult >= 4) {
      return;
    }

    setPassengerCount((prevCounts) => ({
      ...prevCounts,
      [type]: prevCounts[type] + 1,
    }));
  };

  const handleDecrement = (type) => {
    if (passengerCount[type] <= 0) {
      return;
    }

    setPassengerCount((prevCounts) => ({
      ...prevCounts,
      [type]: prevCounts[type] - 1,
    }));
  };

  const applyPassengerSelection = () => {
    if (passengerCount.adult === 4) {
      closePassengerTab();
    } else {
      alert("인원수를 다시 선택해주세요.");
      if (lastScore > 0) {
        setScore(lastScore - 10);
        console.log(lastScore - 10);
      }
    }
  };

  return (
    <div className="korail-last-body">
      <div className="passenger-count-box" onClick={openPassengerTab}>
        <div className="passenger-count-label">승객 연령 및 좌석수</div>
        <div className="passenger-count-select">
          어른 {passengerCount.adult}명
        </div>
      </div>

      {isPassengerTabOpen && (
        <div className="passenger-tab">
          <div className="passenger-type">
            어른(만 13세 이상)
            <button onClick={() => handleDecrement("adult")}>−</button>
            {passengerCount.adult}
            <button onClick={() => handleIncrement("adult")}>+</button>
          </div>
          <div className="passenger-type">
            어린이(만 6-12세)
            <button onClick={() => handleDecrement("child")}>−</button>
            {passengerCount.child}
            <button onClick={() => handleIncrement("child")}>+</button>
          </div>
          <div className="passenger-type">
            유아(만 6세 미만)
            <button onClick={() => handleDecrement("infant")}>−</button>
            {passengerCount.infant}
            <button onClick={() => handleIncrement("infant")}>+</button>
          </div>
          <div className="passenger-type">
            경로(만 65세 이상)
            <button onClick={() => handleDecrement("senior")}>−</button>
            {passengerCount.senior}
            <button onClick={() => handleIncrement("senior")}>+</button>
          </div>
          <div className="passenger-type">
            중증 장애인
            <button onClick={() => handleDecrement("severeDisability")}>
              −
            </button>
            {passengerCount.severeDisability}
            <button onClick={() => handleIncrement("severeDisability")}>
              +
            </button>
          </div>
          <div className="passenger-type">
            경증 장애인
            <button onClick={() => handleDecrement("mildDisability")}>−</button>
            {passengerCount.mildDisability}
            <button onClick={() => handleIncrement("mildDisability")}>+</button>
          </div>

          <button className="apply-button" onClick={applyPassengerSelection}>
            적용
          </button>
        </div>
      )}
    </div>
  );
}

export default KorailLastBody;
