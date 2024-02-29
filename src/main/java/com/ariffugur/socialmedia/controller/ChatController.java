package com.ariffugur.socialmedia.controller;

import com.ariffugur.socialmedia.dto.CreateChatRequest;
import com.ariffugur.socialmedia.model.Chat;
import com.ariffugur.socialmedia.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;

    }

    @PostMapping("/create")
    public Chat createChat(@RequestBody CreateChatRequest request) {
        return chatService.createChat(request.reqUser(), request.user2());

    }

    @GetMapping("/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {
        return chatService.findUsersChat(jwt);

    }
}
