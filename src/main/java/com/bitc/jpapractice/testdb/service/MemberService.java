package com.bitc.jpapractice.testdb.service;

import com.bitc.jpapractice.testdb.data.entity.Member;

import java.util.Optional;

public interface MemberService {
  public Optional<Member> findOne(String userId);

  public boolean isValidMember(String userId, String password);
}
