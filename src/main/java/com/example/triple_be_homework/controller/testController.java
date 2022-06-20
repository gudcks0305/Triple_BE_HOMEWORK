package com.example.triple_be_homework.controller;

import com.example.triple_be_homework.auth.entity.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class testController  {

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal User principal , @AuthenticationPrincipal UserPrincipal userPrincipal) {
        log.info("principal.getAuthorities := {}", principal.getAuthorities());
        log.info("principal.getUsername := {}", principal.getUsername());
        log.info("principal.getPassword := {}", principal.getPassword());




        return "test";
    }
}
