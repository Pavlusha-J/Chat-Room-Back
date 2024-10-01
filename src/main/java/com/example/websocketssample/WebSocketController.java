package com.example.websocketssample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) throws Exception {
        Thread.sleep(1000);
        return new Greeting("Sveiki atvykę, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
    private int counter = 0;

    @Scheduled(fixedRate = 3000)
    public void sendGreeting() {
        messagingTemplate.convertAndSend("/topic/heyhey", "Hello " + counter++);
    }
}

