package br.com.ecommerce.consumers.contract;

public interface KafkaConsumerInterface {
    void run();
    void exec();
    void start();
    void interrupt();
}
