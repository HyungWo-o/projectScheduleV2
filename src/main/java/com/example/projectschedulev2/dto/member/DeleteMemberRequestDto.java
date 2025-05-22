package com.example.projectschedulev2.dto.member;

import lombok.Getter;

@Getter
public class DeleteMemberRequestDto {

    private final String password;

    public DeleteMemberRequestDto(String password) {
        this.password = password;
    }
}
