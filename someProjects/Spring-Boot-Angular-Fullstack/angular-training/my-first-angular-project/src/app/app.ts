import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-root',
  imports: [],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = ('My First Angular Project');
  protected readonly firstName = signal('John');
  protected readonly lastName = signal('Doe');
}
