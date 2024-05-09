package kr.ac.kopo.kr.guestbook2024.service;

import kr.ac.kopo.kr.guestbook2024.dto.GuestbookDTO;
import kr.ac.kopo.kr.guestbook2024.entity.Guestbook;
import kr.ac.kopo.kr.guestbook2024.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
