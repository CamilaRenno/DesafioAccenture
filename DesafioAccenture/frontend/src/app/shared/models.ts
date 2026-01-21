export interface Empresa {
  id?: number;
  cnpj: string;
  nomeFantasia: string;
  cep: string;
  fornecedoresIds?: number[];
}

export type DocumentoTipo = 'CPF' | 'CNPJ';

export interface Fornecedor {
  id?: number;
  documento: string;
  tipo: DocumentoTipo;
  nome: string;
  email: string;
  cep: string;
  rg?: string;
  dataNascimento?: string; // ISO string (yyyy-MM-dd)
  empresasIds?: number[];
}
