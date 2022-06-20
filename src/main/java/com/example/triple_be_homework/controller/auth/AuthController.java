package com.example.triple_be_homework.controller.auth;


import com.example.triple_be_homework.auth.entity.UserPrincipal;
import com.example.triple_be_homework.auth.token.AuthToken;
import com.example.triple_be_homework.auth.token.AuthTokenProvider;
import com.example.triple_be_homework.config.properties.AppProperties;
import com.example.triple_be_homework.dto.ApiResponse;
import com.example.triple_be_homework.dto.user.UserReqDto;
import com.example.triple_be_homework.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AppProperties appProperties;
    private final AuthTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    private  final UserService userService;

    @PostMapping("/auth/join")
    public ApiResponse join(@RequestBody UserReqDto user) {

        userService.signUp(user);
        // 수정필요
        return ApiResponse.success("SUCCESS" , true);
    }


    @PostMapping("/auth/login")
    public ApiResponse login(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody UserReqDto reqDto
    ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        reqDto.getEmail(),
                        reqDto.getPassword()
                )
        );


        String userId = authentication.getName();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Date now = new Date();
        AuthToken accessToken = tokenProvider.createAuthToken(
                userId,
                ((UserPrincipal) authentication.getPrincipal()).getUser().getRole().getCode(),
                new Date(now.getTime() + appProperties.getAuth().getTokenExpiry())
        );



        return ApiResponse.success("token", accessToken.getToken());
    }


}