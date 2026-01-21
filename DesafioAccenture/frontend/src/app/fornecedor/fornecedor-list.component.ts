import { Component, OnInit } from '@angular/core';
import { ApiService } from '../core/api.service';
import { Fornecedor } from '../shared/models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-fornecedor-list',
  templateUrl: './fornecedor-list.component.html'
})
export class FornecedorListComponent implements OnInit {
  fornecedores: Fornecedor[] = [];
  filtroNome = '';
  filtroDoc = '';

  constructor(private api: ApiService, private router: Router) {}

  ngOnInit(): void { this.load(); }

  load(): void {
    this.api.searchFornecedores(this.filtroNome, this.filtroDoc).subscribe(f => this.fornecedores = f);
  }

  novo(): void { this.router.navigate(['/fornecedores/new']); }
  editar(f: Fornecedor): void { this.router.navigate(['/fornecedores', f.id]); }
  excluir(f: Fornecedor): void {
    if (confirm('Excluir fornecedor?')) {
      this.api.deleteFornecedor(f.id!).subscribe(() => this.load());
    }
  }
}
