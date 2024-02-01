package br.com.ecommerce.consumers;

import br.com.ecommerce.consumers.contract.AbstractKafkaConsumer;

import java.util.List;

public class EntryPointService {

    private static final EntryPointService INSTANCE = new EntryPointService();

    private final List<AbstractKafkaConsumer> consumers;

    private EntryPointService() {
        this.consumers = List.of(
                new EmailConsumer(),
                new FraudDetectorConsumer(),
                new LogConsumer()
        );
    }

    public static EntryPointService getInstance() {
        return INSTANCE;
    }

    public void start() {
        consumers.forEach(AbstractKafkaConsumer::start);
    }

    public void stop() {
        consumers.forEach(AbstractKafkaConsumer::interrupt);
    }
}
