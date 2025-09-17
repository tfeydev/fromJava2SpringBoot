import EmployeeTable from "./components/EmployeeTable.tsx";

export default function App() {
    return (
        <div className="p-6 bg-gray-100 min-h-screen">
            <h1 className="text-3xl font-bold text-blue-600 mb-10">
                Employee Visualization
            </h1>
            <div className="mt-10">
                <EmployeeTable />
            </div>
        </div>
    );
}
