package com.example.consmessenger;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
public class ConsmessengerApplication {

    static final String exchangeName = "topic-exchange";

    @Bean
    public Queue queue() {
        String uniqueQueueName = "topic-fila-" + UUID.randomUUID();
        return new Queue(uniqueQueueName, false, false, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName, false, false);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha o tipo de usuário:");
        System.out.println("1 - Consumidor");
        System.out.println("2 - Auditoria");

        String tipo = scanner.nextLine();
        String bindingKey;

        if (tipo.equals("2")) {
            bindingKey = "avisos.#";
            System.out.println("Modo Auditor selecionado. Você receberá todas as mensagens enviadas");
        } else {
            System.out.println("Modo Consumidor selecionado.");
            System.out.println("Selecione os tópicos que deseja receber:");
            System.out.println("1 - Avisos Gerais do Governo do Recife");
            System.out.println("2 - Comunicados de Emergência");
            System.out.println("3 - Receber ambos os tipos de mensagens");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    bindingKey = "avisos.gerais";
                    System.out.println("Conectado ao canal de Avisos Gerais do Governo do Recife.");
                    break;
                case "2":
                    bindingKey = "avisos.emergencia";
                    System.out.println("Conectado ao canal de Comunicados de Emergência.");
                    break;
                case "3":
                    bindingKey = "avisos.*";
                    System.out.println("Conectado a todos os canais oficiais de comunicação do Governo do Recife.");
                    break;
                default:
                    bindingKey = "avisos.*";
                    System.out.println("Opção inválida. Conectando a todos os canais por padrão.");
            }
        }

        return BindingBuilder.bind(queue).to(exchange).with(bindingKey);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter,
                                             Queue queue) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue.getName());
        container.setMessageListener(listenerAdapter);
        return container;
    }


    @Bean
    MessageListenerAdapter listenerAdapter(MessageConsumer receiver) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(receiver);
        adapter.setDefaultListenerMethod("receiveMessage");
        adapter.setMessageConverter(null);
        return adapter;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsmessengerApplication.class, args);
    }
}
