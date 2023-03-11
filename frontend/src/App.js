import { useState } from 'react';
import './App.css';
import ActionButton from './components/ActionButton';
import MainPage from './pages/MainPage';

function App() {
  const [countryCode, setCountryCode] = useState("");
  const [isDataFound, setIsDataFound] = useState(true);
  const [bookData, setBookData] = useState(null);

  const randomiseCountry = () => {
    getTop3Data();
  };

  const getRandomCountry = async () => {
    const getCountry = await fetch('http://localhost:8080/getRandomCountry');
    const result = await getCountry.json();
    return result.country.country_code;
  };

  const getTop3Data = async () => {
    let randomCountry = await getRandomCountry();

    const response = await fetch(`http://localhost:8080/getTop3ReadBooks?country_code=${randomCountry}`);
    const top3Books = await response.json();

    if (top3Books.message === "no results" || top3Books.message === "invalid parameter") {
      setIsDataFound(false);
    } else {
      setIsDataFound(true);
      setBookData(top3Books);
      setCountryCode(randomCountry);
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
