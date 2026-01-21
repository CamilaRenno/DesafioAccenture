import { Component, OnInit } from '@angular/core';
import { ApiService } from '../core/api.service';
import { Empresa, Fornecedor } from '../shared/models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-empresa-list',
  templateUrl: './empresa-list.component.html'
})
export class EmpresaListComponent implements OnInit {
  empresas: Empresa[] = [];
  fornecedores: Fornecedor[] = [];
  selectedEmpresa?: Empresa;
  selectedFornecedorId?: number;

  constructor(private api: ApiService, private router: Router) {}

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.api.listEmpresas().subscribe(e => this.empresas = e);
    this.api.searchFornecedores().subscribe(f => this.fornecedores = f);
  }

  novo(): void { this.router.navigate(['/empresas/new']); }
  editar(e: Empresa): void { this.router.navigate(['/empresas', e.id]); }
  excluir(e: Empresa): void {
    if (confirm('Excluir empresa?')) {
      this.api.deleteEmpresa(e.id!).subscribe(() => this.load());
    }
  }

  selecionar(e: Empresa): void { this.selectedEmpresa = e; }
  vincular(): void {
    if (!this.selectedEmpresa || !this.selectedFornecedorId) return;
    this.api.vincular(this.selectedEmpresa.id!, this.selectedFornecedorId).subscribe(() => this.load());
  }
}
