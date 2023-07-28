package com.bitc.jpapractice.testdb.service;

import com.bitc.jpapractice.testdb.data.entity.Member;
import com.bitc.jpapractice.testdb.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinMemberServiceImpl implements JoinMemberService {

  private final MemberRepository repository;

  /* 회원가입 메서드, 아이디 중복 검사 */
  @Override
  public Long join(String userId, String pw) {

    Member member = Member.createUser(userId, pw);
    validateDuplicateMember(member);
    repository.save(member);

    return member.getId();
  }

  /* 아이디 겹치는지 확인용 메서드 */
  private void validateDuplicateMember(Member member){
    repository.findByUserId(member.getUserId())
      .ifPresent(m -> {
        throw new IllegalStateException("이미 존재하는 회원입니다.");
      });
  }
}
