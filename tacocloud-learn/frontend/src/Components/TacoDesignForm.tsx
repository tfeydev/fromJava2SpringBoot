import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

interface Ingredient {
  id: string;
  name: string;
  type: string;
}

function TacoDesignForm() {
  const [ingredients, setIngredients] = useState<Ingredient[]>([]);
  const [selectedIngredients, setSelectedIngredients] = useState<string[]>([]);
  const [tacoName, setTacoName] = useState('');
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState<string | null>(null);

  useEffect(() => {
    axios.get<Ingredient[]>('http://localhost:8080/api/design/ingredients')
      .then(response => setIngredients(response.data))
      .catch(() => setError('Failed to load ingredients'));
  }, []);

  const handleIngredientChange = (id: string) => {
    setSelectedIngredients(prev =>
      prev.includes(id) ? prev.filter(ing => ing !== id) : [...prev, id]
    );
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!tacoName || selectedIngredients.length === 0) {
      setError('Please provide a taco name and select at least one ingredient');
      return;
    }
    const taco = { name: tacoName, ingredients: selectedIngredients };
    axios.post('http://localhost:8080/api/design', taco)
      .then(response => {
        setSuccess(`Taco "${(response.data as { name: string }).name}" created!`);
        setTacoName('');
        setSelectedIngredients([]);
        setError(null);
      })
      .catch(() => setError('Failed to create taco'));
  };

  const groupByType = ingredients.reduce((acc, ing) => {
    acc[ing.type] = acc[ing.type] || [];
    acc[ing.type].push(ing);
    return acc;
  }, {} as Record<string, Ingredient[]>);

  return (
    <div className="max-w-2xl mx-auto mt-10 px-4">
  <h1 className="text-3xl font-bold mb-6 text-blue-600 text-center">Design your taco!</h1>
  
  {error && <p className="text-red-500 mb-4 text-center">{error}</p>}
  {success && <p className="text-green-500 mb-4 text-center">{success}</p>}
  
  <form onSubmit={handleSubmit} className="space-y-6">
    <div>
      <label className="block text-lg font-medium text-gray-700 mb-1">Name your taco:</label>
      <input
        type="text"
        value={tacoName}
        onChange={(e) => setTacoName(e.target.value)}
        className="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
        placeholder="Enter taco name"
      />
    </div>

    {Object.keys(groupByType).map(type => (
      <div key={type} className="mb-6">
        <h3 className="text-xl font-semibold capitalize text-gray-800 border-b pb-1 mb-2">{type}</h3>
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-3">
          {groupByType[type].map(ing => (
            <label key={ing.id} className="flex items-center space-x-2">
              <input
                type="checkbox"
                value={ing.id}
                checked={selectedIngredients.includes(ing.id)}
                onChange={() => handleIngredientChange(ing.id)}
                className="h-4 w-4 text-blue-600 rounded focus:ring-blue-500"
              />
              <span className="text-gray-700">{ing.name}</span>
            </label>
          ))}
        </div>
      </div>
    ))}

    <div className="flex justify-center">
      <button
        type="submit"
        className="bg-blue-500 text-white px-8 py-3 rounded-lg hover:bg-blue-600 transition-colors"
      >
        Create Taco
      </button>
    </div>
  </form>

  <div className="text-center mt-6">
    <Link to="/" className="text-blue-500 hover:underline">Back to Home</Link>
  </div>
</div>

  );
}

export default TacoDesignForm;