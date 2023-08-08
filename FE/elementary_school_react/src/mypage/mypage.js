import { useState } from "react";
import style from "../css/mypage.module.css";
import My_J_userImg from "./img/userImg.png";
import { Link } from "react-router-dom";
import Sidebar from "../sidebar/sidebar";

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
      <div className={style.myJCenter}>
        <h1 style={{ marginBottom: "3vh" }}>나의정보</h1>
        <div className={style.myJFlex} style={{ marginBottom: "3vh" }}>
          <img src={My_J_userImg} className={style.myJUser} alt="유저" />
          <div className={`${style.myJFlexColumn} ${style.myJ15Font}`}>
            <div className={style.myJFlex}>
              <div>아이디 : </div>&nbsp;<div>milkDiet@gmail.com</div>
            </div>
            <div className={style.myJFlex}>
              <div>역할 : </div>&nbsp;<div>선생님</div>
            </div>
            <div className={style.myJFlex}>
              <div>포인트 : </div>&nbsp;<div>1004 Point</div>
            </div>
          </div>
        </div>
        <div className={`${style.myJFlex} ${style.myJ15Font}`}>
          <div className={style.myJButton}>
            <Link to="/posting">내가 쓴 게시글</Link>
          </div>
          <div className={style.myJButton}>
            <Link to="/comment">내가 쓴 댓글</Link>
          </div>
        </div>
        <div className={style.myJBottom} onClick={myJshowClick}>
          회원 탈퇴
        </div>
      </div>
      {myJuserDropShow && (
        <div className={`${style.myJCenter} ${style.myJdropUser}`}>
          <div className={style.myJuserDropContent}>
            <div>
              <h1>국민학교를 자퇴하시겠습니까?</h1>
            </div>
            <div className={style.myJdropFlex}>
              <Link
                to="/"
                className={style.myJdropButton}
                style={{ backgroundColor: "#91C8E4" }}
              >
                예
              </Link>
              <div
                className={style.myJdropButton}
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
  );
}
export default MyJMypage;
