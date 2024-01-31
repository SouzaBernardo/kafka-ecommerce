package br.com.ecommerce.infra;

import br.com.ecommerce.application.service.EmailService;
import br.com.ecommerce.application.service.FraudDetectorService;
import br.com.ecommerce.application.service.LogService;

public class EntryPoint {

    private static final EntryPoint INSTANCE = new EntryPoint();
    private final EmailService emailService;
    private final FraudDetectorService fraudDetectorService;
    private final LogService logService;

    private EntryPoint() {
        this.emailService = new EmailService();
        this.fraudDetectorService = new FraudDetectorService();
        this.logService = new LogService();
    }

    public static EntryPoint getInstance() {
        return INSTANCE;
    }
    public void stop() {
        emailService.interrupt();
        fraudDetectorService.interrupt();
        logService.interrupt();
    }
    public void start() {
        emailService.start();
        fraudDetectorService.start();
        logService.start();
    }
}
