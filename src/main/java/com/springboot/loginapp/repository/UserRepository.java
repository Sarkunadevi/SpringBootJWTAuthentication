package com.springboot.loginapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.loginapp.entity.UserData;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData, Long> {
 Optional<UserData> findByUsername(String username);
}