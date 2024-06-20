package kr.ac.kopo.kr.guestbook2024.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import kr.ac.kopo.kr.guestbook2024.dto.GuestbookDTO;
import kr.ac.kopo.kr.guestbook2024.dto.PageRequestDTO;
import kr.ac.kopo.kr.guestbook2024.dto.PageResuitDTO;
import kr.ac.kopo.kr.guestbook2024.entity.Guestbook;
import kr.ac.kopo.kr.guestbook2024.entity.QGuestbook;
import kr.ac.kopo.kr.guestbook2024.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class GuestbookServiceimpl implements GuestbookService{
    private final GuestbookRepository repository;
    @Override
    public Long register(GuestbookDTO dto) {
        Guestbook entity = dtoToEntity(dto);
        repository.save(entity);

        return entity.getGno();
    }

    @Override
    public PageResuitDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);/*where정의 조건식*/
        Page<Guestbook> result = repository.findAll(booleanBuilder,pageable);/* 조건식이 포함된 select문 */
        Function<Guestbook,GuestbookDTO> fn = (entity -> entityToDto(entity));
        return new PageResuitDTO<>(result,fn);
    }

    @Override
    public void modify(GuestbookDTO dto) {
        Optional<Guestbook> result = repository.findById(dto.getGno());

        if(result.isPresent()){
            Guestbook entity = result.get();
            entity.changeTitle(dto.getTitle());
            entity.changecontent(dto.getContent());
            repository.save(entity);
        }
    }

    @Override
    public void remove(Long gno) {
        repository.deleteById(gno);
    }

    @Override
    public GuestbookDTO read(Long gno) {
        Optional<Guestbook> result = repository.findById(gno);

        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    @Override
    public BooleanBuilder getSearch(PageRequestDTO requestDTO){
            String type = requestDTO.getType();
            String keyword = requestDTO.getKeyword();

            BooleanBuilder booleanBuilder = new BooleanBuilder();
            QGuestbook qGuestbook = QGuestbook.guestbook;
            BooleanExpression booleanExpression = qGuestbook.gno.gt(0L);
            booleanExpression.and(booleanExpression);

            //화면에서 검색조건을 선택하지 않은 경우 검색 타입=null 및 검색어 입력 x 일때
        if(type==null || keyword.trim().length()==0 || type.trim().length()==0){
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("t")){
            conditionBuilder.or(qGuestbook.title.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qGuestbook.content.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qGuestbook.writer.contains(keyword));
        }

        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

}
