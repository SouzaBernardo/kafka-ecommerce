package br.com.ecommerce.producers;

import br.com.ecommerce.domain.Order;
import br.com.ecommerce.producers.contract.AbstractKafkaProducer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import static br.com.ecommerce.config.KafkaProperties.ECOMMERCE_NEW_ORDER_TOPIC;
import static br.com.ecommerce.config.KafkaProperties.producerProperties;

public class NewOrderProducer extends AbstractKafkaProducer<Order> {

    public NewOrderProducer() {
        super(new KafkaProducer<>(producerProperties()), ECOMMERCE_NEW_ORDER_TOPIC);
    }

    public void send(String orderId, Order order) {
        var callback = getCallback();
        var orderRecord = new ProducerRecord<>(topic, orderId, order);
        try {
            producer.send(orderRecord, callback).get();
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
