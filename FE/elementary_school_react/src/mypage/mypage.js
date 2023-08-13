import { useState } from "react";
import "../css/mypage.css";
import My_J_userImg from "./img/userImg.png";
import { Link } from "react-router-dom";
import Sidebar from "../sidebar/sidebar";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faRightFromBracket, faFileLines, faComments } from '@fortawesome/free-solid-svg-icons';

function MyJMypage() {
  const [myJuserDropShow, setmyJUserDropShow] = useState(false);
  const myJdropClick = () => {
    setmyJUserDropShow(false);
  };
  const myJshowClick = () => {
    setmyJUserDropShow(true);
  };

  return (
    <div>
      <Sidebar />
      
      <div className="myJCenter">
        <div className="myJContainer">
          <div className="myJRole">학생</div>
          <img src={My_J_userImg}/>
          <div className="myJMail">hwangpadark@gmail.com</div>
          <span className="myJPoint">포인트 : 1004p</span>

          <div className="myJCommu">
              <div className="myJButtonTop">
                <Link to="/posting"><FontAwesomeIcon icon={faFileLines}/> 내가 쓴 게시글</Link>
              </div>
              <div className="myJButtonMiddle">
                <Link to="/comment"><FontAwesomeIcon icon={faComments}/> 내가 쓴 댓글</Link>
              </div>
              <div className="myJLogout">
                <Link to="/"><FontAwesomeIcon icon={faRightFromBracket}/> 로그아웃</Link>
              </div>
          </div>  
          <span className="myJDrop" onClick={myJshowClick}>
            회원 탈퇴
          </span>
        </div>
      
      {myJuserDropShow && (
        <div className="myJCenter myJdropUser">
          <div className="myJuserDropContent">
            <div>
              <h1>국민학교를 자퇴하시겠습니까?</h1>
            </div>
            <div className="myJdropFlex">
              <Link
                to="/"
                className="myJdropButton"
                style={{ backgroundColor: "#91C8E4" }}
              >
                예
              </Link>
              <div
                className="myJdropButton"
                style={{ backgroundColor: "#4682A9", color: "white" }}
                onClick={myJdropClick}
              >
                아니요
              </div>
            </div>
          </div>
        </div>
      )}
      </div> 
    </div>
  );
}
export default MyJMypage;
