import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmpresaListComponent } from './empresa/empresa-list.component';
import { EmpresaFormComponent } from './empresa/empresa-form.component';
import { FornecedorListComponent } from './fornecedor/fornecedor-list.component';
import { FornecedorFormComponent } from './fornecedor/fornecedor-form.component';

const routes: Routes = [
  { path: '', redirectTo: 'fornecedores', pathMatch: 'full' },
  { path: 'empresas', component: EmpresaListComponent },
  { path: 'empresas/new', component: EmpresaFormComponent },
  { path: 'empresas/:id', component: EmpresaFormComponent },
  { path: 'fornecedores', component: FornecedorListComponent },
  { path: 'fornecedores/new', component: FornecedorFormComponent },
  { path: 'fornecedores/:id', component: FornecedorFormComponent },
];

@NgModule({ imports: [RouterModule.forRoot(routes)], exports: [RouterModule] })
export class AppRoutingModule {}
