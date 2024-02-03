package br.com.ecommerce.producers;

import br.com.ecommerce.producers.contract.AbstractKafkaProducer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import static br.com.ecommerce.config.KafkaProperties.ECOMMERCE_SEND_EMAIL_TOPIC;
import static br.com.ecommerce.config.KafkaProperties.producerProperties;

public class EmailProducer extends AbstractKafkaProducer<String> {

    public EmailProducer() {
        super(new KafkaProducer<>(producerProperties()), ECOMMERCE_SEND_EMAIL_TOPIC);
    }

    public void send(String emailId, String email) {
        var callback = getCallback();
        var orderRecord = new ProducerRecord<>(topic, emailId, email);
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
