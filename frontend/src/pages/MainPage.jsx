import React from "react";
import Accordion from "../components/Accordion";
import ErrorComponent from "../components/ErrorComponent";
import "./MainPage.css";

export default function MainPage({ isDataFound, bookData }) {
  return (
    <div className="main">
      {isDataFound && bookData != null ? (
        <div className="accordion-list">
          {bookData.map((book, idx) => (
            <Accordion index={idx} book={book} />
          ))}
        </div>
      ) : (
        <ErrorComponent />
      )}
    </div>
  );
}
