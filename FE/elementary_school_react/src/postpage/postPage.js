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

    useEffect(() => {
        async function fetchPostData() {
            try {
                const response = await axios.get(`/post/${category}/${postId}`); // URL 형식 변경
                console.log(response.data);
                setSelectedPost(response.data); // setPost 대신 setSelectedPost 사용
            } catch (error) {
                console.error('게시물 데이터를 가져오는 중 에러가 발생했습니다.', error);
                setSelectedPost(null);
            }
        }

        fetchPostData();
    }, [category, postId]);

    if (!selectedPost) {
        return <div>게시물을 불러오는 중 오류가 발생했습니다.</div>;
    }

    const { title, memberId, content, likeCount, viewCount, comments } = selectedPost;

    return (
        <div>
            <Sidebar />
            <div className="post-container">
                <PostHeader title={title} authorEmail={memberId} />
                <PostMain content={content} likes={likeCount} views={viewCount} />
                {category === "소통하기" || category === "질문하기" ? (
                    <PostLike postId={postId} /*initialLikes={selectedPost.likes}*/ />
                ) : null}
                <PostFooter comments={comments} />
            </div>
        </div>
    );
};

export default PostPage;
