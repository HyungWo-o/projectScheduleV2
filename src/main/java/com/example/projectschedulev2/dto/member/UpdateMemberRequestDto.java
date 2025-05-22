package com.example.projectschedulev2.dto.member;

import lombok.Getter;

@Getter
public class UpdateMemberRequestDto {

    private final String name;

    private final String oldPassword;

    private final String newPassword;

    public UpdateMemberRequestDto(String name, String oldPassword, String newPassword) {
        this.name = name;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
