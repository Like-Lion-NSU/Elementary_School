import React, { useState } from "react";
import "../css/korailBody.css";

function KorailSecondBody({ setScore, lastScore }) {
  const [currentDate, setCurrentDate] = useState(
    new Date("2023-09-23T00:00:00")
  );
  const [dateTabOpen, setDateTabOpen] = useState(false);
  const [timeTabOpen, setTimeTabOpen] = useState(false);
  const [selectedDate, setSelectedDate] = useState("23일(토)");
  const [selectedTime, setSelectedTime] = useState("00시");

  const toggleDateTimeTabs = () => {
    setDateTabOpen(!dateTabOpen);
    setTimeTabOpen(!timeTabOpen);
  };

  const handleDateSelection = (date) => {
    setSelectedDate(date);
    setSelectedTime("00시");
    toggleDateTimeTabs();
  };

  const handleTimeSelection = (time) => {
    setSelectedTime(time);
  };

  const applySelection = () => {
    if (selectedDate === "23일(토)" && selectedTime === "16시") {
      setCurrentDate(new Date(2023, 8, 23, 16, 0, 0));
      toggleDateTimeTabs();
    } else {
      alert("날짜나 시간을 다시 선택해주세요.");
      if (lastScore > 0) {
        setScore(lastScore - 10);
      }
    }
  };

  const generateDateTabs = () => {
    const importantDates = [
      "23일(토)",
      "24일(일)",
      "25일(월)",
      "26일(화)",
      "27일(수)",
      "28일(목)",
      "29일(금)",
      "30일(토)",
    ];
    return (
      <div className='date-tabs'>
        {importantDates.map((date) => (
          <div
            key={date}
            className={`date-tab ${selectedDate === date ? "selected" : ""}`}
            onClick={() => handleDateSelection(date)}
          >
            {date}
          </div>
        ))}
      </div>
    );
  };

  const generateTimeTabs = () => {
    const hours = Array.from({ length: 24 }, (_, i) =>
      i < 10 ? `0${i}시` : `${i}시`
    );
    return (
      <div className='time-tabs'>
        {hours.map((hour) => (
          <div
            key={hour}
            className={`time-tab ${selectedTime === hour ? "selected" : ""}`}
            onClick={() => handleTimeSelection(hour)}
          >
            {hour}
          </div>
        ))}
      </div>
    );
  };

  return (
    <div className='korail-body-date-time'>
      <div className='date-box' onClick={toggleDateTimeTabs}>
        <div className='date-label'>출발일</div>
        <div className='date-select'>
          {currentDate.toLocaleDateString()}{" "}
          {currentDate.toLocaleTimeString([], {
            hour: "2-digit",
            minute: "2-digit",
          })}
        </div>
      </div>
      {dateTabOpen && timeTabOpen && (
        <>
          {generateDateTabs()}
          {generateTimeTabs()}
          <button className='apply-button' onClick={applySelection}>
            적용
          </button>
        </>
      )}
    </div>
  );
}

export default KorailSecondBody;
