package br.com.ecommerce;

import br.com.ecommerce.core.order.NewOrder;
import br.com.ecommerce.infra.EntryPoint;

import java.util.List;
import java.util.Random;

public class Main {

    public static final int SLEEP_TIME = 5000;

    public static void main(String[] args) {

        var messages = List.of(
                "01,02,03,04",
                "05,06,07,08",
                "11,12,13,14",
                "15,16,17,18"
        );

        EntryPoint.getInstance().start();

        messages.forEach(order -> {
            sleep();
            NewOrder.sendMessage(order, "EMAIL VALUE");
            sleep();
        });

        EntryPoint.getInstance().stop();
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