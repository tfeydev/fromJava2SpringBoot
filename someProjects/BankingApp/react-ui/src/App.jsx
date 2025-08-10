import { useState, useEffect } from 'react';

function App() {
  const [customers, setCustomers] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/customer-account-view')
      .then(response => {
        if (!response.ok) throw new Error('Netzwerkantwort war nicht ok');
        return response.json();
      })
      .then(data => setCustomers(data))
      .catch(error => console.error('Fehler:', error));
  }, []);

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold mb-4 text-center">Banking Customer View</h1>
      <table className="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
        <thead className="bg-gray-200">
          <tr>
            <th className="py-2 px-4 text-left">Customer ID</th>
            <th className="py-2 px-4 text-left">Name</th>
            <th className="py-2 px-4 text-left">Email</th>
            <th className="py-2 px-4 text-left">Account Number</th>
            <th className="py-2 px-4 text-left">Balance</th>
          </tr>
        </thead>
        <tbody>
          {customers.map(customer => (
            <tr key={customer.customer_id} className="border-b">
              <td className="py-2 px-4">{customer.customer_id}</td>
              <td className="py-2 px-4">{customer.name}</td>
              <td className="py-2 px-4">{customer.email}</td>
              <td className="py-2 px-4">{customer.accountNumber}</td>
              <td className="py-2 px-4">{customer.balance}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;