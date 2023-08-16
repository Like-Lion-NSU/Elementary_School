import React from "react";
import "../css/boardTable.css";
import { Link } from "react-router-dom";

function BoardTable({ posts }) {
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
                    {posts.map((post) => (
                        <tr key={post.id}>
                            <td>
                                <Link to={`/post/${post.category}/${post.id}`}>{post.id}</Link>
                            </td>
                            <td>{post.title}</td>
                            <td>{post.memberId}</td>
                            <td>{post.updateAt}</td>
                            <td>{post.viewCount}</td>
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