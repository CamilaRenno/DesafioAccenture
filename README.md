# DesafioAccenture

A aplicação permite o cadastro e gerenciamento de empresas e fornecedores, seguindo as regras:
- Relacionamento N:N entre empresas e fornecedores
- CPF/CNPJ são únicos
- Validação do CEP através de API externa
- Regras específicas para fornecedores pessoa física no estado do Paraná


Tecnologias utilizadas:
* Back-end: 
   - Java 17
   - Spring Boot 3
   - Spring Data JPA
   - Bean Validation
   - OpenFeign (integração CEP)
   - JUnit 5 + Mockito

* Front-end
   - Angular 16+
   - Angular Material
   - Reactive Forms
   - Jasmine + Karma

* Banco de Dados
   - PostgreSQL 15
