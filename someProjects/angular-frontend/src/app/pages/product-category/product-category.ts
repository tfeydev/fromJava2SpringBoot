import { JsonPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ProductCategoryService } from '../../service/product-category-service';

@Component({
  selector: 'app-product-category',
  imports: [
    JsonPipe
  ],
  templateUrl: './product-category.html',
  styleUrl: './product-category.css'
})
export class ProductCategory implements OnInit {
  jsonResponse: any;

  constructor(private productCategoryService: ProductCategoryService) {}

  ngOnInit(): void {
    this.productCategoryService.getProductCategories().subscribe({
      next: (data) => this.jsonResponse = data,
      error: (err) => this.jsonResponse = { error: 'Fehler beim Laden', details: err }
    });
  }
}
