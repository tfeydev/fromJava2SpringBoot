import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../service/product-service';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-products',
  imports: [
    JsonPipe
  ],
  templateUrl: './products.html',
  styleUrl: './products.css'
})
export class Products implements OnInit {
  jsonResponse: any;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.getProducts().subscribe({
      next: (data) => this.jsonResponse = data,
      error: (err) => this.jsonResponse = { error: 'Fehler beim Laden', details: err }
    });
  }
}
