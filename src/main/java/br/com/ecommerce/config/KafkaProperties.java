package br.com.ecommerce.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class KafkaProperties {
    public static final String HOST = "localhost:9092";
    public static final String MAX_POLL_RECORDS = "1";
    public static final String ECOMMERCE_SEND_EMAIL_TOPIC = "ECOMMERCE_SEND_EMAIL";
    public static final String ECOMMERCE_NEW_ORDER_TOPIC = "ECOMMERCE_NEW_ORDER";
    public static final String ANY_ECOMMERCE_TOPIC = "ECOMMERCE.*";


    public static Properties consumerProperties(Class<?> type, Class<?> rootClass) {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer.class.getName());
        properties.setProperty(GsonDeserializer.TYPE_CONFIG, type.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, rootClass.getName());
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, rootClass.getName() + UUID.randomUUID());
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, MAX_POLL_RECORDS);
        return properties;
    }

    public static Properties consumerProperties(Class<?> type, Class<?> rootClass, Map<String, String> extraProperties) {
        var properties = consumerProperties(type, rootClass);
        properties.putAll(extraProperties);
        return properties;
    }
    public static Properties producerProperties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
        return properties;
    }
}
