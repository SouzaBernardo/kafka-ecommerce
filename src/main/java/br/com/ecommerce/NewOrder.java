package br.com.ecommerce;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class NewOrder {

    public static final String ECOMMERCE_NEW_ORDER = "ECOMMERCE_NEW_ORDER";
    public static final String ECOMMERCE_SEND_EMAIL = "ECOMMERCE_SEND_EMAIL";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, String>(properties());
        String value = "14,13,11,12";
        Callback callback = (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.printf("%s:::%s/%s/%s%n", data.topic(), data.partition(), data.offset(), data.timestamp());
        };
        var record = new ProducerRecord<>(ECOMMERCE_NEW_ORDER, value, value);
        producer.send(record, callback).get();
        var email = "email ....................................... ";
        var emailRecord = new ProducerRecord<>(ECOMMERCE_SEND_EMAIL, email, email);
        producer.send(emailRecord, callback).get();
    }

    private static Properties properties() {
        var properties = new Properties();
        String host = "localhost:9092";
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
