package com.levelupseon.guestbook.service;

import com.levelupseon.guestbook.dto.GuestbookDTO;
import com.levelupseon.guestbook.entity.Guestbook;
import com.levelupseon.guestbook.repository.GuestbookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class GuestbookServiceImpl implements GuestbookService {
  private GuestbookRepository repository;

  public Long write(GuestbookDTO guestbookDTO) {
    return repository.save(toEntity(guestbookDTO)).getGno();
  }

  public GuestbookDTO read(Long gno) {
    return toDto(repository.findById(gno).orElseThrow());
  }

  public List<GuestbookDTO> readAll() {
    return repository.findAll().stream().map(this::toDto).toList();
  }


  public int modify(GuestbookDTO guestbookDTO) {
    repository.save(toEntity(guestbookDTO));
    return repository.existsById(guestbookDTO.getGno()) ? 1 : 0;
  }

  public int remove(Long gno) {
    repository.deleteById(gno);
    return repository.existsById(gno) ? 1 : 0;
    //반환타입이 int 이기 때문에
  }

  public Guestbook toEntity(GuestbookDTO guestbookDTO) {
    return GuestbookService.super.toEntity(guestbookDTO);
  }

  public GuestbookDTO toDto(Guestbook guestbook) {
    return GuestbookService.super.toDto(guestbook);
  }
}
