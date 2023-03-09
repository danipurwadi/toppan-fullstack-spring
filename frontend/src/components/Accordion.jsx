import { useState } from "react";
import "./Accordion.css";
import { ReactComponent as Arrow } from "../assets/arrow.svg";
import AccordionChildren from "./AccordionChildren";

export default function Accordion({
  index,
  book,
  changeActiveIndex,
  isActive,
}) {
  return (
    <>
      <div className="accordion">
        <div className="accordion-content">
          <h1>{`${index + 1} ${book.name}`}</h1>
          <button
            className="accordion-button"
            onClick={() => changeActiveIndex(index)}
          >
            <Arrow />
          </button>
        </div>
        <div className="accordion-author">
          <p>{`by ${book.author}`}</p>
        </div>
      </div>
      {isActive ? (
        <div className="accordion-children">
          {book.borrower.map((name, idx) => (
            <AccordionChildren key={idx} name={name} />
          ))}
        </div>
      ) : null}
    </>
  );
}
