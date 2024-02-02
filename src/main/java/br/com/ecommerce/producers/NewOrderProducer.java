package br.com.ecommerce.producers;

import br.com.ecommerce.producers.contract.AbstractKafkaProducer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.Closeable;
import java.util.UUID;

import static br.com.ecommerce.config.KafkaProperties.ECOMMERCE_NEW_ORDER_TOPIC;
import static br.com.ecommerce.config.KafkaProperties.ECOMMERCE_SEND_EMAIL_TOPIC;
import static br.com.ecommerce.config.KafkaProperties.producerProperties;

public class NewOrderProducer extends AbstractKafkaProducer {

    public NewOrderProducer() {
        super(new KafkaProducer<>(producerProperties()));
    }

    public void send(String orderValue, String emailValue) {
        var callback = getCallback();
        var id = UUID.randomUUID().toString();
        var orderRecord = new ProducerRecord<>(ECOMMERCE_NEW_ORDER_TOPIC, orderValue + id, orderValue);
        var emailRecord = new ProducerRecord<>(ECOMMERCE_SEND_EMAIL_TOPIC, emailValue + id, emailValue);

        try {
            producer.send(orderRecord, callback).get();
            producer.send(emailRecord, callback).get();
        } catch (Exception e) {
            System.out.println("ERROR on SEND Message >>>> " + e.getMessage());
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
