package com.navjot.spring.security.controller;

import com.navjot.spring.security.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl service;

    @GetMapping
//    @PreAuthorize("hasAuthority('USER')")
    private ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi User.");
    }


    @GetMapping("/hi")
    @PreAuthorize("hasAuthority('USER')")
    private ResponseEntity<String> sayHello1(){
        return ResponseEntity.ok("Hi User with pre auth.");
    }

//    @PatchMapping
//    public ResponseEntity<?> changePassword(
//            @RequestBody ChangePasswordRequest request,
//            Principal connectedUser
//    ) {
//        service.changePassword(request, connectedUser);
//        return ResponseEntity.ok().build();
//    }
}
