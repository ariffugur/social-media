package com.ariffugur.socialmedia.repository;

import com.ariffugur.socialmedia.model.Chat;
import com.ariffugur.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    public List<Chat> findByUserId(Integer id);

    @Query("select c from Chat c where :user Member of c.users and :reqUser Member of c.users")
    public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);
}
