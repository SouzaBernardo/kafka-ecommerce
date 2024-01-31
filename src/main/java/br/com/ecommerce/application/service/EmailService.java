package br.com.ecommerce.application.service;

import br.com.ecommerce.application.service.contract.AbstractKafkaConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static br.com.ecommerce.core.config.KafkaProperties.consumerProperties;


public class EmailService extends AbstractKafkaConsumer {
    public static final String ECOMMERCE_SEND_EMAIL = "ECOMMERCE_SEND_EMAIL";

    public void exec() {
        var consumer = new KafkaConsumer<String, String>(consumerProperties(EmailService.class));
        consumeRecords(consumer, ECOMMERCE_SEND_EMAIL);
    }
}
