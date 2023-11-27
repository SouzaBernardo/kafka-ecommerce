package br.com.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Properties;
import java.util.regex.Pattern;


public class LogService {

    public static final String ANY = "ECOMMERCE.*";

    public static void main(String[] args) {
        var consumer = new KafkaConsumer<String, String>(properties());
        consumer.subscribe(Pattern.compile("ECOMMERCE.*"));
        boolean b = true;
        while (b) {
            var records = consumer.poll(Duration.ofMillis(100));
            if (!records.isEmpty()) {
                System.out.println("Found records");
                records.forEach(record -> {
                    System.out.println("-------------------------------------");
                    System.out.println("Log ::: " + record.topic());
                    System.out.println(record.key());
                    System.out.println(record.value());
                    System.out.println(record.partition());
                    System.out.println(record.offset());
                });
            }
        }
    }

    private static Properties properties() {
        var properties = new Properties();
        String host = "localhost:9092";
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, LogService.class.getName());
        return properties;
    }
}
