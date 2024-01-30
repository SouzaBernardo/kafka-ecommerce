package br.com.ecommerce;

import br.com.ecommerce.core.order.NewOrder;
import br.com.ecommerce.infra.EntryPoint;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final int SLEEP_TIME = 5000;
    public static final String STOP = "c";
    public static int count = 0;

    public static void main(String[] args) {

        var messages = List.of(
                "01,02,03,04",
                "05,06,07,08",
                "11,12,13,14",
                "15,16,17,18"
        );

        var entrypoint = EntryPoint.listening();

        messages.forEach(order -> {
            sleep();
            NewOrder.sendMessage(order, "[Email content 0" + count++ + "]");
            sleep();
        });

        entrypoint.stop();
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

    public static void threadStarting(Class<?> javaClass) {
        System.out.println("-----------------------------");
        System.out.println("Starting a thread ::: " + javaClass.getName());
        System.out.println("-----------------------------");
    }
}