# Solutis School Dev Trail 2024 - SQUAD 1 - Locadora de Automóveis

---

Neste repositório, você encontrará a aplicação desenvolvida pelo **SQUAD 1** para o **Desafio da Locadora**.

### Integrantes do SQUAD:

- **Danielle Sanches**
- **Bruno Gomes**
- **Fábio Macedo**

## Tecnologias Utilizadas

- Java 
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven


## Estrutura do Projeto
O projeto é organizado nas seguintes camadas:

1. **Entities**: Contém as classes que representam as entidades no banco de dados.
2. **Repository**: Contém as interfaces que estendem `JpaRepository` para realizar operações de persistência.
3. **Service**: Contém as classes de serviço responsáveis pela lógica de negócios.
4. **Controller**: Contém as classes responsáveis por gerenciar as requisições HTTP.
5. **DTO**: Este objeto carrega dados entre processos na aplicação, em especial entre a camada de controle (Controller) e outras camadas (como a de serviço ou persistência).


## Classes Implementadas

### 1. Cliente

A classe `Cliente` representa um cliente da locadora.

- **Atributos**:
  - `id`: Identificador único.
  - `nome`: Nome completo do cliente.
  - `dataNascimento`: Data de nascimento do cliente.
  - `cpf`: CPF do cliente.
  - `cnh`: Número da CNH do cliente.
  - `email`: Endereço de e-mail do cliente.

- **Funcionalidades**:
  - Cadastro de novos clientes.
  - Validação de e-mail para evitar duplicidade.

### 2. Carro

A classe `Carro` representa um veículo disponível para aluguel.

- **Atributos**:
  - `id`: Identificador único.
  - `fabricante`: Fabricante do veículo.
  - `modelo`: Modelo do veículo.
  - `categoria`: Categoria do veículo (ex: carro, SUV, caminhonete).
  - `acessorios`: Acessórios disponíveis no veículo.
  - `precoPorDia`: Preço de aluguel por dia.
  - `imagemUrl`: URL da imagem do veículo.

- **Funcionalidades**:
  - Listagem de veículos disponíveis.
  - Filtro por categoria e acessórios.

### 3. Aluguel

A classe `Aluguel` representa uma reserva de aluguel de veículo.

- **Atributos**:
  - `id`: Identificador único.
  - `cliente`: Referência ao cliente que realizou a reserva.
  - `veiculo`: Referência ao veículo reservado.
  - `dataInicio`: Data de início da reserva.
  - `dataFim`: Data de término da reserva.
  - `confirmado`: Indica se a reserva foi confirmada.

- **Funcionalidades**:
  - Criação de novas reservas.
  - Confirmação de reservas e bloqueio de datas para veículos reservados.

### 4. GET STARTED

 - Verifique as credenciais do seu próprio Banco de Dados local, insira-os no arquivo `.properties`
 - Após isso, rode a aplicação para o Hibernate criar as tabelas no seu Banco de Dados
 - Para popular o Banco de Dados, vá no arquivo `.properties` e altere a linha `spring.flyway.enabled=true` deixando seu valor como true
 - Após isso, rode a aplicação, e então o FlyWay irá popular seu Banco.
