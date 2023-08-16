import React, { useEffect } from "react";
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

function Community() {
  useEffect(() => {
    axios({
      method: "POST",
      url: "/community/posts",
    }).then((result)=>{
      
    })
  });
  return (
    <div>
      <Sidebar />
      <BoardHeader boardTitle="소통해요" />
      <BoardTable boardTitle="소통해요" posts={posts} />
    </div>
  );
}

export default Community;

// import React, { useEffect, useState } from 'react';
// import Sidebar from '../sidebar/sidebar';
// import BoardHeader from './boardHeader';
// import BoardTable from './boardTable';
// import axios from 'axios';

// function Community() {
//   const [posts, setPosts] = useState([]);
//   const boardTitle = '소통해요'; // 카테고리 변경시 변경 필요

//   useEffect(() => {
//     async function fetchPosts() {
//       try {
//         const response = await axios.get(`/api/posts/${boardTitle}`); // API 엔드포인트에 맞게 수정
//         setPosts(response.data);
//       } catch (error) {
//         console.error('게시물 데이터를 가져오는 중 에러가 발생했습니다.', error);
//       }
//     }

//     fetchPosts();
//   }, [boardTitle]);

//   return (
//     <div>
//       <Sidebar />
//       <BoardHeader boardTitle={boardTitle} />
//       <BoardTable boardTitle={boardTitle} posts={posts} />
//     </div>
//   );
// }

// export default Community;
