package kz.yeskebekov.metSocial.services.impl;

import kz.yeskebekov.metSocial.entities.Message;
import kz.yeskebekov.metSocial.entities.MessageDto;
import kz.yeskebekov.metSocial.entities.Users;
import kz.yeskebekov.metSocial.mappers.UsersMapper;
import kz.yeskebekov.metSocial.repositories.MessageRepository;
import kz.yeskebekov.metSocial.repositories.UsersRepository;
import kz.yeskebekov.metSocial.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public HashMap<String, Long> getMessagesByUser(Long userId) {
        HashMap<String,Long> friends = new HashMap<>();
        for(Message message : messageRepository.findAll()){
            if(message.getSender().getId()==userId || message.getReceiver().getId()==userId){
                if(message.getSender().getId()!=userId){
                    friends.put(message.getSender().getFullName(),message.getSender().getId());
                }else if(message.getReceiver().getId()!=userId){
                    friends.put(message.getReceiver().getFullName(),message.getReceiver().getId());
                }
            }
        }
        return friends;
    }

    @Override
    public MessageDto sendMessage(String uniqChatId, Message message) {
//        message.setUniqChatTopic(uniqChatId);
        Message savedMessage = messageRepository.save(message);
        MessageDto messageDto = MessageDto.builder()
                .id(savedMessage.getId())
                .messageText(savedMessage.getMessageText())
                .uniqChatTopic(savedMessage.getUniqChatTopic())
                .sender(usersMapper.mapToUserDTO(savedMessage.getSender()))
                .receiver(usersMapper.mapToUserDTO(savedMessage.getReceiver()))
                .messageDate(savedMessage.getMessageDate()).build();
        return messageDto;
    }

    @Override
    public List<MessageDto> getChat(Long friendId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = usersRepository.findAllByEmail(authentication.getName());
        List<Message> chat = messageRepository.findAll().stream()
                .filter( message -> message.getSender().getId()==currentUser.getId() ||
                        message.getReceiver().getId()== currentUser.getId())
                .filter( message -> message.getSender().getId()==friendId||
                        message.getReceiver().getId()== friendId)
                .toList();
        List<MessageDto> newChat = new ArrayList<>();
        for(Message message : chat){
            newChat.add(new MessageDto(message.getId(), message.getMessageText(), message.getUniqChatTopic(),
                    usersMapper.mapToUserDTO(message.getSender()), usersMapper.mapToUserDTO(message.getReceiver()),
                    message.getMessageDate()));
        }
        return newChat;
    }
}
