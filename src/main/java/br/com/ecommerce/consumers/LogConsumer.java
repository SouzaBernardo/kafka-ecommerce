package br.com.ecommerce.consumers;

import br.com.ecommerce.consumers.contract.AbstractKafkaConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static br.com.ecommerce.config.KafkaProperties.consumerProperties;


public class LogConsumer extends AbstractKafkaConsumer {
    public static final String ANY = "ECOMMERCE.*";

    public void exec() {
        var consumer = new KafkaConsumer<String, String>(consumerProperties(LogConsumer.class));
        consumeRecords(consumer, ANY);
    }
}
