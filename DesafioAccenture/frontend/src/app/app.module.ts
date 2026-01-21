import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { EmpresaListComponent } from './empresa/empresa-list.component';
import { EmpresaFormComponent } from './empresa/empresa-form.component';
import { FornecedorListComponent } from './fornecedor/fornecedor-list.component';
import { FornecedorFormComponent } from './fornecedor/fornecedor-form.component';
import { MaterialModule } from './core/material.module';
import { ErrorInterceptor } from './core/error.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    EmpresaListComponent, EmpresaFormComponent,
    FornecedorListComponent, FornecedorFormComponent
  ],
  imports: [BrowserModule, ReactiveFormsModule, FormsModule, HttpClientModule, AppRoutingModule, MaterialModule],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }],
  bootstrap: [EmpresaListComponent,AppComponent]
})
export class AppModule {}
