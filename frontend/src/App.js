import { useState, useEffect } from 'react';
import './App.css';
import ActionButton from './components/ActionButton';
import MainPage from './pages/MainPage';

function App() {
  const [countryCode, setCountryCode] = useState("SG");
  const [isDataFound, setIsDataFound] = useState(true);
  const [bookData, setBookData] = useState(null);
  const randomiseCountry = () => {
    getTop3Data();
    return "UK";
  };

  const getTop3Data = async () => {
    const response = await fetch('https://c7cfbf3c-19b9-45b1-b49e-20f3f301ff80.mock.pstmn.io/getTop3ReadBooks');
    const parsedResponse = await response.json();
    console.log(parsedResponse);
    setBookData(parsedResponse);
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
        isDataFound={isDataFound}
        bookData={bookData} />
    </div>
  );
}

export default App;
