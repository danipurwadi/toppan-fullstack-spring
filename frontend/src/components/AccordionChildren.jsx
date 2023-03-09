import "./AccordionChildren.css";

export default function AccordionChildren({ name }) {
  return (
    <div className="customer" id="customer">
      <p>{`${name}`}</p>
    </div>
  );
}
