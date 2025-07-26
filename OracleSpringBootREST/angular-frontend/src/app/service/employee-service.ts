import { inject } from '@angular/core';
import { Employee } from '../common/hr/employee';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { EmployeePage } from '../common/hr/employee-page';

const API_URL = '/api/employees';

export class EmployeeService {
  private readonly http = inject(HttpClient);

  getEmployees(page: number, size: number): Observable<EmployeePage> {
    return this.http.get<EmployeePage>(`${API_URL}?page=${page}&size=${size}`);
  }

  getEmployeeById(id: number): Observable<EmployeePage> {
    return this.http.get<EmployeePage>(`${API_URL}/${id}`);
  }
  
}