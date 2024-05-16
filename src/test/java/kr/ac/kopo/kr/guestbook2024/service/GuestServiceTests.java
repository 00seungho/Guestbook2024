package kr.ac.kopo.kr.guestbook2024.service;

import kr.ac.kopo.kr.guestbook2024.dto.GuestbookDTO;
import kr.ac.kopo.kr.guestbook2024.dto.PageRequestDTO;
import kr.ac.kopo.kr.guestbook2024.dto.PageResuitDTO;
import kr.ac.kopo.kr.guestbook2024.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GuestServiceTests {
    @Autowired
    private GuestbookService service;
    @Test
    public void testRegister(){
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("등록 연습 title 1")
                .content("등록 연습 content 1")
                .writer("등록 연습 writer 1")
                .build();
        service.register(guestbookDTO);
    }
    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(30)
                .size(10)
                .build();
        PageResuitDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);
        List<GuestbookDTO> list = resultDTO.getDtoList();
        for (GuestbookDTO guestbookDTO: resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }
    }
    @Test
    public void testList2(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(25)
                .size(10)
                .build();
        PageResuitDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        System.out.println("start: " + resultDTO.getStart());
        System.out.println("end: " + resultDTO.getEnd());
        System.out.println("prev: " + resultDTO.isPrev());
        System.out.println("next: " + resultDTO.isNext());
        for (GuestbookDTO guestbookDTO: resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }
        for (Integer pageNum: resultDTO.getPageList()){
            System.out.println(pageNum.intValue());
        }
    }
}
