package br.com.ecommerce.producers.contract;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.Closeable;

public abstract class AbstractKafkaProducer implements KafkaProducerInterface, Closeable {

     protected final KafkaProducer<String, String> producer;

     protected AbstractKafkaProducer(KafkaProducer<String, String> producer) {
          this.producer = producer;
     }

     public abstract void send(String key, String value);

     @Override
     public void close() {
          this.producer.close();
     }
}
