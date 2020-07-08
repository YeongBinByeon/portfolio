import React, { useEffect, useState } from "react";
import Axios from "axios";
import Post from "./Post";

const apiUrl = "http://localhost:8000/api/posts/";

function PostList() {
  const [postList, setPostList] = useState([]);
  useEffect(() => {
    Axios.get(apiUrl)
      //Axios.get() 호출시 결과 promise 객체 반환함. status code 400 미만이면 then 수행
      .then((response) => {
        const { data } = response;
        console.log("loaded response :", response);
        setPostList(data);
      })
      //status code 400 이상이면 예외 수행
      .catch((error) => {
        // error.response;
      });
    console.log("mounted");
  }, []);
  return (
    <div>
      {postList.map((post) => {
        return <Post post={post} key={post.id} />;
      })}
    </div>
  );
}

export default PostList;
