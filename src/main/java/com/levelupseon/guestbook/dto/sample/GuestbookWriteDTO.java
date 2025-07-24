package com.levelupseon.guestbook.dto.sample;

import com.levelupseon.guestbook.entity.Guestbook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GuestbookWriteDTO {
  private Long gno;
  private String title;
  private String content;
  private String writer;

//  private GuestbookWriteDTO(Guestbook guestbook) {
//    this.gno = guestbook.getGno();
//    this.title = guestbook.getTitle();
//    this.content = guestbook.getContent();
//    this.writer = guestbook.getWriter();
//  }
  public Guestbook toEntity() {
    return Guestbook.builder().content(content).title(title).writer(writer).build();
    //여기서 유효성 검증한다
  }
}
