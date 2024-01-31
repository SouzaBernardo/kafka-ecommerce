package br.com.ecommerce.application.service.contract;

import br.com.ecommerce.Main;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static java.time.Duration.ofMinutes;
import static java.util.regex.Pattern.compile;

public abstract class AbstractKafkaConsumer extends Thread implements KafkaConsumerInterface {

    public abstract void exec();
    public void run() {
        try {
           exec();
        } catch (Exception ex) {
            this.interrupt();
        }
    }

    protected void consumeRecords(KafkaConsumer<String, String> consumer, String topic) {
        consumer.subscribe(compile(topic));
        while (this.isAlive()) {
            var records = consumer.poll(ofMinutes(10));
            if (!records.isEmpty()) {
                records.forEach(record -> {
                    System.out.println("-------------------------------------");
                    System.out.println("SUCCESS TO GET MESSAGE:");
                    System.out.println(">>> Found record :: " + this.getClass().getSimpleName());
                    System.out.printf(">>> %s:::%s/%s/%s%n", record.topic(), record.partition(), record.offset(), record.timestamp());
                    Main.sleep();
                });
            }
        }
    }

    public void start() {
        System.out.println("-----------------------------");
        System.out.println("Starting a thread ::: " + this.getClass().getSimpleName());
        System.out.println("-----------------------------");
        super.start();
    }

    public void interrupt() {
        System.out.println("-----------------------------");
        System.out.println("Interrupt a thread ::: " + this.getClass().getSimpleName());
        System.out.println("-----------------------------");
        super.interrupt();
    }
}
