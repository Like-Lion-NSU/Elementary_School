import "../css/chat.css";
import Sidebar from "../sidebar/sidebar";
import React, { useCallback, useRef, useState, useEffect } from "react";
//npm init , npm install express ws 해야하나
//ws : 웹소캣 라이브러리를 뚫어주는 라이브러리?
const Chatpage = () => {
  const [chatMsg, setchatMsg] = useState("");
  const [name, setName] = useState("");
  const [chatt, setChatt] = useState([]); //채팅내용
  const [chkLog, setChkLog] = useState(false); //로그인
  const [socketData, setSocketData] = useState(); //수신데이터

  const ws = useRef(null); //webSocket을 담는 변수,
  //컴포넌트가 변경될 때 객체가 유지되어야하므로 'ref'로 저장
  //Ref : No렌더링, 변수들의 값 유지->변경시 렌더링을 발생시키지 않아야하는 값
  //Ref에 담긴 값 가져오는게 .current

  const chatMsgBox = chatt.map((item, idx) => (
    <div
      key={idx}
      className={item.sender === "user" ? "me" : "other"}
      // 상대방의 아이디는 왼쪽에, 사용자 자신은 오른쪽에 표시
    >
      {item.sender !== "user" && (
        <span>
          <b>{item.sender}</b>
        </span>
      )}
      [ {item.date} ]<br />
      <span>{item.chatMsg}</span>
    </div>
  ));

  useEffect(() => {
    if (socketData !== undefined) {
      const tempData = chatt.concat(socketData);
      //chatt배열에 내용 추가
      console.log(tempData);
      setChatt(tempData);
    }
  }, [socketData]);

  const onText = (event) => {
    console.log(event.target.value);
    setchatMsg(event.target.value);
  };

  const webSocketLogin = useCallback(() => {
    //ws.current = new WebSocket("ws://localhost:8080/socket/chatt");
    //웹소켓 열어달라고 서버에게 부탁하는 코드??

    ws.current.onmessage = (message) => {
      const dataSet = JSON.parse(message.data);
      setSocketData(dataSet);
    };
  });

  const send = useCallback(() => {
    //함수를 memoization
    if (!chkLog) {
      webSocketLogin();
      setChkLog(true);
    }

    if (chatMsg !== "") {
      const data = {
        sender: "user", // 사용자 자신을 나타내는 식별자
        chatMsg,
        date: new Date().toLocaleString(),
      }; //전송 데이터(JSON)

      const temp = JSON.stringify(data);

      if (ws.current.readyState === 0) {
        //readyState는 웹 소켓 연결 상태를 나타냄
        ws.current.onopen = () => {
          //webSocket이 맺어지고 난 후, 실행
          console.log(ws.current.readyState);
          ws.current.send(temp);
        };
      } else {
        ws.current.send(temp);
      }
    } else {
      alert("메세지를 입력하세요.");
      document.getElementById("chatMsg").focus();
      return;
    }
    setchatMsg("");
  });
  return (
    <div>
      <Sidebar />
      <div className="chatWrapper">
        <div className="chatTitle">1:1 채팅</div>
        <div className="chatContainer">
          <div id="talk">
            <div className="talk-shadow"></div>
            {chatMsgBox}
          </div>
          <div className="chatFoot">
            <div id="sendZone">
              <textarea
                id="chatMsg"
                value={chatMsg}
                onChange={onText}
                onKeyDown={(ev) => {
                  if (ev.key === "Enter") {
                    send();
                  }
                }}
              ></textarea>
              <input
                type="button"
                value="전 송"
                id="chatBtnSend"
                onClick={send}
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default Chatpage;
