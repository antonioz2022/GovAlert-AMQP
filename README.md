# GovAlert-AMQP

# 📢 Sistema de Alerta Governamental

Este projeto simula um sistema de comunicação oficial do Governo do Recife, permitindo o **envio e recebimento de mensagens públicas em tempo real** via RabbitMQ. Os usuários podem atuar como **produtores de alertas**, **consumidores (cidadãos)** ou **auditores (para fiscalização e transparência)**.

---

## 🧭 O que você pode fazer?

- 🔔 **Produtor** (Governo): Envia mensagens para tópicos como "avisos gerais" e "emergências".
- 👥 **Consumidor** (Cidadão): Recebe mensagens de interesse (avisos gerais, emergências ou ambos).
- 🕵️‍♂️ **Auditor**: Recebe **todas** as mensagens para fins de monitoramento.

---

## 💻 Tecnologias Utilizadas

| Componente  | Linguagem | Bibliotecas/Ferramentas |
|-------------|-----------|--------------------------|
| Produtor    | Python    | pika, ssl                |
| Consumidor  | Java      | Spring Boot, AMQP        |
| Middleware  | RabbitMQ  | CloudAMQP (AMQPS)        |

---

## 🚀 Como Usar o Sistema

### 📥 1. Executar o Consumidor/Auditor (Java)

> Recomendado executar primeiro, antes do produtor, para garantir que você verá as mensagens ao vivo.

**Pré-requisitos**:
- Java 17+
- Maven

**Passos**:

```bash
cd consumidor-java
./mvnw spring-boot:run

Durante a execução, o programa perguntará:

Etapa 1 - Escolha do tipo de usuário:
```bash
Escolha o tipo de usuário:
1 - Consumidor
2 - Auditoria
