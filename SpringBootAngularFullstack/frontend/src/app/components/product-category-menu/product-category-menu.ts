import { Component } from '@angular/core';
import { ProductCategory } from '../../common/product-category';
import { ProductService } from '../../services/product-service';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-category-menu',
  imports: [
    CommonModule,
    RouterModule
  ],
  templateUrl: './product-category-menu.html',
  styleUrls: ['./product-category-menu.css']
})
export class ProductCategoryMenu {

  productCategories: ProductCategory[] = [];

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.listProductCategories();
  }

  listProductCategories() {
    this.productService.getProductCategories().subscribe(
      data => {
        this.productCategories = data;
      }
    );
  }

}
