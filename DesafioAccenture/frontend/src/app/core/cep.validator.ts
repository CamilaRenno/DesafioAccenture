import { AbstractControl, AsyncValidatorFn, ValidationErrors } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { map, catchError, of, debounceTime, switchMap } from 'rxjs';

export function cepAsyncValidator(http: HttpClient): AsyncValidatorFn {
  return (control: AbstractControl) => {
    const cep = (control.value || '').replace(/\D/g, '');
    if (!cep || cep.length < 8) return of(null);
    return of(cep).pipe(
      debounceTime(300),
      switchMap(c => http.get<any>(`https://cep.la/api/${c}`, { headers: { Accept: 'application/json' } })),
      map(resp => (resp && resp.uf ? null : { cepInvalido: true })),
      catchError(() => of({ cepInvalido: true }))
    );
  };
}
