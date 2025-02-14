# MS Business Application

## Descrição

A aplicação MS Business é um sistema de gerenciamento para produtos e pedidos. Esta aplicação permite o cadastro de clientes, produtos e a criação de pedidos. Além disso, a aplicação utiliza Kafka para o gerenciamento de mensagens entre serviços.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA**: Para interação com o banco de dados MySQL.
- **Spring Security**: Para autenticação e autorização.
- **Kafka**: Para mensageria e comunicação entre serviços.
- **Swagger (Springdoc OpenAPI)**: Para documentação da API.

## Requisitos

- Java 17
- MySQL (pode ser executado via Docker)
- Kafka e Zookeeper (pode ser executado via Docker)
- Maven

## Instalação

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/emersonoliveira01/ms-sankhya-order.git
   cd ms-sankhya-order

2. **Executar o MySQL, Kafka e Zookeeper usando Docker:**
   ```bash
   docker-compose up -d

3. **Executar a aplicação:**
   ```bash
   mvn spring-boot:run

4. **Documentação da API:**
A documentação da API é gerada automaticamente pelo Swagger. Você pode acessá-la através do seguinte URL:

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- API Docs: http://localhost:8080/v3/api-docs
