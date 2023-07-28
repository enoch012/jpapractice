package com.bitc.jpapractice.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = "com.bitc.jpapractice.testdb", // 첫번째 DB가 있는 패키지(폴더)경로
  entityManagerFactoryRef = "firstEntityManagerFactory", // EntityManager 의 이름
  transactionManagerRef = "firstTransactionManager" // 트랜잭션 매니저의 이름
)
public class DatabaseConfiguration {

  private final JpaProperties jpaProperties;
  private final HibernateProperties hibernateProperties;

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource firstDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Primary // Bean 우선순위 설정
  @Bean(name = "firstEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean firstEntityManager(EntityManagerFactoryBuilder builder){
    Map<String, Object> properties = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    DataSource dataSource = firstDataSource();
    return builder
      .dataSource(dataSource)
      // 첫번째 DB와 관련된 엔티티들이 있는 패키지(폴더) 경로
      .packages("com.bitc.jpapractice.testdb.data.entity")
      .persistenceUnit("firstEntityManager")
      .properties(properties)
      .build();

  }

  @Primary
  @Bean(name = "firstTransactionManager")
  public PlatformTransactionManager firstTransactionManager(
    final @Qualifier("firstEntityManagerFactory") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean
  ){
    return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
  }
}
