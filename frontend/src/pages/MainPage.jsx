import React from "react";
import ErrorComponent from "../components/ErrorComponent";
import "./MainPage.css";

export default function MainPage({ isDataFound }) {
  return (
    <div className="main">
      {isDataFound ? (
        <ErrorComponent />
      ) : (
        <div>
          <h1>Test 123</h1>
        </div>
      )}
    </div>
  );
}
