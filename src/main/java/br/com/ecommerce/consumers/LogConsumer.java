package br.com.ecommerce.consumers;

import br.com.ecommerce.consumers.contract.AbstractKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Map;

import static br.com.ecommerce.config.KafkaProperties.ANY_ECOMMERCE_TOPIC;
import static br.com.ecommerce.config.KafkaProperties.consumerProperties;


public class LogConsumer extends AbstractKafkaConsumer<String> {

    protected LogConsumer() {
        super(new KafkaConsumer<>(consumerProperties(String.class,
                LogConsumer.class,
                Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()))),
                ANY_ECOMMERCE_TOPIC);
    }

    public void exec() {
        consumeRecords();
    }
}
