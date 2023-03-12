import "./AccordionChildren.css";

export default function AccordionChildren({ name }) {
  return (
    <div className="customer" id="customer" data-testid="customer">
      <p>{`${name}`}</p>
    </div>
  );
}
