package com.levelupseon.guestbook.service;

import com.levelupseon.guestbook.dto.GuestbookDTO;
import com.levelupseon.guestbook.dto.PageRequestDTO;
import com.levelupseon.guestbook.dto.PageResponseDTO;
import com.levelupseon.guestbook.entity.Guestbook;
import com.levelupseon.guestbook.entity.QGuestbook;
import com.levelupseon.guestbook.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class GuestbookServiceImpl implements GuestbookService {
  //Domain Driven Development

  private final GuestbookRepository repository;
  private final RestClient.Builder builder;

  public Long write(GuestbookDTO guestbookDTO) {
    return repository.save(toEntity(guestbookDTO)).getGno();
  }

  public GuestbookDTO read(Long gno) {
    return toDto(repository.findById(gno).orElse(null));
  }

  @Transactional(readOnly = true)
  public List<GuestbookDTO> readAll() {
    return repository.findAll(Sort.by(Sort.Direction.DESC, "gno")).stream().map(this::toDto).toList();
  }

  @Override
  public PageResponseDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO pageRequestDTO) {
    BooleanBuilder getSearch = getSearch(pageRequestDTO);
    return new PageResponseDTO<>(repository.findAll(getSearch, pageRequestDTO.getPageable(Sort.by(Sort.Direction.DESC, "gno"))), this::toDto);
    //findAll 시 page 타입으로 반환됨

  }

  public void modify(GuestbookDTO guestbookDTO) {
    repository.save(toEntity(guestbookDTO));
  }

  public void remove(Long gno) {
    repository.deleteById(gno);
  }

  public BooleanBuilder getSearch(PageRequestDTO dto) {
    String type = dto.getType();
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    QGuestbook guestbook = QGuestbook.guestbook;
    String keyword = dto.getKeyword();
    booleanBuilder.and(guestbook.gno.gt(0));

    if(type == null || type.trim().isEmpty()) {
      return booleanBuilder;
    }

    BooleanBuilder conditionBuilder = new BooleanBuilder();
    if(type.contains("t")){
      conditionBuilder.or(guestbook.title.contains(keyword));
  }
    if(type.contains("c")){
    conditionBuilder.or(guestbook.content.contains(keyword));
    }
    if(type.contains("w")){
      conditionBuilder.or(guestbook.writer.contains(keyword));
    }
    booleanBuilder.and(conditionBuilder);
    return booleanBuilder;
  }

}
