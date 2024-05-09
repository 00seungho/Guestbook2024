package kr.ac.kopo.kr.guestbook2024.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor //자동 생성자
@NoArgsConstructor // 자동 생성자
@Builder
@Data
public class GuestbookDTO {

    private Long gno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate,modDate;


}
