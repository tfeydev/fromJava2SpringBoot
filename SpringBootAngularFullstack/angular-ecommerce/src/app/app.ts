import { Component, signal } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ProductCategoryMenu } from "./components/product-category-menu/product-category-menu";
import { Search } from './components/search/search';

@Component({
  selector: 'app-root',
  imports: [
    RouterModule,
    ProductCategoryMenu,
    Search
],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular-ecommerce');
}
