package com.bitc.jpapractice.testdb.service;


import com.bitc.jpapractice.testdb.data.entity.Member;
import com.bitc.jpapractice.testdb.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository repository;

  @Override
  public Optional<Member> findOne(String userId) {
    return repository.findByUserId(userId);
  }

  @Override
  public boolean isValidMember(String userId, String password) {

    Optional<Member> member = findOne(userId);
    if (member.isPresent()){
      return member.get().getPw().equals(password);
    }
    return false;
  }

}
