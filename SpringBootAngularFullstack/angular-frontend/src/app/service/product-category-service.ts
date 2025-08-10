import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ProductCategoryService {
  private apiUrl = '/api/product-categories'; // falls Proxy verwendet wird

  constructor(private http: HttpClient) {}

  getProductCategories(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
}