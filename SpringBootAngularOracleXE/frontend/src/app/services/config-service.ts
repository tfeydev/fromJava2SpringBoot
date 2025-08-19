import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  public apiBaseUrl: string = '';

  constructor(private http: HttpClient) {}

  loadConfig(): Promise<void> {
    return this.http.get<{ apiBaseUrl: string }>('/assets/config.json')
      .toPromise()
      .then(config => {
        if (config && config.apiBaseUrl) {
          this.apiBaseUrl = config.apiBaseUrl;
        } else {
          this.apiBaseUrl = '';
        }
      });
  }
  
}
