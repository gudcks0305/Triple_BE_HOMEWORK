package com.example.triple_be_homework.point.entity;

import lombok.Getter;

@Getter
public enum PointType {
    CONTENT("1자 이상"),
    PHOTO("1장 이상 사진첨부"),
    FIRST("첫번째 리뷰 게시"),
    DEL_ALL_PHOTO("모든 사진 삭제"),
    DEL_REVIEW("리뷰 삭제");

    private final String detail;

    PointType(String detail) {
        this.detail = detail;
    }
}