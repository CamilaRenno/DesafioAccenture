import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ApiService } from '../core/api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { cepAsyncValidator } from '../core/cep.validator';

@Component({
  selector: 'app-empresa-form',
  templateUrl: './empresa-form.component.html'
})
export class EmpresaFormComponent implements OnInit {
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
      cnpj: ['', Validators.required],
      nomeFantasia: ['', Validators.required],
      cep: ['', {
        validators: [Validators.required],
        asyncValidators: [cepAsyncValidator(this.http)],
        updateOn: 'blur'
      }]
    });

    this.id = +this.route.snapshot.paramMap.get('id')!;
    if (this.id) {
      this.api.getEmpresa(this.id).subscribe(e => this.form.patchValue(e));
    }
  }

  salvar(): void {
    if (this.form.invalid) return;
    const empresa = this.form.value;
    if (this.id) {
      this.api.updateEmpresa(this.id, empresa).subscribe(() => this.router.navigate(['/empresas']));
    } else {
      this.api.createEmpresa(empresa).subscribe(() => this.router.navigate(['/empresas']));
    }
  }
}
