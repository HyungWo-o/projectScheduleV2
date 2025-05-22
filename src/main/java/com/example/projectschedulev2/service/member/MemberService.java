package com.example.projectschedulev2.service.member;

import com.example.projectschedulev2.dto.member.MemberResponseDto;
import com.example.projectschedulev2.dto.member.SignUpResponseDto;
import com.example.projectschedulev2.entity.member.Member;
import com.example.projectschedulev2.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String name, String email, String password) {

        Member member = new Member(name, email, password);

        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(savedMember.getId(), savedMember.getName(), savedMember.getEmail(), savedMember.getCreatedAt(), savedMember.getUpdatedAt());
    }

    public MemberResponseDto findById(Long id) {

        Optional<Member> optionalMember = memberRepository.findById(id);

        if (optionalMember.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "회원 정보를 찾을 수 없습니다.");
        }

        Member findMember = optionalMember.get();

        return new MemberResponseDto(findMember.getName(), findMember.getEmail());
    }

    @Transactional
    public void update(Long id, String name, String oldPassword, String newPassword) {

        Member findMember = memberRepository.findByIdOrElseThrow(id);

        if (!findMember.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        findMember.update(name, newPassword);

    }

    public void delete(Long id, String password) {

        Member findMember = memberRepository.findByIdOrElseThrow(id);

        if (!findMember.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        memberRepository.delete(findMember);

    }
}
