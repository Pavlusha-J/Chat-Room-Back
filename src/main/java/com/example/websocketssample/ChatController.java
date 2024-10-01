package com.example.websocketssample;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

@Controller
public class ChatController {

    @MessageMapping("/send")  // Žinučių siuntimas
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        message.setSentAt(System.currentTimeMillis());
        return message;
    }

    @MessageMapping("/join")  // Prisijungimo pranešimas
    @SendTo("/topic/messages")
    public ChatMessage join(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        message.setContent("prisijungė");
        return message;
    }

    @MessageMapping("/leave")  // Atsijungimo pranešimas
    @SendTo("/topic/messages")
    public ChatMessage leave(ChatMessage message) {
        message.setContent("paliko pokalbį");
        return message;
    }
}


