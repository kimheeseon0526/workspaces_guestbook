package com.levelupseon.guestbook.service;

import com.levelupseon.guestbook.dto.GuestbookDTO;
import com.levelupseon.guestbook.repository.GuestbookRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class GuestbookserviceTest {

  @Autowired
  private  GuestbookService service;

  @Test
  public void testExist() {
    log.info(service);
  }

  @Test
  public void testregister() {
    Long gno = service.write(GuestbookDTO.builder().title("제목1").content("내용").writer("기미선").build());
    Assertions.assertNotNull(gno);
    log.info(gno);
  }

  @Test
  public void testList() {
    //Optional<GuestbookDTO> result = service.
    //List<GuestbookDTO> guestbookDTOS = s
  }
}
