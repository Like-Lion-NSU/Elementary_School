import React, { useEffect, useState } from "react";
import Sidebar from "../sidebar/sidebar";
import BoardHeader from "./boardHeader";
import BoardTable from "./boardTable";
import axios from "axios";

const posts = [
  // 더미 데이터
  {
    id: 1,
    title: "첫 번째 게시물",
    author: "글쓴이1",
    date: "2023-08-15",
    views: 10,
  },
  {
    id: 2,
    title: "두 번째 게시물",
    author: "글쓴이2",
    date: "2023-08-16",
    views: 20,
  },
  {
    id: 3,
    title: "세 번째 게시물",
    author: "글쓴이3",
    date: "2023-08-17",
    views: 30,
  },
  {
    id: 4,
    title: "네 번째 게시물",
    author: "글쓴이4",
    date: "2023-08-18",
    views: 40,
  },
  {
    id: 5,
    title: "다섯 번째 게시물",
    author: "글쓴이5",
    date: "2023-08-19",
    views: 50,
  },
];

function Policy() {
  useEffect(() => {
    const accessToken = getCookieValue("accessToken");
    async function chatlist() {
      try {
        const response = await axios({
          method: "GET",
          url: "/v1/chat/room",
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        });
        console.log(response);
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
  });
  return (
    <div>
      <Sidebar />
      <BoardHeader boardTitle="정책정보" />
      <BoardTable boardTitle="정책정보" posts={posts} />
    </div>
  );
}

export default Policy;
