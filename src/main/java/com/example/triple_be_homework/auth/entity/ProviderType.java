package com.example.triple_be_homework.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProviderType {

    LOCAL("local", "일반 ID,PW 로그인");
    private final String code;
    private final String displayName;
}
