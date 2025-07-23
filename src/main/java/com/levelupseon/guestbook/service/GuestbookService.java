package com.levelupseon.guestbook.service;

import com.levelupseon.guestbook.dto.GuestbookDTO;
import com.levelupseon.guestbook.entity.Guestbook;

import java.util.List;

public interface GuestbookService {
  //CRUD
  Long write(GuestbookDTO guestbookDTO);
  GuestbookDTO read(Long gno);
  List<GuestbookDTO> readAll();
  int modify(GuestbookDTO guestbookDTO);
  int remove(Long gno);


  default Guestbook toEntity(GuestbookDTO guestbookDTO){
    return Guestbook.builder()
            .gno(guestbookDTO.getGno())
            .title(guestbookDTO.getTitle())
            .content(guestbookDTO.getContent())
            .writer(guestbookDTO.getWriter())
            .build();
  }

  default GuestbookDTO toDto(Guestbook  guestbook){
    return GuestbookDTO.builder()
            .gno(guestbook.getGno())
            .title(guestbook.getTitle())
            .content(guestbook.getContent())
            .writer(guestbook.getWriter())
            .regDate(guestbook.getRegDate())
            .modDate(guestbook.getModDate())
            .build();
  }

}
