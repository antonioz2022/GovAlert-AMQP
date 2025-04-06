import pika
import ssl

url = "amqps://dqtrbpho:LNvje2lDMw4IU-Pvf6TFb-gbB2aw4tyU@jackal-01.rmq.cloudamqp.com/dqtrbpho"
params = pika.URLParameters(url)

context = ssl.SSLContext(ssl.PROTOCOL_TLS_CLIENT)
context.check_hostname = False
context.verify_mode = ssl.CERT_NONE
params.ssl_options = pika.SSLOptions(context)

connection = pika.BlockingConnection(params)
channel = connection.channel()

exchange_name = 'topic-exchange'
channel.exchange_declare(exchange=exchange_name, exchange_type='topic', durable=False, auto_delete=False)

print("Escolha o tópico para enviar a mensagem:")
print("1 - avisos.gerais")
print("2 - avisos.emergencia")
choice = input("Digite 1 ou 2: ")

if choice == '1':
    routing_key = 'avisos.gerais'
elif choice == '2':
    routing_key = 'avisos.emergencia'
else:
    print("Opção inválida. Encerrando.")
    connection.close()
    exit()

print(f"Você está enviando mensagens para o tópico '{routing_key}'. Digite 'exit' para sair.")

while True:
    message = input("Digite a mensagem para enviar: ")
    if message.lower() == 'exit':
        break

    channel.basic_publish(
        exchange=exchange_name,
        routing_key=routing_key,
        body=message
    )

    print(f"[x] Mensagem enviada para o tópico '{routing_key}'")

connection.close()
print("Conexão encerrada.")