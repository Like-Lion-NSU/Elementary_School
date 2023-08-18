import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import PostHeader from "./postHeader";
import PostMain from "./postMain";
import PostFooter from "./postFooter";
import PostLike from "./postLike";
import Sidebar from "../sidebar/sidebar";
import axios from "axios";
import "../css/post.css";

const PostPage = () => {
  const { category, postId } = useParams();
  const [selectedPost, setSelectedPost] = useState(null);
  let checkedLike;

  useEffect(() => {
    async function fetchPostData() {
      try {
        const accessToken = getCookieValue("accessToken");
        axios({
          method: "GET",
          url: `/post/${category}/${postId}`,
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }).then((response) => {
          checkedLike = response.data.data.postLiked.delete;
          // console.log(response.data.data);
          // console.log(response.data.data.photos);
          setSelectedPost(response.data.data);
        });
      } catch (error) {
        console.error(
          "게시물 데이터를 가져오는 중 에러가 발생했습니다.",
          error
        );
        setSelectedPost(null);
      }
    }

    function getCookieValue(cookieName) {
      const cookies = document.cookie.split(";");
      for (const cookie of cookies) {
        const [name, value] = cookie.trim().split("=");
        if (name === cookieName) {
          return value;
        }
      }
    }

    fetchPostData();
  }, [category, postId]);

  if (!selectedPost) {
    return <div>게시물을 불러오는 중 오류가 발생했습니다.</div>;
  }

  const { title, memberId, content, likeCount, viewCount, comments, photos } =
    selectedPost;
  return (
    <div>
      <Sidebar />
      <div className="post-container">
        <PostHeader title={title} authorEmail={memberId} />
        <PostMain
          postId={postId}
          category={category}
          title={title}
          content={content}
          likes={likeCount}
          views={viewCount}
          imageUrl={photos[0].photoUrl}
        />
        {category === "소통해요" || category === "질문해요" ? (
          <PostLike
            category={category}
            postId={postId}
            initialLikes={likeCount}
            checkedLike={checkedLike}
          />
        ) : null}
        <PostFooter comments={comments} />
      </div>
    </div>
  );
};

export default PostPage;
