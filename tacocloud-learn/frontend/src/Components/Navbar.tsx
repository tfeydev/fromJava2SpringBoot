// src/components/Navbar.tsx
import { Link } from 'react-router-dom';

export default function Navbar() {
  return (
    <nav className="bg-white shadow-md border-b">
      <div className="max-w-7xl mx-auto px-4 py-3 flex justify-center">
        <div className="space-x-4">
          <Link to="/">
            <button className="bg-indigo-600 text-white px-4 py-2 rounded hover:bg-indigo-700 transition">
              Home
            </button>
          </Link>
          <Link to="/design">
            <button className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700 transition">
              Design Taco
            </button>
          </Link>
          <Link to="/order">
            <button className="bg-amber-600 text-white px-4 py-2 rounded hover:bg-amber-700 transition">
              Order
            </button>
          </Link>
        </div>
      </div>
    </nav>
  );
}
