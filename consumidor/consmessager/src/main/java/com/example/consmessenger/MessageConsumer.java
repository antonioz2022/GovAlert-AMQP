package com.example.consmessenger;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MessageConsumer {
	
	public void receiveMessage(Message message) {
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        String routingKey = message.getMessageProperties().getReceivedRoutingKey();
        String timestamp = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(new Date());

        System.out.printf("[%s] %s : %s%n", timestamp, routingKey, body);
    }
}
