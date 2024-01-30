package br.com.ecommerce.application.service;

import br.com.ecommerce.application.service.contract.KafkaConsumerInterface;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;

import static br.com.ecommerce.Main.threadStarting;
import static br.com.ecommerce.core.config.KafkaProperties.consumerProperties;
import static java.util.regex.Pattern.compile;


public class LogService extends Thread implements KafkaConsumerInterface {
    public static final String ANY = "ECOMMERCE.*";

    public LogService() {
        threadStarting(LogService.class);
        this.start();
    }

    public void run() {

        try (var consumer = new KafkaConsumer<String, String>(consumerProperties(LogService.class))) {
            consumer.subscribe(compile(ANY));
            while (true) {

                if (!this.isAlive()) {
                    return;
                }

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
        } catch (Exception ex) {
            System.out.println("CLOSING CONNECTION WITH KAFKA IN LOGSERVICE");
        }
    }
}
