package br.com.ecommerce.service;

import br.com.ecommerce.service.contract.KafkaConsumerInterface;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;

import static br.com.ecommerce.Main.ANY;
import static br.com.ecommerce.Main.threadStarting;
import static br.com.ecommerce.config.KafkaProperties.consumerProperties;
import static java.util.regex.Pattern.compile;


public class LogService extends Thread implements KafkaConsumerInterface {

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
            System.out.println("ERROR with kafka consumer");
        }
    }
}
