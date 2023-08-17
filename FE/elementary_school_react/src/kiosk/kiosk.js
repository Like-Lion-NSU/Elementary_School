import React, { useState, useEffect } from "react";
import MegaHeader from "../kiosk/megaHeader";
import KioskLayout from "./kioskLayout";
import MegaMenu from "./megaMenu";
import Sidebar from "../sidebar/sidebar";
import Megabody_ice from "./megabody_ice";
import Megabody_choco from "./megabody_choco";
import Megabody_tea from "./megabody_tea";
import MegaFooter from "./megaFooter";
import axios from "axios";
import Kioskproblem from "./kioskproblem";

const Kiosk = () => {
  const [problemopen, setProblemopen] = useState(true);
  const [lastScore, setScore] = useState(100);
  const [selectedCategory, setSelectedCategory] = useState("커피(Ice)");
  const [selectedIceMenu, setSelectedIceMenu] = useState(false);
  const [selectedBokMenu, setSelectedBokMenu] = useState(false);
  const [selectedChoMenu, setSelectedChoMenu] = useState(false);
  const [Icecount, setIce] = useState(0);
  const [Chocount, setCho] = useState(0);
  const [Bokcount, setBok] = useState(0);
  const [userInfo, setUserInfo] = useState(null);

  useEffect(() => {
    const fetchMyPage = async () => {
      try {
        const accessToken = getCookieValue("accessToken"); // 예시 함수로 쿠키 값 추출
        console.log("accessToken:", accessToken); // 추가된 부분

        const response = await axios.get("/kiosk", {
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

            const refreshResponse = await axios.post("/auth/refresh", null, {
              headers: {
                Authorization: `Bearer ${refreshToken}`,
              },
            });

            const newAccessToken = refreshResponse.data;
            // 새로운 AccessToken을 사용하여 다시 마이페이지 정보 요청 등을 수행
            const refreshedResponse = await axios.get("/kiosk", {
              headers: {
                Authorization: `Bearer ${newAccessToken}`,
                Accept: "application/json", // JSON 응답을 요청한다고 설정
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
    <KioskLayout>
      <Kioskproblem problemopen={problemopen} setProblemopen={setProblemopen} />
      <Sidebar />
      <div className="left-panel"></div>
      <div className="center-panel">
        <MegaHeader lastScore={lastScore} setProblemopen={setProblemopen} />
        <MegaMenu
          setSelectedCategory={setSelectedCategory}
          selectedCategory={selectedCategory}
          setScore={setScore}
          lastScore={lastScore}
        />
        {selectedCategory === "커피(Ice)" && (
          <Megabody_ice
            setSelectedIceMenu={setSelectedIceMenu}
            setIce={setIce}
            setScore={setScore}
            lastScore={lastScore}
          />
        )}
        {selectedCategory === "스무디&프라페" && (
          <Megabody_choco
            setSelectedChoMenu={setSelectedChoMenu}
            setCho={setCho}
            setScore={setScore}
            lastScore={lastScore}
          />
        )}
        {selectedCategory === "티(Tea)" && (
          <Megabody_tea
            setSelectedBokMenu={setSelectedBokMenu}
            setBok={setBok}
            setScore={setScore}
            lastScore={lastScore}
          />
        )}
        <MegaFooter
          selectedIceMenu={selectedIceMenu}
          selectedBokMenu={selectedBokMenu}
          selectedChoMenu={selectedChoMenu}
          setSelectedIceMenu={setSelectedIceMenu}
          setSelectedBokMenu={setSelectedBokMenu}
          setSelectedChoMenu={setSelectedChoMenu}
          Icecount={Icecount}
          setIce={setIce}
          Chocount={Chocount}
          setCho={setCho}
          Bokcount={Bokcount}
          setBok={setBok}
          lastScore={lastScore}
          setScore={setScore}
        />
      </div>
      <div className="right-panel"></div>
    </KioskLayout>
  );
};

export default Kiosk;
