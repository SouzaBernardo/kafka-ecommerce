package br.com.ecommerce.producers.contract;

public interface KafkaProducerInterface {

    void send(String key, String value);
}
