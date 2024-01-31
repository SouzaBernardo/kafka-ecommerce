package br.com.ecommerce.application.service;

import br.com.ecommerce.application.service.contract.AbstractKafkaConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static br.com.ecommerce.core.config.KafkaProperties.consumerProperties;


public class LogService extends AbstractKafkaConsumer {
    public static final String ANY = "ECOMMERCE.*";

    public void exec() {
        var consumer = new KafkaConsumer<String, String>(consumerProperties(LogService.class));
        consumeRecords(consumer, ANY);
    }
}
