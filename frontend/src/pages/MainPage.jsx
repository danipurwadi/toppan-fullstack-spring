import React, { useState } from "react";
import Accordion from "../components/Accordion";
import ErrorComponent from "../components/ErrorComponent";
import "./MainPage.css";

export default function MainPage({ isDataFound, bookData }) {
  const [activeIndex, setActiveIndex] = useState([0, 0, 0]);

  const changeActiveIndex = index => {
    let newActiveIndex = [0, 0, 0];
    if (activeIndex[index] === 0) {
      newActiveIndex[index] = 1;
    }
    setActiveIndex(newActiveIndex);
  };

  return (
    <div className="main">
      {isDataFound && bookData != null ? (
        <div className="accordion-list">
          {bookData.map((book, idx) => (
            <Accordion
              key={idx}
              index={idx}
              book={book}
              changeActiveIndex={changeActiveIndex}
              isActive={activeIndex[idx]}
            />
          ))}
        </div>
      ) : (
        <ErrorComponent />
      )}
    </div>
  );
}
