import React, { useState, useEffect } from "react";
import KorailHeader from "../korail/korailHeader";
import KorailFirstBody from "../korail/korailFirstBody";
import KorailSecondBody from "../korail/korailSecondBody";
import KorailFooter from "../korail/korailFooter";
import KorailLayout from "../korail/korailLayout";
import Sidebar from "../sidebar/sidebar";
import KorailMenu from "../korail/korailMenu";
import KorailLastBody from "../korail/korailLastBody";
import Korailproblem from "../korail/korailproblem";
import { ScoreProvider, useScore } from "../korail/context";

const Korail = () => {
  const [problemopen, setProblemopen] = useState(true);
  const { lastScore, updateScore } = useScore();
  const [selectedCategory, setSelectedCategory] = useState("편도");

  // useEffect(() => {
  //   const fetchMyPage = async () => {
  //     try {
  //       const accessToken = getCookieValue("accessToken"); // 예시 함수로 쿠키 값 추출
  //       console.log("accessToken:", accessToken); // 추가된 부분

  //       const response = await axios.get("/v1/kiosk", {
  //         headers: {
  //           Authorization: `Bearer ${accessToken}`,
  //           Accept: "application/json", // JSON 응답을 요청한다고 설정
  //         },
  //       });

  //       setUserInfo(response.data);
  //       console.log(userInfo);
  //     } catch (error) {
  //       if (error.response && error.response.status === 401) {
  //         try {
  //           const refreshToken = getCookieValue("refreshToken"); // 예시 함수로 쿠키 값 추출

  //           const refreshResponse = await axios.post("/v1/auth/refresh", null, {
  //             headers: {
  //               Authorization: `Bearer ${refreshToken}`,
  //             },
  //           });

  //           const newAccessToken = refreshResponse.data;
  //           // 새로운 AccessToken을 사용하여 다시 마이페이지 정보 요청 등을 수행
  //           const refreshedResponse = await axios.get("/v1/kiosk", {
  //             headers: {
  //               Authorization: `Bearer ${newAccessToken}`,
  //               Accept: "application/json", // JSON 응답을 요청한다고 설정
  //             },
  //           });

  //           setUserInfo(refreshedResponse.data);
  //         } catch (refreshError) {
  //           // RefreshToken으로 새로운 AccessToken 발급 실패
  //           // 로그아웃 처리 등을 수행
  //         }
  //       }
  //       // Handle other errors
  //     }
  //   };

  //   fetchMyPage();
  // }, []);

  // // 쿠키 값 추출 함수 예시
  // function getCookieValue(cookieName) {
  //   const cookies = document.cookie.split(";");
  //   for (const cookie of cookies) {
  //     const [name, value] = cookie.trim().split("=");
  //     if (name === cookieName) {
  //       return value;
  //     }
  //   }
  // }

  return (
    <ScoreProvider>
      <KorailLayout>
        <Korailproblem
          problemopen={problemopen}
          setProblemopen={setProblemopen}
        />
        <Sidebar />
        <div className='left-panel'></div>
        <div className='center-panel'>
          <KorailHeader lastScore={lastScore} setProblemopen={setProblemopen} />
          <KorailMenu
            selectedCategory={selectedCategory}
            setSelectedCategory={setSelectedCategory}
            setScore={updateScore}
            lastScore={lastScore}
          />
          <div className='korail-body'>
            <KorailFirstBody setScore={updateScore} lastScore={lastScore} />
            <KorailSecondBody setScore={updateScore} lastScore={lastScore} />
            <KorailLastBody setScore={updateScore} lastScore={lastScore} />
            <KorailFooter />
          </div>
        </div>
        <div className='right-panel'></div>
      </KorailLayout>
    </ScoreProvider>
  );
};

export default Korail;
