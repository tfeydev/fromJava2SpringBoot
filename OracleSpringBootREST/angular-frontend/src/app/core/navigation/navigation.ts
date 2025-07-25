import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatToolbarModule,
    MatTabsModule
  ],
  templateUrl: './navigation.html',
  styleUrls: ['./navigation.css']
})
export class Navigation {

  constructor(private router: Router) {}

  onTabChange(index: number) {
    const routes = ['/', '/products', '/product-category', '/employees'];
    this.router.navigate([routes[index]]);
  }
}
