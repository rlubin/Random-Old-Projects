import React from "react";
import PercentageChange from "./PercentageChange";
import Flexbox from "flexbox-react";

function App() {
  return (
    <Flexbox
      flexDirection="row"
      alignItems="center"
      justifyContent="center"
      flexGrow={1}
      style={{ height: "100vh", width: "100vw" }}
    >
      <PercentageChange></PercentageChange>
    </Flexbox>
  );
}

export default App;
