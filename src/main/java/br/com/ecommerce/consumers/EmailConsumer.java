package br.com.ecommerce.consumers;

import br.com.ecommerce.consumers.contract.AbstractKafkaConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static br.com.ecommerce.config.KafkaProperties.ECOMMERCE_SEND_EMAIL_TOPIC;
import static br.com.ecommerce.config.KafkaProperties.consumerProperties;


public class EmailConsumer extends AbstractKafkaConsumer {

    public EmailConsumer() {
        super(new KafkaConsumer<>(consumerProperties(EmailConsumer.class)), ECOMMERCE_SEND_EMAIL_TOPIC);
    }

    public void exec() {
        consumeRecords();
    }
}
