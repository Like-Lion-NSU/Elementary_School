import React, { useEffect, useState } from "react";
import Sidebar from "../sidebar/sidebar";
import BoardHeader from "./boardHeader";
import BoardTableChat from "./boardTableChat";
import axios from "axios";

function Policy() {
  const [res, setResponse] = useState();
  useEffect(() => {
    const accessToken = getCookieValue("accessToken");
    async function chatlist() {
      try {
        const response = await axios({
          method: "GET",
          url: "/v1/chat/rooms",
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        });
        console.log(response.data);
        setResponse(response.data);
      } catch (error) {
        console.log("채팅방목록 가져오다 에러", error);
      }
    }
    chatlist();
    function getCookieValue(cookieName) {
      const cookies = document.cookie.split(";");
      for (const cookie of cookies) {
        const [name, value] = cookie.trim().split("=");
        if (name === cookieName) {
          return value;
        }
      }
    }
  }, []);
  return (
    <div>
      <Sidebar />
      <BoardHeader boardTitle="채팅방목록" />
      <BoardTableChat boardTitle="채팅방" res={res} />
    </div>
  );
}

export default Policy;
