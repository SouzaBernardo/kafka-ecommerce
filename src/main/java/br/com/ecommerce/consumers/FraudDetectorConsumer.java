package br.com.ecommerce.consumers;

import br.com.ecommerce.Main;
import br.com.ecommerce.consumers.contract.AbstractKafkaConsumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static br.com.ecommerce.config.KafkaProperties.ECOMMERCE_NEW_ORDER_TOPIC;
import static br.com.ecommerce.config.KafkaProperties.consumerProperties;

public class FraudDetectorConsumer extends AbstractKafkaConsumer {

    protected FraudDetectorConsumer() {
        super(new KafkaConsumer<>(consumerProperties(FraudDetectorConsumer.class)), ECOMMERCE_NEW_ORDER_TOPIC);
    }

    public void exec() {
        Main.sleep();
        Main.sleep();
        consumeRecords();
    }
}
