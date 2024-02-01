package br.com.ecommerce.producers;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.UUID;

import static br.com.ecommerce.consumers.EmailConsumer.ECOMMERCE_SEND_EMAIL;
import static br.com.ecommerce.consumers.FraudDetectorConsumer.ECOMMERCE_NEW_ORDER;
import static br.com.ecommerce.config.KafkaProperties.producerProperties;

public class NewOrderProducer {

    public static void sendMessage(String orderValue, String emailValue) {
        try (var producer = new KafkaProducer<String, String>(producerProperties())) {
            var callback = getCallback();
            var id = UUID.randomUUID().toString();
            var orderRecord = new ProducerRecord<>(ECOMMERCE_NEW_ORDER, orderValue + id, orderValue);
            var emailRecord = new ProducerRecord<>(ECOMMERCE_SEND_EMAIL, emailValue + id, emailValue);

            try {
                producer.send(orderRecord, callback).get();
                producer.send(emailRecord, callback).get();
            } catch (Exception e) {
                System.out.println("ERROR on SEND Message >>>> " + e.getMessage());
            }
        }
    }

    private static Callback getCallback() {
        return (data, ex) -> {
            if (ex != null) {
                return;
            }
            System.out.println("SUCCESS TO SEND MESSAGE:");
            System.out.printf(">>> %s:::%s/%s/%s%n", data.topic(), data.partition(), data.offset(), data.timestamp());
        };
    }
}
