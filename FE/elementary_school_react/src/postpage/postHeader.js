import React from "react";
import "../css/post.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const PostHeader = ({
  title,
  email,
  currentMemberEmail,
  postMemberId,
  currentMemberId,
}) => {
  const navigate = useNavigate();
  const author = email;
  const currentId = currentMemberId;
  const currentEmail = currentMemberEmail;
  const MakeRoom = async () => {
    const accessToken = getCookieValue("accessToken");
    try {
      const response = await axios({
        method: "POST",
        url: `/v1/chat/${postMemberId}`,
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
      console.log("내아이디", currentId);
      console.log(currentEmail);
      navigate(`/chat/${response.data.roomId}`, {
        state: {
          email: currentEmail,
          room: response.data.roomId,
        },
      });
    } catch (error) {
      console.log("방만들다가 에러", error);
    }
  };
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
    <div className="post-header">
      <h2>{title}</h2>
      <p>{email}</p>
      {email !== currentMemberEmail && <button onClick={MakeRoom}>채팅</button>}
    </div>
  );
};

export default PostHeader;
