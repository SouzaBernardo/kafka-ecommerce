package br.com.ecommerce.infra;

import br.com.ecommerce.application.service.EmailService;
import br.com.ecommerce.application.service.FraudDetectorService;
import br.com.ecommerce.application.service.LogService;
import br.com.ecommerce.application.service.contract.AbstractKafkaConsumer;

import java.util.List;

public class EntryPoint {

    private static final EntryPoint INSTANCE = new EntryPoint();

    private final List<AbstractKafkaConsumer> consumers;

    private EntryPoint() {
        this.consumers = List.of(
                new EmailService(),
                new FraudDetectorService(),
                new LogService()
        );
    }

    public static EntryPoint getInstance() {
        return INSTANCE;
    }

    public void start() {
        consumers.forEach(AbstractKafkaConsumer::start);
    }

    public void stop() {
        consumers.forEach(AbstractKafkaConsumer::interrupt);
    }
}
