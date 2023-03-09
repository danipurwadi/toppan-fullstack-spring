import React from "react";
import ErrorComponent from "../components/ErrorComponent";
import "./MainPage.css";

export default function MainPage({ isDataFound, bookData }) {
  return (
    <div className="main">
      {isDataFound && bookData != null ? (
        <div>
          {bookData.map((book, idx) => (
            <h1 key={idx}>{book.name}</h1>
          ))}
        </div>
      ) : (
        <ErrorComponent />
      )}
    </div>
  );
}
