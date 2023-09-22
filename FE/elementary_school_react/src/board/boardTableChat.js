import React from "react";
import "../css/boardTable.css";
import { useNavigate } from "react-router-dom";

function BoardTableChat({ res }) {
  const navigate = useNavigate();
  const moveChatroom = (key) => {
    navigate(`/chat/${key}`, {
      state: { email: res[key][1], room: key },
    });
  };
  return (
    <div>
      <div className="board-table-container">
        <table className="board-table">
          <thead>
            <tr>
              <th>번호</th>
              <th>채팅방이름</th>
            </tr>
          </thead>
          <tbody className="board-table-body">
            {res &&
              Object.keys(res).map((key, index) => (
                <tr
                  key={key}
                  onClick={() => {
                    moveChatroom(key);
                  }}
                >
                  <td>{index + 1} </td>
                  <td>{res[key][0]}</td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default BoardTableChat;
