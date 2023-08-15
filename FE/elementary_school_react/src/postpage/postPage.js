import React from 'react';
import { useParams } from 'react-router-dom';
import PostHeader from './postHeader';
import PostMain from './postMain';
import PostFooter from './postFooter';
import PostLike from './postLike';
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
        <PostLike postId={selectedPost.id} initialLikes={selectedPost.likes} />
        <PostFooter comments={selectedPost.comments} />
      </div>
    </div>
  );
};

export default PostPage;

// import React, { useEffect, useState } from 'react';
// import { useParams } from 'react-router-dom';
// import PostHeader from './postHeader';
// import PostMain from './postMain';
// import PostFooter from './postFooter';
// import PostLike from './postLike';
// import Sidebar from '../sidebar/sidebar';
// import axios from 'axios'; // axios 추가
// import '../css/post.css';

// const PostPage = () => {
//   const { postId } = useParams();
//   const [selectedPost, setSelectedPost] = useState(null);

//   useEffect(() => {
//     async function fetchPostData() {
//       try {
//         const response = await axios.get(`/api/post/${postId}`); // API 엔드포인트에 맞게 수정
//         setSelectedPost(response.data);
//       } catch (error) {
//         console.error('게시물 데이터를 가져오는 중 에러가 발생했습니다.', error);
//         setSelectedPost(null);
//       }
//     }

//     fetchPostData();
//   }, [postId]);

//   if (!selectedPost) {
//     return <div>게시물을 불러오는 중 오류가 발생했습니다.</div>;
//   }

//   return (
//     <div>
//       <Sidebar />
//       <div className="post-container">
//         <PostHeader title={selectedPost.title} authorEmail={selectedPost.authorEmail} />
//         <PostMain content={selectedPost.content} likes={selectedPost.likes} views={selectedPost.views} />
//         <PostLike postId={selectedPost.id} initialLikes={selectedPost.likes} />
//         <PostFooter comments={selectedPost.comments} />
//       </div>
//     </div>
//   );
// };

// export default PostPage;

// import React, { useEffect, useState } from 'react';
// import { useParams } from 'react-router-dom';
// import PostHeader from './postHeader';
// import PostMain from './postMain';
// import PostFooter from './postFooter';
// import PostLike from './postLike';
// import Sidebar from '../sidebar/sidebar';
// import axios from 'axios';
// import '../css/post.css';

// const PostPage = () => {
//   const { postId } = useParams();
//   const [selectedPost, setSelectedPost] = useState(null);

//   useEffect(() => {
//     async function fetchPostData() {
//       try {
//         const response = await axios.get(`/api/post/${postId}`);
//         setSelectedPost(response.data);
//       } catch (error) {
//         console.error('게시물 데이터를 가져오는 중 에러가 발생했습니다.', error);
//         setSelectedPost(null);
//       }
//     }

//     fetchPostData();
//   }, [postId]);

//   if (!selectedPost) {
//     return <div>게시물을 불러오는 중 오류가 발생했습니다.</div>;
//   }

//   return (
//     <div>
//       <Sidebar />
//       <div className="post-container">
//         <PostHeader title={selectedPost.title} authorEmail={selectedPost.authorEmail} />
//         <PostMain content={selectedPost.content} likes={selectedPost.likes} views={selectedPost.views} />
//         {selectedPost.category === '소통해요' || selectedPost.category === '질문해요' ? (
//           <PostLike postId={selectedPost.id} initialLikes={selectedPost.likes} />
//         ) : null}
//         <PostFooter comments={selectedPost.comments} />
//       </div>
//     </div>
//   );
// };

// export default PostPage;