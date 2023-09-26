import React from "react";
import "../css/boardTable.css";
import { useNavigate } from "react-router-dom";

function BoardTableChat({ res }) {
  const navigate = useNavigate();
  const moveChatroom = (ob) => {
    navigate(`/chat/${ob.roomId}`, {
      state: { email: ob.memberEmail, room: ob.roomId },
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
              res.map((ob, index) => (
                <tr
                  key={ob.roomId}
                  onClick={() => {
                    moveChatroom(ob);
                  }}
                >
                  <td>{index + 1} </td>
                  <td>{ob.roomTitle}</td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default BoardTableChat;
