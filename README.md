<div align="center">

  [![English](https://img.shields.io/badge/English-blue)](README.md)
  [![Português](https://img.shields.io/badge/Português-green)](README-pt-br.md)

</div>

# AIM Client - TOC2 Protocol Implementation

A chat client implementation based on the reverse engineering of AOL's legacy **TOC2 protocol**.

This project focuses on studying low-level networking, binary data handling, and modernizing legacy codebases.

## Technical Highlights

* **Low-Level Networking:** Implemented TCP/IP communication using raw `java.net.Socket` and I/O streams, bypassing high-level frameworks to understand the core of network programming.
* **Binary & Text Protocol:** Handled binary framing (SFLAP) and text-based command parsing manually.
* **Clean Architecture:** Refactored legacy patterns (Java 1.4 era) into modern Java 21 standards, clearly separating Connection, Messaging, and UI layers.
* **Concurrency:** Utilized Threads and Lambdas for asynchronous message processing.
* **Automated Testing:** Comprehensive JUnit 5 test suite covering proprietary encryption ("roasting"), packet framing, and command parsing.

## Features

* **Secure Login:** Implementation of the proprietary "Roasting" password encryption algorithm.
* **Instant Messaging:** Sending and receiving Direct Messages (IM).
* **Social Features:** Chat Room management and Buddy List synchronization.

## Tech Stack

* **Java 21**
* **JUnit 5**
* **Java Sockets (java.net)**
* **Maven**

Developed by [Gabriel Marques] - [LinkedIn](https://www.linkedin.com/in/cgmarquess/?locale=en_US)
