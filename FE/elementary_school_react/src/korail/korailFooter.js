import React from "react";
import { Link } from "react-router-dom";
import _ from "lodash";
import "../css/korailFooter.css";

function KorailFooter({
  departure,
  arrival,
  selectedDate,
  selectedTime,
  passengerCount,
}) {
  const movetokorailSeat = () => {
    console.log("출발", departure);
    console.log("도착", arrival);
    console.log("선택된 날짜", selectedDate);
    console.log("선택된 시간", selectedTime);
    console.log("인원", passengerCount);
    if (
      departure === "서울" &&
      arrival === "부산" &&
      selectedDate === "23일(토)" &&
      selectedTime === "16시" &&
      _.isEqual(passengerCount, {
        adult: 4,
        child: 0,
        infant: 0,
        senior: 0,
        severeDisability: 0,
        mildDisability: 0,
      })
    ) {
      window.location.href = "/korailSeat";
    } else {
      alert("문제를 다시한번 확인해주세요!");
    }
  };
  return (
    <div className="korailFooter" onClick={movetokorailSeat}>
      열차 조회하기
    </div>
  );
}

export default KorailFooter;
