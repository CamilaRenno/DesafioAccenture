-- Criação da tabela Empresa
CREATE TABLE empresas (
    id SERIAL PRIMARY KEY,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    nome_fantasia VARCHAR(255) NOT NULL,
    cep VARCHAR(9) NOT NULL
);

-- Criação da tabela Fornecedor
CREATE TABLE fornecedores (
    id SERIAL PRIMARY KEY,
    documento VARCHAR(18) NOT NULL UNIQUE, -- CPF ou CNPJ
    tipo VARCHAR(4) NOT NULL CHECK (tipo IN ('CPF','CNPJ')),
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    rg VARCHAR(20),
    data_nascimento DATE
);

-- Tabela de relacionamento N:N entre Empresa e Fornecedor
CREATE TABLE empresa_fornecedor (
    empresa_id INT NOT NULL,
    fornecedor_id INT NOT NULL,
    PRIMARY KEY (empresa_id, fornecedor_id),
    CONSTRAINT fk_empresa FOREIGN KEY (empresa_id) REFERENCES empresas(id) ON DELETE CASCADE,
    CONSTRAINT fk_fornecedor FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id) ON DELETE CASCADE
);

-- Índices adicionais
CREATE INDEX idx_empresas_cnpj ON empresas(cnpj);
CREATE INDEX idx_fornecedores_documento ON fornecedores(documento);
CREATE INDEX idx_fornecedores_nome ON fornecedores(nome);
