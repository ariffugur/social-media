package com.ariffugur.socialmedia.repository;

import com.ariffugur.socialmedia.model.Chat;
import com.ariffugur.socialmedia.model.Reel;
import com.ariffugur.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    public List<Chat> findByUsersId(Integer id);

    @Query("SELECT c FROM Chat c WHERE :user MEMBER OF c.users AND :reqUser MEMBER OF c.users")
    public Chat findChatByUserId(@Param("user") User user, @Param("reqUser") User reqUser);
}
