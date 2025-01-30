# Sistema de Agendamento de Transferências

Este é um projeto de backend para um sistema de agendamento de transferências financeiras. O projeto foi desenvolvido utilizando **Spring Boot** e uma base de dados em memória **H2**.

## Decisões Arquiteturais

1. **Spring Boot**:
    - O Spring Boot foi escolhido como framework para a construção do backend devido à sua capacidade de simplificar a configuração e a inicialização de uma aplicação Java.
    - Ele também oferece uma boa integração com outras ferramentas do ecossistema Spring, como **Spring Data JPA** para persistência de dados e **Spring Web** para a criação de APIs RESTful.

2. **Banco de Dados H2**:
    - O **H2** foi escolhido como banco de dados em memória por ser leve e fácil de configurar, sendo ideal para este projeto que não requer persistência em longo prazo.
    - O banco é utilizado para armazenar os agendamentos de transferências financeiras.

3. **Java 11**:
    - A versão 11 do **Java** foi escolhida por ser a versão LTS (Long-Term Support), garantindo estabilidade e segurança.

4. **Arquitetura**:
    - O backend segue uma **arquitetura de camadas**:
        - **Controller Layer**: Responsável pela comunicação com o frontend via REST API.
        - **Service Layer**: Contém a lógica de negócios, como o cálculo de taxas de transferência e validações.
        - **Repository Layer**: Gerencia as operações de persistência de dados utilizando Spring Data JPA.

5. **Padrões de Design**:
    - **DTO (Data Transfer Object)** foi utilizado para transferir dados entre o frontend e o backend.
    - **Exceções Personalizadas** foram implementadas para tratar erros específicos, como **TaxaInvalidaException**, para garantir uma resposta clara e padronizada para o frontend.


---

## Tecnologias Utilizadas

- **Java 11**: Linguagem de programação.
- **Spring Boot**: Framework utilizado para desenvolver a API RESTful.
- **Spring Data JPA**: Para persistência de dados no banco H2.
- **H2 Database**: Banco de dados em memória.
- **Maven**: Gerenciador de dependências e build system.
- **JUnit 5**: Para testes unitários.
- **Jacoco**: Para cobertura de testes unitários.

---

## Versões

- **Java**: 11
- **Spring Boot**: 2.7.x
- **Spring Data JPA**: 2.7.x
- **H2 Database**: 2.1.x
- **Junit**: 5.10.0
- **Mockito**: 5.5.0
- **jacoco**: 0.8.7
---

## Instruções para Subir o Projeto

### Requisitos

- **Java 11** instalado na sua máquina.
- **Maven** para gerenciar dependências e construir o projeto.

### 1. Clonar o Repositório

Clone este repositório para sua máquina local:

```bash
git clone https://github.com/artBaycos/schedule-transfer
cd schedule-transfer
