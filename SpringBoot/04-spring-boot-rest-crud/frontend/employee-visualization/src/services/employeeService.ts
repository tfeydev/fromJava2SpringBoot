export type Employee = {
    firstName: string;
    lastName: string;
    email: string;
    _links: {
        self: { href: string };
        employee: { href: string };
    };
};

interface ApiResponse {
    _embedded: {
        employees: Employee[];
    };
}

export async function fetchEmployees(): Promise<Employee[]> {
    const response = await fetch("http://localhost:8080/api/employees");
    if (!response.ok) throw new Error(`Fetch failed: ${response.status}`);
    const data: ApiResponse = await response.json();
    return data._embedded.employees || [];
}

export async function addEmployee(employee: Omit<Employee, "_links">): Promise<Employee> {
    const response = await fetch("http://localhost:8080/api/employees", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(employee),
    });
    if (!response.ok) throw new Error(`Add failed: ${response.status}`);
    return await response.json();
}

export async function replaceEmployee(employee: Employee): Promise<Employee> {
    const response = await fetch(employee._links.self.href, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(employee),
    });
    if (!response.ok) throw new Error(`Replace failed: ${response.status}`);
    return await response.json();
}

export async function updateEmployee(employee: Employee, data: Partial<Employee>): Promise<Employee> {
    const response = await fetch(employee._links.self.href, {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
    });
    if (!response.ok) throw new Error(`Update failed: ${response.status}`);
    return await response.json();
}

export async function deleteEmployee(employee: Employee): Promise<void> {
    const response = await fetch(employee._links.self.href, { method: "DELETE" });
    if (!response.ok) throw new Error(`Delete failed: ${response.status}`);
}
