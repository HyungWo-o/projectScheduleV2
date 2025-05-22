package com.example.projectschedulev2.service.schedule;

import com.example.projectschedulev2.dto.schedule.ScheduleResponseDto;
import com.example.projectschedulev2.entity.member.Member;
import com.example.projectschedulev2.entity.schedule.Schedule;
import com.example.projectschedulev2.repository.member.MemberRepository;
import com.example.projectschedulev2.repository.schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String name, String title, String content) {

        Member findMember = memberRepository.findMemberByNameOrElseThrow(name);

        Schedule schedule = new Schedule(title, content);
        schedule.setMember(findMember);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getMember().getName(), savedSchedule.getTitle(), savedSchedule.getContent());
    }

    public ScheduleResponseDto findById(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getMember().getName(), findSchedule.getTitle(), findSchedule.getContent());
    }

    public List<ScheduleResponseDto> findAll() {

        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();

    }

    @Transactional
    public void update(Long id, String title, String content, String password) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        Member findMember = memberRepository.findMemberByNameOrElseThrow(findSchedule.getMember().getName());

        if (!findMember.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        findSchedule.update(title, content);
    }

    public void delete(Long id, String password) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        Member findMember = memberRepository.findMemberByNameOrElseThrow(findSchedule.getMember().getName());

        if (!findMember.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.delete(findSchedule);

    }
}
