import React from 'react';
import Sidebar from '../sidebar/sidebar';
import BoardHeader from './boardHeader';
import BoardTable from './boardTable';

const posts = [
  // 더미 데이터
  { id: 1, title: '첫 번째 게시물', author: '글쓴이1', date: '2023-08-15', views: 10 },
  { id: 2, title: '두 번째 게시물', author: '글쓴이2', date: '2023-08-16', views: 20 },
  { id: 3, title: '세 번째 게시물', author: '글쓴이3', date: '2023-08-17', views: 30 },
  { id: 4, title: '네 번째 게시물', author: '글쓴이4', date: '2023-08-18', views: 40 },
  { id: 5, title: '다섯 번째 게시물', author: '글쓴이5', date: '2023-08-19', views: 50 },
];

function Question() {
  return (
    <div>
      <Sidebar />
      <BoardHeader boardTitle="질문해요" />
      <BoardTable boardTitle="질문해요" posts={posts} />
    </div>
  );
}

export default Question;