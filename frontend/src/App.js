import { useState } from 'react';
import './App.css';
import ActionButton from './components/ActionButton';
import MainPage from './pages/MainPage';

function App() {
  const [countryCode, setCountryCode] = useState("SG");
  const [isDataFound, setIsDataFound] = useState(true);

  const randomiseCountry = () => {
    return "UK";
  };
  return (
    <div>
      <div className="navbar">
        <ActionButton
          countryCode={countryCode}
          onChange={() => setCountryCode(randomiseCountry())}
        />
      </div>
      <MainPage
        isDataFound={isDataFound} />
    </div>
  );
}

export default App;
