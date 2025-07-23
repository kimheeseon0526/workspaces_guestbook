package com.levelupseon.guestbook;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@Log4j2
public class GuestbookApplication {

  public static void main(String[] args) {
    SpringApplication.run(GuestbookApplication.class, args);
    log.info("GuestbookApplication started");
  }

}
