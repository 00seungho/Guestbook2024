package kr.ac.kopo.kr.guestbook2024.service;

import kr.ac.kopo.kr.guestbook2024.dto.GuestbookDTO;
import kr.ac.kopo.kr.guestbook2024.dto.PageRequestDTO;
import kr.ac.kopo.kr.guestbook2024.dto.PageResuitDTO;
import kr.ac.kopo.kr.guestbook2024.entity.Guestbook;

public interface GuestbookService {
    //dto 객체가 필요함
    Long register(GuestbookDTO dto);
    // 한 페이지에 보여질 글 목록(GuestbookDTO 객체)이 저장된 List정보를 갖고 있는 PageResultDTO객체 참조값을 반환하는 기능
    PageResuitDTO<GuestbookDTO,Guestbook> getList(PageRequestDTO requestDTO);
    default Guestbook dtoToEntity(GuestbookDTO dto){
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }
    default GuestbookDTO entityToDto(Guestbook entity){
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}
