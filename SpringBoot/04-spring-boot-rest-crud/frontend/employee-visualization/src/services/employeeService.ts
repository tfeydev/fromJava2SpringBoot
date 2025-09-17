// src/services/employeeService.ts

export type Employee = {
    firstName: string;
    lastName: string;
    email: string;
};

// Represents the full API response with the HAL format
interface ApiResponse {
    _embedded: {
        employees: Employee[];
    };
}

export async function fetchEmployees(): Promise<Employee[]> {
    try {
        const response = await fetch("http://localhost:8080/api/employees");

        if (!response.ok) {
            // Throw a more specific error with the status code
            throw new Error(`Failed to fetch data: ${response.status} ${response.statusText}`);
        }

        const data: ApiResponse = await response.json();

        // Safely access the embedded 'employees' array, returning an empty array if not found
        return data._embedded?.employees || [];

    } catch (error) {
        console.error("Error fetching employees:", error);
        // Re-throw the error or return an empty array, depending on your error handling strategy
        throw error;
    }
}