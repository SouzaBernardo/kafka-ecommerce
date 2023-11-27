package br.com.ecommerce.service;

import br.com.ecommerce.Main;
import br.com.ecommerce.service.contract.KafkaConsumerInterface;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static br.com.ecommerce.Main.ECOMMERCE_SEND_EMAIL;
import static br.com.ecommerce.Main.threadStarting;
import static br.com.ecommerce.config.KafkaProperties.consumerProperties;
import static java.time.Duration.ofMillis;
import static java.util.Collections.singletonList;


public class EmailService extends Thread implements KafkaConsumerInterface {

    public EmailService() {
        threadStarting(EmailService.class);
        this.start();
    }

    public void run() {
        try (var consumer = new KafkaConsumer<>(consumerProperties(EmailService.class))) {
            consumer.subscribe(singletonList(ECOMMERCE_SEND_EMAIL));
            while (true) {

                if (!this.isAlive()) {
                    return;
                }

                var records = consumer.poll(ofMillis(100));
                if (!records.isEmpty()) {
                    System.out.println("Found records");
                    records.forEach(record -> {
                        System.out.println("-------------------------------------");
                        System.out.println("Sending email, checking for fraud");
                        System.out.println(record.key());
                        System.out.println(record.value());
                        Main.sleep();
                        System.out.println("Email sent");
                    });
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR with kafka consumer");
        }
    }
}
