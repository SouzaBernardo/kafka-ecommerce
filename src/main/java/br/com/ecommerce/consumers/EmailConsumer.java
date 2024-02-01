package br.com.ecommerce.consumers;

import br.com.ecommerce.consumers.contract.AbstractKafkaConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static br.com.ecommerce.config.KafkaProperties.consumerProperties;


public class EmailConsumer extends AbstractKafkaConsumer {
    public static final String ECOMMERCE_SEND_EMAIL = "ECOMMERCE_SEND_EMAIL";

    public void exec() {
        var consumer = new KafkaConsumer<String, String>(consumerProperties(EmailConsumer.class));
        consumeRecords(consumer, ECOMMERCE_SEND_EMAIL);
    }
}
