package br.com.ecommerce.application.service;

import br.com.ecommerce.Main;
import br.com.ecommerce.application.service.contract.AbstractKafkaConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static br.com.ecommerce.core.config.KafkaProperties.consumerProperties;

public class FraudDetectorService extends AbstractKafkaConsumer {
    public static final String ECOMMERCE_NEW_ORDER = "ECOMMERCE_NEW_ORDER";

    public void exec() {
        var consumer = new KafkaConsumer<String, String>(consumerProperties(FraudDetectorService.class));
        Main.sleep();
        Main.sleep();
        consumeRecords(consumer, ECOMMERCE_NEW_ORDER);
    }
}
