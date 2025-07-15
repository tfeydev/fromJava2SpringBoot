import { useNavigate } from 'react-router-dom';

function Home() {

  const navigate = useNavigate();

  return (
    <div className="max-w-2xl mx-auto text-center">
      <img src="/TacoCloud.png" alt="Taco Cloud Logo" className="mx-auto mb-4 w-48" />
      <h1 className="text-4xl font-bold mb-4 text-blue-600">Welcome to Taco Cloud!</h1>
      <p className="text-lg mb-6 text-gray-700">Create your own delicious tacos with our custom designer.</p>
      <button
        onClick={() => navigate('/design')}
        className="bg-blue-500 text-white px-6 py-3 rounded-lg hover:bg-blue-600 transition-colors"
      >
        Design a Taco
      </button>
    </div>

  );
}

export default Home;