package com.example.projectschedulev2.dto.schedule;

import lombok.Getter;

@Getter
public class DeleteScheduleRequestDto {

    private final String password;

    public DeleteScheduleRequestDto(String password) {
        this.password = password;
    }
}
