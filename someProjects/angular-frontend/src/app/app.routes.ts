import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Products } from './pages/products/products';
import { ProductCategory } from './pages/product-category/product-category';

export const routes: Routes = [

  { path: '', component: Home },
  { path: 'products', component: Products },
  { path: 'product-category', component: ProductCategory },
  { path: '**', redirectTo: '' }
  
];
