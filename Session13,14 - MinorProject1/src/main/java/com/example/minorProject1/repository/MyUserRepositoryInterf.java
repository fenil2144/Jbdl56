package com.example.minorProject1.repository;

import com.example.minorProject1.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepositoryInterf extends JpaRepository<MyUser, Integer> {
    MyUser findByUsername(String username);
}
