package com.example.triple_be_homework.event.controller;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class testController  {
    @GetMapping("/")
    public String test() {
        return "test";
    }


}
