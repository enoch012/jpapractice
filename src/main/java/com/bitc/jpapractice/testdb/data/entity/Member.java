package com.bitc.jpapractice.testdb.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="jpa_member")
@Getter
/* 매개변수 없는 생성자 자동 주입 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String userId;

  @Column(nullable = false)
  private String pw;

  @Column(nullable = false)
  private String roles;

  private Member(Long id, String userId, String pw, String roleUser) {
    this.id = id;
    this.userId = userId;
    this.pw = pw;
    this.roles = roleUser;
  }

  public static Member createUser(String userId, String pw){
    return new Member(null, userId, pw, "USER");
  }

}
