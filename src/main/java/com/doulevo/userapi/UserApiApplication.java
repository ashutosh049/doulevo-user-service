package com.doulevo.userapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

import static com.doulevo.userapi.util.ObjectUtils.isEmpty;
import static com.doulevo.userapi.util.constants.SystemConstants.TimeZone.UTC;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class UserApiApplication {

  @Value("${app.doulevo.timezone}")
  private String timezone;

  public static void main(String[] args) {
    SpringApplication.run(UserApiApplication.class, args);
  }

  @PostConstruct
  void started() {
    log.info("System default timezone:: {}", TimeZone.getDefault());
    if (isEmpty(timezone)) {
      log.warn(
          "Unable to locate property \"app.doulevo.timezone\". Setting \""
              + UTC
              + "\" as default time zone.");
      TimeZone.setDefault(TimeZone.getTimeZone(UTC));
    } else {
      TimeZone.setDefault(TimeZone.getTimeZone(timezone));
    }
    log.info("System default timezone set as :: {}", TimeZone.getDefault());
  }
}
