import "./NavBar.css";

export default function NavBar({ countryCode, onChange }) {
  return (
    <button onClick={onChange} className="button">
      <p style={{ margin: 0 }}>Get country: {countryCode}</p>
    </button>
  );
}
