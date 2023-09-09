import React, { useEffect, useState } from "react";
import Sidebar from "../sidebar/sidebar";
import BoardHeader from "./boardHeader";
import BoardTable from "./boardTable";
import axios from "axios";

function Community() {
  const [res, setResponse] = useState(null);
  const category = "소통해요";
  useEffect(() => {
    console.log("들어옴?");
    const fetchPosts = async () => {
      try {
        const accessToken = getCookieValue("accessToken");
        const response = await axios({
          method: "GET",
          url: `/v1/${category}/posts`,
          headers: {
            Authorization: `Bearer ${accessToken}`,
            Accept: "application/json",
          },
        });
        console.log(response.data.data);
        setResponse(response.data.data);
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
            const refreshedResponse = await axios.get("/v1/${category}/posts", {
              headers: {
                Authorization: `Bearer ${newAccessToken}`,
                Accept: "application/json",
              },
            });

            setResponse(refreshedResponse.data.data);
          } catch (refreshError) {
            // RefreshToken으로 새로운 AccessToken 발급 실패
            // 로그아웃 처리 등을 수행
          }
        }
        // Handle other errors
      }
    };
    console.log("실행돼?");
    fetchPosts();
  }, []);
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
      <BoardHeader boardTitle={category} />
      <BoardTable res={res} />
    </div>
  );
}

export default Community;
