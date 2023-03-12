import "./ErrorComponent.css";

export default function ErrorComponent() {
  return (
    <div
      className="error-message"
      data-testid="error-message"
      id="error-message"
    >
      <p>No data found</p>
    </div>
  );
}
