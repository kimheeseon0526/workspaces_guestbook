package com.levelupseon.guestbook.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<DTO, Entity> {
  //리스트
  private List<DTO> list;
  private int page, size, start, end, totalPage;
  private boolean prev, next;

  private List<Integer> pageList;

  public PageResponseDTO(Page<Entity> page, Function<Entity, DTO> mapper) {
    //page type -> list type
    list = page.stream().map(mapper).toList();

    totalPage = page.getTotalPages();

    makePageList(page.getPageable());

  }
  private void makePageList(Pageable pageable) {
    final int PAGE_VIEW_COUNT = 5;

    page = pageable.getPageNumber() + 1;
    size = pageable.getPageSize();

    int tempEnd = (int)(Math.ceil(page / 1d / PAGE_VIEW_COUNT)) * PAGE_VIEW_COUNT;
    start = tempEnd - (PAGE_VIEW_COUNT - 1) ;
    prev = start > 1;
    end = totalPage > tempEnd ? tempEnd : totalPage;
    next = totalPage > tempEnd;

    pageList = IntStream.rangeClosed(start, end).boxed().toList();
  }
}
