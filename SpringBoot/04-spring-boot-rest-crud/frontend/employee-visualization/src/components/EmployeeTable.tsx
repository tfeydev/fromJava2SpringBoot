import { useEffect, useState } from "react";
import {
    fetchEmployees,
    addEmployee,
    replaceEmployee,
    updateEmployee,
    deleteEmployee,
    type Employee,
} from "../services/employeeService";
import * as React from "react";

export default function EmployeeTable() {
    const [employees, setEmployees] = useState<Employee[]>([]);
    const [selectedEmployee, setSelectedEmployee] = useState<Employee | null>(null);

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const [showAddForm, setShowAddForm] = useState(false);
    const [editMode, setEditMode] = useState(false);
    const [patchMode, setPatchMode] = useState(false);

    const [addFormData, setAddFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
    });

    const [editFormData, setEditFormData] = useState<Employee | null>(null);
    const [patchData, setPatchData] = useState<{ firstName?: string; lastName?: string; email?: string }>({
        lastName: "",
    });

    useEffect(() => {
        (async () => {
            try {
                const data = await fetchEmployees();
                setEmployees(data);
            } catch (e) {
                const msg = e instanceof Error ? e.message : "Unknown error";
                setError(msg);
            } finally {
                setLoading(false);
            }
        })();
    }, []);

    const handleAddSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const created = await addEmployee(addFormData);
            setEmployees((prev) => [...prev, created]);
            setSelectedEmployee(created);
            setShowAddForm(false);
            setAddFormData({ firstName: "", lastName: "", email: "" });
        } catch (e) {
            alert(e instanceof Error ? e.message : "Unknown error");
        }
    };

    const handleEditSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!editFormData) return;
        try {
            const updated = await replaceEmployee(editFormData);
            setEmployees((prev) =>
                prev.map((emp) =>
                    emp._links.self.href === updated._links.self.href ? updated : emp
                )
            );
            setSelectedEmployee(updated);
            setEditMode(false);
            setPatchMode(false);
        } catch (e) {
            alert(e instanceof Error ? e.message : "Unknown error");
        }
    };

    const handlePatchSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!selectedEmployee) return;
        try {
            // Nur die gesetzten Felder patchen (leere Werte werden entfernt)
            const body: Record<string, string> = {};
            if (patchData.firstName && patchData.firstName !== selectedEmployee.firstName) {
                body.firstName = patchData.firstName;
            }
            if (patchData.lastName && patchData.lastName !== selectedEmployee.lastName) {
                body.lastName = patchData.lastName;
            }
            if (patchData.email && patchData.email !== selectedEmployee.email) {
                body.email = patchData.email;
            }
            if (Object.keys(body).length === 0) {
                setPatchMode(false);
                return;
            }

            const updated = await updateEmployee(selectedEmployee, body);
            setEmployees((prev) =>
                prev.map((emp) =>
                    emp._links.self.href === updated._links.self.href ? updated : emp
                )
            );
            setSelectedEmployee(updated);
            setPatchMode(false);
        } catch (e) {
            alert(e instanceof Error ? e.message : "Unknown error");
        }
    };

    const handleDelete = async () => {
        if (!selectedEmployee) return;
        if (!window.confirm(`Delete ${selectedEmployee.firstName} ${selectedEmployee.lastName}?`)) return;
        try {
            await deleteEmployee(selectedEmployee);
            setEmployees((prev) =>
                prev.filter((emp) => emp._links.self.href !== selectedEmployee._links.self.href)
            );
            setSelectedEmployee(null);
            setEditMode(false);
            setPatchMode(false);
        } catch (e) {
            alert(e instanceof Error ? e.message : "Unknown error");
        }
    };

    if (loading) {
        return <div className="p-6 text-gray-600">Loading...</div>;
    }
    if (error) {
        return <div className="p-6 text-red-600">Error: {error}</div>;
    }

    return (
        <div className="container mx-auto p-6 bg-white rounded-lg shadow">
            <h2 className="text-2xl font-bold text-gray-800 mt-8">Employee Overview</h2>

            <button
                onClick={() => {
                    setShowAddForm((s) => !s);
                    setEditMode(false);
                    setPatchMode(false);
                }}
                className="mb-4 px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition"
            >
                {showAddForm ? "Cancel" : "Add Employee"}
            </button>

            {showAddForm && (
                <form onSubmit={handleAddSubmit} className="mb-6 p-4 border rounded bg-gray-50">
                    <h3 className="text-lg font-semibold mb-4">Add New Employee</h3>
                    <input
                        type="text"
                        placeholder="First Name"
                        value={addFormData.firstName}
                        onChange={(e) => setAddFormData({ ...addFormData, firstName: e.target.value })}
                        className="w-full mb-2 border px-3 py-2 rounded"
                        required
                    />
                    <input
                        type="text"
                        placeholder="Last Name"
                        value={addFormData.lastName}
                        onChange={(e) => setAddFormData({ ...addFormData, lastName: e.target.value })}
                        className="w-full mb-2 border px-3 py-2 rounded"
                        required
                    />
                    <input
                        type="email"
                        placeholder="Email"
                        value={addFormData.email}
                        onChange={(e) => setAddFormData({ ...addFormData, email: e.target.value })}
                        className="w-full mb-4 border px-3 py-2 rounded"
                        required
                    />
                    <button
                        type="submit"
                        className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
                    >
                        Submit
                    </button>
                </form>
            )}

            <div className="overflow-x-auto">
                <table className="min-w-full border border-gray-200 divide-y divide-gray-200">
                    <thead className="bg-gray-100">
                    <tr>
                        <th className="table-th">First Name</th>
                        <th className="table-th">Last Name</th>
                        <th className="table-th">Email</th>
                    </tr>
                    </thead>
                    <tbody className="bg-white divide-y divide-gray-200">
                    {employees.map((emp) => (
                        <tr
                            key={emp._links.self.href}
                            className="table-tr cursor-pointer"
                            onClick={() => {
                                setSelectedEmployee(emp);
                                setEditMode(false);
                                setPatchMode(false);
                            }}
                        >
                            <td className="table-td">{emp.firstName}</td>
                            <td className="table-td">{emp.lastName}</td>
                            <td className="table-td">{emp.email}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>

            {selectedEmployee && !editMode && !patchMode && (
                <div className="mt-6 p-4 border border-gray-300 rounded-lg bg-gray-50">
                    <h3 className="text-xl font-semibold text-gray-700 mb-2">Details</h3>
                    <p><strong>First Name:</strong> {selectedEmployee.firstName}</p>
                    <p><strong>Last Name:</strong> {selectedEmployee.lastName}</p>
                    <p><strong>Email:</strong> {selectedEmployee.email}</p>

                    <div className="mt-4 flex flex-wrap gap-2">
                        <button
                            onClick={() => {
                                setEditMode(true);
                                setEditFormData({ ...selectedEmployee });
                            }}
                            className="px-4 py-2 bg-yellow-500 text-white rounded hover:bg-yellow-600 transition"
                        >
                            Edit (PUT)
                        </button>
                        <button
                            onClick={() => {
                                setPatchMode(true);
                                setPatchData({
                                    firstName: selectedEmployee.firstName,
                                    lastName: selectedEmployee.lastName,
                                    email: selectedEmployee.email,
                                });
                            }}
                            className="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600 transition"
                        >
                            Update (PATCH)
                        </button>
                        <button
                            onClick={handleDelete}
                            className="px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700 transition"
                        >
                            Delete
                        </button>
                    </div>
                </div>
            )}

            {editMode && editFormData && (
                <form onSubmit={handleEditSubmit} className="mt-6 p-4 border rounded bg-yellow-50">
                    <h3 className="text-lg font-semibold mb-4">Edit Employee</h3>
                    <input
                        type="text"
                        value={editFormData.firstName}
                        onChange={(e) => setEditFormData({ ...editFormData, firstName: e.target.value })}
                        className="w-full mb-2 border px-3 py-2 rounded"
                        required
                    />
                    <input
                        type="text"
                        value={editFormData.lastName}
                        onChange={(e) => setEditFormData({ ...editFormData, lastName: e.target.value })}
                        className="w-full mb-2 border px-3 py-2 rounded"
                        required
                    />
                    <input
                        type="email"
                        value={editFormData.email}
                        onChange={(e) => setEditFormData({ ...editFormData, email: e.target.value })}
                        className="w-full mb-4 border px-3 py-2 rounded"
                        required
                    />
                    <div className="flex gap-2">
                        <button
                            type="submit"
                            className="px-4 py-2 bg-yellow-600 text-white rounded hover:bg-yellow-700"
                        >
                            Save Changes
                        </button>
                        <button
                            type="button"
                            onClick={() => setEditMode(false)}
                            className="px-4 py-2 bg-gray-300 text-gray-800 rounded hover:bg-gray-400"
                        >
                            Cancel
                        </button>
                    </div>
                </form>
            )}

            {patchMode && selectedEmployee && (
                <form onSubmit={handlePatchSubmit} className="mt-6 p-4 border rounded bg-green-50">
                    <h3 className="text-lg font-semibold mb-4">Update Selected Fields (PATCH)</h3>

                    <label className="block text-sm font-medium text-gray-700 mb-1">First Name</label>
                    <input
                        type="text"
                        value={patchData.firstName ?? ""}
                        onChange={(e) => setPatchData((d) => ({ ...d, firstName: e.target.value }))}
                        className="w-full mb-3 border px-3 py-2 rounded"
                        placeholder={selectedEmployee.firstName}
                    />

                    <label className="block text-sm font-medium text-gray-700 mb-1">Last Name</label>
                    <input
                        type="text"
                        value={patchData.lastName ?? ""}
                        onChange={(e) => setPatchData((d) => ({ ...d, lastName: e.target.value }))}
                        className="w-full mb-3 border px-3 py-2 rounded"
                        placeholder={selectedEmployee.lastName}
                    />

                    <label className="block text-sm font-medium text-gray-700 mb-1">Email</label>
                    <input
                        type="email"
                        value={patchData.email ?? ""}
                        onChange={(e) => setPatchData((d) => ({ ...d, email: e.target.value }))}
                        className="w-full mb-4 border px-3 py-2 rounded"
                        placeholder={selectedEmployee.email}
                    />

                    <div className="flex gap-2">
                        <button
                            type="submit"
                            className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700"
                        >
                            Save Update
                        </button>
                        <button
                            type="button"
                            onClick={() => setPatchMode(false)}
                            className="px-4 py-2 bg-gray-300 text-gray-800 rounded hover:bg-gray-400"
                        >
                            Cancel
                        </button>
                    </div>
                </form>
            )}
        </div>
    );
}
