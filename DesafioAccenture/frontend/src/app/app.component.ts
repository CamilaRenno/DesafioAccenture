import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <mat-toolbar color="primary">
      <span>Desafio Full-Stack</span>
      <span style="flex:1 1 auto"></span>
      <a mat-button routerLink="/empresas">Empresas</a>
      <a mat-button routerLink="/fornecedores">Fornecedores</a>
    </mat-toolbar>
    <router-outlet></router-outlet>
  `
})
export class AppComponent {}
