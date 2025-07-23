package com.levelupseon.guestbook.repository;

import com.levelupseon.guestbook.entity.Guestbook;
import com.levelupseon.guestbook.entity.QGuestbook;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class GuestbookRepositoryTest {
  @Autowired
  private GuestbookRepository repository;

  @Test
  public void testExist() {
    log.info("{}", repository);
  }

  @Test
  public void testInsert() {
    repository.save(Guestbook.builder().title("제목").content("내용").writer("작성자").build());
  }

  @Test
  public void insertDummies() {
    IntStream.rangeClosed(1,300).forEach(i -> {
      Guestbook guestbook = Guestbook.builder()
              .title("Title..." + i)
              .content("Content..." + i)
              .writer("user" + (i % 10))
              .build();
      log.info("{}", repository.save(guestbook));
    });
  }

  @Test
  public void updateTest() {
    Optional<Guestbook> result = repository.findById(1L);

    if(result.isPresent()) {
      Guestbook guestbook = result.get();

      guestbook.setTitle("제목 변경");
      guestbook.setContent("내용 변경");

      repository.save(guestbook);

    }
  }

  @Test
  public void testQuery1() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
    QGuestbook qGuestbook = QGuestbook.guestbook;
    String keyword = "1";
    BooleanBuilder builder = new BooleanBuilder();
    BooleanExpression expression = qGuestbook.title.contains(keyword);
    builder.and(qGuestbook.title.contains("?").or(qGuestbook.content.contains("?")));
    builder.and(expression);
    builder.and(qGuestbook.gno.gt(0L));
    Page<Guestbook> result = repository.findAll(builder, pageable);
    result.stream().forEach(guestbook ->  log.info(guestbook));
  }

  @Test
  public void testQuery2() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
    QGuestbook qGuestbook = QGuestbook.guestbook;
    String keyword = "1";
    BooleanBuilder builder = new BooleanBuilder();
    BooleanExpression expression1 = qGuestbook.title.contains(keyword);
    BooleanExpression expression2 = qGuestbook.content.contains(keyword);
    BooleanExpression be = expression1.or(expression2);
    builder.and(qGuestbook.title.contains("?").or(qGuestbook.content.contains("?")));
    builder.and(be);
    builder.and(qGuestbook.gno.gt(0));

    Page<Guestbook> result = repository.findAll(builder, pageable);
    result.stream().forEach(guestbook ->  log.info(guestbook));
  }
}
