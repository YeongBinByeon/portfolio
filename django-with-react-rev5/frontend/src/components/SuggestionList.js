import React, { useState, useEffect } from "react";
import { Card } from "antd";
import Suggestion from "./Suggestion";
import Axios from "axios";
import useAxios from "axios-hooks";
import { useAppContext } from "store";
import "./SuggestionList.scss";

export default function SuggestionList({ style }) {
  const {
    store: { jwtToken },
  } = useAppContext();

  const headers = { Authorization: `JWT ${jwtToken}` };

  const [{ data: userList, loading, error }, refetch] = useAxios({
    url: "http://localhost:8000/accounts/suggestions/",
    headers,
  });

  return (
    <div style={style}>
      {loading && <div>Loading ...</div>}
      {error && <div>로딩 중에 에러가 발생했습니다.</div>}
      <button onClick={() => refetch()}>Reload</button>
      <Card title="Suggestion for you" size="small">
        {userList &&
          userList.map((SuggestionUser) => (
            <Suggestion
              key={SuggestionUser.username}
              SuggestionUser={SuggestionUser}
            />
          ))}
      </Card>
    </div>
  );
}
