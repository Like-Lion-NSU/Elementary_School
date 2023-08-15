import React from 'react';
import { useParams } from 'react-router-dom';
import PostHeader from './postHeader';
import PostMain from './postMain';
import PostFooter from './postFooter';
import Sidebar from '../sidebar/sidebar';
import '../css/post.css';

const dummyPosts = [
  {
    id: 1,
    title: '테스트 게시물 1 제목입니다.',
    authorEmail: 'test1@example.com',
    content: '테스트 게시물 1 내용입니다.',
    likes: 10,
    views: 50,
    comments: [],
  },
  {
    id: 2,
    title: '테스트 게시물 2 제목입니다.',
    authorEmail: 'test2@example.com',
    content: '테스트 게시물 2 내용입니다.',
    likes: 15,
    views: 30,
    comments: [],
  },
  // 다른 더미 데이터도 추가
];

const PostPage = () => {
  const { postId } = useParams();

  // postId에 해당하는 더미 데이터 찾기
  const selectedPost = dummyPosts.find(post => post.id === Number(postId));

  if (!selectedPost) {
    return <div>게시물이 없습니다.</div>;
  }

  return (
    <div>
      <Sidebar />
    <div className="post-container">
      <PostHeader title={selectedPost.title} authorEmail={selectedPost.authorEmail} />
      <PostMain content={selectedPost.content} likes={selectedPost.likes} views={selectedPost.views} />
      <PostFooter comments={selectedPost.comments} />
    </div>
    </div>
  );
};

export default PostPage;