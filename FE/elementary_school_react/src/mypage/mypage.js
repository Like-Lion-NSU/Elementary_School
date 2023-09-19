import { useState, useEffect } from "react";
import "../css/mypage.css";
import My_J_userImg from "./img/userImg.png";
import { Link } from "react-router-dom";
import Sidebar from "../sidebar/sidebar";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faRightFromBracket,
  faFileLines,
  faComments,
} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";

function MyJMypage() {
  const [myJuserDropShow, setmyJUserDropShow] = useState(false);
  const myJdropClick = () => {
    setmyJUserDropShow(false);
  };
  const myJshowClick = () => {
    setmyJUserDropShow(true);
  };
  const [userInfo, setUserInfo] = useState(null);

  useEffect(() => {
    const fetchMyPage = async () => {
      try {
        const accessToken = getCookieValue("accessToken"); // 예시 함수로 쿠키 값 추출
        console.log("accessToken:", accessToken); // 추가된 부분

        const response = await axios.get("/v1/mypage", {
          headers: {
            Authorization: `Bearer ${accessToken}`,
            Accept: "application/json", // JSON 응답을 요청한다고 설정
          },
        });

        setUserInfo(response.data);
        console.log(userInfo);
      } catch (error) {
        if (error.response && error.response.status === 401) {
          try {
            const refreshToken = getCookieValue("refreshToken"); // 예시 함수로 쿠키 값 추출

            const refreshResponse = await axios.post("/v1/auth/refresh", null, {
              headers: {
                Authorization: `Bearer ${refreshToken}`,
              },
            });

            const newAccessToken = refreshResponse.data;
            // 새로운 AccessToken을 사용하여 다시 마이페이지 정보 요청 등을 수행
            const refreshedResponse = await axios.get("/v1/mypage", {
              headers: {
                Authorization: `Bearer ${newAccessToken}`,
              },
            });

            setUserInfo(refreshedResponse.data);
          } catch (refreshError) {
            // RefreshToken으로 새로운 AccessToken 발급 실패
            // 로그아웃 처리 등을 수행
          }
        }
        // Handle other errors
      }
    };

    fetchMyPage();
  }, []);

  const deleteMember = () => {
    const accessToken = getCookieValue("accessToken");
    axios({
      method: "DELETE",
      url: "/v1/drop",
      headers: {
        Authorization: `Bearer ${accessToken}`,
        "Content-Type": "application/x-www-form-urlencoded",
      },
    })
      .then((response) => {
        console.log(response);
        window.location.href = "/";
      })
      .catch((error) => {
        console.error(error);
      });
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
      <div className="myJCenter">
        <Sidebar />
        {userInfo && (
          <div className="myJContainer">
            <div className="myJRole">
              {userInfo.role === "TEACHER" ? "선생님" : "학생"}
            </div>
            <img src={My_J_userImg} />
            <div className="myJMail">{userInfo.email}</div>
            <span className="myJPoint">포인트 : {userInfo.point}p</span>

            <div className="myJCommu">
              <div className="myJButtonTop">
                <Link to="/posting">
                  <FontAwesomeIcon icon={faFileLines} /> 내가 쓴 게시글
                </Link>
              </div>
              <div className="myJButtonMiddle">
                <Link to="/comments">
                  <FontAwesomeIcon icon={faComments} /> 내가 쓴 댓글
                </Link>
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
        )}

        {myJuserDropShow && (
          <div className="myJCenter myJdropUser">
            <div className="myJuserDropContent">
              <div>
                <h1>국민학교를 자퇴하시겠습니까?</h1>
              </div>
              <div className="myJdropFlex">
                <div
                  className="myJdropButton"
                  style={{ backgroundColor: "#4682A9", color: "white" }}
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
