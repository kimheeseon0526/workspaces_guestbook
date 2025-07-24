package com.levelupseon.guestbook.service;

import com.levelupseon.guestbook.dto.GuestbookDTO;
import com.levelupseon.guestbook.dto.PageRequestDTO;
import com.levelupseon.guestbook.dto.PageResponseDTO;
import com.levelupseon.guestbook.entity.Guestbook;
import com.levelupseon.guestbook.repository.GuestbookRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

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
  public void testWrite() {
    Long gno = service.write(GuestbookDTO.builder().title("제목1").content("내용").writer("기미선").build());
    Assertions.assertNotNull(gno);
    log.info(gno);
  }

  @Test
  public void testRead() {
    Long gno = 1L;  //given
    GuestbookDTO dto = service.read(gno);
    //쿼리문에 있는 내용과 일치하는지 확인
    GuestbookDTO expect = GuestbookDTO.builder().title("제목 변경").content("내용 변경").writer("작성자").gno(gno).build();

    Assertions.assertEquals(dto.getTitle(), expect.getTitle());
    Assertions.assertEquals(dto.getContent(), expect.getContent());
    Assertions.assertEquals(dto.getWriter(), expect.getWriter());
  }

  @Test
  public void testReadAll() {
    service.readAll().forEach(log::info);
  }

  @Test
  @Transactional
  @Commit
  public void testModify() {
    Long gno = 1L;
    GuestbookDTO dto = service.read(gno);
    dto.setContent("수정 내용");
    service.modify(dto);
  }

  @Test
  public void testRemove() {
    service.remove(10L);
  }

  @Test
  public void testList() {
//    PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
//    PageResponseDTO<GuestbookDTO, Guestbook>  responseDTO = service.getList(pageRequestDTO);

    PageResponseDTO<?,?> dto = service.getList(PageRequestDTO.builder().page(1).size(5).build());
    log.info(dto);
  }

  @Test
  public void testSearch() {
    PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
            .page(1)
            .size(5)
            .type("c")
            .keyword("한글")
            .build();

    PageResponseDTO<GuestbookDTO, Guestbook> responseDTO = service.getList(pageRequestDTO);

      log.info(responseDTO);
//    log.info(responseDTO.isPrev());
//    log.info(responseDTO.isNext());
//    log.info(responseDTO.getTotalPage());
  }
}
