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
      <div
        className="accordion"
        id={`book-item-${index + 1}`}
        data-testid={`book-item-${index + 1}`}
      >
        <div className="accordion-content">
          <h1>{`${index + 1} ${book.name}`}</h1>
          <button
            className="book-toggle"
            id="book-toggle"
            data-testid="book-toggle"
            onClick={() => changeActiveIndex(index)}
          >
            <Arrow />
          </button>
        </div>
        <div className="accordion-author">
          <p>{`by ${book.author}`}</p>
        </div>
      </div>
      {isActive && book.borrower.length > 0 ? (
        <div className="accordion-children">
          {book.borrower.map((name, idx) => (
            <AccordionChildren key={idx} name={name} />
          ))}
        </div>
      ) : null}
    </>
  );
}
