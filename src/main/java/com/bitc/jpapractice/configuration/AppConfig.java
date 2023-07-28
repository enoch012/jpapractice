package com.bitc.jpapractice.configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  /* JPA 관련 설정 */
  @PersistenceContext(unitName = "firstEntityManager")
  private EntityManager firstEntityManager;

  /* QueryDsl 관련 설정 */
  // 나중에 시간 있으면 이것도 연습해보자
}
