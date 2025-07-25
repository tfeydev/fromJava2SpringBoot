import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Products } from './pages/products/products';
import { ProductCategory } from './pages/product-category/product-category';
import { Employees } from './pages/employees/employees';
import { Navigation } from './core/navigation/navigation';

export const routes: Routes = [
  {
    path: '',
    component: Navigation,
    children: [
      { path: '', component: Home },
      { path: 'products', component: Products },
      { path: 'product-category', component: ProductCategory },
      { path: 'employees', component: Employees }
    ]
  },  
  { path: '**', redirectTo: '' }
];
