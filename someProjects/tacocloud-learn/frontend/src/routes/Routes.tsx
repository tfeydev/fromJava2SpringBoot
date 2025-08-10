import { Routes, Route } from 'react-router-dom';
import Home from '../pages/Home';
import TacoDesignForm from '../Components/TacoDesignForm';

function RoutesComponent() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/design" element={<TacoDesignForm />} />
      <Route path="/order" element={<OrderForm />} /> {/* Neue Route */}
    </Routes>
  );
}

export default RoutesComponent;