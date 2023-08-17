import React from "react";
import "../css/boardTable.css";
import { Link } from "react-router-dom";

function BoardTable({ response }) {
    return (
        <div>
            <div className='board-table-container'>
                <table className='board-table'>
                    <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>날짜</th>
                        <th>조회수</th>
                    </tr>
                    </thead>
                    <tbody className='board-table-body'>
                    {response && response.map((response) => (
                        <tr key={response.id}>
                            <td>
                                <Link to={`/post/${response.category}/${response.postId}`}>
                                    {response.postId}
                                </Link>
                            </td>
                            <td>{response.title}</td>
                            <td>{response.email}</td>
                            <td>{response.updateAt}</td>
                            <td>{response.viewCount}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
            <Link to='/writePost' className='board-write-button'>
                글쓰기
            </Link>
        </div>
    );
}

export default BoardTable;
