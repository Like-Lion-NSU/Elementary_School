import React, { useState } from "react";
import { Link } from "react-router-dom";
import "../css/korailHeader.css";

function TrainSchedule({ setScore, lastScore }) {
  const trainData = [
    {
      trainName: "KTX",
      departureTime: "16:02",
      arrivalTime: "19:20",
      normalClassPrice: "매진",
      specialClassPrice: "매진",
    },
    {
      trainName: "무궁화호",
      departureTime: "16:08",
      arrivalTime: "21:32",
      normalClassPrice: "28,600원",
      specialClassPrice: "-",
    },
    {
      trainName: "KTX",
      departureTime: "16:28",
      arrivalTime: "18:58",
      normalClassPrice: "매진",
      specialClassPrice: "매진",
    },
    {
      trainName: "KTX",
      departureTime: "16:57",
      arrivalTime: "19:40",
      normalClassPrice: "59,800원",
      specialClassPrice: "83,700원",
    },
    {
      trainName: "무궁화호",
      departureTime: "17:08",
      arrivalTime: "22:32",
      normalClassPrice: "28,600원",
      specialClassPrice: "-",
    },
    {
      trainName: "KTX",
      departureTime: "17:27",
      arrivalTime: "19:50",
      normalClassPrice: "-",
      specialClassPrice: "83,700원",
    },
    {
      trainName: "KTX",
      departureTime: "18:08",
      arrivalTime: "20:35",
      normalClassPrice: "59,800원",
      specialClassPrice: "83,700원",
    },
    {
      trainName: "KTX",
      departureTime: "19:36",
      arrivalTime: "22:01",
      normalClassPrice: "59,800원",
      specialClassPrice: "83,700원",
    },
  ];

  const [selectedTrain, setSelectedTrain] = useState(null);

  const handleTrainClick = (train) => {
    setSelectedTrain(train);
  };

  const handleTrainBtn1Click = () => {
    if (lastScore > 0) {
      setScore(lastScore - 10);
      alert("다른 시간대의 기차표를 예매해주세요");
    }
  };

  return (
    <div>
      <div className='korailSeatHeader'>
        <div className='seoul'>서울</div>
        <div className='seoultobusan'>→</div>
        <div className='busan'>부산</div>
      </div>
      <div className='korailSeatDate'>
        <button>이전날</button>
        <div>2023년 9월 23일 (토)</div>
        <button>다음날</button>
      </div>
      <table className='train-schedule-table'>
        <thead>
          <tr>
            <th>열차</th>
            <th>출발</th>
            <th>도착</th>
            <th>일반실</th>
            <th>특/우등</th>
          </tr>
        </thead>
        <tbody className='tbody'>
          {trainData.map((train, index) => (
            <tr key={index}>
              <td className='trainMargin'>{train.trainName}</td>
              <td className='trainMargin'>{train.departureTime}</td>
              <td className='trainMargin'>{train.arrivalTime}</td>
              <td className='trainMargin'>
                {train.departureTime === "16:08" ? (
                  <button
                    onClick={() => handleTrainClick(train)}
                    className='trainBtn'
                  >
                    {train.normalClassPrice}
                  </button>
                ) : (
                  <button
                    className='trainBtn1'
                    onClick={() => {
                      handleTrainBtn1Click();
                    }}
                  >
                    {train.normalClassPrice}
                  </button>
                )}
              </td>
              <td>
                <button
                  className='trainBtn1'
                  onClick={() => {
                    handleTrainBtn1Click();
                  }}
                >
                  {train.specialClassPrice}
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {selectedTrain && (
        <div className='trainBtnBtn'>
          <div>일반실 5시간 45분 소요</div>
          <div className='trainScheduleBtn'>
            <button>열차 시각</button>
            <button>운임요금</button>
            <Link to={`/korailSeatNum`}>
              <button>좌석선택</button>
            </Link>
          </div>
        </div>
      )}
    </div>
  );
}

export default TrainSchedule;
