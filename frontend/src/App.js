import { useState } from 'react';
import './App.css';
import ActionButton from './components/ActionButton';
import MainPage from './pages/MainPage';

function App() {
  const [countryCode, setCountryCode] = useState("SG");
  const [isDataFound, setIsDataFound] = useState(true);
  const [bookData, setBookData] = useState(null);

  const randomiseCountry = () => {
    getRandomCountry();
    getTop3Data();
  };

  const getRandomCountry = async () => {
    const getCountry = await fetch('https://c7cfbf3c-19b9-45b1-b49e-20f3f301ff80.mock.pstmn.io/getRandomCountry');
    const country = await getCountry.json();
    setCountryCode(country.country.country_code);
  };

  const getTop3Data = async () => {
    const response = await fetch(`https://c7cfbf3c-19b9-45b1-b49e-20f3f301ff80.mock.pstmn.io/getTop3ReadBooks?${countryCode}`);
    const top3Books = await response.json();

    if (top3Books.message === "no results") {
      setIsDataFound(false);
    } else {
      setIsDataFound(true);
      setBookData(top3Books);
    }
  };

  return (
    <div>
      <div className="navbar">
        <ActionButton
          countryCode={countryCode}
          onChange={() => setCountryCode(randomiseCountry())}
        />
      </div>
      <div className="content">
        <MainPage
          isDataFound={isDataFound}
          bookData={bookData} />
      </div>
    </div>
  );
}

export default App;
