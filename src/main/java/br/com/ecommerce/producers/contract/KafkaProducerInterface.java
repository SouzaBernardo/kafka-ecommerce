package br.com.ecommerce.producers.contract;

public interface KafkaProducerInterface<T> {

    void send(String key, T value);
}
