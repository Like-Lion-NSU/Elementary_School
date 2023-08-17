import React, { useState, useEffect } from "react";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";
import { faHeart as faHeartRegular } from "@fortawesome/free-regular-svg-icons";
import "../css/post.css";

const PostLike = ({ category, postId, checkedLike }) => {
    const [liked, setLiked] = useState(false);
    const [currentLikeId, setCurrentLikeId] = useState(null);

    useEffect(() => {
        if (checkedLike === false) {
            setLiked(true);
            setCurrentLikeId(null);
        } else {
            setLiked(checkedLike);
        }
    }, [checkedLike]);

    const handleLiked = async () => {
        const accessToken = getCookieValue("accessToken");
        if (!liked) {
            try {
                const response = await axios({
                    method: "put",
                    url: `/post/${category}/${postId}/like`,
                    headers: {
                        Authorization: `Bearer ${accessToken}`,
                    },
                });
                setCurrentLikeId(response.data.likeId);
                setLiked(!liked);
                console.log("좋아요 처리 완료");
            } catch (error) {
                console.log("좋아요 처리 중 에러가 발생했습니다.", error);
            }
        } else {
            if (currentLikeId !== null) {
                try {
                    await axios({
                        method: "DELETE",
                        url: `/post/${category}/${postId}/like/${currentLikeId}`,
                        headers: {
                            Authorization: `Bearer ${accessToken}`,
                        },
                    });
                    setCurrentLikeId(null);
                    setLiked(!liked);
                    console.log("좋아요 취소 처리 완료");
                } catch (error) {
                    console.log("좋아요 취소 처리 중 에러가 발생했습니다.", error);
                }
            } else {
                console.error("좋아요 취소에 필요한 정보가 부족합니다.");
            }
        }
    };

    function getCookieValue(cookieName) {
        const cookies = document.cookie.split(";");
        for (const cookie of cookies) {
            const [name, value] = cookie.trim().split("=");
            if (name === cookieName) {
                return value;
            }
        }
        return "";
    }

    return (
        <div className="post-like">
            <button className="like-button" onClick={handleLiked}>
                {liked ? (
                    <FontAwesomeIcon icon={faHeart} />
                ) : (
                    <FontAwesomeIcon icon={faHeartRegular} />
                )}
            </button>
        </div>
    );
};

export default PostLike;