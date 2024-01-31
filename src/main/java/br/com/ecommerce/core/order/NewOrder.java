package br.com.ecommerce.core.order;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.UUID;

import static br.com.ecommerce.application.service.EmailService.ECOMMERCE_SEND_EMAIL;
import static br.com.ecommerce.application.service.FraudDetectorService.ECOMMERCE_NEW_ORDER;
import static br.com.ecommerce.core.config.KafkaProperties.producerProperties;

public class NewOrder {

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
                System.out.println(ex.getMessage());
                return;
            }
            System.out.printf("%s:::%s/%s/%s%n", data.topic(), data.partition(), data.offset(), data.timestamp());
        };
    }
}
