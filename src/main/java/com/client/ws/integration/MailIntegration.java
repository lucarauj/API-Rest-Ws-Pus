package com.client.ws.integration;

public interface MailIntegration {

    void send(String mailTo, String message, String subject);
}
