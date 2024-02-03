package br.com.ecommerce.producers.contract;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.Closeable;

public abstract class AbstractKafkaProducer<T> implements KafkaProducerInterface<T>, Closeable {

     protected final KafkaProducer<String, T> producer;
     protected final String topic;

     protected AbstractKafkaProducer(KafkaProducer<String, T> producer, String topic) {
          this.producer = producer;
          this.topic = topic;
     }

     public abstract void send(String key, T value);

     @Override
     public void close() {
          this.producer.close();
     }
}
