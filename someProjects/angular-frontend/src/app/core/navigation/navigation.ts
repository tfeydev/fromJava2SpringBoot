import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NavigationEnd, Router, RouterModule } from '@angular/router';
import { filter, Subscription } from 'rxjs';

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

  selectedIndex = 0;
  private sub!: Subscription;

  readonly tabs = [
    { label: 'Home', path: '/' },
    { label: 'Products', path: '/products' },
    { label: 'Product Category', path: '/product-category' },
  ];

  constructor(private router: Router) {  }

  ngOnInit(): void {
    // Bei jedem NavigationEnd-Event das active Tab neu setzen:
    this.sub = this.router.events
      .pipe(filter(evt => evt instanceof NavigationEnd))
      .subscribe((evt: NavigationEnd) => {
        const url = evt.urlAfterRedirects.split('?')[0];
        const idx = this.tabs.findIndex(t => t.path === url);
        this.selectedIndex = idx >= 0 ? idx : 0;
      });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }private syncTabWithRoute(): void {
    const currentUrl = this.router.url.split('?')[0];
    const foundIndex = this.tabs.findIndex(tab => tab.path === currentUrl);
    this.selectedIndex = foundIndex !== -1 ? foundIndex : 0;
  }

  onTabChange(index: number): void {
    const tab = this.tabs[index];
    if (tab && tab.path !== this.router.url) {
      this.router.navigateByUrl(tab.path);
    }
  }

}
