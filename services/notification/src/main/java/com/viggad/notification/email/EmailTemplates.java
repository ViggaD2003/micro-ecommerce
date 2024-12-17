package com.viggad.notification.email;

import lombok.Getter;

public enum EmailTemplates {

    PAYMENT_CONFIRMATION("payemt-confirmation.html", "Payment_successfully_process"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order_confirmation");


    @Getter
    private final String template;

    @Getter
    private final String subject;


    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
