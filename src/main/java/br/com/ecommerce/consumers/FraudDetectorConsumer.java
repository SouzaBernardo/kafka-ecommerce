package br.com.ecommerce.consumers;

import br.com.ecommerce.Main;
import br.com.ecommerce.consumers.contract.AbstractKafkaConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static br.com.ecommerce.config.KafkaProperties.consumerProperties;

public class FraudDetectorConsumer extends AbstractKafkaConsumer {
    public static final String ECOMMERCE_NEW_ORDER = "ECOMMERCE_NEW_ORDER";

    public void exec() {
        var consumer = new KafkaConsumer<String, String>(consumerProperties(FraudDetectorConsumer.class));
        Main.sleep();
        Main.sleep();
        consumeRecords(consumer, ECOMMERCE_NEW_ORDER);
    }
}
