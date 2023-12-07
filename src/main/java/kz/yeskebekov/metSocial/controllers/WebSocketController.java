package kz.yeskebekov.metSocial.controllers;

import kz.yeskebekov.metSocial.entities.Message;
import kz.yeskebekov.metSocial.entities.MessageDto;
import kz.yeskebekov.metSocial.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WebSocketController {
    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat/{uniqChatId}")
    @SendTo("/topic/messages/{uniqChatId}")
    public MessageDto processMessage(@PathVariable("uniqChatId") String uniqChatId,
                                     @RequestBody Message message){
        return messageService.sendMessage( uniqChatId, message);
    }
}
