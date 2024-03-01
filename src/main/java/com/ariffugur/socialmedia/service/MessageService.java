package com.ariffugur.socialmedia.service;

import com.ariffugur.socialmedia.dto.CreateNewMessageRequest;
import com.ariffugur.socialmedia.model.Chat;
import com.ariffugur.socialmedia.model.Message;
import com.ariffugur.socialmedia.model.User;
import com.ariffugur.socialmedia.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ChatService chatService;

    public MessageService(MessageRepository messageRepository, UserService userService, ChatService chatService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.chatService = chatService;
    }

    public Message createMessage(String jwt, Integer chatId, CreateNewMessageRequest request) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Chat newChat = chatService.findChatById(chatId);
        Message message = Message.builder()
                .content(request.content())
                .image(request.image())
                .user(user)
                .chat(newChat)
                .timestamp(LocalDateTime.now())
                .build();
        return messageRepository.save(message);
    }

    public List<Message> findChatsMessage(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);
        return messageRepository.findByChatId(chatId);

    }
}
