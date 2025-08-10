import { Component } from '@angular/core';
import { SalesPerson } from './sales-person';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sales-person-list',
  imports: [CommonModule],
  templateUrl: './sales-person-list-bootstrap.html',
  styleUrl: './sales-person-list.css'
})
export class SalesPersonList {

  // create an array of objects
  salesPersonList: SalesPerson[] = [
    new SalesPerson('John', 'Doe', 'john.doe@example.com', 50000),
    new SalesPerson('Jane', 'Smith', 'jane.smith@example.com', 40000),
    new SalesPerson('Jim', 'Brown', 'jim.brown@example.com', 90000),
    new SalesPerson('Emily', 'Johnson', 'emily.johnson@example.com', 60000)
  ];

}
