package com._gi.sig.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com._gi.sig.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,UUID>{
    @Query("SELECT u FROM User u WHERE u.login = ?1")
    public List<User> findByLogin(String login);
}
