package kr.ac.kopo.kr.guestbook2024.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResuitDTO<DTO,Entity>{
    //화면에 보여질 글 목록: GuestbookDTO객체 참조값들이 저장된 리스트
    private List<DTO> dtoList;
    //전체 페이지 수
    private int totalPage;
    //현재 페이지 번호
    private int page;
    //한 페이지에 보여지는 글 목록 개수
    private int size;
    //한 화면에 아래쪽에 보여질 시작 페이지 번호
    private int start;
    //한 화면에 아래쪽에 보여질 마지막 페이지 번호
    private int end;
    //화면을 바꿔줄 이전, 다음 링크가 보여지거나 보이지 않게 설정할 때 필요
    private boolean prev,next;
    //한 화면에 보여질 페이지 번호 목록이 저장
    private List<Integer> pageList;


    public PageResuitDTO(Page<Entity> result, Function<Entity, DTO> fn){
        //매개변수로 전달받은 결과행들과 Entity를 DTO로 변환한 fn를 사용해서 list에 DTO객체들을 저장
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();//301개의 행을 갖고 있다면 전체페이지수는 31페이지다.
        makePageList(result.getPageable());
    }
    private void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        int temEnd = (int)(Math.ceil(page/10.0)) * 10;
        start = temEnd - 9;
        //삼항조건연산자에서 조건식이 true면 마지막 화면이 아님
        //삼항조건연산자에서 조건식이 flase면 마지막 화면임
        //마지막 화면이 아닌 경우 1 ~ 3번째 화면, 마지막 화면은 4번째 화면을 의미함
        end = totalPage > temEnd ? temEnd : totalPage;

        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());
        prev = start > 1; //2~마지막 화면까지 true
        next = totalPage > temEnd;//1~마지막 바로 전 화면 까지가 true
    }
}
