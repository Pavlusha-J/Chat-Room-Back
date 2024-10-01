package com.example.websocketssample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private String selectedZone = "Europe/Vilnius"; // Pradinis pasirinkimas

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

    @MessageMapping("/setTimezone")
    public void setTimezone(TimeSMS message) {
        this.selectedZone = message.getZone();
    }

    @Scheduled(fixedRate = 1000)
    public void sendTime() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(selectedZone));
        String formattedTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        messagingTemplate.convertAndSend("/topic/time", new TimeSMS(formattedTime, selectedZone));
    }
    @Controller
    public class JuokeliuController {

        private final SimpMessagingTemplate messagingTemplate;
        private final List<String> jokes = Arrays.asList(
                "Kodėl kompiuteriai nemoka žvejoti? Nes jie bijo klaidų!",
                "Kaip vadinasi programuotojų šventė? Kodinis Kalėdų vakaras!",
                "Kas geriau už naują programinę įrangą? Nemokama nauja programinė įranga!",
                "Kodėl programuotojai neina į lauką? Nes jie negali ištrinti saulės spindulių!"
        );
        private final Random random = new Random();

        @Autowired
        public JuokeliuController(SimpMessagingTemplate messagingTemplate) {
            this.messagingTemplate = messagingTemplate;
        }

        @Scheduled(fixedRate = 5000)
        public void sendJoke() {
            String joke = jokes.get(random.nextInt(jokes.size()));
            messagingTemplate.convertAndSend("/topic/jokes", joke);
        }
    }


}

