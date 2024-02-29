package com.ariffugur.socialmedia.service;

import com.ariffugur.socialmedia.model.Chat;
import com.ariffugur.socialmedia.model.User;
import com.ariffugur.socialmedia.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserService userService;
    ;

    public ChatService(ChatRepository chatRepository, UserService userService) {
        this.chatRepository = chatRepository;
        this.userService = userService;
    }

    public Chat createChat(String reqUser, String user2) {
      User newReqUser =  userService.findUserByUsername(reqUser);
       User newUser2 = userService.findUserByUsername(user2);
        Chat isExist = chatRepository.findChatByUserId(newReqUser, newUser2);
        if (isExist != null) {
            return isExist;
        }
        Chat chat = new Chat();
        chat.getUsers().add(newUser2);
        chat.getUsers().add(newReqUser);
        chat.setTimestamp(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> opt = chatRepository.findById(chatId);
        if (opt.isEmpty()) {
            throw new Exception("Chat not found" + chatId);
        }
        return opt.get();
    }

    public List<Chat> findUsersChat(String jwt) {
        User user = userService.findUserByJwt(jwt);
        return chatRepository.findByUsersId(user.getId());
    }
}
