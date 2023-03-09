import { useState } from 'react';
import './App.css';
import ActionButton from './components/ActionButton';
import MainPage from './pages/MainPage';

function App() {
  const [countryCode, setCountryCode] = useState("SG");

  const randomiseCountry = () => {
    return "UK";
  };
  return (
    <div className="App">
      <ActionButton
        countryCode={countryCode}
        onChange={() => setCountryCode(randomiseCountry())}
      />
      <MainPage />
    </div>
  );
}

export default App;
