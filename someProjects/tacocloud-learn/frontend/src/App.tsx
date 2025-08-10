import { BrowserRouter } from 'react-router-dom';
import Navbar from './Components/Navbar';
import RoutesComponent from './routes/Routes';

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <RoutesComponent />
    </BrowserRouter>
  );
}

export default App;