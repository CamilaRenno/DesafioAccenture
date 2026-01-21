import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Empresa, Fornecedor } from '../shared/models';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private base = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  // Empresas
  listEmpresas(): Observable<Empresa[]> { return this.http.get<Empresa[]>(`${this.base}/empresas`); }
  getEmpresa(id: number): Observable<Empresa> { return this.http.get<Empresa>(`${this.base}/empresas/${id}`); }
  createEmpresa(e: Partial<Empresa>): Observable<Empresa> { return this.http.post<Empresa>(`${this.base}/empresas`, e); }
  updateEmpresa(id: number, e: Partial<Empresa>): Observable<Empresa> { return this.http.put<Empresa>(`${this.base}/empresas/${id}`, e); }
  deleteEmpresa(id: number): Observable<void> { return this.http.delete<void>(`${this.base}/empresas/${id}`); }
  vincular(empresaId: number, fornecedorId: number): Observable<Empresa> {
    return this.http.post<Empresa>(`${this.base}/empresas/${empresaId}/vincular/${fornecedorId}`, {});
  }
  desvincular(empresaId: number, fornecedorId: number): Observable<Empresa> {
    return this.http.post<Empresa>(`${this.base}/empresas/${empresaId}/desvincular/${fornecedorId}`, {});
  }

  // Fornecedores
  searchFornecedores(nome?: string, documento?: string): Observable<Fornecedor[]> {
    const params = new URLSearchParams();
    if (nome) params.append('nome', nome);
    if (documento) params.append('documento', documento);
    const qs = params.toString();
    return this.http.get<Fornecedor[]>(`${this.base}/fornecedores${qs ? '?' + qs : ''}`);
  }
  getFornecedor(id: number): Observable<Fornecedor> { return this.http.get<Fornecedor>(`${this.base}/fornecedores/${id}`); }
  createFornecedor(f: Partial<Fornecedor>): Observable<Fornecedor> { return this.http.post<Fornecedor>(`${this.base}/fornecedores`, f); }
  updateFornecedor(id: number, f: Partial<Fornecedor>): Observable<Fornecedor> { return this.http.put<Fornecedor>(`${this.base}/fornecedores/${id}`, f); }
  deleteFornecedor(id: number): Observable<void> { return this.http.delete<void>(`${this.base}/fornecedores/${id}`); }
}
