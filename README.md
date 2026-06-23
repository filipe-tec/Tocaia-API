# Tocaia API

API RESTful desenvolvida para gestão de ocorrências de segurança pública, focada em justiça e bem-estar social.

## 🚀 Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 4.0.6**
* **Spring Security (JWT)**
* **H2 Database (In-Memory)**
* **Hibernate/JPA**
* **Flyway Migration**

## 🛠️ Funcionalidades
* **Autenticação:** Sistema de login seguro com emissão de tokens JWT.
* **Segurança Baseada em Perfis:** Diferenciação entre `ROLE_POLICIAL` (gestão de ocorrências) e `ROLE_CIDADAO` (registro de denúncias).
* **Registro de Ocorrências:** Usuários cidadãos podem registrar denúncias anonimizadas por CPF.
* **Regras de Negócio:** Bloqueio de baixa em ocorrências já resolvidas e restrição de acesso por perfil.
* **Tratamento de Erros:** API com respostas padronizadas no padrão RFC 7807 (Problem Details).

## 📋 Como Executar
1. Clone o repositório.
2. Certifique-se de ter o JDK 21 instalado.
3. Importe o projeto como um projeto **Maven** na sua IDE (IntelliJ ou Eclipse).
4. Execute a classe `TocaiaApiApplication`.
5. O sistema subirá automaticamente na porta `8080`.

## 🧪 Como Testar
O sistema injeta automaticamente dois usuários policiais ao iniciar:
* **CPF:** `11122233344` / **Senha:** `123456`
* **CPF:** `55566677788` / **Senha:** `123456`

### Roteiro no Postman
1. **Cadastro de Cidadão:** `POST /usuarios` -> `{"cpf": "99988877766", "senha": "senha_cidadao"}`
2. **Login:** `POST /login` -> Envie as credenciais e copie o token JWT gerado.
3. **Denunciar:** `POST /ocorrencias` -> Header `Authorization: Bearer <seu_token>` e body com os dados da ocorrência.
4. **Resolução (Apenas Policial):** `PUT /ocorrencias/{id}/resolucao` -> Requer Token de um dos Policiais injetados.
