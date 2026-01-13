<div align="center">
  
  [![Português](https://img.shields.io/badge/Português-green)](README-pt-br.md)
  [![English](https://img.shields.io/badge/English-blue)](README.md)

</div>

# AIM Client - Implementação do Protocolo TOC2

Projeto de estudo e implementação de um cliente de chat baseado na engenharia reversa do protocolo TOC2 da AOL (AIM).

O foco deste projeto é demonstrar domínio sobre redes de baixo nível, manipulação de dados binários e modernização de código legado.

## Destaques Técnicos

* **Networking Low-Level:** Implementação de comunicação via TCP/IP utilizando apenas `java.net.Socket` e streams, sem frameworks de alto nível.
* **Protocolo Binário & Texto:** Tratamento manual de framing binário (SFLAP) e parsing de comandos de texto do protocolo.
* **Arquitetura Limpa:** Refatoração completa de padrões antigos (era Java 1.4) para o Java 21 moderno, separando camadas de Conexão, Mensageria e UI.
* **Concorrência:** Uso de Threads e Lambdas para processamento assíncrono de mensagens recebidas.
* **Testes Automatizados:** Cobertura de testes unitários com JUnit 5 para validação de criptografia ("roasting"), framing de pacotes e parsing de comandos.

## Funcionalidades

* **Login Seguro:** Implementação do algoritmo de criptografia proprietário "Roasting".
* **Mensagens Instantâneas:** Envio e recebimento de Mensagens Diretas (IM).
* **Social:** Gerenciamento de Salas de Chat e Lista de Amigos.

## Tecnologias

* **Java 21**
* **JUnit 5**
* **Java Sockets (java.net)**
* **Maven**

## Como Rodar

1. Clone o repositório:
```bash
git clone [https://github.com/cgmarquess/aim-client.git](https://github.com/cgmarquess/aim-client.git)
```
2. Entre na pasta do projeto:
```bash
cd aim-client
```
3. Rode os testes para validar a lógica do protocolo:
```bash
mvn test
```
4. Execute a aplicação:
```bash
mvn exec:java
```

Desenvolvido por [Gabriel Marques] - [[LinkedIn](https://www.linkedin.com/in/cgmarquess/)]
