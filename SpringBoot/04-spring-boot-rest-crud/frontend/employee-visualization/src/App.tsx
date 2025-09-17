import EmployeeTable from "./components/EmployeeTable.tsx";

export default function App() {
    return (
        <div className="p-6 bg-gray-100">
            <h1 className="text-3xl font-bold text-blue-600 underline">
                Employee Visualizataion
            </h1>
            <EmployeeTable />
        </div>
    );
}
