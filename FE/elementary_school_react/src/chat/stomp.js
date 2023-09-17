import React, { useState, useEffect } from "react";
import Stomp from "stompjs";
import "../css/chat.css";
import Sidebar from "../sidebar/sidebar.js";
import axios from "axios";

const ChatComponent = ({ currentEmail, authorEmail }) => {
  const [message, setMessage] = useState("");
  const [chatMessages, setChatMessages] = useState([]);
  const [stompClient, setStompClient] = useState(null); // WebSocket 클라이언트 객체를 업데이트하거나 초기화

  useEffect(() => {
    async function getInfo() {
      let currentUrl = window.location.href;
      currentUrl = currentUrl
        .replace("http://115.85.183.239/chat/", "")
        .split("/");
      console.log(currentUrl);
      const response = await axios({
        method: "POST",
        url: "/v1/chat",
        data: {
          current: currentUrl[0],
          post: currentUrl[1],
        },
      });
    }
    getInfo();
    // WebSocket 연결을 설정합니다.
    const socket = new WebSocket("ws://localhost:8081/ws-stomp"); // WebSocket 주소로 수정해야 합니다.

    const client = Stomp.over(socket);
    client.connect(
      {},
      () => {
        // 연결이 성공하면 구독합니다.
        client.subscribe(`/sub/chat/enter/1`, (message) => {
          onMessageReceived(message.body);
        });
        setStompClient(client);
      },
      (error) => {
        console.error("WebSocket Error: ", error);
      }
    );

    return () => {
      // 컴포넌트가 언마운트될 때 WebSocket 연결을 정리합니다.
      if (stompClient) {
        // null 아닐 때
        stompClient.disconnect();
      }
    };
  }, [authorEmail]);

  const onMessageReceived = (messageBody) => {
    // 메시지를 받았을 때 처리합니다.
    setChatMessages([...chatMessages, JSON.parse(messageBody)]);
  };

  const handleInputChange = (event) => {
    setMessage(event.target.value);
  };

  const sendMessage = () => {
    // 메시지를 서버로 보냅니다.
    if (stompClient && message) {
      const messageData = {
        sender: currentEmail,
        content: message,
        timestamp: new Date().toLocaleTimeString(),
      };
      stompClient.send(`/pub/api/chat/enter`, {}, JSON.stringify(messageData));
      setMessage("");
    }
  };

  const ClickEnter = (ev) => {
    if (ev.key === "Enter") {
      sendMessage();
      ev.preventDefault(); // 줄바꿈 방지
    }
  };

  return (
    <div>
      <Sidebar />
      <div className="chatWrapper">
        <div className="chatTitle">1:1 채팅</div>
        <div className="chatContainer">
          <div id="talk">
            <div className="talk-shadow"></div>
            {chatMessages.map((msg, index) => (
              <div
                key={index}
                className={msg.sender === currentEmail ? "me" : "other"}
              >
                {msg.sender !== currentEmail && (
                  <span>
                    <b>{msg.sender}</b>
                  </span>
                )}
                <div className="message-content">{msg.content}</div>
                <div className="message-timestamp">{msg.timestamp}</div>
              </div>
            ))}
          </div>
          <div className="chatFoot">
            <div id="sendZone">
              <textarea
                id="chatMsg"
                value={message}
                onChange={handleInputChange}
                onKeyDown={ClickEnter}
              ></textarea>
              <input
                type="button"
                value="전 송"
                id="chatBtnSend"
                onClick={sendMessage}
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ChatComponent;
