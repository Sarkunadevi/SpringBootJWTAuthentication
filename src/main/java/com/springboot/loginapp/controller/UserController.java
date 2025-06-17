package com.springboot.loginapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.loginapp.entity.UserData;
import com.springboot.loginapp.repository.UserRepository;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

 private final UserRepository userRepository;

 // GET: List all users
 @GetMapping
 public ResponseEntity<List<UserData>> getAllUsers() {
     return ResponseEntity.ok(userRepository.findAll());
 }

 // PUT: Update a user by ID
 @PutMapping("/{id}")
 public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserData updatedUser) {
     Optional<UserData> optionalUser = userRepository.findById(id);

     if (optionalUser.isEmpty()) {
         return ResponseEntity.notFound().build();
     }

     UserData user = optionalUser.get();
     user.setUsername(updatedUser.getUsername());
     user.setPassword(updatedUser.getPassword()); // Optional: Re-encrypt if needed
     userRepository.save(user);

     return ResponseEntity.ok(user);
 }

 // DELETE: Delete a user by ID
 @DeleteMapping("/{id}")
 public ResponseEntity<?> deleteUser(@PathVariable Long id) {
     if (!userRepository.existsById(id)) {
         return ResponseEntity.notFound().build();
     }
     userRepository.deleteById(id);
     return ResponseEntity.ok("User deleted successfully");
 }
}

