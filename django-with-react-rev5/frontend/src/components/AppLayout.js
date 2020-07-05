import React from "react";
import AppHeader from "./AppHeader";
import AppFooter from "./AppFooter";

function AppLayout(props) {
  const { children } = props;
  console.log(props);
  return (
    <>
      <AppHeader />
      {children}
      <AppFooter />
    </>
  );
}

export default AppLayout;
