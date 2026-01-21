import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ApiService } from '../core/api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { cepAsyncValidator } from '../core/cep.validator';
import { isCpf, isCnpj } from '../core/cpf-cnpj.validator';

@Component({
  selector: 'app-fornecedor-form',
  templateUrl: './fornecedor-form.component.html'
})
export class FornecedorFormComponent implements OnInit {
  form!: FormGroup;
  id?: number;

  constructor(
    private fb: FormBuilder,
    private api: ApiService,
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      documento: ['', Validators.required],
      tipo: ['CPF', Validators.required],
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      cep: ['', {
        validators: [Validators.required],
        asyncValidators: [cepAsyncValidator(this.http)],
        updateOn: 'blur'
      }],
      rg: [''],
      dataNascimento: ['']
    });

    this.id = +this.route.snapshot.paramMap.get('id')!;
    if (this.id) {
      this.api.getFornecedor(this.id).subscribe(f => this.form.patchValue(f));
    }
  }

  salvar(): void {
    if (this.form.invalid) return;
    const f = this.form.value;

    // validação extra CPF/CNPJ
    if (f.tipo === 'CPF' && !isCpf(f.documento)) { alert('CPF inválido'); return; }
    if (f.tipo === 'CNPJ' && !isCnpj(f.documento)) { alert('CNPJ inválido'); return; }

    if (this.id) {
      this.api.updateFornecedor(this.id, f).subscribe(() => this.router.navigate(['/fornecedores']));
    } else {
      this.api.createFornecedor(f).subscribe(() => this.router.navigate(['/fornecedores']));
    }
  }
}
