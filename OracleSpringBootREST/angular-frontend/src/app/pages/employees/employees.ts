import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Employee } from '../../common/hr/employee';
import { EmployeeService } from '../../service/employee-service';
import { EmployeePage } from '../../common/hr/employee-page';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-employees',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './employees.html',
  styleUrls: ['./employees.css']
})
export class Employees implements AfterViewInit{

  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'email', 'phone', 'hireDate', 'jobId'];
  dataSource = new MatTableDataSource<Employee>([]);
  totalElements = 0;
  loading = false;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private service: EmployeeService) {}

  ngAfterViewInit(): void {
    this.loadData();
    this.paginator.page.subscribe(() => this.loadData());
  }

loadData(): void {
  this.loading = true;
  const page = this.paginator.pageIndex || 0;
  const size = this.paginator.pageSize || 10;

  this.service.getEmployees(page, size).subscribe({
    next: (response: EmployeePage) => {
      console.log('Parsed response:', response);
      this.dataSource.data = response.content;
      this.totalElements = response.totalElements;
      this.loading = false;
    },
    error: (error: HttpErrorResponse) => {
      console.error('Error details:', error);
      console.log('Error status:', error.status);
      console.log('Error message:', error.message);
      console.log('Error response:', error.error);
      this.loading = false;
    },
    complete: () => console.log('Request completed')
  });
}

  // loadData(): void {
  //   this.loading = true;
  //   const page = this.paginator.pageIndex || 0;
  //   const size = this.paginator.pageSize || 10;

  //   this.service.getEmployees(page, size).subscribe((response: EmployeePage) => {
  //     this.dataSource.data = response.content;
  //     this.totalElements = response.totalElements;
  //     this.loading = false;
  //   });
    
  // }

}
