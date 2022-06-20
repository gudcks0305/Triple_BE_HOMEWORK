package com.example.triple_be_homework.service;

import com.example.triple_be_homework.auth.entity.ProviderType;
import com.example.triple_be_homework.auth.entity.RoleType;
import com.example.triple_be_homework.dto.user.UserReqDto;
import com.example.triple_be_homework.entity.User;
import com.example.triple_be_homework.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public void signUp(UserReqDto user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        User newUser = User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .providerType(ProviderType.LOCAL)
                .role(RoleType.USER)
                .build();
        userRepository.save(newUser);
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );
    }
}
