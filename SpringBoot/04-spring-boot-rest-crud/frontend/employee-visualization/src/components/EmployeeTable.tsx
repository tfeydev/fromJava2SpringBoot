import { useEffect, useState } from "react";
import { fetchEmployees, type Employee } from "../services/employeeService";
import "../index.css";

export default function EmployeeTable() {
    const [employees, setEmployees] = useState<Employee[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const loadEmployees = async () => {
            try {
                setLoading(true);
                const data = await fetchEmployees();
                setEmployees(data);
                setError(null);
            } catch (err) {
                setError(err instanceof Error ? err.message : "Unbekannter Fehler.");
            } finally {
                setLoading(false);
            }
        };
        loadEmployees();
    }, []);

    if (loading) return <p className="text-gray-500">Lade Mitarbeiterdaten...</p>;
    if (error) return <p className="text-red-600 font-bold">Fehler: {error}</p>;
    if (employees.length === 0) return <p className="text-gray-500">Keine Mitarbeiter gefunden.</p>;

    return (
        <div className="container mx-auto p-4 sm:p-6 bg-white rounded-lg shadow-lg">
            <h2 className="text-2xl font-bold text-gray-800 mb-6">Mitarbeiterübersicht</h2>
            <div className="overflow-x-auto">
                <table className="min-w-full border border-gray-200 divide-y divide-gray-200">
                    <thead className="bg-gray-100">
                    <tr>
                        <th className="table-th">Vorname</th>
                        <th className="table-th">Nachname</th>
                        <th className="table-th">E-Mail</th>
                    </tr>
                    </thead>
                    <tbody className="bg-white divide-y divide-gray-200">
                    {employees.map((employee, index) => (
                        <tr key={index} className="hover:bg-gray-50 transition-colors duration-200">
                            <td className="table-td font-medium text-gray-900">{employee.firstName}</td>
                            <td className="table-td">{employee.lastName}</td>
                            <td className="table-td">{employee.email}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
