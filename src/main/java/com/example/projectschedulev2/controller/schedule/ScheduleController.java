package com.example.projectschedulev2.controller.schedule;

import com.example.projectschedulev2.dto.schedule.CreateScheduleRequestDto;
import com.example.projectschedulev2.dto.schedule.DeleteScheduleRequestDto;
import com.example.projectschedulev2.dto.schedule.ScheduleResponseDto;
import com.example.projectschedulev2.dto.schedule.UpdateScheduleRequestDto;
import com.example.projectschedulev2.service.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto requestDto) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto.getName(), requestDto.getTitle(), requestDto.getContent());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {

        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateScheduleRequestDto requestDto) {

        scheduleService.update(id, requestDto.getTitle(), requestDto.getContent(), requestDto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody DeleteScheduleRequestDto requestDto) {

        scheduleService.delete(id, requestDto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
