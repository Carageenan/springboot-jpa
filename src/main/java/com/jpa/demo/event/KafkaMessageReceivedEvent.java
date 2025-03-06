package com.jpa.demo.event;

import org.springframework.context.ApplicationEvent;

public class KafkaMessageReceivedEvent extends ApplicationEvent {
    private String message;

    public KafkaMessageReceivedEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
