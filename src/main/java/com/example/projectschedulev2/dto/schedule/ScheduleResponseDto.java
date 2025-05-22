package com.example.projectschedulev2.dto.schedule;

import com.example.projectschedulev2.entity.schedule.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;

    private final String name;

    private final String title;

    private final String contents;

    public ScheduleResponseDto(Long id, String name, String title, String contents) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.contents = contents;
    }

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(), schedule.getMember().getName(), schedule.getTitle(), schedule.getContent());
    }
}
