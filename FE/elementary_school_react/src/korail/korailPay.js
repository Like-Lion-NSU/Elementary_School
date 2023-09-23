import React, { useState } from "react";
import KorailPayHeader from "../korail/korailPayHeader";
import KorailPayMenu from "../korail/korailPayMenu";
import KorailPayBody from "../korail/korailPayBody";
import KorailLayout from "../korail/korailLayout";
import Sidebar from "../sidebar/sidebar";
import Korailproblem from "../korail/korailproblem";
import { ScoreProvider, useScore } from "../korail/context";

const KorailPay = () => {
  const [problemopen, setProblemopen] = useState(false);
  const { lastScore, updateScore } = useScore();
  const [selectedCategory, setSelectedCategory] = useState("카드결제");

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
          <div className='korail-body'>
            <KorailPayHeader
              KorailHeader
              lastScore={lastScore}
              setProblemopen={setProblemopen}
            />
            <KorailPayMenu
              selectedCategory={selectedCategory}
              setSelectedCategory={setSelectedCategory}
              setScore={updateScore}
              lastScore={lastScore}
            />
            <KorailPayBody setScore={updateScore} lastScore={lastScore} />
          </div>
        </div>
        <div className='right-panel'></div>
      </KorailLayout>
    </ScoreProvider>
  );
};

export default KorailPay;
