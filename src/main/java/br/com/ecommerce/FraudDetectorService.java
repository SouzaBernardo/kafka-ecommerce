package br.com.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static br.com.ecommerce.NewOrder.ECOMMERCE_NEW_ORDER;

public class FraudDetectorService {
    public static void main(String[] args) {
        var consumer = new KafkaConsumer<String, String>(properties());
        consumer.subscribe(Collections.singletonList(ECOMMERCE_NEW_ORDER));
        boolean b = true;
        while(b){
            var records = consumer.poll(Duration.ofMillis(100));
            if (!records.isEmpty()){
                System.out.println("Found records");
                records.forEach(record -> {
                    System.out.println("-------------------------------------");
                    System.out.println("Processing new order, checking for fraud");
                    System.out.println(record.key());
                    System.out.println(record.value());
                    sleep(5000);
                    System.out.println("Order processed");
                });
            }
        }
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties properties() {
        var properties = new Properties();
        String host = "localhost:9092";
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, FraudDetectorService.class.getName());
        return properties;
    }
}
