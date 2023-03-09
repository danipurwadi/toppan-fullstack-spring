import "./Accordion.css";
import { ReactComponent as Arrow } from "../assets/arrow.svg";

export default function Accordion({ index, book }) {
  return (
    <>
      <div className="accordion">
        <div className="accordion-content">
          <h1>{`${index + 1} ${book.name}`}</h1>
          <button className="accordion-button">
            <Arrow />
          </button>
        </div>
        <div className="accordion-author">
          <p>{`by ${book.author}`}</p>
        </div>
      </div>
    </>
  );
}
