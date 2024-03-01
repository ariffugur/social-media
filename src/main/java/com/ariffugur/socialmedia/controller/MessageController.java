package com.ariffugur.socialmedia.controller;

import com.ariffugur.socialmedia.dto.CreateNewMessageRequest;
import com.ariffugur.socialmedia.model.Message;
import com.ariffugur.socialmedia.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send/{chatId}")
    public Message createMessage(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatId, @RequestBody CreateNewMessageRequest request) throws Exception {
        return messageService.createMessage(jwt, chatId, request);
    }
    @GetMapping("/{chatId}")
    public List<Message> findChatsMessage(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatId) throws Exception {
        return messageService.findChatsMessage(jwt,chatId);
    }
}
