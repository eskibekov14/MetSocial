package kz.yeskebekov.metSocial.controllers;

import kz.yeskebekov.metSocial.entities.Message;
import kz.yeskebekov.metSocial.entities.MessageDto;
import kz.yeskebekov.metSocial.entities.Users;
import kz.yeskebekov.metSocial.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(value = "/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{userId}")
    public HashMap<String, Long> getMessages(@PathVariable(name = "userId") Long userId){
        return messageService.getMessagesByUser(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/chat/{friendId}")
    public List<MessageDto> getChat(@PathVariable(name = "friendId") Long friendId){
        return messageService.getChat(friendId);
    }

}
