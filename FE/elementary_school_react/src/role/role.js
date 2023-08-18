import React, { useState, useEffect } from "react";
import "../css/role.css";
import axios from "axios";

const RolePage = () => {
  const [userInfo, setUserInfo] = useState(null);
  useEffect(() => {
    roleselect();
  }, []);

  const roleselect = async (role) => {
    try {
      const accessToken = getCookieValue("accessToken");
      console.log("accessToken:", accessToken);
      const response = await axios.post(
        "/v1/role/decide",
        { role },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
            "Content-Type": "application/x-www-form-urlencoded",
          },
        }
      );

      if (response.status === 200) {
        window.location.href = "/home";
      }
    } catch (error) {
      if (error.response && error.response.status === 401) {
        try {
          const refreshToken = getCookieValue("refreshToken");

          const refreshResponse = await axios
            .post("/v1/auth/refresh", null, {
              headers: {
                Authorization: `Bearer ${refreshToken}`,
              },
            })
            .then((result) => {
              if (result === "403") {
                window.location.href = "/403";
              }
            });
          const newAccessToken = refreshResponse.data;
          const refreshedResponse = await axios.get("/v1/role/decide", {
            headers: {
              Authorization: `Bearer ${newAccessToken}`,
            },
          });

          if (refreshedResponse.status === 200) {
            window.location.href = "/home";
          }

          setUserInfo(refreshedResponse.data);
        } catch (refreshError) {}
      }
    }
  };
  roleselect();

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
    <div className="role-page" id="roleE-container">
      <h1 id="roleE-title">역할을 선택해주세요.</h1>
      <div className="role-buttons">
        <button className="roleE-button" onClick={() => roleselect("TEACHER")}>
          <span role="img">👩‍🏫</span>
          <div className="roleE-role">선생님</div>
        </button>
        <button className="roleE-button" onClick={() => roleselect("STUDENT")}>
          <span role="img">👩‍🎓</span>
          <div className="roleE-role">학생</div>
        </button>
      </div>
    </div>
  );
};

export default RolePage;
