package br.com.ecommerce.consumers;

import br.com.ecommerce.consumers.contract.AbstractKafkaConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static br.com.ecommerce.config.KafkaProperties.ANY_ECOMMERCE_TOPIC;
import static br.com.ecommerce.config.KafkaProperties.consumerProperties;


public class LogConsumer extends AbstractKafkaConsumer {

    protected LogConsumer() {
        super(new KafkaConsumer<>(consumerProperties(LogConsumer.class)), ANY_ECOMMERCE_TOPIC);
    }

    public void exec() {
        consumeRecords();
    }
}
