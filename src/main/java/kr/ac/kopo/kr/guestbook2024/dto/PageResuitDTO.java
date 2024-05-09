package kr.ac.kopo.kr.guestbook2024.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageResuitDTO<DTO,Entity>{
    private List<DTO> dtoList;

    public PageResuitDTO(Page<Entity> result, Function<Entity, DTO> fn){
        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}
