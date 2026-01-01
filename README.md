# AIM Client - Implementação do Protocolo TOC2

Projeto de estudo e implementação de um cliente de chat baseado na engenharia reversa do protocolo TOC2 da AOL (AIM). 

**Destaques Técnicos:**
* Networking Low-Level: Implementação de comunicação via TCP/IP utilizando apenas `java.net.Socket` e streams, sem frameworks de alto nível.
* Protocolo Binário & Texto: Tratamento de framing binário (SFLAP) e parsing de comandos de texto do protocolo.
* Arquitetura Limpa: Refatoração completa de código legado (Java 1.4) para padrões modernos (Java 17+), separando camadas de Conexão, Mensageria e UI.
* Concorrência: Uso de Threads e Lambdas para processamento assíncrono de mensagens recebidas.
* Testes Automatizados: Cobertura de testes unitários com JUnit 5 para validação de criptografia ("roasting"), framing de pacotes e parsing de comandos.

**Funcionalidades:**
* Login com criptografia proprietária (Roasting).
* Envio e recebimento de Mensagens Diretas (IM).
* Gerenciamento de Salas de Chat e Lista de Amigos.
