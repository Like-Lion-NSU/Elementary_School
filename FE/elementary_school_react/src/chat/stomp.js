import React, { useState, useEffect } from "react";
import Stomp from "stompjs";
import "../css/chat.css";
import Sidebar from "../sidebar/sidebar.js";
import { useLocation } from "react-router-dom";

const ChatComponent = () => {
  const [message, setMessage] = useState("");
  const [chatMessages, setChatMessages] = useState([]);
  const [stompClient, setStompClient] = useState(null); // WebSocket 클라이언트 객체를 업데이트하거나 초기화
  const location = useLocation();
  const currentEmail = location.state.email;
  const room = location.state.room;

  useEffect(() => {
    const socket = new WebSocket("ws://115.85.183.239:8081/ws-stomp"); // WebSocket 주소로 수정해야 합니다.

    const client = Stomp.over(socket);
    client.connect(
      {},
      () => {
        // 연결이 성공하면 구독합니다.
        client.subscribe(`/sub/chat/message/${room}`, (message) => {
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
  }, []);

  //채팅보낼때마다 스크롤 가장아래로
  useEffect(() => {
    function prepareScroll() {
      window.setTimeout(() => {
        let chatUI = document.querySelector("#talk");
        chatUI.scrollTop = chatUI.scrollHeight;
      }, 50);
    }
    prepareScroll();
  }, [chatMessages]);

  const onMessageReceived = (messageBody) => {
    // 메시지를 받았을 때 처리합니다.
    console.log("지금찍히고 있는건가?", JSON.parse(messageBody));
    const messag = JSON.parse(messageBody);
    console.log(chatMessages);
    const data = {
      content: messag.content,
      sender: messag.sender,
      timestamp: message.timestamp,
    };
    setChatMessages((prevChatMessages) => [...prevChatMessages, data]);
  };

  const handleInputChange = (event) => {
    setMessage(event.target.value);
  };

  const sendMessage = () => {
    // 메시지를 서버로 보냅니다.
    if (stompClient && message) {
      let url = window.location.href;
      url = url.replace("http://115.85.183.239/chat/", "");
      const messageData = {
        roomId: url,
        Sender: currentEmail,
        content: message,
        timestamp: new Date().toLocaleTimeString(),
      };
      const accessToken = getCookieValue("accessToken");
      const headers = {
        // 원하는 헤더를 추가합니다.
        Authorization: `Bearer ${accessToken}`,
      };

      stompClient.send(
        `/pub/api/chat/message/${room}`,
        {},
        JSON.stringify(messageData)
      );
      setMessage("");
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
