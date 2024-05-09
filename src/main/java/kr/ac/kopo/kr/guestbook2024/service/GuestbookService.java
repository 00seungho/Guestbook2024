package kr.ac.kopo.kr.guestbook2024.service;

import kr.ac.kopo.kr.guestbook2024.dto.GuestbookDTO;
import kr.ac.kopo.kr.guestbook2024.entity.Guestbook;

public interface GuestbookService {
    //dto 객체가 필요함
    Long register(GuestbookDTO dto);
    default Guestbook dtoToEntity(GuestbookDTO dto){
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }
}
