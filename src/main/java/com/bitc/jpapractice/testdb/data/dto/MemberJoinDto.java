package com.bitc.jpapractice.testdb.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberJoinDto {
  private String userId;
  private String pw;
}
