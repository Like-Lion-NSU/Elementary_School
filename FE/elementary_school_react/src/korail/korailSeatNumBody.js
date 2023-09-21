import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import seat1 from "./img/seat1.jpeg";
import seat2 from "./img/seat2.jpeg";
import seat3 from "./img/seat3.jpeg";
import seat4 from "./img/seat4.jpeg";
import seat5 from "./img/seat5.jpeg";
import seat6 from "./img/seat6.jpeg";
import seat7 from "./img/seat7.jpeg";
import seat8 from "./img/seat8.jpeg";
import seat19 from "./img/seat19.PNG";
import seat20 from "./img/seat20.PNG";
import seat17 from "./img/seat17.PNG";
import seat18 from "./img/seat18.PNG";
import "../css/korailSeatNumBody.css";

function KorailSeatNumBody({ setScore, lastScore }) {
  const [currentImage1, setCurrentImage1] = useState(seat1);
  const [currentImage2, setCurrentImage2] = useState(seat2);
  const [currentImage3, setCurrentImage3] = useState(seat3);
  const [currentImage4, setCurrentImage4] = useState(seat4);
  const [currentImage5, setCurrentImage5] = useState(seat5);

  const [selectedSeats, setSelectedSeats] = useState([]);
  const navigate = useNavigate();

  const handleImageClick = (
    currentImage,
    setCurrentImage,
    originalImage,
    seatNumber
  ) => {
    if (currentImage === originalImage) {
      setCurrentImage("");
      setSelectedSeats(selectedSeats.filter((seat) => seat !== seatNumber));
    } else {
      setCurrentImage(originalImage);
      setSelectedSeats([...selectedSeats, seatNumber]);
    }
  };

  const handleCompleteClick = () => {
    if (selectedSeats.length === 4) {
      navigate("/korailPay");
    } else {
      alert("인원수를 다시 선택해주세요.");
      if (lastScore > 0) {
        setScore(lastScore - 10);
      }
    }
  };

  return (
    <div className='SNBimg'>
      <div className='korailSeatDate'>
        <button>5호차</button>
        <div>무궁화호(일반실)</div>
        <button>7호차</button>
      </div>
      <div className='seat-container'>
        <img
          src={currentImage1}
          alt='Seat1'
          className='seat1'
          onClick={() =>
            handleImageClick(currentImage1, setCurrentImage1, seat1, "1")
          }
        />
        <div className='imgG'>
          <img
            src={currentImage2 || seat2}
            alt='Seat2'
            className='seat2'
            onClick={() =>
              handleImageClick(currentImage2, setCurrentImage2, seat19, "2")
            }
          />
          <img
            src={currentImage3 || seat3}
            alt='Seat3'
            className='seat3'
            onClick={() =>
              handleImageClick(currentImage3, setCurrentImage3, seat20, "3")
            }
          />
          <img src={seat8} alt='Seat8' className='seat8' />
          <img
            src={currentImage4 || seat4}
            alt='Seat4'
            className='seat4'
            onClick={() =>
              handleImageClick(currentImage4, setCurrentImage4, seat18, "4")
            }
          />
          <img
            src={currentImage5 || seat5}
            alt='Seat5'
            className='seat5'
            onClick={() =>
              handleImageClick(currentImage5, setCurrentImage5, seat17, "5")
            }
          />
        </div>
        <img src={seat6} alt='Seat6' className='seat6' />
        <img src={seat7} alt='Seat7' className='seat7' />
      </div>
      {selectedSeats.length > 0 && (
        <button className='complete-button' onClick={handleCompleteClick}>
          선택 완료 ({selectedSeats.length}명)
        </button>
      )}
    </div>
  );
}

export default KorailSeatNumBody;
