package br.com.ecommerce;

import br.com.ecommerce.order.NewOrder;
import br.com.ecommerce.service.EmailService;
import br.com.ecommerce.service.FraudDetectorService;
import br.com.ecommerce.service.LogService;

import java.util.List;
import java.util.Random;

public class Main {

    public static final String ECOMMERCE_NEW_ORDER = "ECOMMERCE_NEW_ORDER";
    public static final String ECOMMERCE_SEND_EMAIL = "ECOMMERCE_SEND_EMAIL";
    public static final String ANY = "ECOMMERCE.*";
    public static final int SLEEP_TIME = 5000;
    public static int count = 0;

    public static void main(String[] args) {

        var messages = List.of("01,02,03,04", "05,06,07,08", "11,12,13,14");

        var emailService = new EmailService();
        var fraudDetectorService = new FraudDetectorService();
        var logService = new LogService();

        messages.forEach(order -> {
            sleep();
            NewOrder.sendMessage(order, "[Email content 0" + count++ + "]");
            sleep();
        });

        emailService.interrupt();
        fraudDetectorService.interrupt();
        logService.interrupt();
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

    public static void threadStarting(Class javaClass) {
        System.out.println("-----------------------------");
        System.out.println("Starting a thread ::: " + javaClass.getName());
        System.out.println("-----------------------------");
    }
}