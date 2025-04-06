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
```
Durante a execução, o programa perguntará:

Etapa 1 - Escolha do tipo de usuário:
```bash
Escolha o tipo de usuário:
1 - Consumidor
2 - Auditoria
```

1 - Consumidor: será solicitado que você escolha quais mensagens quer receber:
```bash
Selecione os tópicos:
1 - Avisos Gerais do Governo do Recife
2 - Comunicados de Emergência
3 - Receber ambos os tipos
```

2 - Auditoria: você receberá todas as mensagens, independente do tópico.

Resultado esperado (exemplo):
```bash
[06/04/2025 - 14:21] avisos.gerais : Feriado municipal nesta segunda-feira.
```

📨 2. Enviar Mensagens com o Produtor (Python)
Pré-requisitos:

Python 3.x

Instalar dependência:
```bash
pip install pika
```

Passos:
```bash
cd produtor-py
python produtor.py
```

Durante a execução, o programa perguntará:

Etapa 1 - Escolha do tópico:
```bash
Escolha o tópico para enviar a mensagem:
1 - avisos.gerais
2 - avisos.emergencia
```

Etapa 2 - Digite a mensagem:
```bash
Digite a mensagem para enviar:
> Alerta de enchente no Bairro do Recife.
```

Resultado esperado:
```bash
[x] Mensagem enviada para o tópico 'avisos.emergencia'
```

🎯 Funcionalidades
✅ Suporte a múltiplos produtores
✅ Suporte a múltiplos consumidores simultâneos
✅ Auditoria recebe tudo (usando wildcard avisos.#)
✅ Fila exclusiva e temporária para cada instância de consumidor
✅ Configurável via terminal – sem interface gráfica necessária

🗂 Exemplo de Estrutura de Mensagem
```bash
[dd/MM/yyyy - HH:mm] nome_tópico : corpo_da_mensagem
```
Exemplo:
```bash
[06/04/2025 - 16:35] avisos.emergencia : Evacuação preventiva no bairro da Várzea.
```

📎 Observações Técnicas
O sistema utiliza o tipo de Exchange topic.

As filas dos consumidores são temporárias e autoexcluídas ao encerrar o programa.

A auditoria funciona como um consumidor especial com assinatura em avisos.#.

## Membros do grupo:

  - **Antônio Albuquerque** -[aaon@cesar.school](mailto:aaon@cesar.school)
  - **João Augusto** - 
  - **Julia Boto** - 
  - **Leonardo Mello** - [ljam2@cesar.school](mailto:ljam2@cesar.school)


