# Sistema de Fila de Atendimento

## 1. Sobre o projeto

O **Sistema de Fila de Atendimento** é uma aplicação desenvolvida em Java 21, executada pelo terminal, com o objetivo de organizar o atendimento de pessoas por meio da emissão e do gerenciamento de senhas.

O sistema trabalha com duas modalidades de atendimento: **comum** e **preferencial**. As senhas preferenciais possuem prioridade sobre as comuns, respeitando a ordem de chegada dentro de cada categoria.

O projeto foi desenvolvido utilizando o padrão de projeto **Singleton**, aplicado ao gerador de senhas, garantindo que exista uma única instância responsável pelo controle da numeração.

## 2. Principais funcionalidades

- Emissão de senhas comuns e preferenciais.
- Numeração sequencial independente para cada tipo de senha.
- Prioridade de atendimento para senhas preferenciais.
- Organização das filas respeitando a ordem de chegada.
- Chamada da próxima senha para atendimento.
- Consulta das senhas que aguardam atendimento.
- Registro do histórico de atendimentos, incluindo horários de emissão e atendimento.
- Armazenamento do histórico em arquivo CSV durante a execução do sistema.

**Observação:** o histórico é reiniciado quando a aplicação é iniciada novamente.

## 3. Tecnologias utilizadas

- **Java 21:** linguagem de programação.
- **Maven:** gerenciamento de dependências e construção do projeto.
- **JUnit 5:** execução dos testes automatizados.
- **CSV:** armazenamento dos registros de atendimento.

## 4. Estrutura do projeto

O projeto foi organizado em pacotes, separando as responsabilidades de cada parte da aplicação.

| Pacote | Responsabilidade |
|---|---|
| `cli` | Interface de interação com o usuário e apresentação do menu. |
| `model` | Representação das entidades do sistema, como senha e atendimento. |
| `service` | Regras de negócio, geração de senhas e gerenciamento das filas. |
| `repository` | Contratos e implementação do armazenamento do histórico. |

Principais classes e interfaces:

- `App`: ponto de entrada da aplicação.
- `MenuAtendimento`: apresenta as opções e recebe as interações do usuário.
- `Senha`: representa uma senha emitida.
- `TipoSenha`: identifica a modalidade comum ou preferencial.
- `Atendimento`: representa um atendimento realizado.
- `GeradorDeSenhas`: interface para a geração de senhas.
- `GeradorDeSenhasImpl`: implementação responsável pela geração e numeração das senhas.
- `FilaAtendimento`: gerencia as filas e a ordem de atendimento.
- `HistoricoRepository`: contrato para armazenamento do histórico.
- `HistoricoArquivoRepository`: implementação responsável pelo registro em arquivo.

## 5. Padrão de projeto: Singleton

O padrão **Singleton** foi escolhido para controlar a criação e o acesso à instância responsável pela geração das senhas.

No sistema, a classe `GeradorDeSenhasImpl` utiliza esse padrão para garantir que exista apenas um gerador compartilhado durante a execução da aplicação.

Essa escolha é importante porque a numeração das senhas precisa ser centralizada. Caso diferentes partes do sistema criassem seus próprios geradores, poderiam existir contadores independentes, ocasionando números repetidos ou inconsistências na identificação das senhas.

A implementação utiliza o recurso *Holder*, por meio de uma classe interna estática, para disponibilizar a instância única sob demanda. Os contadores são mantidos separadamente por tipo de senha e utilizam `AtomicInteger` para controlar a numeração.

### Por que o Singleton é adequado?

- Garante uma única instância do gerador.
- Centraliza o controle da numeração das senhas.
- Evita a criação de geradores independentes.
- Permite que diferentes partes do sistema compartilhem o mesmo controle.
- Simplifica o gerenciamento de um recurso que precisa ser único durante a execução.

### Outros padrões que poderiam ser utilizados

Embora o Singleton seja adequado à necessidade de centralizar a geração de senhas, outros padrões também poderiam participar de uma solução semelhante:

- **Factory Method:** poderia ser utilizado para centralizar a criação de senhas, permitindo definir o tipo de objeto criado de acordo com a modalidade de atendimento.
- **Strategy:** poderia permitir diferentes estratégias de organização ou priorização das filas, caso o sistema passasse a oferecer regras de atendimento configuráveis.
- **Dependency Injection:** poderia ser utilizada para fornecer as dependências do sistema, como o gerador de senhas e o repositório de histórico, sem que as classes precisassem criá-las diretamente.

Esses padrões atendem a necessidades diferentes. Para o requisito específico de manter um único gerador e uma numeração centralizada, o Singleton foi considerado a escolha mais direta e adequada.

## 6. Aplicação dos princípios SOLID

Os princípios SOLID foram considerados na organização das classes e interfaces, buscando melhorar a separação de responsabilidades, a manutenção e a possibilidade de evolução do sistema.

### S — Single Responsibility Principle (Responsabilidade Única)

Cada classe possui uma responsabilidade específica:

- `MenuAtendimento`: interação com o usuário.
- `FilaAtendimento`: gerenciamento das filas.
- `GeradorDeSenhasImpl`: geração e controle da numeração das senhas.
- `HistoricoArquivoRepository`: armazenamento do histórico.
- `Atendimento`: representação dos dados de um atendimento.

### O — Open/Closed Principle (Aberto/Fechado)

A interface `HistoricoRepository` permite que novas formas de armazenamento sejam implementadas sem exigir alterações nas classes que dependem desse contrato.

Por exemplo, seria possível adicionar futuramente um repositório que armazenasse o histórico em banco de dados, mantendo a interface existente.

### L — Liskov Substitution Principle (Substituição de Liskov)

A implementação `HistoricoArquivoRepository` pode ser utilizada no lugar da abstração `HistoricoRepository`, respeitando o contrato definido pela interface.

Isso permite que outras implementações do repositório sejam utilizadas sem comprometer as classes que dependem dessa abstração.

### I — Interface Segregation Principle (Segregação de Interfaces)

O projeto utiliza interfaces com responsabilidades específicas, como `GeradorDeSenhas` e `HistoricoRepository`.

Dessa forma, as classes dependem apenas das operações necessárias para suas respectivas funções.

### D — Dependency Inversion Principle (Inversão de Dependência)

A classe `MenuAtendimento` recebe dependências por meio de abstrações, como `GeradorDeSenhas` e `HistoricoRepository`, em vez de depender diretamente das implementações concretas.

Isso reduz o acoplamento e facilita futuras substituições de implementação.

## 7. Como executar o projeto

### Pré-requisitos

- Java JDK 21 ou superior.
- Maven instalado.
- Git (opcional, para clonar o repositório).

### Clonar o repositório

```bash
git clone https://github.com/Murilo11/fila-atendimento.git
```

### Acessar a pasta do projeto

```bash
cd fila-atendimento
```

### Executar os testes

```bash
mvn test
```

Para executar a aplicação, abra o projeto em uma IDE compatível com Java e execute a classe `br.edu.projeto.App`.

## 8. Testes automatizados

O projeto utiliza JUnit 5 para verificar o funcionamento das principais funcionalidades e regras de negócio.

Os testes contemplam:

- Geração e numeração das senhas.
- Gerenciamento e prioridade das filas.
- Registro do histórico de atendimentos.
- Representação e formatação das senhas.
- Comportamento das entidades do sistema.

**Resultado dos testes:** 15 testes executados, todos aprovados.

---

## 9. Equipe

Projeto desenvolvido como atividade acadêmica, com aplicação de padrões de projeto e princípios SOLID utilizando Java.