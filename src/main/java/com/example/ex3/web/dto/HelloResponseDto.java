package com.example.ex3.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor //final만 적용
public class HelloResponseDto {
    private final String name;
    private final int amount;

}
