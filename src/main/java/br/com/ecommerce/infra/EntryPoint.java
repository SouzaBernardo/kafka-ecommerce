package br.com.ecommerce.infra;

import br.com.ecommerce.application.service.EmailService;
import br.com.ecommerce.application.service.FraudDetectorService;
import br.com.ecommerce.application.service.LogService;

public class EntryPoint {

    private final EmailService emailService;
    private final FraudDetectorService fraudDetectorService;
    private final LogService logService;

    private EntryPoint() {
        this.emailService = new EmailService();
        this.fraudDetectorService = new FraudDetectorService();
        this.logService = new LogService();
    }

    public static EntryPoint listening() {
        return new EntryPoint();
    }
    public void stop() {
        emailService.interrupt();
        fraudDetectorService.interrupt();
        logService.interrupt();
    }
}
