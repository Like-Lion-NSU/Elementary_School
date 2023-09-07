import React, { useEffect, useState } from "react";
import Sidebar from "../sidebar/sidebar";
import BoardHeader from "./boardHeader";
import BoardTable from "./boardTable";
import axios from "axios";

function Userpost() {
  const [res, setResponse] = useState();
  const category = "내가 쓴 게시글";

  useEffect(() => {
    async function fetchPosts() {
      try {
        const accessToken = getCookieValue("accessToken");
        axios({
          method: "GET",
          url: `/v1/user/posts`,
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }).then((response) => {
          console.log(response.data.data);
          setResponse(response.data.data);
        });
      } catch (error) {
        console.error(
          "게시물 데이터를 가져오는 중 에러가 발생했습니다.",
          error
        );
      }
    }

    function getCookieValue(cookieName) {
      const cookies = document.cookie.split(";");
      for (const cookie of cookies) {
        const [name, value] = cookie.trim().split("=");
        if (name === cookieName) {
          return value;
        }
      }
    }

    fetchPosts();
  }, [category]);

  return (
    <div>
      <Sidebar />
      <BoardHeader boardTitle={category} />
      <BoardTable res={res} />
    </div>
  );
}

export default Userpost;
