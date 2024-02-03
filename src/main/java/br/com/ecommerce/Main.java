package br.com.ecommerce;

import br.com.ecommerce.consumers.EntryPointService;
import br.com.ecommerce.domain.Order;
import br.com.ecommerce.producers.EmailProducer;
import br.com.ecommerce.producers.NewOrderProducer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Main {

    public static final int SLEEP_TIME = 5000;

    public static void main(String[] args) {

        var messages = List.of(
                "01,02,03,04",
                "05,06,07,08",
                "11,12,13,14",
                "15,16,17,18"
        );

        EntryPointService.getInstance().start();
        var newOrderProducer = new NewOrderProducer();
        var emailProducer = new EmailProducer();
        messages.forEach(order -> {
            sleep();
            var userId = UUID.randomUUID().toString();
            var orderId = UUID.randomUUID().toString();
            var amount = BigDecimal.valueOf(Math.random() * 500 + 1);
            var order1 = new Order(userId, orderId, amount);
            newOrderProducer.send(order, order1);
            emailProducer.send(order, "[ SEND EMAIL FROM " + order + " ]");
            sleep();
        });

        EntryPointService.getInstance().stop();
    }

    public static void sleep() {
        try {
            Thread.sleep(getRandomNumber());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(SLEEP_TIME) + 1;
    }
}