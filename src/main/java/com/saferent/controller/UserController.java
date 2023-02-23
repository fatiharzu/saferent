package com.saferent.controller;

import com.saferent.dto.response.UserDTO;
import com.saferent.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
    public class UserController
    {
        private final UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }

        // !!! getAllUser

        @GetMapping("/auth/all")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<List<UserDTO>> getAllUsers()
        {
           List<UserDTO> allUsers = userService.getAllUsers();
           return ResponseEntity.ok(allUsers);
        }

        // !!! sisteme giris yapan kullanici bilgisi....
        @GetMapping
        @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
        public ResponseEntity<UserDTO> getUser()
        {
          UserDTO userDTO = userService.getPrincipal();

          return  ResponseEntity.ok(userDTO);
        }
    }
