import { useState } from "react";
import "../css/mypage.css";
import My_J_userImg from "./img/userImg.png";
import { Link } from "react-router-dom";
import Sidebar from "../sidebar/sidebar";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faRightFromBracket } from "@fortawesome/free-solid-svg-icons";
import axios from "axios";

function MyJMypage() {
  const [myJuserDropShow, setmyJUserDropShow] = useState(false);
  const myJdropClick = () => {
    setmyJUserDropShow(false);
  };
  const myJshowClick = () => {
    setmyJUserDropShow(true);
  };
  const deleteMember = () => {
    const accessToken = getCookieValue("accessToken");
    axios({
      method: "DELETE",
      url: "/drop",
      headers: {
        Authorization: `Bearer ${accessToken}`,
        "Content-Type": "application/x-www-form-urlencoded",
      },
    }).then((res) => {});
  };
  // 쿠키 값 추출 함수 예시
  function getCookieValue(cookieName) {
    const cookies = document.cookie.split(";");
    for (const cookie of cookies) {
      const [name, value] = cookie.trim().split("=");
      if (name === cookieName) {
        return value;
      }
    }
  }

  return (
    <div>
      <Sidebar />

      <div className="myJCenter">
        <div className="myJContainer">
          <div className="myJRole">학생</div>
          <img src={My_J_userImg} />
          <div className="myJMail">hwangpadark@gmail.com</div>
          <span className="myJPoint">포인트 : 1004p</span>

          <div className="myJCommu">
            <div className="myJButtonTop">
              <Link to="/posting">내가 쓴 게시글</Link>
            </div>
            <div className="myJButtonMiddle">
              <Link to="/comments">내가 쓴 댓글</Link>
            </div>
            <div className="myJLogout">
              <Link to="/">
                <FontAwesomeIcon icon={faRightFromBracket} /> 로그아웃
              </Link>
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
                <div
                  to="/"
                  className="myJdropButton"
                  style={{ backgroundColor: "#91C8E4" }}
                  onClick={deleteMember}
                >
                  예
                </div>
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
