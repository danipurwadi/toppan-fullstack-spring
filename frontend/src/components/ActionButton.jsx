import "./ActionButton.css";

export default function ActionButton({ countryCode, onChange }) {
  return (
    <button
      onClick={onChange}
      className="button"
      data-testid="action-btn"
      id="action-btn"
    >
      <p style={{ margin: 0 }}>Get country: {countryCode}</p>
    </button>
  );
}
