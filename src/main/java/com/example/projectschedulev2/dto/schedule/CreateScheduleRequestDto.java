package com.example.projectschedulev2.dto.schedule;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private final String name;

    private final String title;

    private final String content;

    public CreateScheduleRequestDto(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
    }
}
