package br.com.ecommerce.consumers;

import br.com.ecommerce.consumers.contract.AbstractKafkaConsumer;
import br.com.ecommerce.domain.Email;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static br.com.ecommerce.config.KafkaProperties.ECOMMERCE_SEND_EMAIL_TOPIC;
import static br.com.ecommerce.config.KafkaProperties.consumerProperties;


public class EmailConsumer extends AbstractKafkaConsumer<Email> {

    public EmailConsumer() {
        super(new KafkaConsumer<>(consumerProperties(Email.class, EmailConsumer.class)), ECOMMERCE_SEND_EMAIL_TOPIC);
    }

    public void exec() {
        consumeRecords();
    }
}
