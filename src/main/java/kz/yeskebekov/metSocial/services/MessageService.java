package kz.yeskebekov.metSocial.services;

import kz.yeskebekov.metSocial.entities.Message;
import kz.yeskebekov.metSocial.entities.MessageDto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public interface MessageService {
    HashMap<String,Long> getMessagesByUser(Long userId);
    MessageDto sendMessage(String uniqChatTopic, Message message);
    List<MessageDto> getChat(Long friendId);
}
